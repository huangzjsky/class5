package e5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 20189205
 */
public class DigestPass {
    public static String MD5(String ss) throws Exception{
        String x=ss;
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(x.getBytes(StandardCharsets.UTF_8));
        byte s[ ]=m.digest( );
        String result="";
        for (int i=0; i<s.length; i++){
            result+=Integer.toHexString((0x000000ff & s[i]) |
                    0xffffff00).substring(6);
        }
        return result;
    }
}
