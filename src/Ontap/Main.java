package Ontap;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

//        new LoginFrame();
        new SinhVienFrame();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });


    }
}
