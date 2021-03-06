package Ontap.panels;

import Ontap.utils.TableSinhVien;
import Ontap.utils.DBSinhVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ButtonPanel extends JPanel  implements ActionListener{
    JButton Add = new JButton("Add");
    JButton Update = new JButton("Update");
    JButton Delete = new JButton("Delete");
    JButton Clear = new JButton("Clear");
    JButton Export = new JButton("Export");
    JButton Import = new JButton("Import");
    JButton exportDatabase = new JButton("Export to database");

    InformationPanel informationPanel;
    TableSinhVien tbSV;
    DBSinhVien dbSinhVien;

    public ButtonPanel(InformationPanel informationPanel, TableSinhVien tbSV, DBSinhVien db){
        dbSinhVien = db;
        this.informationPanel = informationPanel;
        this.tbSV = tbSV;

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
        Add.addActionListener(this);

        //Button "Update"
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(Update, gbc);
        Update.addActionListener(this);

        //Button "Delete"
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(Delete, gbc);
        Delete.addActionListener(this);

        //Button "Clear"
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(Clear, gbc);
        Clear.addActionListener(this);

        //Button "Export"
        gbc.gridx = 0;
        gbc.gridy = 2;

        gbc.gridwidth = 3;
        add(Export, gbc);
        Export.addActionListener(this);

        //Button "Import"
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        add(Import, gbc);
        Import.addActionListener(this);

        //Button "Import"
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(exportDatabase, gbc);
        exportDatabase.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (Delete.equals(e.getSource())) {
            tbSV.deleteData(informationPanel.getData()[0]);
            informationPanel.resetData();
        }
        else if (Add.equals(e.getSource())) {
            if (informationPanel.checkFilled()) {
                boolean isSuccessfull =  tbSV.insertData(informationPanel.getData());

                if (isSuccessfull)
                    informationPanel.resetData();
            }
        }
        else if (Update.equals(e.getSource())) {
            boolean isSuccessfull = tbSV.updateData(informationPanel.getData());

            if (isSuccessfull)
                informationPanel.resetData();
        }
        else if (Clear.equals(e.getSource())) {
            informationPanel.resetData();
        }
        else if(Import.equals(e.getSource())){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                tbSV.imporDataFromFile(file);
            }
        }
        else if(Export.equals(e.getSource())){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                tbSV.exportDataFile(file);
            }
        }
        else if (exportDatabase.equals(e.getSource())){
            dbSinhVien.resetData();
            tbSV.exportDatabase(dbSinhVien);

        }



    }
}
