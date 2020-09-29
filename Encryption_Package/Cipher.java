package Encryption_Package;

import java.util.HashMap;

import Encryption_Package.Display.Cipher_GUI;

/**
 * Main Method that launches the GUI
 * 
 * @author Shelby Huffman
 */
public class Cipher {
    public static void main(String[] args) {
        Cipher_GUI.launchGui();
    }

    public static HashMap<String, String> mapInitalize(HashMap<String, String> map) {
        map.put("a", "z");
        map.put("b", "y");
        map.put("c", "x");
        map.put("d", "w");
        map.put("e", "v");
        map.put("f", "u");
        map.put("g", "t");
        map.put("h", "s");
        map.put("i", "r");
        map.put("j", "q");
        map.put("k", "p");
        map.put("l", "o");
        map.put("m", "n");
        map.put("n", "m");
        map.put("o", "l");
        map.put("p", "k");
        map.put("q", "j");
        map.put("r", "i");
        map.put("s", "h");
        map.put("t", "g");
        map.put("u", "f");
        map.put("v", "e");
        map.put("w", "d");
        map.put("x", "c");
        map.put("y", "b");
        map.put("z", "a");

        return map;
    }
}
