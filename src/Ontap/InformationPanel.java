package Ontap;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    JTextField IDField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField GPAField = new JTextField();
    JTextField addressField = new JTextField();
    JTextArea noteField = new JTextArea();

    InformationPanel(){
        setLayout(new GridBagLayout());
        //Set gbc
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER  ;

        //ADd student ID
        JLabel label = new JLabel("Student ID");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        IDField.setPreferredSize(new Dimension(200, 20));
        gbc.insets = new Insets(0, 10, 0, 10);
        add(IDField, gbc);


        gbc.insets = new Insets(0, 10, 0, 0);
        //Add Student Name
        label = new JLabel("Student Name");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 10);
        add(nameField, gbc);


        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.fill = GridBagConstraints.NONE;

        //Add Student GPA
        label = new JLabel("Student GPA");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 0, 10);
        add(GPAField, gbc);


        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.fill = GridBagConstraints.NONE;

        //Add Student Address
        label = new JLabel("Address");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 0, 10);
        add(addressField, gbc);


        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.fill = GridBagConstraints.NONE;

        //Add Student Note
        label = new JLabel("Note");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(label, gbc);


        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weighty = 10;
        gbc.insets = new Insets(0, 10, 0, 10);
        JScrollPane sp = new JScrollPane(noteField);
        add(sp, gbc);


        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
    }

    public JTextField getIDField() {
        return IDField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getGPAField() {
        return GPAField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextArea getNoteField() {
        return noteField;
    }


}
