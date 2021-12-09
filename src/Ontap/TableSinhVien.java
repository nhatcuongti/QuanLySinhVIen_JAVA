package Ontap;

import Ontap.models.ListStudent;
import Ontap.models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;

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
            JOptionPane.showMessageDialog(null, str, "Fail Add", JOptionPane.CANCEL_OPTION);
            return false;
        }

        return true;
    }

    void deleteData(String ID){
        if (getSelectedRowCount() == 1) {
            model.removeRow(getSelectedRow());
            listStudent.deleteStudent(ID);


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


    public boolean updateData(String[] data) {
        if (getSelectedRowCount() == 1) {
            String ID = (String) model.getValueAt(getSelectedRow(), 0);
            String str = listStudent.updateStudent(data, ID);
            if (str.equals("")){
                for (int i=0; i < data.length; i++)
                    model.setValueAt(data[i], getSelectedRow(), i);

                JOptionPane.showMessageDialog(null, "Update Successfully", "Update Method", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }


            JOptionPane.showMessageDialog(null, str, "Fail Update", JOptionPane.CANCEL_OPTION);
        }
        else if (getRowCount() == 0)
            JOptionPane.showMessageDialog(null, "Your table is empty !!", "Fail Delete", JOptionPane.CANCEL_OPTION);
        else if (getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "You doesn't choose row !!", "Fail Delete", JOptionPane.CANCEL_OPTION);

        return false;
    }

    public void imporDataFromFile(File file) {
        System.out.println(file);
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file),"UTF-8"));

            while (true){
                String ID = br.readLine();
                System.out.println(ID);
                if (null == ID)
                    break;

                String Name = br.readLine();
                String GPA = br.readLine();
                String Img = br.readLine();
                String Address = br.readLine();
                String Note = br.readLine();
                br.readLine();

                String[] newRow = {ID, Name, GPA, Address, Img, Note};
                insertData(newRow);

            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportDataFile(File file) {
        try {
            listStudent.writeStudentToFileCSV(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
