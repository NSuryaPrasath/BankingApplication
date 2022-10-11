package banking;

import java.util.Scanner;

public class Homepage 
{
	static Scanner sc=new Scanner(System.in);
	
	public static void init()
	{
		Admin admin=new Admin();
		Customer customer=new Customer();
		System.out.println("********** BANKING APPLICATION **********");
		System.out.println("\n\n1 --> Admin\n2 --> Customer\n3 --> Exit");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:admin.init();break;
		case 2:customer.init();break;
		case 3:System.exit(0);break;
		default:System.out.print("\n***Warning : You have entered wrong input.***\ngive correct input\n");
		}
	}
	public static void main(String args[])
	{
		init();
	}
}
