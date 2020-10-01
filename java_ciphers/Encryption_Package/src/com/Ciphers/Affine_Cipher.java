package java_ciphers.Encryption_Package.src.com.Ciphers;

import java.util.HashMap;
import java.util.Map.Entry;

import java_ciphers.Encryption_Package.src.com.Utility.Cipher_Utility;

public class Affine_Cipher {
    public static int ALPHABET_SIZE = 26;
    public static int keyA;
    public static int keyB;

    private static HashMap<String, Integer> affineAlphabet = new HashMap<String, Integer>();
    // The probability a english letter will be used in a text of 1000 letters
    private static double[] englishLettersProbabilities = { 0.073, 0.009, 0.030, 0.044, 0.130, 0.028, 0.016, 0.035,
            0.074, 0.002, 0.003, 0.035, 0.025, 0.078, 0.074, 0.027, 0.003, 0.077, 0.063, 0.093, 0.027, 0.013, 0.016,
            0.005, 0.019, 0.001 };

    public static int[] breakAffineCipher(String msg) {
        int[] keys = new int[2];
        int a = 0;
        int b = 0;
        // Calculate the expected frequencies of the letters in a given message
        // I.E Based on the prob a letter will appear in 1000 letters of english
        // we are finding what is the expected frequency given the length of our message
        double[] expectedLettersFrequencies = Caesar_Cipher.expectedLettersFrequencies(msg.length());

        // Calculate the chi squares

        // Contains the calculated chi-squares for every permutation
        double[] chiSquares = new double[312];

        int index = 0;
        // Iterate through every offset between 0 and 25, ten store it in chi-sqaure
        // after calculation
        for (int i = 0; i < ALPHABET_SIZE - 1; i++) {
            a++;
            b = 0;
            for (int j = 0; j < ALPHABET_SIZE - 1; j++) {
                b++;
                if (Cipher_Utility.isPrimeToM(a)) {
                    // Decipher the message based on the current iteration offset
                    String decipherAttempt = affineDecryption(msg, a, b);

                    // Counting the letters in each message
                    long[] lettersFrequencies = Caesar_Cipher.observedLettersFrequencies(decipherAttempt);

                    // Finally, utilizing the chi square test to calculate chi-square
                    double chiSquare = Caesar_Cipher.chiSquareTest(expectedLettersFrequencies, lettersFrequencies);
                    chiSquares[index] = chiSquare;
                } else {
                    continue;
                }
                index++;
            }
        }

        int probableOffset = 0;

        for (int offset = 0; offset < chiSquares.length; offset++) {
            System.out.println(String.format("Chi-Square for offset %d: %.2f", offset, chiSquares[offset]));
            if (chiSquares[offset] < chiSquares[probableOffset]) {
                probableOffset = offset;
            }
        }

        return keys;
    }

    public static String affineEncryption(String msg, int a, int b) {
        keyA = a;
        keyB = b;

        affineAlphabet = affineMapInitalize(affineAlphabet);
        StringBuilder result = new StringBuilder();

        // Iterate through the message
        for (char character : msg.toCharArray()) {
            // If the character is upper case
            if (Character.isUpperCase(character)) {
                // Find the number associated with the current character
                // (needs to be lower case and a string since the hash map only has lower case
                // strings)
                int currentPosition = affineAlphabet.get(String.valueOf(character).toLowerCase());

                // Algorithm to calculate the new number
                int newPosition = ((keyA * currentPosition) + keyB) % ALPHABET_SIZE;

                // Utilize this new number to match it with a letter in the hash map and append
                // to the new message
                result.append(String.valueOf(Cipher_Utility.getValue(newPosition, affineAlphabet)).toUpperCase());
            } else if (Character.isLowerCase(character)) {
                // Find the number associated with the current character
                int currentPosition = affineAlphabet.get(String.valueOf(character));

                // Algorithm to calculate the new number
                int newPosition = ((keyA * currentPosition) + keyB) % ALPHABET_SIZE;

                // Utilize this new number to match it with a letter in the hash map and append
                // to the new message
                result.append(String.valueOf(Cipher_Utility.getValue(newPosition, affineAlphabet)));
            } else {
                // If the character is not in the alphabet, just append it and leave alone
                result.append(character);
            }
        }

        return result.toString();
    }

    public static String affineDecryption(String msg, int a, int b) {
        keyA = a;
        keyB = b;
        int a_inverse = inverse(keyA);

        affineAlphabet = affineMapInitalize(affineAlphabet);
        StringBuilder result = new StringBuilder();

        // Iterate through the message
        for (char character : msg.toCharArray()) {
            // If the character is upper case
            if (Character.isUpperCase(character)) {
                // Algorithm to calculate the old character
                char oldCharacter = (char) (((a_inverse * ((character + 'A' - keyB)) % ALPHABET_SIZE)) + 'A');

                // Append the old character
                result.append(String.valueOf(oldCharacter));
            } else if (Character.isLowerCase(character)) {
                // Algorithm to calculate the old number
                char oldCharacter = (char) (((a_inverse * (character - 'a' - keyB + ALPHABET_SIZE)) % ALPHABET_SIZE)
                        + 'a');

                // Append the old character
                result.append(String.valueOf(oldCharacter));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    public static int inverse(int a) {
        int tmp = 0;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            tmp = (a * i) % ALPHABET_SIZE;

            // if (a*i)%26 == 1
            if (tmp == 1) {
                return i;
            }
        }
        return a;
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
