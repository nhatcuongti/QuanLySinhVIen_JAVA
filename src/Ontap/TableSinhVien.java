package Ontap;

import Ontap.models.ListStudent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class TableSinhVien extends JTable {
    DefaultTableModel model;
    ListStudent listStudent = new ListStudent();

    public TableSinhVien(TableModel dm) {
        super(dm);
        setAutoCreateRowSorter(true);
        model = (DefaultTableModel) getModel();

    }

    boolean isValidData(){
        return true;
    }

    boolean insertData(String[] rowData ){
        String str = listStudent.addStudent(rowData);
        if (str.equals(""))
            model.addRow(rowData);
        else{
            JOptionPane.showMessageDialog(null, str, "Fail Add", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    void deleteData(){
        if (getSelectedRowCount() == 1) {
            model.removeRow(getSelectedRow());

            JOptionPane.showMessageDialog(null, "Delete Successfully", "Delete Action", JOptionPane.INFORMATION_MESSAGE);

        }
        else if (getRowCount() == 0)
            JOptionPane.showMessageDialog(null, "Your table is empty !!", "Fail Delete", JOptionPane.INFORMATION_MESSAGE);
        else if (getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "You doesn't choose row !!", "Fail Delete", JOptionPane.CANCEL_OPTION);
    }

    public String[] getSelectedData(){
        String[] data = {"", "", "", "", "", ""};
        int row = getSelectedRow();

        for (int i = 0; i < 6; i++)
            data[i] = (String) model.getValueAt(row, i);

        return data;
    }


    public void updateData(String[] data) {
        if (getSelectedRowCount() == 1) {
            for (int i=0; i < data.length; i++)
                model.setValueAt(data[i], getSelectedRow(), i);

            JOptionPane.showMessageDialog(null, "Update Successfully", "Update Method", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (getRowCount() == 0)
            JOptionPane.showMessageDialog(null, "Your table is empty !!", "Fail Delete", JOptionPane.CANCEL_OPTION);
        else if (getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "You doesn't choose row !!", "Fail Delete", JOptionPane.CANCEL_OPTION);

    }
}
