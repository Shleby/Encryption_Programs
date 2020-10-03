package Encryption_Package.Ciphers;

public class Rail_Fence_Cipher {
    public static String encryptRailFenceCipher(String msg, int key) {
        if (key < 0) {
            return "Negative key value not accepted, please try again";
        } else if (key == 0) {
            key = 1;
        }

        String result = "";
        // loop until result is the same length as the message
        for (int i = 0; i < key; i++) {
            int numChars = 0;
            for (int j = i; j < msg.length(); j += getKey(numChars++, i, key)) {
                if (msg.charAt(j) != ' ') 
                    result += msg.charAt(j);
            }
        }
        return result;
    }

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
