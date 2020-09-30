package java.Encryption_Package.src.com.Ciphers;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * The caesar cipher is a substitution cipher that relies on a key to decide how
 * to encrypt or decrypt a message. It is broken by utilizing the chi square
 * statistical method and finding the lowest chi-square to guess the offset key.
 */
public class Caesar_Cipher {
    private static int ALPHABET_SIZE = 26;
    // The probability a english letter will be used in a text of 1000 letters
    private static double[] englishLettersProbabilities = { 0.073, 0.009, 0.030, 0.044, 0.130, 0.028, 0.016, 0.035,
            0.074, 0.002, 0.003, 0.035, 0.025, 0.078, 0.074, 0.027, 0.003, 0.077, 0.063, 0.093, 0.027, 0.013, 0.016,
            0.005, 0.019, 0.001 };

    /**
     * Simple caesar encryption utiliziing a shift of offset which is chosen by the
     * spinner that is created once Caesar Cipher is selected.
     */
    public static String caesarCipherEncryption(String msg, int offSet) {
        StringBuilder result = new StringBuilder();

        for (char character : msg.toCharArray()) {
            if (character != ' ' && Character.isLowerCase(character)) {
                int currentPosition = character - 'a';
                int newPosition = (currentPosition + offSet) % ALPHABET_SIZE;
                char newCharacter = (char) ('a' + newPosition);
                result.append(newCharacter);
            } else if (character != ' ' && Character.isUpperCase(character)) {
                int currentPosition = character - 'A';
                int newPosition = (currentPosition + offSet) % ALPHABET_SIZE;
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
            if (character != ' ' && Character.isLowerCase(character)) {
                int currentPosition = character - 'a';
                int newPosition = (currentPosition + (ALPHABET_SIZE - offSet)) % ALPHABET_SIZE;
                char newCharacter = (char) ('a' + newPosition);
                result.append(newCharacter);
            } else if (character != ' ' && Character.isUpperCase(character)) {
                int currentPosition = character - 'A';
                int newPosition = (currentPosition + (ALPHABET_SIZE - offSet)) % ALPHABET_SIZE;
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
        double[] expectedLettersFrequencies = expectedLettersFrequencies(msg.length());

        // Calculate the chi squares

        // Contains the calculated chi-squares for every offset between 0 and 25
        double[] chiSquares = new double[ALPHABET_SIZE];

        // Iterate through every offset between 0 and 25, ten store it in chi-sqaure
        // after calculation
        for (int offset = 0; offset < chiSquares.length; offset++) {
            // Decipher the message based on the current iteration offset
            String decipherAttempt = caesarCipherDecryption(msg, offset);

            // Counting the letters in each message
            long[] lettersFrequencies = observedLettersFrequencies(decipherAttempt);

            // Finally, utilizing the chi square test to calculate chi-square
            double chiSquare = chiSquareTest(expectedLettersFrequencies, lettersFrequencies);
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

    private static double chiSquareTest(double[] expectedLettersFrequencies, long[] lettersFrequencies) {
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

    private static long[] observedLettersFrequencies(String message) {
        return IntStream.rangeClosed('a', 'z').mapToLong(letter -> countLetter((char) letter, message)).toArray();
    }

    private static long countLetter(char letter, String message) {
        return message.chars().filter(character -> character == letter).count();
    }

    private static double[] expectedLettersFrequencies(int messageLength) {
        return Arrays.stream(englishLettersProbabilities).map(probability -> probability * messageLength).toArray();
    }
}
