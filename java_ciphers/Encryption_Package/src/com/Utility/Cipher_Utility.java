package java_ciphers.Encryption_Package.src.com.Utility;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import java.util.Arrays;

/**
 * This Utility class contains methods that are used consistently throughout
 * most if not all of the ciphers implemented
 */
public class Cipher_Utility {
    // A list of values that are relatively prime to m = 26
    public static int[] primes = { 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25 };
    public static final int ALPHABET_SIZE = 26;
    // The probability a english letter will be used in a text of 1000 letters
    public static double[] englishLettersProbabilities = { 0.073, 0.009, 0.030, 0.044, 0.130, 0.028, 0.016, 0.035,
            0.074, 0.002, 0.003, 0.035, 0.025, 0.078, 0.074, 0.027, 0.003, 0.077, 0.063, 0.093, 0.027, 0.013, 0.016,
            0.005, 0.019, 0.001 };

    public static double chiSquareTest(double[] expectedLettersFrequencies, long[] lettersFrequencies) {
        double[] chiSquares = new double[ALPHABET_SIZE];

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            // Calculate the residual for the current iteration by subtracting the Observed
            // - Expected
            double residual = lettersFrequencies[i] - expectedLettersFrequencies[i];

            // Square the residual
            residual = Math.pow(residual, 2);

            // Divide the residual by the expected value and store it in the array
            chiSquares[i] = residual / expectedLettersFrequencies[i];
        }

        double chiSquareResult = 0;
        // Add up the sum of all the chi squares calculated
        for (int j = 0; j < chiSquares.length; j++) {
            chiSquareResult += chiSquares[j];
        }

        return chiSquareResult;
    }

    public static long[] observedLettersFrequencies(String message) {
        return IntStream.rangeClosed('a', 'z').mapToLong(letter -> countLetter((char) letter, message)).toArray();
    }

    public static long countLetter(char letter, String message) {
        return message.chars().filter(character -> character == letter).count();
    }

    public static double[] expectedLettersFrequencies(int messageLength) {
        return Arrays.stream(englishLettersProbabilities).map(probability -> probability * messageLength).toArray();
    }

    public static boolean isPrimeToM(int n) {
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
