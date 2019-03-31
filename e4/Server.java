package e4;

import e1.*;
import e3.Crypt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
/**
 * 20189025
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
            MyDC evaluator = new MyDC();
            int result = evaluator.evaluate(m);
            out.writeUTF(result + " ");
            out.writeUTF(result + " ");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
