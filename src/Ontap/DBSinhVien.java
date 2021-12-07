package Ontap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    boolean isValid(){
        String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName;
        try {
            connection = DriverManager.getConnection(URL, user, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }




}
