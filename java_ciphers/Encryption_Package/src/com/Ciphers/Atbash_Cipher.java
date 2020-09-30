package java_ciphers.Encryption_Package.src.com.Ciphers;

import java.util.HashMap;
import java_ciphers.Encryption_Package.src.com.Cipher;

public class Atbash_Cipher {
    private static HashMap<String, String> atbashAlphabet = new HashMap<String, String>();

    public static String atbashCipherCryption(String msg) {
        atbashAlphabet = Cipher.mapInitalize(atbashAlphabet);
        String result = "";
        for (int i = 0; i < msg.length(); i++) {
            if (atbashAlphabet.containsKey(String.valueOf(msg.charAt(i)))) {
                result += atbashAlphabet.get(String.valueOf(msg.charAt(i)));
            } else {
                result += String.valueOf(msg.charAt(i));
            }
        }
        return result;
    }
}
