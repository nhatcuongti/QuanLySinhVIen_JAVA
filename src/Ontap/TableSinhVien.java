package Ontap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableSinhVien extends JTable {
    String[] columnNames;
    String[][] rowDataStudent;
    DefaultTableModel model;
    public TableSinhVien(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        this.columnNames = (String[]) columnNames;
        this.rowDataStudent = (String[][]) rowData;

        setAutoCreateRowSorter(true);
        model = (DefaultTableModel) getModel();
    }

    void insertData(String[] rowData ){

    }


}
