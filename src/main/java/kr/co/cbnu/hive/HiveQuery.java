package kr.co.cbnu.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class HiveQuery {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String args[]) throws SQLException {

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
//        Connection con = DriverManager.getConnection("jdbc:hive2://192.168.131.145:10000/weather_db", "hadoop", "123456");
        Connection con = DriverManager.getConnection("jdbc:hive2://133.186.162.214:10000/nak_test", "", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tb_test");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }
    }
}
