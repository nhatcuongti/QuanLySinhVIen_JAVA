package Ontap.panels;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    JTextField IDField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField GPAField = new JTextField();
    JTextField addressField = new JTextField();
    JTextField imgField = new JTextField();
    JTextArea noteField = new JTextArea();

    public InformationPanel(){
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

        //Add Student Image
        label = new JLabel("Image");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 0, 10);
        add(imgField, gbc);


        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.fill = GridBagConstraints.NONE;

        //Add Student Note
        label = new JLabel("Note");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(label, gbc);


        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 5;
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

    public boolean checkFilled(){
        System.out.println("Hello");
        if (IDField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "ID is empty !!", "Fail Add", JOptionPane.CANCEL_OPTION);
            return false;
        }
        else if (nameField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Name is empty !!", "Fail Add", JOptionPane.CANCEL_OPTION);
            return false;
        }
        else if (GPAField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "GPA is empty !!", "Fail Add", JOptionPane.CANCEL_OPTION);
            return false;
        }
        else if (addressField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Address is empty !!", "Fail Add", JOptionPane.CANCEL_OPTION);
            return false;
        }
        else if (imgField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Image is empty !!", "Fail Add", JOptionPane.CANCEL_OPTION);
            return false;
        }
        else if (noteField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Note is empty !!", "Fail Add", JOptionPane.CANCEL_OPTION);
            return false;
        }

        return true;
    }

    public String[] getData(){
        String[] data = {IDField.getText(), nameField.getText(), GPAField.getText(), addressField.getText(), imgField.getText(), noteField.getText()};
        return data;
    }

    public void insertData(String[] data){
        IDField.setText(data[0]);
        nameField.setText(data[1]);
        GPAField.setText(data[2]);
        addressField.setText(data[3]);
        imgField.setText(data[4]);
        noteField.setText(data[5]);
    }

    public void resetData(){
        IDField.setText("");
        nameField.setText("");
        GPAField.setText("");
        addressField.setText("");
        imgField.setText("");
        noteField.setText("");
    }




}
