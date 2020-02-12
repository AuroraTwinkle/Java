package Controll;
import PCB.PCBStatus;
import PCB.Process;
import PCB.ProcessListMannger;
import PCB.ProcessMannger;
import RCB.RList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Control {
    ProcessListMannger processMannger;
    RList rList;

    public ProcessListMannger getProcessMannger() {
        return processMannger;
    }

    public void setProcessMannger(ProcessListMannger processMannger) {
        this.processMannger = processMannger;
    }

    public RList getrList() {
        return rList;
    }

    public void setrList(RList rList) {
        this.rList = rList;
    }

    @Contract(pure = true)
    public Control(ProcessListMannger processListMannger, RList rList){
        processMannger=processListMannger;
        this.rList=rList;
    }
    public Control(){
        processMannger=new ProcessListMannger();
        rList=new RList();
    }
    public void add( Process parent, Process process)throws Exception{
        if(parent!=null){
            parent.addChild(process);
        }
        add(process);
    }
    public void add(Process process)throws Exception{
        processMannger.add(process);
        if(process.getStatus()!= PCBStatus.Blocked){
            if(processMannger.getRunningP()==null){
                processMannger.remove(process.getName());
                processMannger.setRunningP(process);
            }
            if(processMannger.getRunningP().getPriority().compareTo(process.getPriority())<0){
                processMannger.RunningtoReady();
                processMannger.ReadytoRunning(process.getName());
            }
        }
    }
    public void add(String name,Process process)throws Exception{
        Process parent=processMannger.getProcess(name);
        add(parent,process);
    }
    public void destory(String name)throws Exception{
        Process process=processMannger.getProcess(name);
        Process parent=process.getParent();
        parent.removeChild(process);
        List<Process> children=process.getChildren();
        Map<Long,Integer> resourceMap=process.getResourseMap();
        for(Process child:children){
            destory(child.getName());
        }
        for(Long rid:resourceMap.keySet()){
            relase(process.getName(),rid);
            resourceMap.remove(rid);
        }
    }
    private void blocktoReady(@NotNull List<Process> processes)throws Exception{
        for(Process process:processes){
            processMannger.BlockedtoReady(process.getName());
            if(process.getStatus()!= PCBStatus.Blocked&&
                    processMannger.getRunningP().getPriority().compareTo(process.getPriority())<0){
                processMannger.RunningtoReady();
                processMannger.ReadytoRunning(process.getName());
            }
        }
    }
    private void blockProcess(Process process)throws Exception{
       processMannger.RunningtoBlocked();
       processMannger.nextRunning();
    }
    private List<Process> relaseHandler(String pName,Long rid){
        List<Process> processes=new ArrayList<>();
        Process process=processMannger.getProcess(pName);
        Map<Long,Integer> resourceMap=process.getResourseMap();
        int num=resourceMap.get(rid);
        processes=rList.realseResourse(process,num,rid);
        resourceMap.remove(rid);
        return processes;
    }
    public void relase(String pName,Long rid)throws Exception{
        Process process=processMannger.getProcess(pName);
        Map<Long,Integer> resourceMap=process.getResourseMap();
        int num=resourceMap.get(rid);
        relase(process,rid,num);
    }
    public void relase(String pName,Long rid,Integer num)throws Exception{
        Process process=processMannger.getProcess(pName);
        relase(process,rid,num);
    }
    public void relase(Process process,Long rid,Integer num)throws Exception{
        List<Process> processes=new ArrayList<>();
        Map<Long,Integer> resourceMap=process.getResourseMap();
        if(num>=resourceMap.get(rid)){
            resourceMap.remove(rid);
        }
        processes=rList.realseResourse(process,num,rid);
        blocktoReady(processes);
    }
    public void timeOut()throws Exception{
        Process running= processMannger.getRunningP();
        running.setStatus(PCBStatus.Ready);
        processMannger.add(running);
        processMannger.nextRunning();
    }
    public void request(String pName,@NotNull Long rid,@NotNull Integer num)throws Exception{
        Process process=processMannger.getProcess(pName);
        boolean flag=rList.requestResourse(process,rid,num);
        if(flag){
            process.addResouse(rid,num);
        }
        else {
            blockProcess(process);
        }
    }
    public void printRList(){
        System.out.println(rList.toString());
    }
}