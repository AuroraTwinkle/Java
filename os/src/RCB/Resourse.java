package RCB;

import PCB.Process;

import java.util.*;

public class Resourse {
private Long rid;
private int allNumber;
private int availableNumber;
private LinkedHashMap<Process,Integer> waitingList;
    public Resourse(Long rid, int allNumber) {
        this.availableNumber=allNumber;
        this.rid = rid;
        this.allNumber = allNumber;
        waitingList=new LinkedHashMap<>();
    }

    public Long getRid() {
        return rid;
    }

    public LinkedHashMap<Process,Integer> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(LinkedHashMap<Process,Integer> waitingList) {
        this.waitingList = waitingList;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public int getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(int allNumber) {
        this.allNumber = allNumber;
    }

    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }
    public void addWaitingList(Process process,Integer integer){
        waitingList.put(process,integer);
    }
    public void removeWaitingList(Process process){
        waitingList.remove(process);
    }
    public boolean allocate(int request){
        if(request>allNumber){
            throw new RuntimeException("too large resourse request");
        }
        if(request<=availableNumber){
            availableNumber-=request;
            return true;
        }
        return false;
    }
    public List<Process> release(int num){
        List<Process> processes=new ArrayList<>();
        availableNumber+=num;
        Set<Map.Entry<Process,Integer>> waitingSet=waitingList.entrySet();
        Iterator<Map.Entry<Process,Integer>> waitingIterator=waitingSet.iterator();
        while (waitingIterator.hasNext()){
            Map.Entry<Process,Integer> entry=waitingIterator.next();
            if(entry.getValue()<availableNumber){
                Process process=entry.getKey();
                process.addResouse(getRid(),entry.getValue());
                availableNumber-=entry.getValue();
                waitingIterator.remove();
                processes.add(process);
            }
        }
        return processes;
    }

    @Override
    public String toString() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("name=");
        buffer.append(rid);
        buffer.append("::");
        buffer.append("totalResource=");
        buffer.append(allNumber);
        buffer.append("::");
        buffer.append("aviliableNumber=");
        buffer.append(availableNumber);
        for(Process process:waitingList.keySet()){
            buffer.append("\n");
            buffer.append("waitingProcess:");
            buffer.append("Process:::");
            buffer.append(process.getName());
            buffer.append("::");
            buffer.append(waitingList.get(process));
        }
        return buffer.toString();
    }
}
