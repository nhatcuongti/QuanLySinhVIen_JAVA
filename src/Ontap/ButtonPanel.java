package Ontap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel  implements ActionListener{
    JButton Add = new JButton("Add");
    JButton Update = new JButton("Update");
    JButton Delete = new JButton("Delete");
    JButton Clear = new JButton("Clear");
    JButton Export = new JButton("Export");
    JButton Import = new JButton("Import");

    InformationPanel informationPanel;
    TableSinhVien tbSV;

    ButtonPanel(InformationPanel informationPanel, TableSinhVien tbSV){
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

        //Button "Import"
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        add(Import, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Delete.equals(e.getSource())) {
            tbSV.deleteData();
            informationPanel.resetData();
        }
        else if (Add.equals(e.getSource())) {
            if (informationPanel.checkFilled()) {
                tbSV.insertData(informationPanel.getData());

                informationPanel.resetData();
            }
        }
        else if (Update.equals(e.getSource())) {
                tbSV.updateData(informationPanel.getData());
                informationPanel.resetData();
        }
        else if (Clear.equals(e.getSource())) {
            informationPanel.resetData();
        }

    }
}
