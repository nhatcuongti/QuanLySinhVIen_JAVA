package Ontap;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel{
    JButton Add = new JButton("Add");
    JButton Update = new JButton("Update");
    JButton Delete = new JButton("Delete");
    JButton Export = new JButton("Export");
    JButton Import = new JButton("Import");
    ButtonPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        //Button "Add"
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(Add, gbc);

        //Button "Update"
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(Update, gbc);

        //Button "Delete"
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(Delete, gbc);

        //Button "Export"
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.gridwidth = 3;
        add(Export, gbc);

        //Button "Import"
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(Import, gbc);
    }
}
