package Encryption_Package;

import main.java.Encryption_Package.Cipher_GUI;

/**
 * Hello world!
 *
 */
public class Cipher {

    public static void main(String[] args) {
        Cipher_GUI.launchGui();
    }

    public static String caesarCipherEncryption(String msg) {
        System.out.println("Encrypting utilizng the Caesar Cipher Technique.");
        System.out.println(msg);

        return msg;
    }

    public static String caesarCipherDecryption(String msg) {
        System.out.println("Decrypting utilizng the Caesar Cipher Technique.");
        System.out.println(msg);

        return msg;
    }
}
