import java.util.Scanner;

public class SocketClient {

    public static void main(String args[]) throws Exception {
        boolean quit=false;
        while (!quit) {
            MySocket mySocket = new MySocket("127.0.0.1", 55555);
            System.out.println("请输入查询方式:");
            System.out.println("1.按姓名");
            System.out.println("2.按学号");
            System.out.println("3.按序号");
            System.out.println("4.按姓名(模糊查询)");
            System.out.println("5.按学号(模糊查询)");
            System.out.println("6.按序号(模糊查询)");
            System.out.println("7.退出");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("请输入姓名:");
                    String name=sc.nextLine();
                    mySocket.send("1 2 "+name);
                    System.out.println("查询中......");
                    mySocket.receive();
                    break;
                case "2":
                    System.out.println("请输入学号:");
                    String id=sc.nextLine();
                    mySocket.send("1 1 "+id);
                    System.out.println("查询中......");
                    mySocket.receive();
                    break;
                case "3":
                    System.out.println("请输入序号:");
                    String index=sc.nextLine();
                    mySocket.send("1 0 "+index);
                    System.out.println("查询中......");
                    mySocket.receive();
                    break;
                case "4":
                    System.out.println("请输入姓名:");
                    String sname=sc.nextLine();
                    mySocket.send("0 2 "+sname);
                    System.out.println("查询中......");
                    mySocket.receive();
                    break;
                case "5":
                    System.out.println("请输入学号:");
                    String sid=sc.nextLine();
                    mySocket.send("0 1 "+sid);
                    System.out.println("查询中......");
                    mySocket.receive();
                    break;
                case "6":
                    System.out.println("请输入序号:");
                    String sindex=sc.nextLine();
                    mySocket.send("0 0 "+sindex);
                    System.out.println("查询中......");
                    mySocket.receive();
                    break;
                case "7":
                    quit = true;
            }
        }
    }
}
