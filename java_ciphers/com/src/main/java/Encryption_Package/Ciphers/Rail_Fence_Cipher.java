package Encryption_Package.Ciphers;

import Encryption_Package.Utility.Cipher_Utility;

public class Rail_Fence_Cipher {
    /**
     * This method encrypts messages utilizing the rail fence cipher, a transposition cipher
     * @param msg The msg to be encrypted
     * @param key The key utilzed for how many rails to use
     * @param spaces IF this is true, keep spaces in message, if not, replace spaces with the '_' char
     * @return
     */
    public static String railFenceEncryption(String msg, int key, boolean spaces) {
        if (key < 0) {
            return "Negative key value not accepted, please try again";
        } else if (key == 0) {
            key = 1;
        }

        StringBuilder result = new StringBuilder();
        // loop until result is the same length as the message
        for (int i = 0; i < key; i++) {
            int numChars = 0;
            for (int j = i; j < msg.length(); j += getKey(numChars++, i, key)) {
                if (spaces) { // Only append letters and spaces
                    if (Character.isLetter(msg.charAt(j)) || msg.charAt(j) == ' ')
                        result.append(msg.charAt(j));
                } else { // only append letters and '_' to represent spaces
                    if (Character.isLetter(msg.charAt(j)))
                        result.append(msg.charAt(j));
                    else if (msg.charAt(j) == ' ')
                        result.append("_");
                }
            }
        }
        return result.toString();
    }

    public static String railFenceDecryption(String msg, int key, boolean spaces) {
		if (key < 0) {
            throw new ArithmeticException("Negative key value");
        }
        StringBuilder result = new StringBuilder();
        int pos = 0; // Position in source string
        for(int i = 0; i < key; i++) { // Look rows
            int numChars = 0; // The number of the character in the row
            for(int j = i; j < msg.length(); j += getKey(numChars++, i, key)) {
                result.setCharAt(i, msg.charAt(pos++));
            }
        }

        return result.toString();
    }
    
    public static int breakRailFrenceCipher(String msg, boolean spaces) {
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
            String decipherAttempt = railFenceDecryption(msg, offset, spaces);

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

    /**
     * Increments or decrements the key based on rails
     * @param i
     * @param row
     * @param size
     * @return current rail
     */
    private static int getKey(int i, int row, int size) {
        if ((size == 0) || (size == 1)) {
            return 1;
        }
        if ((row == 0) || (row == size - 1)) {
            return (size - 1) * 2;
        }
        if (i % 2 == 0) {
            return (size - 1 - row) * 2;
        }
        return 2 * row;
    }
}
