package PCB;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProcessMannger {
    List<Process> processList;
    public ProcessMannger(){
        processList=new LinkedList<>();
    }
    public ProcessMannger(List<Process> processList){
        this.processList=processList;
    }
    public void add(Process process){
         processList.add(processList.size(),process);
    }
    public boolean remove(String name){
        Process process=get(name);
        if(process!=null){
            return processList.remove(process);
        }
        return false;
    }
    public Process get(String name){
        Process process=null;
        Iterator<Process> processIterator=processList.iterator();
        while (processIterator.hasNext()){
            Process temp=processIterator.next();
            if(temp.getName().equals(name)){
                process=temp;
                break;
            }
        }
        return process;
    }
    public Process get(){
        if(processList.size()>0){
            return processList.get(0);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuffer buffer=new StringBuffer();
        for(Process process:processList){
            buffer.append(process);
            buffer.append("->");
        }
        return buffer.toString();
    }
}
