package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import util.DataBaseConnection;

public class BankAccountDao {
	public void createAccount(long accountNumber, String accountHolderName, String email, long phoneNumber, double balance,
			String accountType, LocalDateTime dateTime)
	{
		String query="INSERT INTO BANK_ACCOUNT VALUES(?,?,?,?,?,?,?)";
		Connection connection=DataBaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setLong(1, accountNumber);
			preparedStatement.setString(2, accountHolderName);
			preparedStatement.setString(3, email);
			preparedStatement.setLong(4, phoneNumber);
			preparedStatement.setDouble(5, balance);
			preparedStatement.setString(6, accountType);
			preparedStatement.setTimestamp(7,java.sql.Timestamp.valueOf(dateTime));
			
			int result=preparedStatement.executeUpdate();
			System.out.println(result);
			if(result>0)
				System.out.println("account details stored successfully");
			else
				System.out.println("Unknown error found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
