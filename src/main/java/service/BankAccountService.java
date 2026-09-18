package service;

import java.time.LocalDateTime;

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
}
