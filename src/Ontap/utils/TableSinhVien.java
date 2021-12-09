package Ontap.utils;

import Ontap.models.ListStudent;
import Ontap.models.Student;
import Ontap.utils.DBSinhVien;

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

    public boolean insertData(String[] rowData){
        String str = listStudent.addStudent(rowData);
        if (str.equals(""))
            model.addRow(rowData);
        else{
            JOptionPane.showMessageDialog(null, str, "Fail Add", JOptionPane.CANCEL_OPTION);
            return false;
        }

        return true;
    }

    public void deleteData(String ID){
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

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    public String getEncodingFile(File file){
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            int first = bis.read();
            int second = bis.read();
            if (first == 0xFF && second== 0xFE)
                return "UTF-16LE";
            else if (first == 0xFE && second == 0xFF)
                return "UTF-16BE";
            else
                return "UTF-8";

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    public void imporDataFromFile(File file) {
        System.out.println(getFileExtension(file));
        String Extension = getFileExtension(file);
        if (Extension.equals(".csv") || Extension.equals(".txt")){
                listStudent.resetData();
                model.setRowCount(0);
                if (Extension.equals(".csv"))
                    importDataFromFileCSV(file);
                else
                    importDataFromFileTxT(file);
        }
        else JOptionPane.showMessageDialog(null,
                                    file.getName() + " is not .csv or .txt file",
                                        "Import File",
                                            JOptionPane.WARNING_MESSAGE);

    }

    private void importDataFromFileCSV(File file){
        BufferedReader br = null;
        try {
            String charset = getEncodingFile(file);
            System.out.println(charset);
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file),charset));

            br.readLine();

            while (true){
                String csvStr = br.readLine();
                if (csvStr == null)
                    break;

                Student newStudent = new Student(csvStr);
                String ID = newStudent.getID();
                String Name = newStudent.getName();
                String GPA = String.valueOf(newStudent.getGPA());
                String Img = newStudent.getImage();
                String Address = newStudent.getAddress();
                String Note = newStudent.getNotes();

                String[] newRow = {ID, Name, GPA, Address, Img, Note};
                if (!insertData(newRow)){
                    JOptionPane.showMessageDialog(null, "Data is not valid", "Import Data", JOptionPane.INFORMATION_MESSAGE);
                    br.close();
                    return;
                };
            }
            br.close();
            JOptionPane.showMessageDialog(null, "Import Successfully", "Import Data", JOptionPane.INFORMATION_MESSAGE);


        }  catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Data on file is not valid", "Import Data", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }

    }

    private void importDataFromFileTxT(File file) {
        BufferedReader br = null;
        try {
            String charset = getEncodingFile(file);
            System.out.println(charset);
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file),charset));

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

                if (!insertData(newRow)){
                    JOptionPane.showMessageDialog(null, "Data is not valid", "Import Data", JOptionPane.INFORMATION_MESSAGE);
                    br.close();
                    return;
                };
            }
            br.close();
            JOptionPane.showMessageDialog(null, "Import Successfully", "Import Data", JOptionPane.INFORMATION_MESSAGE);

        }  catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Data on file is not valid", "Import Data", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    public void exportDataFile(File file) {
        String Extension = getFileExtension(file);
        if (Extension.equals(".csv") || Extension.equals(".txt")) {
            try {
                if (Extension.equals(".csv"))
                    listStudent.writeStudentToFileCSV(file);
                else
                    listStudent.writeStudentToFileTXT(file);

                JOptionPane.showMessageDialog(null, "Export Successfully", "Export Data", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, file.getName() + " is not .csv or .txt file", "Export File", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void exportDatabase(DBSinhVien dbSinhVien) {
        listStudent.exportDataToDatabase(dbSinhVien);
    }
}
