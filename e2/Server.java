package e2;

import e1.*;
import java.io.*;
import java.net.*;
/**
 * 20189205
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket serverForClient=null;
        Socket socketOnServer= null;
        DataOutputStream out=null;
        DataInputStream  in=null;
        try {
            serverForClient = new ServerSocket(9205);
        } catch (IOException e) {
            System.out.println(e);
        }
        try{ System.out.println("等待客户连接");
            socketOnServer = serverForClient.accept(); //堵塞状态，除非有客户呼叫
            out=new DataOutputStream(socketOnServer.getOutputStream());
            in=new DataInputStream(socketOnServer.getInputStream());
            String s=in.readUTF(); // in读取信息，堵塞状态
            System.out.println("服务器收到客户的提问:"+s);
            MyDC evaluator = new MyDC();
            MyBC exchange=new MyBC();
            int  result = evaluator.evaluate(exchange.exc(s));
            out.writeUTF(result+" ");
        }
        catch(Exception e) {
            System.out.println("客户已断开"+e);
        }
    }
}
