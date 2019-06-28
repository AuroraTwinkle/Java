import java.io.*;
import java.net.Socket;

public class MySocket {
    private Socket socket;
    MySocket(String host, int port) throws IOException {
        socket=new Socket(host,port);//建立socket连接
    }

    void send(String message) throws IOException {
        DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
        outputStream.writeUTF(message);
        outputStream.flush();
    }
    void receive() throws IOException {
        InputStream inputStream=socket.getInputStream();
        DataInputStream dataInputStream=new DataInputStream(inputStream);
        String[] strArr=dataInputStream.readUTF().split(",");
        if(strArr[0].equals("true"))
        {
            System.out.println("查询成功:");
            System.out.println("序号   学号   姓名   班级   成绩");
            for(int i=1;i<strArr.length;i++){
                System.out.println(strArr[i]);
            }
        }
        else{
            System.out.println("查询失败，服务端不存在该数据");
        }
        System.out.println();
    }
}
