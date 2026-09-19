package entity;

import java.time.LocalDateTime;

public class BankAccount {
	private long accountNumber;
	private String accountHolderName;
	private String email;
	private long phoneNumber;
	private double balance;
	private String accountType;
	private LocalDateTime dateTime;
	
	/*
	 * NO Argument constructor
	 */
	public BankAccount()
	{
		
	}
	
	//parameterized constructor
	public BankAccount(long accountNumber, String accountHolderName, String email, long phoneNumber, double balance,
			String accountType, LocalDateTime dateTime) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.accountType = accountType;
		this.dateTime = dateTime;
	}
	//Getters and Setters

	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
