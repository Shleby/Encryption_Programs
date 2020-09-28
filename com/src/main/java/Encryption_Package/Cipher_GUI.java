package main.java.Encryption_Package;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import Encryption_Package.Cipher;

public class Cipher_GUI {
    private static JLabel labelOne;
    private static JLabel labelTwo;
    private static JLabel labelThree;

    public static JComboBox comboOne;
    public static JRadioButton radioOne;
    public static JRadioButton radioTwo;
    public static JButton submitButton;
    public static JTextArea inputArea;
    public static JTextArea outputArea;

    public static void launchGui() {
        JFrame mainFrame = new JFrame();

        labelOne = new JLabel("Choose Encryption Cipher: ");
        labelOne.setBounds(20, 20, 180, 20);

        String cipherChoices[] = { "", "CaesarCipher", "FakeCipher" };
        comboOne = new JComboBox(cipherChoices);
        comboOne.setBounds(190, 20, 110, 20);

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
                if (comboOne.getSelectedItem().equals("CaesarCipher")) {
                    System.out.println("Caesar Cipher is Selected");
                    if (radioOne.isSelected()) {
                        System.out.println("Encryption is Selected");
                        outputArea.setText(Cipher.caesarCipherEncryption(inputArea.getText()));
                    } else if (radioTwo.isSelected()) {
                        System.out.println("Decryption is Selected");
                        outputArea.setText(Cipher.caesarCipherDecryption(inputArea.getText()));
                    } else {
                        System.out.println("Error: No mode has been selected");
                    }
                } else if (comboOne.getSelectedItem().equals("FakeCipher")) {
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

        mainFrame.add(labelOne);
        mainFrame.add(comboOne);
        mainFrame.add(labelThree);
        mainFrame.add(radioOne);
        mainFrame.add(radioTwo);
        mainFrame.add(labelTwo);
        mainFrame.add(inputArea);
        mainFrame.add(outputArea);
        mainFrame.add(submitButton);

        mainFrame.setTitle("Encryption Program");
        mainFrame.setSize(1000, 700);// 400 width and 500 height
        mainFrame.setLayout(null);// using no layout managers
        mainFrame.setVisible(true);// making the frame visible
    }
}
