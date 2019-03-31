package e2;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * 20189205
 */
public class Client {
    public static void main(String[] args) {
        Socket mysocket;
        DataInputStream in=null;
        DataOutputStream out=null;

        try {
            Scanner sc = new Scanner(System.in);
            mysocket=new Socket("localhost",9205);
            System.out.println("连接成功，请输入表达式" );
            in=new DataInputStream(mysocket.getInputStream());
            out=new DataOutputStream(mysocket.getOutputStream());
            String expression=sc.nextLine();
            out.writeUTF(expression);
            String  s=in.readUTF();
            System.out.println("客户收到服务器的回答:");
            System.out.println(expression + " = " + s);
        }
        catch(Exception e) {
            System.out.println("服务器已断开"+e);
        }
    }
}