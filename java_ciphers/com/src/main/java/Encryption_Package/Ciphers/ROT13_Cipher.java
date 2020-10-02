package java_ciphers.com.src.main.java.Encryption_Package.Ciphers;

/**
 * The Rot13 Cipher is simply a caesar cipher with a fixed key of 13. Therfore,
 * call the caesar cipher and use only a key of 13
 * 
 * @author Shelby Huffman
 */
public class ROT13_Cipher {
    public static int key = 13;

    public static String rot13Encryption(String msg) {
        return Caesar_Cipher.caesarCipherEncryption(msg, key);
    }

    public static String rot13Decryption(String msg) {
        return Caesar_Cipher.caesarCipherEncryption(msg, key);
    }
}
