package e5;

import e1.*;
import e3.Crypt;
import e4.DHCoder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * 20189205
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket serverForClient = null;
        Socket socketOnServer = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        byte[] publicKey1 = null, privateKey1 = null;
        byte[] key = null;
        try {
            serverForClient = new ServerSocket(8765);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            System.out.println("等待客户呼叫");
            socketOnServer = serverForClient.accept(); //堵塞状态，除非有客户呼叫
            out = new DataOutputStream(socketOnServer.getOutputStream());
            in = new DataInputStream(socketOnServer.getInputStream());
            String publicKey = in.readUTF(); // in读取信息，堵塞状态
            System.out.println("接收到客户端公钥为：" + publicKey);
            Map<String, Object> keyMap1 = DHCoder.initKey();
            publicKey1 = DHCoder.getPublicKey(keyMap1);
            privateKey1 = DHCoder.getPrivateKey(keyMap1);
            key = DHCoder.getSecretKey(Crypt.parseHexStr2Byte(publicKey), privateKey1);
            if(publicKey1!= null)out.writeUTF(Crypt.parseByte2HexStr(publicKey1));
            String s=in.readUTF();
            System.out.println("服务器收到客户的提问:" + s);
            String m = new String(Crypt.decrypt(Crypt.parseHexStr2Byte(s), Crypt.parseByte2HexStr(key)));
            System.out.println("解密后得到:" + m);
            String MD5 = DigestPass.MD5(m);
            System.out.println("MD5加密后的结果是："+MD5);
            String post=in.readUTF();
            if (MD5.equals(post)){
                System.out.println("和客户端的MD5值一致");
                System.out.println("服务器收到客户的请求:计算后缀表达式" );
                MyDC evaluator = new MyDC();
                int result = evaluator.evaluate(m);
                out.writeUTF(result + " ");
            }
            else {
                System.out.println("警告：和客户端的MD5值不一致！");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
