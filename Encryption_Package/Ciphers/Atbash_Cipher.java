package Encryption_Package.Ciphers;

import java.util.HashMap;

public class Atbash_Cipher {
    private static HashMap<String, String> atbashAlphabet = new HashMap<String, String>();

    public static String atbashCipherCryption(String msg) {
        mapInitalize();
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

    private static void mapInitalize() {
        atbashAlphabet.put("a", "z");
        atbashAlphabet.put("b", "y");
        atbashAlphabet.put("c", "x");
        atbashAlphabet.put("d", "w");
        atbashAlphabet.put("e", "v");
        atbashAlphabet.put("f", "u");
        atbashAlphabet.put("g", "t");
        atbashAlphabet.put("h", "s");
        atbashAlphabet.put("i", "r");
        atbashAlphabet.put("j", "q");
        atbashAlphabet.put("k", "p");
        atbashAlphabet.put("l", "o");
        atbashAlphabet.put("m", "n");
        atbashAlphabet.put("n", "m");
        atbashAlphabet.put("o", "l");
        atbashAlphabet.put("p", "k");
        atbashAlphabet.put("q", "j");
        atbashAlphabet.put("r", "i");
        atbashAlphabet.put("s", "h");
        atbashAlphabet.put("t", "g");
        atbashAlphabet.put("u", "f");
        atbashAlphabet.put("v", "e");
        atbashAlphabet.put("w", "d");
        atbashAlphabet.put("x", "c");
        atbashAlphabet.put("y", "b");
        atbashAlphabet.put("z", "a");
    }
}
