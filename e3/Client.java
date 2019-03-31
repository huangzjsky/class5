package e3;

import e1.*;
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
            String psw = "123445";
            Scanner sc = new Scanner(System.in);
            mysocket=new Socket("localhost",9210);
            System.out.println("连接成功，请中缀输入表达式" );
            in=new DataInputStream(mysocket.getInputStream());
            out=new DataOutputStream(mysocket.getOutputStream());
            String expression=sc.nextLine();
            MyBC exchange=new MyBC();
            String postfix=exchange.exc(expression);
            System.out.println("转化为后缀表达式为：" + postfix);
            String c = Crypt.parseByte2HexStr(Crypt.encrypt(postfix,psw));
            System.out.println("加密后得到：" + c );
            out.writeUTF(c);
            String  s=in.readUTF();
            System.out.println("客户收到服务器的回答:");
            System.out.println(expression + " = " + s);
        }
        catch(Exception e) {
            System.out.println("服务器已断开"+e);
        }
    }
}