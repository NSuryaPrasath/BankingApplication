package banking;

import java.util.HashMap;
import java.util.Scanner;

public class Admin
{
	CustomerVar cusvar=null;
	Scanner sc=new Scanner(System.in);
	int customerId=0;
	long accountNumber=23450000;
	String customerName;
	String dob;
	String city;
	long phoneNumber;
	String emailId;
	String accounttype;
	float amount;
	Homepage homepage=new Homepage();
	static HashMap<Integer,CustomerVar> customerdetail=new HashMap<Integer,CustomerVar>();
	public void init()
	{
		System.out.println("\n***** Admin Login *****");
		System.out.print("\nEnter Admin Name     => ");
		String adminName=sc.next();
		System.out.print("\nEnter Admin Password => ");
		String password=sc.next();
		if(adminName.equals("admin"))
		{
			if(password.equals("admin"))
			{
				admin();
			}
			else
			{
				System.out.println("*** Invalid Password ***");
				init();
			}
		}
		else
		{
			System.out.println("*** Invalid Admin Name ***");
			init();
		}
	}
	public void admin()
	{
		System.out.println("*** WELCOME ADMIN ***\n");
		System.out.println("\n1 --> Create New Customer Account\n2 --> View Customer Details\n3 --> View Specific Customer Details\n4 --> Delete Customer Details\n5 --> Logout\n6 --> Close");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:createAccount();break;
		case 2:displayCustomer();break;
		case 3:displaySpecificCustomer();break;
		case 4:deleteCustomerAccount();break;
		case 5:Homepage.init();break;
		case 6:System.exit(0);break;
		default:System.out.println("\n** Give Correct Input **");break;
		}
	}
	public void accountType()
	{
		//String accounttype=null;
		System.out.println("Account Type\n1 --> Saving Account\n2 --> Current Account\n");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:customerdetail.get(customerId).setAccounttype("Saving");break;
		case 2:customerdetail.get(customerId).setAccounttype("Current");break;
		default:System.out.println("Enter Correct Input ");
		accountType();break;
		}
	}
	public float amountCheck()
	{
		System.out.println("Enter the Amount");
		float amount=sc.nextInt();
		if(customerdetail.get(customerId).getAccounttype().equals("Saving"))
		{
			if(amount<1000)
			{
				System.out.println("Deposit Amount should be above Rs.1000");
				amountCheck();
			}
			else
			{
				return amount;
			}
		}
		else if(customerdetail.get(customerId).getAccounttype().equals("Current"))
		{
			if(amount<3000)
			{
				System.out.println("Deposit Amount should be above Rs.3000");
				amountCheck();
			}
			else
			{
				return amount;
			}
		}
		return 0;
	}
	public void setCustomerAccount(int customerId,long accountNumber)
	{
		cusvar=new CustomerVar();
		cusvar.setCustomerId(customerId);
		cusvar.setAccountNumber(accountNumber);
		cusvar.setCustomerName(null);
		cusvar.setDob(null);
		cusvar.setCity(null);
		cusvar.setPhoneNumber(0);
		cusvar.setEmailId(null);
		cusvar.setPassword(null);
		customerdetail.put(cusvar.getCustomerId(), cusvar);
	}
	public void createAccount()
	{
		System.out.println("*** Create New Customer Account ***\n");
		setCustomerAccount(++customerId,++accountNumber);
		System.out.print("\nEnter Customer Name => ");
		sc.next();
		String customerName=sc.nextLine();
		System.out.println("Enter Date of Birth(DD-MM-YYYY) => ");
		String dob=sc.next();
		System.out.println("Enter City => ");
		String city=sc.next();
		System.out.println("Enter Phone Number => ");
		long phoneNumber=sc.nextLong();
		System.out.println("Enter Email Id => ");
		String emailId=sc.next();
		accountType();
		System.out.println("Minimum Amount to deposit for Saving Account = Rs.1000\n");
		System.out.println("Minimum Amount to deposit for Current Account = Rs.3000\n");
		float amount=amountCheck();
		customerdetail.get(customerId).setCustomerName(customerName);
		customerdetail.get(customerId).setDob(dob);
		customerdetail.get(customerId).setCity(city);
		customerdetail.get(customerId).setPhoneNumber(phoneNumber);
		customerdetail.get(customerId).setEmailId(emailId);
		customerdetail.get(customerId).setAmount(amount);
		admin();
	}
	public void displayCustomer()
	{
		System.out.println("Account Number | DOB | Ph.NO | Mail | City | Account Type | Amount");
		for(int i=1;i<=customerdetail.size();i++)
		{
			System.out.println(customerdetail.get(i).getAccountNumber()+" "+customerdetail.get(i).getCustomerName()+" "+customerdetail.get(i).getDob()+" "+customerdetail.get(i).getPhoneNumber()+" "+customerdetail.get(i).getEmailId()+" "+customerdetail.get(i).getCity()+" "+customerdetail.get(i).getAccounttype()+" "+customerdetail.get(i).getAmount());
		}
		admin();
	}
	public void displaySpecificCustomer()
	{
		System.out.println("Enter Customer Account Number");
		long accountNumber=sc.nextLong();
		for(int i=1;i<=customerdetail.size();i++)
		{
			if(customerdetail.get(i).getAccountNumber()==accountNumber)
			{
				System.out.println("Account Number | DOB | Ph.NO | Mail | City | Account Type | Amount");
				System.out.println(customerdetail.get(i).getAccountNumber()+" "+customerdetail.get(i).getCustomerName()+" "+customerdetail.get(i).getCustomerName()+" "+customerdetail.get(i).getDob()+" "+customerdetail.get(i).getPhoneNumber()+" "+customerdetail.get(i).getEmailId()+" "+customerdetail.get(i).getCity()+" "+customerdetail.get(i).getAccounttype()+" "+customerdetail.get(i).getAmount());
				break;
			}
		}
		admin();
	}
	public void deleteCustomerAccount()
	{
		System.out.println("Enter Customer Account Number");
		long accountNumber=sc.nextLong();
		boolean check=false;
		int size=customerdetail.size();
		for(int i=1;i<=size;i++)
		{
			if(customerdetail.get(i).getAccountNumber()==accountNumber)
			{
				customerdetail.remove(i);
				check=true;
				System.out.println("Successfully Deleted");
			}
			if(check)
			{
				int k=i;
				if(customerdetail.containsKey(++k))
				{
					customerdetail.get(k).setCustomerId(i);
					accountNumber=customerdetail.get(k).getAccountNumber();
					customerName=customerdetail.get(k).getCustomerName();
					dob=customerdetail.get(k).getDob();
					city=customerdetail.get(k).getCity();
					phoneNumber=customerdetail.get(k).getPhoneNumber();
					emailId=customerdetail.get(k).getEmailId();
					accounttype=customerdetail.get(k).getAccounttype();
					amount=customerdetail.get(k).getAmount();
					cusvar.setAccountNumber(accountNumber);
					cusvar.setCustomerName(customerName);
					cusvar.setDob(dob);
					cusvar.setCity(city);
					cusvar.setPhoneNumber(phoneNumber);
					cusvar.setEmailId(emailId);
					cusvar.setAccounttype(accounttype);
					cusvar.setAmount(amount);
					customerdetail.put(i, cusvar);
					customerdetail.remove(k);
					size=customerdetail.size();
				}
			}			
		}
		admin();
	}
}
