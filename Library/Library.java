/**
 * Library class: 
 * The library can buy new books for its collection, and people (Person class) are able to check books out, 
 * or if they're unavailable, put them on hold.
 * 
 * Bashir Kadri
 * Jan 11, 2022
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Library 
{
	ArrayList<Person> people;
	ArrayList<Media> collection;
	String name;
	String address;
	static int totalUsers = 0;
	
	//open Scanner
	Scanner input = new Scanner(System.in);
	
	public Library()
	{
		//add FileIO later
		this.people = new ArrayList<Person>();
		this.collection = new ArrayList<Media>();
		this.name = "TBD";
		this.address = "TBD";
	}
	public Library(String name, String address)
	{
		this.people = new ArrayList<Person>();
		this.collection = new ArrayList<Media>();
		this.name = name;
		this.address = address;
	}
	
	public void printCollection()
	{
		//just print all the titles of all the items
		for (Media m : this.collection)
		{
			System.out.println(m.title);
		}
	}
	public void checkOut(Media m, Person p)
	{
		//**we can discuss this later, but this is how I think the program will work, tell me if we want to do it differently
		String response = "";
		
		//Check if the current Media is available or not
		if (m.availability) //is available
		{
			//If available, check if the Person has enough room to borrow the book
			if (p.possessions.size() < p.MAX_POSSESSIONS)
			{
				//ask user if they want to check it out
				do
				{
					System.out.printf("%s is available, would you like to borrow this item?\n", m.title);
					response = input.nextLine();
					
					//formatting stuff
					response.toLowerCase(); 
					response.trim();
				}
				while (response == "yes" || response == "no");
				
				//and then finally, if they answer yes, add to their checked out items and change the Media's availability
				if (response == "yes")
				{
					System.out.printf("You have succesfully checked out %s.\n", m.title);
					p.possessions.add(m);
					m.availability = false;
				}
				else if (response == "no") //nothing changes, give them some message
				{
					System.out.printf("%s has not been checked out.\n", m.title);
				}
				
			}
			else //item is available, but their "cart" is full, give some message
			{
				System.out.println("Sorry, you have too many items currently checked out. Please try again another time.");
			}
		}
		else //not available, put Person in queue
		{
			//tell user Media is not available, then ask if they want to put on hold
			//ask user if they want to check it out
			do
			{
				System.out.printf("Sorry, %s is not available, would you like to put this item on hold?\n", m.title);
				response = input.nextLine();
				
				//formatting stuff
				response.toLowerCase(); 
				response.trim();
			}
			while (response == "yes" || response == "no");
			
			//and then finally, if they answer yes, add them to queue, if no, do nothing
			if (response == "yes")
			{
				System.out.printf("You have succesfully been added to the queue for %s.\n", m.title);
				m.hold.enQueue(p);
			}
			else if (response == "no") //nothing changes, give them some message
			{
				System.out.printf("%s has not been added to the queue.\n", m.title);
			}
		}
		
	}
	public void returnBook(Media m, Person p)
	{
		//this should be simpler, to return a book just change availability, 
		//"notify" next person in queue if there is anyone (just do this in console for now) 
		//and change Person's "cart"
		
		//first change availability
		m.availability = true;
		
		//then notify next person in queue
		if (!(m.hold.isEmpty())) //ie. is NOT empty
		{
			System.out.printf("%s is next to pick up the book!\n", m.hold.deQueue().name); //doing it this way removes them from the queue & returns name at the same time
		}
		
		//and finally, edit Person's cart
		p.possessions.remove(m);
	}
	

	public Person findPerson(int target)
	{
		for (int i = 0; i < this.people.size(); i++)
		{
			if (this.people.get(i).cardNum == target)
			{
				return this.people.get(i);
			}
		}
		return null; //no match found
	}
	
	public static void main(String[] args)
	{
		 
		Scanner sc = new Scanner(System.in);
		
		Library JMPL = new Library("John McCrae Public Library", "123 Internet Road");
		
		while (true) 
		{
			
			System.out.print("\nWould you like to borrow/return items to a library? (y/n) ");
			
			String response = sc.nextLine();
			
			if (response.toLowerCase().equals("y")) 
			{
				
				Person person;
				
				System.out.print("\nDo you have a library card? (y/n) ");
				
				response = sc.nextLine();
				
				if (response.toLowerCase().equals("y")) 
				{
					
					System.out.print("\nPlease enter you card number: ");
					int cardNum;
					
					try 
					{
						cardNum = sc.nextInt();
						sc.nextLine();
						person = JMPL.findPerson(cardNum);
						
						if (person == null) {
							person = createPerson();
						}
					}
					catch (Exception e) 
					{
						person = createPerson();
					}	
				}
					
				else {
					
					person = createPerson();
				}
				
				// then assign card number to person
					
			}
			else 
			{
				sc.close();
				break;
			}
		}
		sc.close();
	}
		
	public static Person createPerson() 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nCreate a library card:");
		System.out.print("\nWhat is your name? ");
		String name = sc.nextLine();
		System.out.print("How old are you? ");
		short age = sc.nextShort();
		sc.nextLine();
		
		sc.close();
		return new Person(name, age);
	}
}
