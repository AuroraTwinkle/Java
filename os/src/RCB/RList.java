package RCB;

import PCB.Process;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RList {
    private List<Resourse> resourseList;
    public RList(){
        resourseList=new ArrayList<>();
    }
    public void add(Resourse resourse){
        resourseList.add(resourse);
    }
    public boolean remove(Resourse resourse){
        return resourseList.remove(resourse);
    }
    public boolean remove(@NotNull Long rid){
        Iterator<Resourse> iterator=resourseList.iterator();
        boolean flag=false;
        while (iterator.hasNext()){
            Resourse resourse=iterator.next();
            if(resourse.getRid().equals(rid)){
                flag=true;
                iterator.remove();
                break;
            }
        }
        return flag;
    }
    public boolean requestResourse(Process process, Long rid, int num){
        boolean flag=false;
        for(Resourse resourse:resourseList){
            if(resourse.getRid().equals(rid)){
                flag=resourse.allocate(num);
                if(!flag){
                    resourse.addWaitingList(process,num);
                    break;
                }
            }
        }
        return flag;
    }
    public List<Process> realseResourse(Process process, int num, Long rid){
        List<Process> processes=new ArrayList<>();
        for(Resourse resourse:resourseList){
            if(resourse.getRid().equals(rid)){
               processes=resourse.release(num);
               break;
            }
        }
        return processes;
    }

    @Override
    public String toString() {
        StringBuffer  buffer=new StringBuffer();
        for(Resourse resourse:resourseList){
            buffer.append(resourse.toString());
            buffer.append("\n--------------------------------------------------\n");
        }
        return buffer.toString();
    }
}
