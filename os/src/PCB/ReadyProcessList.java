package PCB;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ReadyProcessList {
    private ProcessMannger initList;
    private ProcessMannger userList;
    private ProcessMannger systemList;
    public ReadyProcessList(){
        initList=new ProcessMannger();
        userList=new ProcessMannger();
        systemList=new ProcessMannger();
    }
    @Contract(pure = true)
    public ReadyProcessList(ProcessMannger initList, ProcessMannger userList, ProcessMannger systemList) {
        this.initList = initList;
        this.userList = userList;
        this.systemList = systemList;
    }
    public void add(@NotNull Process process){

        switch (process.getPriority()){
            case Init:initList.add(process);
                        break;
            case User:userList.add(process);
                        break;
            case System:systemList.add(process);
                        break;
            default: throw new RuntimeException("ERROR PRIORITY");
        }
    }
    public boolean remove(String name, @NotNull Priority priority){
        boolean flag=false;
        switch (priority){
            case Init:flag=initList.remove(name);
                break;
            case User:flag=userList.remove(name);
                break;
            case System:flag=systemList.remove(name);
                break;
            default: throw new RuntimeException("ERROE PRIORITY");
        }
        return flag;
    }
    public boolean remove(String name){
        boolean flag=false;
        flag=initList.remove(name);
        if(!flag){
            flag=userList.remove(name);
        }
        if(!flag){
        flag=systemList.remove(name);
        }
        return flag;
    }
    public Process get(String name){
        Process process=null;
        process=initList.get(name);
        if(process==null){
            process=userList.get(name);
        }
        if(process==null){
        process=systemList.get(name);
        }
        return process;
    }
    public Process get(String name, @NotNull Priority priority){
        Process process=null;
        switch (priority){
            case Init:process=initList.get(name);
                break;
            case User:process=userList.get(name);
                break;
            case System:process=systemList.get(name);
                break;
            default: throw new RuntimeException("ERROE PRIORITY");
        }
        return process;
    }
    public Process get(){
        Process process=null;
        process=systemList.get();
        if(process==null)
            process=userList.get();
        if(process==null)
            process=initList.get();
        return process;
    }

    @Override
    public String toString() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("init:::");
        buffer.append(initList.toString());
        buffer.append("\nuser:::");
        buffer.append(userList.toString());
        buffer.append("\nsystem:::");
        buffer.append(systemList.toString());
        return buffer.toString();
    }
}
