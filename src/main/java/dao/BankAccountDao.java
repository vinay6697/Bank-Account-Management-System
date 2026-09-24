package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.BankAccount;
import util.DataBaseConnection;

public class BankAccountDao {
	
public boolean createAccount(BankAccount account)
	{
	
	long accountNumber=account.getAccountNumber();
	String accountHolderName=account.getAccountHolderName();
	String email=account.getEmail();
	long phoneNumber=account.getPhoneNumber();
	double balance=account.getBalance();
	String accountType=account.getAccountType();
	LocalDateTime dateTime=account.getDateTime();
	
		String query="INSERT INTO BANK_ACCOUNT VALUES(?,?,?,?,?,?,?)";
		Connection connection=DataBaseConnection.getConnection();
		int result=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setLong(1, accountNumber);
			preparedStatement.setString(2, accountHolderName);
			preparedStatement.setString(3, email);
			preparedStatement.setLong(4, phoneNumber);
			preparedStatement.setDouble(5, balance);
			preparedStatement.setString(6, accountType);
			preparedStatement.setTimestamp(7,java.sql.Timestamp.valueOf(dateTime));
			
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
	
	public boolean updateAccount(long accountNumber,String email,long phoneNumber)
	{
		String query="UPDATE BANK_ACCOUNT SET EMAIL=?,PHONE_NUMBER=? WHERE ACCOUNT_NUMBER=?";
		
		Connection connection=DataBaseConnection.getConnection();
		int result=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setString(1, email);
			preparedStatement.setLong(2, phoneNumber);
			preparedStatement.setLong(3, accountNumber);
			
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

	public List<BankAccount> findAllAccounts()
	{
		String query="SELECT * FROM BANK_ACCOUNT ORDER BY ACCOUNT_NUMBER ASC";
		Connection connection=DataBaseConnection.getConnection();
		
		List<BankAccount> bankAccounts=new ArrayList<>();
		
		BankAccount account=new BankAccount();
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				long accountNumber=resultSet.getLong(1);
				String name=resultSet.getString(2);
				String email=resultSet.getString(3);
				long phoneNumber=resultSet.getLong(4);
				long balance=resultSet.getLong(5);
				String accountType=resultSet.getString(6);
				LocalDateTime dateTime=resultSet.getTimestamp(7).toLocalDateTime();
				
				account=new BankAccount(accountNumber,name,email,phoneNumber,balance,accountType,dateTime);
				
				bankAccounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bankAccounts.size()!=0?bankAccounts:null;
	}

	public boolean deposit(long accountNumber,double amount)
	{
		String selectQuery="SELECT BALANCE FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER=?";
		Connection connection=DataBaseConnection.getConnection();
		int result=0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			double balance=0;
			while(resultSet.next())
			{
				balance=resultSet.getDouble(1);
			}
			
			balance+=amount;
			
			String updateQuery="UPDATE BANK_ACCOUNT SET BALANCE=? WHERE ACCOUNT_NUMBER=?";
			PreparedStatement prepareStatement1=connection.prepareStatement(updateQuery);
			prepareStatement1.setDouble(1, balance);
			prepareStatement1.setDouble(2, accountNumber);
			
			result=prepareStatement1.executeUpdate();
			
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
	
	public double checkBalance(long accountNumber)
	{
		System.out.println(accountNumber);
		String query="SELECT BALANCE FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER=?";
		
		Connection connection=DataBaseConnection.getConnection();
		double balance=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				balance=resultSet.getDouble(1);
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
		return balance>0.0?balance:0.0;
	}
	
	public boolean withdraw(long accountNumber,double amount)
	{
		String query="SELECT BALANCE FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER=?";
		Connection connection=DataBaseConnection.getConnection();
		
		double balance=0.0;
		int result=0;
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setLong(1, accountNumber);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				balance=resultSet.getDouble(1);
			}
			
			if(amount<balance)
			{
				balance-=amount;
				
				String balanceUpdate="UPDATE BANK_ACCOUNT SET BALANCE=? WHERE ACCOUNT_NUMBER=?";
				PreparedStatement preparedStatement1=connection.prepareStatement(balanceUpdate);
				
				preparedStatement1.setDouble(1, balance);
				preparedStatement1.setLong(2, accountNumber);
				result=preparedStatement1.executeUpdate();
			}
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
	
	public boolean transfer(long senderAccountNumber,double senderMoney,
			long receiverAccountNumber)
	{
		double sendersBalance=0.0;
		double receiversBalance=0.0;
		
		Connection connection=DataBaseConnection.getConnection();
		
		String sendersQuery="SELECT BALANCE FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER=?";
		String recieverQuery="SELECT BALANCE FROM BANK_ACCOUNT WHERE ACCOUNT_NUMBER=?";
		int result1=0;
		int result2=0;
		try {
			
			//fetching the accountNumber sender balance
			PreparedStatement preparedStatement1=connection.prepareStatement(sendersQuery);
			preparedStatement1.setLong(1, senderAccountNumber);
			
			ResultSet senderResultSet=preparedStatement1.executeQuery();
			
			while(senderResultSet.next()) {
				sendersBalance=senderResultSet.getDouble(1);
			}
			
			//fetching the accountNumber receiver balance
			PreparedStatement preparedStatement2=connection.prepareStatement(recieverQuery);
			preparedStatement2.setLong(1, receiverAccountNumber);
			
			ResultSet receiversResultSet=preparedStatement2.executeQuery();
			
			while(receiversResultSet.next())
			{
				receiversBalance=receiversResultSet.getDouble(1);
			}
			
			System.out.println("sender balance:"+sendersBalance);
			System.out.println("receiver balance: "+receiversBalance);
			//checking the balance
			if(senderMoney<=sendersBalance)
				sendersBalance=(sendersBalance-senderMoney);

			System.out.println("senders balance:"+sendersBalance);
			
			//updating the sender balance after transaction successful
			String senderUpdateQuery="UPDATE BANK_ACCOUNT SET BALANCE=? WHERE ACCOUNT_NUMBER=?";
			PreparedStatement preparedStatement3=connection.prepareStatement(senderUpdateQuery);
			
			preparedStatement3.setDouble(1, sendersBalance);
			preparedStatement3.setLong(2, senderAccountNumber);
			
			result1=preparedStatement3.executeUpdate();
			
			receiversBalance=receiversBalance+senderMoney;
			
			System.out.println("receiver balance:"+receiversBalance);
			
			//updating the receivers balance after transaction successful
			String receiverUpdateQuery="UPDATE BANK_ACCOUNT SET BALANCE=? WHERE ACCOUNT_NUMBER=?";
			
			PreparedStatement preparedStatement4=connection.prepareStatement(receiverUpdateQuery);
			preparedStatement4.setDouble(1, receiversBalance);
			preparedStatement4.setLong(2, receiverAccountNumber);
			
			result2=preparedStatement4.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result1>0 && result2>0?true:false;
	}
}
