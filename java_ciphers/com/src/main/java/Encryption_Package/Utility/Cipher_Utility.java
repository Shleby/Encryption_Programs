package Encryption_Package.Utility;

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

    /**
     * Calculate chi square distribution between values between two arrays
     * @param expectedLettersFrequencies
     * @param lettersFrequencies
     * @return array of calculated chi squares
     */
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

    /**
     * This function is used to find how frequent a letter appears in a given string
     * @param message message to be deciphered
     * @return Array showing the frequency a letter appears in the message
     */
    public static long[] observedLettersFrequencies(String message) {
        return IntStream.rangeClosed('a', 'z').mapToLong(letter -> countLetter((char) letter, message)).toArray();
    }

    public static long countLetter(char letter, String message) {
        return message.chars().filter(character -> character == letter).count();
    }

    /**
     * This function calculates the amount of letters we expect to see in a message
     * based off of the english letters probabilities compared to the length of the message we are
     * deciphering
     * @param messageLength length of the message to be deciphered
     * @return array of frequency calculation to show us how many of each letter we should expect to see based off of a message of size messageLength
     */
    public static double[] expectedLettersFrequencies(int messageLength) {
        return Arrays.stream(englishLettersProbabilities).map(probability -> probability * messageLength).toArray();
    }

    /**
     * Tells us if a number is prime to m. M is typicall 26 (alphabet size)
     * @param n Number to be checked
     * @return True or false based off of if it is prime to m or not.
     */
    public static boolean isPrimeToM(int n) {
        for (int i = 0; i < primes.length; i++) {
            if (primes[i] == n) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the value of a Hashmap.
     * @param c The key of a hash map
     * @param map The has map to be looked through
     * @return The value of the hash map requested
     */ 
    public static char getValue(int c, HashMap<String, Integer> map) {
        for (Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(c)) {
                return entry.getKey().charAt(0);
            }
        }
        return '~';
    }

    /**
     * Initializes a hash map with the keys in alphabetical order and values in reverse alphabetical order
     * @param map Map to be initatized
     * @return key, value pairs to be put into hashmap
     */
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
