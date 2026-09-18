package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private static final String url="jdbc:postgresql://localhost:5432/BankDatabase";
	private static final String userName="postgres";
	private static final String password="root";
	
	public static Connection getConnection()
	{
		try {
			Class.forName("org.postgresql.Driver");
			
			return DriverManager.getConnection(url,userName,password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
