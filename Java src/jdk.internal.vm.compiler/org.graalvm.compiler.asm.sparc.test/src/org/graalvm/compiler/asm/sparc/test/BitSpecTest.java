/*
 * Copyright (c) 2016, 2016, Oracle and/or its affiliates. All rights reserved.
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
package org.graalvm.compiler.asm.sparc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.graalvm.compiler.asm.sparc.SPARCAssembler.BitSpec;
import org.graalvm.compiler.asm.sparc.SPARCAssembler.CompositeBitSpec;
import org.graalvm.compiler.asm.sparc.SPARCAssembler.ContinousBitSpec;

public class BitSpecTest {

    private static final BitSpec d4hi = new ContinousBitSpec(23, 20, true, "d4hi");
    private static final BitSpec d4lo = new ContinousBitSpec(7, 4, false, "d4lo");
    private static final BitSpec d8 = new CompositeBitSpec(d4hi, d4lo);

    @Test
    public void testContinousSignExtend() {
        testSetGet(d4hi, 0x00700000, 0x00000007);
        testSetGet(d4hi, 0x00800000, 0xFFFFFFF8);
    }

    @Test
    public void testContinousZeroExtend() {
        testSetGet(d4lo, 0x000000F0, 0x0000000F);
        testSetGet(d4lo, 0x00000070, 0x00000007);
    }

    public void testSetGet(BitSpec bs, int encoded, int decoded) {
        assertTrue(bs.valueFits(decoded));
        assertEquals(encoded, bs.setBits(0, decoded));
        assertEquals(decoded, bs.getBits(encoded));
    }

    @Test
    public void testContinousSignExtendValueFits() {
        assertFalse(d4hi.valueFits(0xf));
        assertFalse(d4hi.valueFits(0x10));
        assertFalse(d4hi.valueFits(0x17));
    }

    @Test
    public void testContinousZeroExtendValueFits() {
        assertFalse(d4lo.valueFits(0x10));
    }

    @Test(expected = AssertionError.class)
    public void testContinousSignExtendSetFail1() {
        d4hi.setBits(0, 0xf);
    }

    @Test(expected = AssertionError.class)
    public void testContinousSignExtendSetFail2() {
        d4hi.setBits(0, 0xFFFFFFF0);
    }

    @Test(expected = AssertionError.class)
    public void testContinousZeroExtendSetFail1() {
        d4lo.setBits(0, 0x10);
    }

    @Test
    public void testCompositeSignExtended() {
        testSetGet(d8, 0x00f000c0, 0xfffffffc);
        testSetGet(d8, 0x008000c0, 0xffffff8c);
        testSetGet(d8, 0x007000c0, 0x7c);
    }

    @Test(expected = AssertionError.class)
    public void testCompositeSignExtendedFail1() {
        d8.setBits(0, 0x00000080);
    }

    @Test(expected = AssertionError.class)
    public void testCompositeSignExtendedFail2() {
        d8.setBits(0, 0xEFFFFF80);
    }

    @Test
    public void testCompositeValueFits() {
        assertTrue(d8.valueFits(0xfffffffc));
        assertTrue(d8.valueFits(0xffffff8c));
        assertTrue(d8.valueFits(0x7c));
        assertFalse(d8.valueFits(0x8c));
        assertFalse(d8.valueFits(0xEFFFFF80));
    }
}
