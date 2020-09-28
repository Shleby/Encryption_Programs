package Encryption_Package;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Cipher_GUI {
    private static JLabel labelOne;
    private static JLabel labelTwo;
    private static JLabel labelThree;
    private static JLabel labelFour;
    private static JLabel cipherLabel;
    private static JLabel decryptLabel;
    private static String cipherChoices[] = { "", "Caesar Cipher", "Fake Cipher" };

    public static JComboBox comboOne;
    public static JComboBox cipherCombo;
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

    public static void launchGui() {
        JFrame mainFrame = new JFrame();
        JFrame breakDialog = new JFrame();

        labelOne = new JLabel("Choose Encryption Cipher: ");
        labelOne.setBounds(20, 20, 180, 20);

        labelFour = new JLabel("Select an Offset Value: ");
        labelFour.setBounds(340, 20, 180, 20);
        labelFour.setVisible(false);

        SpinnerModel value = new SpinnerNumberModel(6, 1, 26, 1);
        offsetSpin = new JSpinner(value);
        offsetSpin.setBounds(480, 20, 40, 20);
        offsetSpin.setVisible(false);

        comboOne = new JComboBox(cipherChoices);
        comboOne.setBounds(190, 20, 110, 20);
        comboOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent j) {
                if (comboOne.getSelectedItem().equals("Caesar Cipher")) {
                    labelFour.setVisible(true);
                    offsetSpin.setVisible(true);
                } else if (comboOne.getSelectedItem() != "Caesar Cipher") {
                    labelFour.setVisible(false);
                    offsetSpin.setVisible(false);
                }
            }
        });
        labelThree = new JLabel("Select Mode: ");
        labelThree.setBounds(20, 60, 80, 20);

        radioOne = new JRadioButton("Encryption Mode");
        radioOne.setBounds(100, 60, 130, 20);
        radioTwo = new JRadioButton("Decryption Mode");
        radioTwo.setBounds(230, 60, 130, 20);
        ButtonGroup modeGroup = new ButtonGroup();
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
                    System.out.println("Caesar Cipher is Selected");
                    if (radioOne.isSelected()) {
                        System.out.println("Encryption is Selected");
                        outputArea.setText(
                                Cipher.caesarCipherEncryption(inputArea.getText(), (int) offsetSpin.getValue()));
                    } else if (radioTwo.isSelected()) {
                        System.out.println("Decryption is Selected");
                        outputArea.setText(
                                Cipher.caesarCipherDecryption(inputArea.getText(), (int) offsetSpin.getValue()));
                    } else {
                        System.out.println("Error: No mode has been selected");
                    }
                } else if (comboOne.getSelectedItem().equals("Fake Cipher")) {
                    System.out.println("Fake Cipher is Selected");
                    if (radioOne.isSelected()) {
                        System.out.println("Encryption is Selected");
                    } else if (radioTwo.isSelected()) {
                        System.out.println("Decryption is Selected");
                    } else {
                        System.out.println("Error: No mode has been selected");
                    }
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

        mainFrame.setTitle("Encryption Program");
        mainFrame.setSize(1000, 700);// 400 width and 500 height
        mainFrame.setLayout(null);// using no layout managers
        mainFrame.setVisible(true);// making the frame visible

        cipherLabel = new JLabel("Select the cipher you wish to break: ");
        cipherCombo = new JComboBox(cipherChoices);

        decryptLabel = new JLabel("Enter the message you wish to break: ");
        decryptArea = new JTextArea();
        decryptArea.setLineWrap(true);
        decryptArea.setBounds(20, 20, 200, 100);

        breakButton = new JButton("Submit");
        breakButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cipherCombo.getSelectedItem().equals("Caesar Cipher")) {
                    System.out.println("Caesar Cipher is Selected");
                    int offsetGuess = Cipher.breakCaesarCipher(decryptArea.getText());
                    String decryptedGuess = Cipher.caesarCipherDecryption(decryptArea.getText(), offsetGuess);
                    outputArea.setText("Results Recorded Below:\nOffset Guess : " + offsetGuess
                            + "\nDecrypted Message : " + decryptedGuess);
                    breakDialog.setVisible(false);
                } else if (cipherCombo.getSelectedItem().equals("Fake Cipher")) {
                    System.out.println("Fake Cipher is Selected");
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
}
