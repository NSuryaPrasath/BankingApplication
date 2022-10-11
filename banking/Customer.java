package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Customer
{
	Scanner sc=new Scanner(System.in);
	long transactionId=342902020000L;
	Admin admin=new Admin();
	CustomerVar cusvar=null;
	int k=0;
	ArrayList<CustomerVar> transaction=new ArrayList<CustomerVar>();
	HashMap<Integer,ArrayList<CustomerVar>> transactiondetail=new HashMap<Integer,ArrayList<CustomerVar>>();
	public void init()
	{
		System.out.println("\n1 --> Signup\n2 --> Login");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:signup();break;
		case 2:login();break;
		}
	}
	public void signup()
	{
		System.out.println("*** Customer Signup ***");
		System.out.println("Enter your Account Number => ");
		long accountNumber=sc.nextLong();
		for(int i=1;i<=admin.customerdetail.size();i++)
		{
			if(admin.customerdetail.get(i).getAccountNumber()==accountNumber)
			{
				System.out.println("*** Create a Password ***");
				System.out.println("Enter the Password => ");
				String password=sc.next();
				System.out.println("Enter Again the Password => ");
				String password2=sc.next();
				if(password.equals(password2))
				{
					admin.customerdetail.get(i).setPassword(password);
					System.out.println("Successfully Created");
					login();
				}
			}
		}
	}
	public void createTransaction(int CustomerId)
	{
		cusvar=new CustomerVar();
		//cusvar.setCustomerId(CustomerId);
		cusvar.setTransactionId(0);
		cusvar.setTransactionType(null);
		cusvar.setAmount(0);
		transaction.add(k,cusvar);
		transactiondetail.put(CustomerId, transaction);
	}
	public void login()
	{
		System.out.println("*** Login ***");
		System.out.println("Enter Your account Number => ");
		long accountNumber=sc.nextLong();
		System.out.println("Enter the Password => ");
		String password=sc.next();
		for(int i=1;i<=admin.customerdetail.size();i++)
		{
			if(admin.customerdetail.get(i).getAccountNumber()==accountNumber)
			{
				if(admin.customerdetail.get(i).getPassword().equals(password))
				{
					//createTransaction(admin.customerdetail.get(i).getCustomerId());
					customerPage(accountNumber);
				}
			}
		}
	}
	public void customerPage(long accountNumber)
	{
		for(int i=1;i<=admin.customerdetail.size();i++)
		{
			if(admin.customerdetail.get(i).getAccountNumber()==accountNumber)
			{
				System.out.println("*** Welcome "+admin.customerdetail.get(i).getCustomerName());
				System.out.println("\n\n\n1 --> Depost\n2 --> WithDraw\n3 --> Transaction Detail\n");
				int choice = sc.nextInt();
				switch(choice)
				{
				case 1:deposit(i,accountNumber);break;
				case 2:withDraw(i,accountNumber);break;
				case 3:transaction(i,accountNumber);break;
			    default:System.out.println("Enter correct input");break; 
				}
			}
		}
	}
	public void deposit(int i,long accountNumber)
	{
		String transactionType="Deposit";
		System.out.println("Enter the Deposit Amount => ");
		int amount=sc.nextInt();
		admin.customerdetail.get(i).setAmount(amount+admin.customerdetail.get(i).getAmount());
		System.out.println("Deposited Successfully");
		createTransaction(admin.customerdetail.get(i).getCustomerId());
		transaction.get(k).setTransactionId(transactionId++);
		transaction.get(k).setTransactionType(transactionType);
		transaction.get(k).setTransactionAmount(amount);
		transactiondetail.put(admin.customerdetail.get(i).getCustomerId(), transaction);
		k++;
		customerPage(accountNumber);
		
	}
	public void withDraw(int i,long accountNumber)
	{
		String transactionType="WithDrawn";
		System.out.println("Enter the Withdrawl Amount => ");
		int amount=sc.nextInt();
		admin.customerdetail.get(i).setAmount(admin.customerdetail.get(i).getAmount()-amount);
		System.out.println("Withdrawned Successfully");
		createTransaction(admin.customerdetail.get(i).getCustomerId());
		transaction.get(k).setTransactionId(transactionId++);
		transaction.get(k).setTransactionType(transactionType);
		transaction.get(k).setTransactionAmount(amount);
		transactiondetail.put(admin.customerdetail.get(i).getCustomerId(), transaction);
		k++;
		customerPage(accountNumber);
	}
	public void transaction(int i,long accountNumber)
	{
		for(int j=0;j<transaction.size();j++)
		{
			System.out.println(transactiondetail.get(i).get(j).getTransactionId()+" "+transactiondetail.get(i).get(j).getTransactionType()+" "+transactiondetail.get(i).get(j).getTransactionAmount());
		}
		System.out.println("Total => \t\t\t"+admin.customerdetail.get(i).getAmount());
		customerPage(accountNumber);
	}
}
