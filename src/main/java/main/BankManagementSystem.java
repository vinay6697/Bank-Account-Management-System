package main;

import java.time.LocalDateTime;
import java.util.Scanner;

import entity.BankAccount;
import service.BankAccountService;

public class BankManagementSystem {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		BankAccountService service=new BankAccountService();
		String c="Y";
		String str="";
		do {
			System.out.println("Enter 1 to create an account");
			System.out.println("Enter 2 to update an account");
			System.out.println("Enter 3 to find an account");
			System.out.println("Enter 4 to delete an account");
			
			int choice=sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					System.out.println("Enter the account number");
					long accountNumber=sc.nextLong();
					
					System.out.println("Enter the account holder name");
					sc.nextLine();
					String name=sc.nextLine();
					
					
					System.out.println("Enter the email");
					String email=sc.nextLine();
					
					System.out.println("Enter the phoneNumber");
					long phoneNumber=sc.nextLong();
					
					System.out.println("Enter the balance");
					double balance=sc.nextDouble();
					sc.nextLine();
					
					System.out.println("Enter the account type");
					String accountType=sc.nextLine();
	//				sc.nextLine();
					
					LocalDateTime dateTime = LocalDateTime.now();
					
					BankAccount account=new BankAccount(accountNumber,name,email,phoneNumber,balance,accountType,dateTime);
					service.createAccount(account);
					break;
				}
				case 2:
				{
					
					break;
				}
				case 3:
				{
					System.out.println("Enter the accountNumber");
					long accountNumber=sc.nextLong();
					
					BankAccount account=service.find(accountNumber);
					if(account!=null) {
						System.out.println("Account number is:"+account.getAccountNumber());
						System.out.println("Customer name  is:"+account.getAccountHolderName());
						System.out.println("Customer email is:"+account.getEmail());
						System.out.println("Customer Phone is:"+account.getAccountNumber());
						System.out.println("Customer balance is:"+account.getBalance());
						System.out.println("account number is:"+account.getAccountType());
						System.out.println("account created on is:"+account.getDateTime());
					}
					else
					{
						System.out.println("account not found");
					}
					
					break;
				}
				case 4:
				{
					break;
				}
				default:
				{
					break;
				}
				
			}//end of switch
			
			System.out.println("do you want to repeat the \nEnter Y for YES  & N for NO");
			str=sc.next();
		}while(c.equalsIgnoreCase(str));
		
		sc.close();
		System.out.println("Connection closed successfully");
	}
}
