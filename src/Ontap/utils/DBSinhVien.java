package Ontap.utils;

import javax.lang.model.element.Name;
import java.sql.*;

/**
 * @author : Ti
 * @mailto : nhatcuongti@mail.com
 * @created : 9:53 PM, 12/7/2021, Tuesday,
 * @Comment :
 **/
public class DBSinhVien {
    String user, password, databaseName;
    Connection connection = null;

    public DBSinhVien(String user, String password, String databaseName) {
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;


    }

    public boolean isValid(){
        String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName;
        try {
            connection = DriverManager.getConnection(URL, user, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection(){
        return connection;
    }


    public void insertData(String ID, String name, double GPA, String address, String note, String img) {
        try {
            PreparedStatement cs=connection.prepareStatement("exec insertData ?,?,?,?,?,?");


            cs.setString(1, ID);

            cs.setString(2, name);

            cs.setFloat(3, (float) GPA);

            cs.setString(4, address);

            cs.setString(5, note);

            cs.setString(6, img);

            cs.execute();
            System.out.println("Process database succsesfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetData(){
        Statement st = null;
        try {
            st = connection.createStatement();
            String strSQL = "Delete Student";
            int value = st.executeUpdate(strSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
