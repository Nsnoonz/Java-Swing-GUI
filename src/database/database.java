package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
	
	public static Connection connect = null;
	public static Statement s = null;
	
//	request.setCharacterEncoding("UTF-8");
//	response.setContentType("text/html; charset=UTF-8");
//	response.setCharacterEncoding("UTF-8");

	public static void db() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String Connection = "jdbc:mysql://localhost/62011270006?useUnicode=true&amp;characterEncoding=UTF-8";
			connect = DriverManager.getConnection(Connection, "root", "");
			
			if (connect != null) {
				//System.out.println("Database Connected.");
			} else {
				System.out.println("Database Connect Failed.");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int add(int ProductID, String ProductName, int price ,int qty ) {
		try {
			s = connect.createStatement();
			String sql = "INSERT INTO product (productID,productName,Price,Qty) "
					+ "VALUES('"+ProductID+"','"+ProductName+"','"+price+"','"+qty+"')";
			s.execute(sql);
			
			return 1;
		} catch (Exception e) {
			return 0 ;
		}
	}
	public static void close() {
		// Close
		try {
			if (connect != null) {
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
