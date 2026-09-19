package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import entity.BankAccount;
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
		finally {
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public boolean updateAccount(long accountNumber,String email,long phonenumber)
	{
		String query="UPDATE BANK_ACCOUNT SET EMAIL=? AND SET PHONENUMBER=?";
		
		Connection connection=DataBaseConnection.getConnection();
		int result=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setString(1, email);
			preparedStatement.setLong(2, phonenumber);
			
			result=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result>0?true:false;
		
	}
	public BankAccount find(long accountNumber)
	{
		String query="SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER =?";
		Connection connection=DataBaseConnection.getConnection();
		BankAccount bankAccount =null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setLong(1, accountNumber);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				long retrieved_accountNumber=resultSet.getLong(1);
				String accountHolderName=resultSet.getString(2);
				String email=resultSet.getString(3);
				long phoneNumber=resultSet.getLong(4);
				double balance=resultSet.getDouble(5);
				String accountType=resultSet.getString(6);
				LocalDateTime dateTime=resultSet.getTimestamp(7).toLocalDateTime();
				
				bankAccount=new BankAccount(retrieved_accountNumber,accountHolderName,email,
						phoneNumber,balance,accountType,dateTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bankAccount;
	}
	
	public boolean delete(long accountNumber)
	{
		int result=0;
		String query="DELETE FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER =?";
		Connection connection=DataBaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setLong(1, accountNumber);
			
			result=preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result>0?true:false;
	}
}
