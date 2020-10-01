package java_ciphers.Encryption_Package.src.com.Utility;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * This Utility class contains methods that are used consistently throughout
 * most if not all of the ciphers implemented
 */
public class Cipher_Utility {
    public static boolean isPrimeToM(int n) {
        // A list of values that are relatively prime to m = 26
        int[] primes = { 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25 };

        for (int i = 0; i < primes.length; i++) {
            if (primes[i] == n) {
                return true;
            }
        }

        return false;
    }

    public static char getValue(int c, HashMap<String, Integer> map) {
        for (Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(c)) {
                return entry.getKey().charAt(0);
            }
        }
        return '~';
    }

    public static HashMap<String, String> mapInitalize(HashMap<String, String> map) {
        map.put("a", "z");
        map.put("b", "y");
        map.put("c", "x");
        map.put("d", "w");
        map.put("e", "v");
        map.put("f", "u");
        map.put("g", "t");
        map.put("h", "s");
        map.put("i", "r");
        map.put("j", "q");
        map.put("k", "p");
        map.put("l", "o");
        map.put("m", "n");
        map.put("n", "m");
        map.put("o", "l");
        map.put("p", "k");
        map.put("q", "j");
        map.put("r", "i");
        map.put("s", "h");
        map.put("t", "g");
        map.put("u", "f");
        map.put("v", "e");
        map.put("w", "d");
        map.put("x", "c");
        map.put("y", "b");
        map.put("z", "a");

        return map;
    }
}
