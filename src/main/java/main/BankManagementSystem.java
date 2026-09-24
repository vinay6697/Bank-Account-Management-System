package main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import entity.BankAccount;
import service.BankAccountService;

public class BankManagementSystem {
	public static void main(String[] args) {System.out.println();
		Scanner sc=new Scanner(System.in);
		BankAccountService service=new BankAccountService();
		String c="Y";
		String str="";
		do {
			System.out.println("Enter 1 to create an account");
			System.out.println("Enter 2 to update an account");
			System.out.println("Enter 3 to find an account");
			System.out.println("Enter 4 to delete an account");
			System.out.println("Enter 5 to find all the accounts");
			System.out.println("Enter 6 to deposit the money into account");
			System.out.println("Enter 7 to check balance");
			System.out.println("Enter 8 to withdraw money");
			System.out.println("Enter 9 to transfer the money");
			
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
					
					LocalDateTime dateTime = LocalDateTime.now();
					
					BankAccount account=new BankAccount(accountNumber,name,email,phoneNumber,balance,accountType,dateTime);
					boolean result=service.createAccount(account);
					if(result)
						System.out.println("Account Created Successfully");
					else
						System.out.println("Account creatin error");
					break;
				}
				case 2:
				{
					System.out.println("Enter the account id");
					long accountNumber=sc.nextLong();
					sc.nextLine();
					
					System.out.println("Enter the email");
					String email=sc.nextLine();
					
					System.out.println("Enter the phoneNumber");
					long phoneNumber=sc.nextLong();
					
					boolean result=service.updateAccount(accountNumber, email, phoneNumber);
					
					if(result)
						System.out.println("account details updated successfully");
					else
						System.out.println("details not updated ");
					break;
				}
				case 3:
				{
					System.out.println("Enter the accountNumber");
					long accountNumber=sc.nextLong();
					
					BankAccount account=service.find(accountNumber);
					if(account!=null) {
						System.out.println("Account number is \t:"+account.getAccountNumber());
						System.out.println("Customer name  is \t:"+account.getAccountHolderName());
						System.out.println("Customer email is \t:"+account.getEmail());
						System.out.println("Customer Phone is \t:"+account.getAccountNumber());
						System.out.println("Customer balance is \t:"+account.getBalance());
						System.out.println("account number is \t:"+account.getAccountType());
						System.out.println("account created on is \t:"+account.getDateTime());
					}
					else
					{
						System.out.println("account not found");
					}
					
					break;
				}
				case 4:
				{
					System.out.println("Enter the accountNumber");
					long accountNumber=sc.nextLong();
					boolean result=service.deleteAccount(accountNumber);
					if(result)
						System.out.println("account deleted successfully");
					else
						System.out.println("Error occured account not deleted");
					break;
				}
				case 5:
				{
					List<BankAccount> bankAccounts=service.findAllAccounts();
					if(bankAccounts!=null)
					{
						for(BankAccount account:bankAccounts)
						{
							System.out.println("Account number is \t:"+account.getAccountNumber());
							System.out.println("Customer name  is \t:"+account.getAccountHolderName());
							System.out.println("Customer email is \t:"+account.getEmail());
							System.out.println("Customer Phone is \t:"+account.getAccountNumber());
							System.out.println("Customer balance is \t:"+account.getBalance());
							System.out.println("account number is \t:"+account.getAccountType());
							System.out.println("account created on is \t:"+account.getDateTime());
							System.out.println("---------------------------------------------");
						}
					}
					else
					{
						System.out.println("unable to fetch all the accounts");
					}
					break;
				}
				case 6:
				{
					System.out.println("Enter the accountNumber");
					long accountNumber=sc.nextLong();
					System.out.println("Enter the amount");
					double amount=sc.nextDouble();
					
					boolean result=service.deposite(accountNumber, amount);
					if(result)
						System.out.println("amount deposited successfully");
					else
						System.out.println("error occured deposite failed");
					break;
				}
				case 7:
				{
					System.out.println("Enter the accountNumber");
					long accountNumber=sc.nextLong();
					
					double balance=service.checkBalance(accountNumber);
					if(balance>0.0)
						System.out.println("balance is: "+balance);
					else
						System.out.println("unable to fetch the balance");
					break;
				}
				case 8:{
					System.out.println("Enter the accountNumber");
					long accountNumber=sc.nextLong();
					
					System.out.println("Enter the amount to be withdrawn");
					double amount=sc.nextDouble();
					
					boolean result=service.withdraw(accountNumber, amount);
					if(result)
						System.out.println("amount withdrawn successfully");
					else
						System.out.println("unable to withdraw the amount");
					break;
				}
				case 9:
				{
					System.out.println("Enter the sender accountNumber");
					long senderAccountNumber=sc.nextLong();
					
					System.out.println("Enter amount to transer");
					double amount=sc.nextDouble();
					
					System.out.println("Enter the reciever accountNumber");
					long receiverAccountNumber=sc.nextLong();
					
					
					if(service.transfer(senderAccountNumber, amount, receiverAccountNumber))
							System.out.println("amount transfered successfully");
					else
						System.out.println("Transaction error occured");
					
					break;
				}
				default:
				{
					System.out.println("Invalid input \nplease enter the valid input");
					break;
				}
				
			}//end of switch
			
			System.out.println("Do you want to repeat the \nEnter Y for YES  & N for NO");
			str=sc.next();
		}while(c.equalsIgnoreCase(str));
		
		sc.close();
		System.out.println("Connection closed successfully");
	}
}
