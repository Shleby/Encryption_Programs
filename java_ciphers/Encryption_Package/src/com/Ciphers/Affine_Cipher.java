package java_ciphers.Encryption_Package.src.com.Ciphers;

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
                result.append(String.valueOf(getValue(newPosition)).toUpperCase());
            } else if (Character.isLowerCase(character)) {
                // Find the number associated with the current character
                int currentPosition = affineAlphabet.get(String.valueOf(character));

                // Algorithm to calculate the new number
                int newPosition = ((keyA * currentPosition) + keyB) % ALPHABET_SIZE;

                // Utilize this new number to match it with a letter in the hash map and append
                // to the new message
                result.append(String.valueOf(getValue(newPosition)));
            } else {
                // If the character is not in the alphabet, just append it and leave alone
                result.append(character);
            }
        }

        return result.toString();
    }

    public static String affineDecryption(String msg, String a, int b) {
        keyA = Integer.parseInt(a);
        keyB = b;
        int a_inverse = inverseOfA(keyA);

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

    public static int inverseOfA(int a) {
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

    public static char getValue(int c) {
        for (Entry<String, Integer> entry : affineAlphabet.entrySet()) {
            if (entry.getValue().equals(c)) {
                return entry.getKey().charAt(0);
            }
        }
        return 'a';
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
