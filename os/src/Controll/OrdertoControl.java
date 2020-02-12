package Controll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import PCB.PCBStatus;
import PCB.Priority;
import PCB.Process;
import RCB.RList;
import RCB.Resourse;

public class OrdertoControl {
    private static Control control=null;
    public static void readOrder(String path)throws Exception{
        try (FileReader reader = new FileReader(path);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] orders=line.split(" ");
                if(orders.length==0){
                    return;
                }
                Arrays.stream(orders).forEach(st->st=st.toLowerCase());
                System.out.print(line+":::::");
                switch (orders[0]){
                    case "cr":
                        if(control==null&&orders.length<3){
                            throw new RuntimeException();
                        }
                        switch (orders[2]){
                            case "0":
                                throw new RuntimeException();
                            case "1":
                                Process Uprocess=new Process(PCBStatus.Ready,Priority.User,orders[1]);
                                control.add(control.getProcessMannger().getRunningP(),Uprocess);
                                break;
                            case "2":
                                Process Sprocess=new Process(PCBStatus.Ready,Priority.System,orders[1]);
                                control.add(control.getProcessMannger().getRunningP(),Sprocess);
                                break;
                        }
                        System.out.println("RunningProcess::"+
                                control.getProcessMannger().getRunningP().toString()+"\n");
                        break;
                    case "init":
                        RList rList=new RList();
                        rList.add(new Resourse(new Long(1),1));
                        rList.add(new Resourse(new Long(2),2));
                        rList.add(new Resourse(new Long(3),3));
                        rList.add(new Resourse(new Long(4),4));
                        control=new Control();
                        control.setrList(rList);
                        Process process=new Process(PCBStatus.Ready,Priority.Init, "init" );
                        control.add(process);
                        System.out.println("RunningProcess::"+
                                control.getProcessMannger().getRunningP().toString()+"\n");
                        break;
                    case "to":
                        System.out.print("BlockProcess::"+
                                control.getProcessMannger().getRunningP().toString()+"::::");
                        control.timeOut();
                        System.out.println("RunningProcess::"+
                                control.getProcessMannger().getRunningP().toString());
                        break;
                    case "de":
                        if(orders.length<2) {
                            throw new RuntimeException("错误指令");
                        }
                        System.out.print("BlockProcess::"+
                                control.getProcessMannger().getRunningP().toString()+"::::");
                        control.destory(orders[1]);
                        System.out.print("RunningProcess::"+
                                control.getProcessMannger().getRunningP().toString());
                        System.out.print("blockList::::");
                        control.processMannger.printBlockList();
                        break;
                    case "req":
                        if(orders.length<3){
                            throw new RuntimeException("错误指令");
                        }
                        control.request(control.getProcessMannger().getRunningP().getName(),
                                Long.valueOf(orders[1]),Integer.valueOf(orders[2]));
                        System.out.println("RunningProcess::"+
                                control.getProcessMannger().getRunningP().toString()+"::::"+line);
                        break;
                    case "rel":
                        if(orders.length<3){
                            throw new RuntimeException("错误指令");
                        }
                        control.relase(control.getProcessMannger().getRunningP().getName(),Long.valueOf(orders[1]),Integer.valueOf(orders[2]));
                        break;
                    case "list":
                        if(orders.length<2){
                            throw new RuntimeException();
                        }
                        switch (orders[1]){
                            case "ready":
                                control.processMannger.printReadyList();
                            case "block":
                                control.printRList();
                            case "running":
                                control.processMannger.printRunning();
                                break;
                            case "res":
                                control.printRList();
                                break;
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
