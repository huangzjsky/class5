package e3;

import e1.*;
import java.io.*;
import java.net.*;
/**
 *  20189025
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket serverForClient=null;
        Socket socketOnServer= null;
        DataOutputStream out=null;
        DataInputStream  in=null;
        try {
            serverForClient = new ServerSocket(9210);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        try{
            String psw = "123445";
            System.out.println("等待客户呼叫");
            socketOnServer = serverForClient.accept(); //堵塞状态，除非有客户呼叫
            out=new DataOutputStream(socketOnServer.getOutputStream());
            in=new DataInputStream(socketOnServer.getInputStream());
            String s = in.readUTF(); // in读取信息，堵塞状态
            System.out.println("服务器收到客户的提问:"+s);
            String m = new String(Crypt.decrypt(Crypt.parseHexStr2Byte(s),psw));
            System.out.println("解密后得到:"+m);
            MyDC evaluator = new MyDC();
            int  result = evaluator.evaluate(m);
            out.writeUTF(result+" ");
            out.writeUTF(result +" ");
        }
        catch(Exception e) {
            System.out.println("客户已断开"+e);
        }
    }
}
