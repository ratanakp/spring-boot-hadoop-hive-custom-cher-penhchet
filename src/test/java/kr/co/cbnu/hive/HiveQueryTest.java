package kr.co.cbnu.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveQueryTest {
	
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String args[]) throws SQLException {

//		java.sql.Driver driver = new HiveDriver();
//		SimpleDriverDataSource ds = new SimpleDriverDataSource(driver, "jdbc:hive2://192.168.131.145/weather:10000");
//		JdbcTemplate hiveTemplate = new JdbcTemplate(ds);
//		System.out.println(hiveTemplate.queryForList("show tables"));
//		System.out.println("Done");
		
		 try {
		      Class.forName(driverName);
		    } catch (ClassNotFoundException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.exit(1);
		    }
		    Connection con = DriverManager.getConnection("jdbc:hive2://192.168.131.145:10000/weather_db", "hadoop", "123456");
		    
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT * FROM weather LIMIT 1000");
		    while(rs.next()){
		    	System.out.println(rs.getString(1) + " " + rs.getString(2));
		    }
	}
}
