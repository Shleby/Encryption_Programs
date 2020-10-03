package Encryption_Package;
import Encryption_Package.Display.Cipher_GUI;
import Encryption_Package.Ciphers.Rail_Fence_Cipher;

/**
 * Main Method that launches the GUI
 * 
 * @author Shelby Huffman
 */
public class Cipher {
    public static void main(String[] args) {
        Cipher_GUI.launchGui();
        System.out.println(Rail_Fence_Cipher.encryptRailFenceCipher("WE ARE DISCOVERED FLEE AT ONCE", 3));
    }
}
