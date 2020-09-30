package java.Encryption_Package.src.com.Ciphers;

import java.util.HashMap;
import java.util.Map.Entry;

public class Affine_Cipher {
    public static int ALPHABET_SIZE = 26;
    public static int keyA;
    public static int keyB;

    private static HashMap<String, Integer> affineAlphabet = new HashMap<String, Integer>();

    public static String affineEncryption(String msg, String a, int b) {
        keyA = Integer.parseInt(a);
        keyB = b;

        affineAlphabet = affineMapInitalize(affineAlphabet);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            if (affineAlphabet.containsKey(String.valueOf(msg.charAt(i)))) {
                result.append((char) ((((keyA * (msg.charAt(i) - 'A')) + keyB) % 26) + 'A'));
            } else {
                result.append(msg.charAt(i));
            }
        }

        return result.toString().toLowerCase();
    }

    public static String affineDecryption(String msg, String a, int b) {
        keyA = Integer.parseInt(a);
        keyB = b;
        int a_inverse = inverseOfA(keyA);

        affineAlphabet = affineMapInitalize(affineAlphabet);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            if (affineAlphabet.containsKey(String.valueOf(msg.charAt(i)))) {
                result.append((char) (((a_inverse * ((msg.charAt(i) + 'A' - keyB)) % 26)) + 'A'));
            } else {
                result.append(msg.charAt(i));
            }
        }

        return result.toString();
    }

    public static int inverseOfA(int keyA) {
        int tmp = 0;
        int result = 0;
        for (int i = 0; i < 26; i++) {
            tmp = (keyA * i) % 26;

            // Check if (a*i)%26 == 1,
            // then i will be the multiplicative inverse of a
            if (tmp == 1) {
                result = i;
            }
        }
        return result;
    }

    public static char getValue(int c) {
        for (Entry<String, Integer> entry : affineAlphabet.entrySet()) {
            if (entry.getValue().equals(c)) {
                return entry.getKey().charAt(0);
            }
        }
        return 0;
    }

    public static HashMap<String, Integer> affineMapInitalize(HashMap<String, Integer> map) {
        map.put("a", 0);
        map.put("b", 1);
        map.put("c", 2);
        map.put("d", 3);
        map.put("e", 4);
        map.put("f", 5);
        map.put("g", 6);
        map.put("h", 7);
        map.put("i", 8);
        map.put("j", 9);
        map.put("k", 10);
        map.put("l", 11);
        map.put("m", 12);
        map.put("n", 13);
        map.put("o", 14);
        map.put("p", 15);
        map.put("q", 16);
        map.put("r", 17);
        map.put("s", 18);
        map.put("t", 19);
        map.put("u", 20);
        map.put("v", 21);
        map.put("w", 22);
        map.put("x", 23);
        map.put("y", 24);
        map.put("z", 25);

        return map;
    }
}
