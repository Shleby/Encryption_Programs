package java_ciphers.Encryption_Package.src.com.Ciphers;

import java.util.HashMap;

import java_ciphers.Encryption_Package.src.com.Utility.Cipher_Utility;

public class Affine_Cipher {
    public static int keyA;
    public static int keyB;

    private static HashMap<String, Integer> affineAlphabet = new HashMap<String, Integer>();

    public static int[] breakAffineCipher(String msg) {
        int[] keys = new int[624];
        int a = 0;
        int b = 0;
        // Calculate the expected frequencies of the letters in a given message
        // I.E Based on the prob a letter will appear in 1000 letters of english
        // we are finding what is the expected frequency given the length of our message
        double[] expectedLettersFrequencies = Cipher_Utility.expectedLettersFrequencies(msg.length());

        // Calculate the chi squares

        // Contains the calculated chi-squares for every permutation
        double[] chiSquares = new double[312];

        int index = 0;
        int keysIndex = 0;
        // Iterate through every offset between 0 and 25, ten store it in chi-sqaure
        // after calculation
        for (int i = 0; i < Cipher_Utility.ALPHABET_SIZE - 1; i++) {
            a++;
            b = 0;
            for (int j = 0; j < Cipher_Utility.ALPHABET_SIZE - 1; j++) {
                b++;
                if (Cipher_Utility.isPrimeToM(a)) {
                    // Decipher the message based on the current iteration offset
                    String decipherAttempt = affineDecryption(msg, a, b);

                    // Counting the letters in each message
                    long[] lettersFrequencies = Cipher_Utility.observedLettersFrequencies(decipherAttempt);

                    // Finally, utilizing the chi square test to calculate chi-square
                    double chiSquare = Cipher_Utility.chiSquareTest(expectedLettersFrequencies, lettersFrequencies);
                    chiSquares[index] = chiSquare;
                    keys[keysIndex] = a;
                    keys[keysIndex + 1] = b;
                } else {
                    continue;
                }
                index++;
                keysIndex += 2;
            }
        }

        int probableOffset = 0;
        int[] result = new int[2];

        for (int i = 0; i < chiSquares.length; i++) {
            System.out.println(String.format("Chi-Square for offset %d: %.2f", i, chiSquares[i]));
            if (chiSquares[i] < chiSquares[probableOffset]) {
                probableOffset = i;
                result[0] = keys[i];
                result[1] = keys[i + 1];
            }
        }

        return result;
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
                int newPosition = ((keyA * currentPosition) + keyB) % Cipher_Utility.ALPHABET_SIZE;

                // Utilize this new number to match it with a letter in the hash map and append
                // to the new message
                result.append(String.valueOf(Cipher_Utility.getValue(newPosition, affineAlphabet)).toUpperCase());
            } else if (Character.isLowerCase(character)) {
                // Find the number associated with the current character
                int currentPosition = affineAlphabet.get(String.valueOf(character));

                // Algorithm to calculate the new number
                int newPosition = ((keyA * currentPosition) + keyB) % Cipher_Utility.ALPHABET_SIZE;

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
                char oldCharacter = (char) (((a_inverse * ((character + 'A' - keyB)) % Cipher_Utility.ALPHABET_SIZE))
                        + 'A');

                // Append the old character
                result.append(String.valueOf(oldCharacter));
            } else if (Character.isLowerCase(character)) {
                // Algorithm to calculate the old number
                char oldCharacter = (char) (((a_inverse * (character - 'a' - keyB + Cipher_Utility.ALPHABET_SIZE))
                        % Cipher_Utility.ALPHABET_SIZE) + 'a');

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
        for (int i = 0; i < Cipher_Utility.ALPHABET_SIZE; i++) {
            tmp = (a * i) % Cipher_Utility.ALPHABET_SIZE;

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
