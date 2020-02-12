package PCB;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class ProcessListMannger {
    private ProcessMannger blockList;
    private ReadyProcessList readyList;
    private Process runningP;
    public ProcessListMannger(){
        blockList=new ProcessMannger();
        readyList=new ReadyProcessList();
    }
    public ProcessMannger getBlockList() {
        return blockList;
    }

    public void setBlockList(ProcessMannger blockList) {
        this.blockList = blockList;
    }

    public ReadyProcessList getReadyList() {
        return readyList;
    }

    public void setReadyList(ReadyProcessList readyList) {
        this.readyList = readyList;
    }

    public Process getRunningP() {
        return runningP;
    }

    public void setRunningP(Process runningP) {
        this.runningP = runningP;
    }

    public void add(@NotNull Process process)throws Exception{
        switch (process.getStatus()){
            case Ready:
                readyList.add(process);
                break;
            case Blocked:
                blockList.add(process);
                break;
            case Running:
            default:
                throw new RuntimeException();
        }
    }
    public boolean remove(String name)throws Exception{
        boolean flag=false;
        flag=blockList.remove(name);
        if(!flag){
            readyList.remove(name);
        }
        return flag;
    }

    public boolean remove(String name,@NotNull PCBStatus status){
        boolean flag=false;
        switch (status){
            case Blocked:
                flag=blockList.remove(name);
                break;
            case Running:
                if(runningP.getName().equals(name)){
                    runningP=null;
                }
            case Ready:
                flag=readyList.remove(name);
                break;
        }
        return flag;
    }
    public Process getProcess(String name){
        Process process=null;
        if(runningP.getName().equals(name)){
            process=runningP;
        }
        if(process==null){
            process=readyList.get(name);
        }
        if(process==null){
            process=blockList.get(name);
        }
        return process;
    }
    public void RunningtoReady()throws Exception{
        runningP.setStatus(PCBStatus.Ready);
        readyList.add(runningP);
    }
    public void ReadytoRunning(String name)throws Exception{
        Process process=readyList.get(name);
        if(process==null||process.getStatus()!=PCBStatus.Ready){
            throw new RuntimeException("错误的进程状态或进程名");
        }
        readyList.remove(name);
        runningP.setStatus(PCBStatus.Ready);
        setRunningP(process);
    }
    public void BlockedtoReady(String name)throws Exception{
        Process process=blockList.get(name);
        if(process==null||process.getStatus()!=PCBStatus.Blocked){
            throw new RuntimeException("错误的进程状态或进程名");
        }
        blockList.remove(name);
        process.setStatus(PCBStatus.Ready);
        readyList.add(process);
    }
    public void RunningtoBlocked()throws Exception{
        runningP.setStatus(PCBStatus.Blocked);
        blockList.add(runningP);
    }
    public boolean nextRunning(){
        Process process=readyList.get();
        if(process!=null){
            readyList.remove(process.getName());
            process.setStatus(PCBStatus.Running);
            runningP=process;
            return true;
        }
        return false;
    }
    public void printReadyList(){
        System.out.println(readyList.toString());
    }
    public void printBlockList(){
        System.out.println(blockList.toString());
    }
    public void printRunning(){
        System.out.println(runningP.toString());
    }
    @Override
    public String toString() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("runningProcess:");
        buffer.append(runningP.toString());
        buffer.append("\nReadyProcess");
        buffer.append(readyList.toString());
        buffer.append("\nBlockProcess");
        buffer.append(blockList.toString());
        return buffer.toString();
    }
}
