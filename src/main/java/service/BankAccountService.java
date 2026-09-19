package service;

import java.time.LocalDateTime;
import java.util.List;

import dao.BankAccountDao;
import entity.BankAccount;

public class BankAccountService {
	BankAccountDao dao=new BankAccountDao();
	
	public void createAccount(BankAccount account)
	{
		long accountNumber=account.getAccountNumber();
		String name=account.getAccountHolderName();
		String email=account.getEmail();
		long phoneNumber=account.getPhoneNumber();
		double balance=account.getBalance();
		String accountType=account.getAccountType();
		LocalDateTime dateTime=account.getDateTime();
		
		dao.createAccount(accountNumber,name,email,phoneNumber,balance,accountType,dateTime);
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
}
