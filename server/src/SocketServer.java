import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    public static void main(String args[]) throws Exception {
        int port = 55555;
        ServerSocket server = new ServerSocket(port);
        System.out.println("server 启动成功");

        ExecutorService threadPool = Executors.newFixedThreadPool(100);//线程池

        while (true) {
            Socket socket = server.accept();//监听端口

            Runnable runnable=()->{
                try {
                    TxtParser txtParser=new TxtParser();
                    InputStream inputStream=socket.getInputStream();
                    DataInputStream dataInputStream=new DataInputStream(inputStream);
                    String mess=dataInputStream.readUTF();
                    System.out.println("receive_msg:"+mess);
                    String[] strArr=mess.split("\\s+");
                    String result=txtParser.searchFromFile(strArr[2],Integer.parseInt(strArr[1]),Integer.parseInt(strArr[0]));
                    DataOutputStream outputStream=new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF(result);
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            threadPool.submit(runnable);
        }
    }
}