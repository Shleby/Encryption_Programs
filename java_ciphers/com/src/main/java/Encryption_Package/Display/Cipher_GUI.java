package Encryption_Package.Display;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import Encryption_Package.Ciphers.*;
import Encryption_Package.Utility.Cipher_Utility;

public class Cipher_GUI {
    private static JLabel labelOne;
    private static JLabel labelTwo;
    private static JLabel labelThree;
    private static JLabel labelFour;
    private static JLabel cipherLabel;
    private static JLabel decryptLabel;
    private static String cipherChoices[] = { "", "Atbash Cipher", "ROT13 Cipher", "Caesar Cipher", "Affine Cipher", "Rail Fence Cipher" };
    private static JFrame breakDialog;
    private static JFrame mainFrame;
    
    public static ButtonGroup modeGroup;
    public static JComboBox<String> comboOne;
    public static JComboBox<String> cipherCombo;
    public static JSpinner aSpin;
    public static JRadioButton radioOne;
    public static JRadioButton radioTwo;
    public static JButton submitButton;
    public static JButton attemptButton;
    public static JButton breakButton;
    public static JButton clearButton;
    public static JTextArea inputArea;
    public static JTextArea outputArea;
    public static JTextArea decryptArea;
    public static JSpinner offsetSpin;
    public static JSpinner bSpin;
    public static JCheckBox cbRails;

    /**
     * Launches GUI for the application. Initializes java swing widgets
     */
    public static void launchGui() {
        mainFrame = new JFrame();
        breakDialog = new JFrame();

        labelOne = new JLabel("Choose Encryption Cipher: ");
        labelOne.setBounds(20, 20, 180, 20);

        labelFour = new JLabel("Select an offset/key Value: ");
        labelFour.setBounds(340, 20, 180, 20);
        labelFour.setVisible(false);

        SpinnerModel offsetValue = new SpinnerNumberModel(6, 1, 26, 1);
        offsetSpin = new JSpinner(offsetValue);
        offsetSpin.setBounds(520, 20, 40, 20);
        offsetSpin.setVisible(false);

        SpinnerModel aValue = new SpinnerNumberModel(0, 0, 25, 1);
        aSpin = new JSpinner(aValue);
        aSpin.setBounds(480, 20, 40, 20);
        aSpin.setVisible(false);

        SpinnerModel bValue = new SpinnerNumberModel(0, 0, 25, 1);
        bSpin = new JSpinner(bValue);
        bSpin.setBounds(540, 20, 40, 20);
        bSpin.setVisible(false);

        cbRails = new JCheckBox("Replace spaces with underscores?");
        cbRails.setBounds(520, 60, 280, 20);
        cbRails.setVisible(false);

        comboOne = new JComboBox<String>(cipherChoices);
        comboOne.setBounds(190, 20, 120, 20);
        comboOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent j) {
                if (comboOne.getSelectedItem().equals("Caesar Cipher")) {
                    visibleChain(true, true, false, false, false);
                } else if (comboOne.getSelectedItem().equals("Rail Fence Cipher")) {
                    visibleChain(true, true, false, false, true);
                } else if (comboOne.getSelectedItem().equals("Affine Cipher")) {
                    visibleChain(false, false, true, true, false);
                } else {
                    visibleChain(false, false, false, false, false);
                }
            }
        });
        labelThree = new JLabel("Select Mode: ");
        labelThree.setBounds(20, 60, 120, 20);

        radioOne = new JRadioButton("Encryption Mode");
        radioOne.setBounds(100, 60, 140, 20);
        radioTwo = new JRadioButton("Decryption Mode");
        radioTwo.setBounds(240, 60, 160, 20);
        modeGroup = new ButtonGroup();
        modeGroup.add(radioOne);
        modeGroup.add(radioTwo);

        labelTwo = new JLabel("Enter text to be encrypted/decrypted: ");
        labelTwo.setBounds(20, 100, 240, 20);

        inputArea = new JTextArea("Input . . .");
        inputArea.setBounds(20, 140, 900, 150);
        inputArea.setLineWrap(true);
        inputArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        outputArea = new JTextArea("Output . . .");
        outputArea.setBounds(20, 330, 900, 150);
        outputArea.setLineWrap(true);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        submitButton = new JButton("Submit");
        submitButton.setBounds(20, 520, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comboOne.getSelectedItem().equals("Caesar Cipher")) {
                    submitContent("Caesar Cipher");
                } else if (comboOne.getSelectedItem().equals("Affine Cipher")) {
                    submitContent("Affine Cipher");
                } else if (comboOne.getSelectedItem().equals("Atbash Cipher")) {
                    submitContent("Atbash Cipher");
                } else if (comboOne.getSelectedItem().equals("ROT13 Cipher")) {
                    submitContent("ROT13 Cipher");
                } else if (comboOne.getSelectedItem().equals("Rail Fence Cipher")) {
                    submitContent("Rail Fence Cipher");
                } else {
                    System.out.println("Error: No Cipher Selected");
                }
            }
        });

        attemptButton = new JButton("Attempt to break the cipher");
        attemptButton.setBounds(160, 520, 200, 30);
        attemptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                breakDialog.setVisible(true);
            }
        });

        mainFrame.add(labelOne);
        mainFrame.add(comboOne);
        mainFrame.add(labelFour);
        mainFrame.add(offsetSpin);
        mainFrame.add(labelThree);
        mainFrame.add(radioOne);
        mainFrame.add(radioTwo);
        mainFrame.add(labelTwo);
        mainFrame.add(inputArea);
        mainFrame.add(outputArea);
        mainFrame.add(submitButton);
        mainFrame.add(attemptButton);
        mainFrame.add(bSpin);
        mainFrame.add(aSpin);
        mainFrame.add(cbRails);

        mainFrame.setTitle("Encryption Program");
        mainFrame.setSize(1000, 700);// 400 width and 500 height
        mainFrame.setLayout(null);// using no layout managers
        mainFrame.setVisible(true);// making the frame visible

        cipherLabel = new JLabel("Select the cipher you wish to break: ");
        cipherCombo = new JComboBox<String>(cipherChoices);

        decryptLabel = new JLabel("Enter the message you wish to break: ");
        decryptArea = new JTextArea();
        decryptArea.setLineWrap(true);
        decryptArea.setBounds(20, 20, 200, 100);

        breakButton = new JButton("Submit");
        breakButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cipherCombo.getSelectedItem().equals("Caesar Cipher")) {
                    System.out.println("Caesar Cipher is Selected");
                    int offsetGuess = Caesar_Cipher.breakCaesarCipher(decryptArea.getText());
                    String decryptedGuess = Caesar_Cipher.caesarCipherDecryption(decryptArea.getText(), offsetGuess);
                    outputArea.setText("Results Recorded Below:\nOffset Guess : " + offsetGuess
                            + "\nDecrypted Message : " + decryptedGuess);
                    breakDialog.setVisible(false);
                } else if (cipherCombo.getSelectedItem().equals("Abtash Cipher")) {
                    System.out.println("Abtash Cipher is Selected");
                    outputArea.setText("No break is needed. Please utilize encryption/decryption functionality.");
                    breakDialog.setVisible(false);
                } else if (cipherCombo.getSelectedItem().equals("ROT13 Cipher")) {
                    System.out.println("ROT13 Cipher is selected");
                    outputArea.setText(
                            "No break is needed. ROT13 will always be a caesar cipher with offset = 13. Please utilize encryption/decryption functionality.");
                    breakDialog.setVisible(false);
                } else if (cipherCombo.getSelectedItem().equals("Affine Cipher")) {
                    System.out.println("Affine Cipher is Selected");
                    int[] keyGuess = Affine_Cipher.breakAffineCipher(decryptArea.getText());
                    String decryptedGuess = Affine_Cipher.affineDecryption(decryptArea.getText(), keyGuess[0],
                            keyGuess[1]);
                    outputArea.setText("Results Recorded Below:\nA Key Guess : " + keyGuess[0] + "\nB Key Guess : "
                            + keyGuess[1] + "\nDecrypted Message : " + decryptedGuess);
                    breakDialog.setVisible(false);
                } else {
                    System.out.println("Error: No Cipher Selected");
                }
            }
        });

        breakDialog.add(cipherLabel);
        breakDialog.add(cipherCombo);
        breakDialog.add(decryptLabel);
        breakDialog.add(decryptArea);
        breakDialog.add(breakButton);

        breakDialog.setTitle("Breaking the Cipher");
        breakDialog.setSize(500, 500);
        breakDialog.setLayout(new FlowLayout(FlowLayout.CENTER));
        breakDialog.setVisible(false);

        clearButton = new JButton("Clear All Settings");
        clearButton.setBounds(400, 520, 200, 30);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputArea.setText("");
                outputArea.setText("");
                decryptArea.setText("");
                comboOne.setSelectedIndex(0);
                cipherCombo.setSelectedItem(0);
                modeGroup.clearSelection();
            }
        });

        mainFrame.add(clearButton);
    }

    /**
     * Actions to be taken when the submit button is pushed
     * @param cipher The current cipher selected
     */
    private static void submitContent(String cipher) {
        System.out.println(cipher + " is Selected");
        if (radioOne.isSelected()) {
            System.out.println("Encryption is Selected");
            if (cipher == "Caesar Cipher") {
                outputArea.setText(Caesar_Cipher.caesarCipherEncryption(inputArea.getText(), (int) offsetSpin.getValue()));
            } else if (cipher == "Atbash Cipher") {
                outputArea.setText(Atbash_Cipher.atbashCipherCryption(inputArea.getText()));
            } else if (cipher == "Affine Cipher") {
                if (Cipher_Utility.isPrimeToM((int) aSpin.getValue())) {
                    outputArea.setText(Affine_Cipher.affineEncryption(inputArea.getText(),
                            (int) aSpin.getValue(), (int) bSpin.getValue()));
                } else {
                    outputArea.setText("Error : Please choose a prime number for Key A relative to 26.");
                }
            } else if (cipher == "ROT13 Cipher") {
                outputArea.setText(ROT13_Cipher.rot13Encryption(inputArea.getText()));
            } else if (cipher == "Rail Fence Cipher") {
                outputArea.setText(
                    Rail_Fence_Cipher.railFenceEncryption(inputArea.getText(), (int) offsetSpin.getValue(), !cbRails.isSelected()));
            }
        } else if (radioTwo.isSelected()) {
            System.out.println("Decryption is Selected");
            if (cipher == "Caesar Cipher") {
                outputArea.setText(Caesar_Cipher.caesarCipherDecryption(inputArea.getText(), (int) offsetSpin.getValue()));
            } else if (cipher == "Atbash Cipher") {
                outputArea.setText(Atbash_Cipher.atbashCipherCryption(inputArea.getText()));
            } else if (cipher == "Affine Cipher") {
                if (Cipher_Utility.isPrimeToM((int) aSpin.getValue())) {
                    outputArea.setText(Affine_Cipher.affineDecryption(inputArea.getText(),
                            (int) aSpin.getValue(), (int) bSpin.getValue()));
                } else {
                    outputArea.setText("Error : Please choose a prime number for Key A relative to 26.");
                }
            } else if (cipher == "ROT13 Cipher") {
                outputArea.setText(ROT13_Cipher.rot13Decryption(inputArea.getText()));
            } else if (cipher == "Rail Fence Cipher") {
                outputArea.setText(Rail_Fence_Cipher.railFenceDecryption(inputArea.getText(), (int) offsetSpin.getValue(), cbRails.isSelected()));
            }
        } else {
            System.out.println("Error: No mode has been selected");
        }
    }

    /**
     * Controls the visibility of these widgets based off of the comboOne selected item
     * @param label labelFour widget
     * @param offsetSpin offsetSpin widget
     * @param a aSpin widget
     * @param b bSpin widget
     * @param cb cbRails widget
     */
    private static void visibleChain(boolean lbl, boolean offset, boolean a, boolean b, boolean cb) {
        labelFour.setVisible(lbl);
        offsetSpin.setVisible(offset);
        aSpin.setVisible(a);
        bSpin.setVisible(b);
        cbRails.setVisible(cb);
    }
}
