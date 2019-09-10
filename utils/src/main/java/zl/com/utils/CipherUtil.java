package zl.com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码
 */
public class CipherUtil {
    public static String md5(String plain) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");

        } catch (NoSuchAlgorithmException ignored) {
        }//不可能发生
        assert md != null;
        md.update(plain.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) {
            int i = b & 0xff;
            if (i < 16) sb.append("0");
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

}
