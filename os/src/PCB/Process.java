package PCB;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Process {
    private Map<Long,Integer> resourseMap;
    private PCBStatus status;
    private Priority priority;
    private String name;
    private List<Process> children;
    private Process parent;

    public Process(String name, Map<Long,Integer> resourseMap, Priority priority, PCBStatus status, List<Process> children, Process parent) {
        this.resourseMap = resourseMap;
        this.status = status;
        this.priority = priority;
        this.name = name;
        this.children = children;
        this.parent = parent;
    }

    public Process(PCBStatus status, Priority priority, String name, Process parent) {
        this(name, new HashMap<Long,Integer>(), priority, status, new ArrayList<>(),parent);
    }
    public Process(PCBStatus status, Map<Long,Integer> resourseMap, Priority priority, String name, Process parent) {
        this(name, resourseMap, priority, status, new ArrayList<>(),parent);
    }

    @Contract(pure = true)
    public Process(PCBStatus status, Priority priority, String name) {
        this.status = status;
        this.priority = priority;
        this.name = name;
        parent=null;
        children=new ArrayList<>();
        resourseMap=new LinkedHashMap<>();
    }

    public PCBStatus getStatus() {
        return status;
    }

    public void setStatus(PCBStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Process> getChildren() {
        return children;
    }

    public void setChildren(List<Process> children) {
        this.children = children;
    }

    public Process getParent() {
        return parent;
    }

    public void setParent(Process parent) {
        this.parent = parent;
    }
    public void addChild(Process child){
        if(child.parent!=this){
           child.setParent(this);
        }
        children.add(child);
    }
    public void addChild(PCBStatus status, Priority priority, String name){
        Process process=new Process(status,priority,name,this);
        addChild(process);
    }
    public void addChildren(List<Process> children){
        this.children.addAll(children);
    }
    public Process removeChild(String name){
        Iterator<Process> iterator=children.iterator();
        Process process=null;
        while (iterator.hasNext()){
            process=iterator.next();
            if(process.getName().equals(name)){
                iterator.remove();
                break;
            }
        }
        if(process==null&&!process.getName().equals(name)){
            process=null;
        }
        return process;
    }

    public Map<Long, Integer> getResourseMap() {
        return resourseMap;
    }

    public void setResourseMap(Map<Long, Integer> resourseMap) {
        this.resourseMap = resourseMap;
    }

    public void showChildren(){
        for(Process process:children){
            System.out.print(process.name+"->");
        }
        System.out.println();
    }
    public boolean removeChild(Process process){
        return children.remove(process);
    }
    public void addResouse(Long rid,Integer num){
        resourseMap.put(rid,num);
    }
    public Integer relaseResouse(@NotNull Long rid){
       Integer num=resourseMap.get(rid);
       return num;
    }
    @Override
    public String toString() {
        return getName();
    }
}
