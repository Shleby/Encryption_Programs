package java_ciphers.Encryption_Package.src.com.Ciphers;

import java_ciphers.Encryption_Package.src.com.Utility.Cipher_Utility;

/**
 * The caesar cipher is a substitution cipher that relies on a key to decide how
 * to encrypt or decrypt a message. It is broken by utilizing the chi square
 * statistical method and finding the lowest chi-square to guess the offset key.
 */
public class Caesar_Cipher {
    /**
     * Simple caesar encryption utiliziing a shift of offset which is chosen by the
     * spinner that is created once Caesar Cipher is selected.
     */
    public static String caesarCipherEncryption(String msg, int offSet) {
        StringBuilder result = new StringBuilder();

        for (char character : msg.toCharArray()) {
            if (Character.isLowerCase(character)) {
                int currentPosition = character - 'a';
                int newPosition = (currentPosition + offSet) % Cipher_Utility.ALPHABET_SIZE;
                char newCharacter = (char) ('a' + newPosition);
                result.append(newCharacter);
            } else if (Character.isUpperCase(character)) {
                int currentPosition = character - 'A';
                int newPosition = (currentPosition + offSet) % Cipher_Utility.ALPHABET_SIZE;
                char newCharacter = (char) ('A' + newPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static String caesarCipherDecryption(String msg, int offSet) {
        StringBuilder result = new StringBuilder();

        for (char character : msg.toCharArray()) {
            if (Character.isLowerCase(character)) {
                int currentPosition = character - 'a';
                int newPosition = (currentPosition + (Cipher_Utility.ALPHABET_SIZE - offSet))
                        % Cipher_Utility.ALPHABET_SIZE;
                char newCharacter = (char) ('a' + newPosition);
                result.append(newCharacter);
            } else if (Character.isUpperCase(character)) {
                int currentPosition = character - 'A';
                int newPosition = (currentPosition + (Cipher_Utility.ALPHABET_SIZE - offSet))
                        % Cipher_Utility.ALPHABET_SIZE;
                char newCharacter = (char) ('A' + newPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static int breakCaesarCipher(String msg) {
        // Calculate the expected frequencies of the letters in a given message
        // I.E Based on the prob a letter will appear in 1000 letters of english
        // we are finding what is the expected frequency given the length of our message
        double[] expectedLettersFrequencies = Cipher_Utility.expectedLettersFrequencies(msg.length());

        // Calculate the chi squares

        // Contains the calculated chi-squares for every offset between 0 and 25
        double[] chiSquares = new double[Cipher_Utility.ALPHABET_SIZE];

        // Iterate through every offset between 0 and 25, ten store it in chi-sqaure
        // after calculation
        for (int offset = 0; offset < chiSquares.length; offset++) {
            // Decipher the message based on the current iteration offset
            String decipherAttempt = caesarCipherDecryption(msg, offset);

            // Counting the letters in each message
            long[] lettersFrequencies = Cipher_Utility.observedLettersFrequencies(decipherAttempt);

            // Finally, utilizing the chi square test to calculate chi-square
            double chiSquare = Cipher_Utility.chiSquareTest(expectedLettersFrequencies, lettersFrequencies);
            chiSquares[offset] = chiSquare;
        }

        int probableOffset = 0;

        for (int offset = 0; offset < chiSquares.length; offset++) {
            System.out.println(String.format("Chi-Square for offset %d: %.2f", offset, chiSquares[offset]));
            if (chiSquares[offset] < chiSquares[probableOffset]) {
                probableOffset = offset;
            }
        }

        return probableOffset;
    }
}
