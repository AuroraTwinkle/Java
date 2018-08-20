/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.graalvm.compiler.core.common.alloc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Deque;

import org.graalvm.compiler.core.common.alloc.TraceBuilderResult.TrivialTracePredicate;
import org.graalvm.compiler.core.common.cfg.AbstractBlockBase;
import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.debug.Indent;

/**
 * Computes traces by selecting the unhandled block with the highest execution frequency and going
 * in both directions, up and down, as long as possible.
 */
public final class BiDirectionalTraceBuilder {

    public static TraceBuilderResult computeTraces(DebugContext debug, AbstractBlockBase<?> startBlock, AbstractBlockBase<?>[] blocks, TrivialTracePredicate pred) {
        return new BiDirectionalTraceBuilder(blocks).build(debug, startBlock, blocks, pred);
    }

    private final Deque<AbstractBlockBase<?>> worklist;
    private final BitSet processed;
    private final Trace[] blockToTrace;

    private BiDirectionalTraceBuilder(AbstractBlockBase<?>[] blocks) {
        processed = new BitSet(blocks.length);
        worklist = createQueue(blocks);
        blockToTrace = new Trace[blocks.length];
    }

    private static Deque<AbstractBlockBase<?>> createQueue(AbstractBlockBase<?>[] blocks) {
        ArrayList<AbstractBlockBase<?>> queue = new ArrayList<>(Arrays.asList(blocks));
        queue.sort(BiDirectionalTraceBuilder::compare);
        return new ArrayDeque<>(queue);
    }

    private static int compare(AbstractBlockBase<?> a, AbstractBlockBase<?> b) {
        return Double.compare(b.probability(), a.probability());
    }

    private boolean processed(AbstractBlockBase<?> b) {
        return processed.get(b.getId());
    }

    @SuppressWarnings("try")
    private TraceBuilderResult build(DebugContext debug, AbstractBlockBase<?> startBlock, AbstractBlockBase<?>[] blocks, TrivialTracePredicate pred) {
        try (Indent indent = debug.logAndIndent("BiDirectionalTraceBuilder: start trace building")) {
            ArrayList<Trace> traces = buildTraces(debug);
            assert traces.get(0).getBlocks()[0].equals(startBlock) : "The first traces always contains the start block";
            return TraceBuilderResult.create(debug, blocks, traces, blockToTrace, pred);
        }
    }

    protected ArrayList<Trace> buildTraces(DebugContext debug) {
        ArrayList<Trace> traces = new ArrayList<>();
        // process worklist
        while (!worklist.isEmpty()) {
            AbstractBlockBase<?> block = worklist.pollFirst();
            assert block != null;
            if (!processed(block)) {
                Trace trace = new Trace(startTrace(debug, block));
                for (AbstractBlockBase<?> traceBlock : trace.getBlocks()) {
                    blockToTrace[traceBlock.getId()] = trace;
                }
                trace.setId(traces.size());
                traces.add(trace);
            }
        }
        return traces;
    }

    /**
     * Build a new trace starting at {@code block}.
     *
     * @param debug
     */
    @SuppressWarnings("try")
    private Collection<AbstractBlockBase<?>> startTrace(DebugContext debug, AbstractBlockBase<?> block) {
        ArrayDeque<AbstractBlockBase<?>> trace = new ArrayDeque<>();
        try (Indent i = debug.logAndIndent("StartTrace: %s", block)) {
            try (Indent indentFront = debug.logAndIndent("Head:")) {
                for (AbstractBlockBase<?> currentBlock = block; currentBlock != null; currentBlock = selectPredecessor(currentBlock)) {
                    addBlockToTrace(debug, currentBlock);
                    trace.addFirst(currentBlock);
                }
            }
            /* Number head blocks. Can not do this in the loop as we go backwards. */
            int blockNr = 0;
            for (AbstractBlockBase<?> b : trace) {
                b.setLinearScanNumber(blockNr++);
            }

            try (Indent indentBack = debug.logAndIndent("Tail:")) {
                for (AbstractBlockBase<?> currentBlock = selectSuccessor(block); currentBlock != null; currentBlock = selectSuccessor(currentBlock)) {
                    addBlockToTrace(debug, currentBlock);
                    trace.addLast(currentBlock);
                    /* This time we can number the blocks immediately as we go forwards. */
                    currentBlock.setLinearScanNumber(blockNr++);
                }
            }
        }
        debug.log("Trace: %s", trace);
        return trace;
    }

    private void addBlockToTrace(DebugContext debug, AbstractBlockBase<?> currentBlock) {
        debug.log("add %s (prob: %f)", currentBlock, currentBlock.probability());
        processed.set(currentBlock.getId());
    }

    /**
     * @return The unprocessed predecessor with the highest probability, or {@code null}.
     */
    private AbstractBlockBase<?> selectPredecessor(AbstractBlockBase<?> currentBlock) {
        AbstractBlockBase<?> next = null;
        for (AbstractBlockBase<?> pred : currentBlock.getPredecessors()) {
            if (!processed(pred) && !isBackEdge(pred, currentBlock) && (next == null || pred.probability() > next.probability())) {
                next = pred;
            }
        }
        return next;
    }

    private static boolean isBackEdge(AbstractBlockBase<?> from, AbstractBlockBase<?> to) {
        assert Arrays.asList(from.getSuccessors()).contains(to) : "No edge from " + from + " to " + to;
        return from.isLoopEnd() && to.isLoopHeader() && from.getLoop().equals(to.getLoop());
    }

    /**
     * @return The unprocessed successor with the highest probability, or {@code null}.
     */
    private AbstractBlockBase<?> selectSuccessor(AbstractBlockBase<?> currentBlock) {
        AbstractBlockBase<?> next = null;
        for (AbstractBlockBase<?> succ : currentBlock.getSuccessors()) {
            if (!processed(succ) && (next == null || succ.probability() > next.probability())) {
                next = succ;
            }
        }
        return next;
    }
}
