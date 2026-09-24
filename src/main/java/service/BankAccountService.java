package service;

import java.util.List;

import dao.BankAccountDao;
import entity.BankAccount;

public class BankAccountService {
	BankAccountDao dao=new BankAccountDao();
	
	public boolean createAccount(BankAccount account)
	{
		if(account==null)
		{
			System.out.println("invalid account");
		return false;
		}
		
		if(account.getBalance()<0)
		{
			System.out.println("balance cannot be negative");
			return false;
		}
		
		if(account.getAccountHolderName()==null || account.getAccountHolderName().isBlank() )
		{
			System.out.println("Account holder name is required");
			return false;
		}
		
		return dao.createAccount(account);
	}
	
	public BankAccount find(long accountNumber)
	{
		return dao.find(accountNumber);
	}
	
	public boolean deleteAccount(long accountNumber) {
		return dao.delete(accountNumber);
	}
	
	public boolean updateAccount(long accountNumber,String email,long phoneNumber)
	{
		return dao.updateAccount(accountNumber,email, phoneNumber);
	}
	
	public List<BankAccount> findAllAccounts()
	{
		return dao.findAllAccounts();
	}
	
	public boolean deposite(long accountNumber,double amount)
	{
		if(accountNumber==0 || amount<0)
			return false;
		else
			return dao.deposit(accountNumber, amount);
	}
	
	public double checkBalance(long accountNumber)
	{
		if(accountNumber==0)
			return 0.0;
		else
			return dao.checkBalance(accountNumber);
	}
	
	public boolean withdraw(long accountNumber,double amount)
	{
		boolean flag=false;
		if(accountNumber!=0 && amount>0)
			flag=dao.withdraw(accountNumber, amount);
		else
			System.out.println("accountDetails should be correct");
		
		return flag;
	}
	
	public boolean transfer(long senderAccountNumber,double senderMoney,long receiverAccountNumber)
	{
		if(senderAccountNumber!=0  && senderMoney>0 && receiverAccountNumber!=0)
			return dao.transfer(senderAccountNumber, senderMoney, receiverAccountNumber);
		else
			return false;
	}
}
