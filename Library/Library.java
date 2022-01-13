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
	Person currentUser; //**new variable
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
		this.currentUser = null;
		this.name = "TBD";
		this.address = "TBD";
	}
	public Library(String name, String address)
	{
		this.people = new ArrayList<Person>();
		this.collection = new ArrayList<Media>();
		this.currentUser = null;
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
					System.out.printf("%s is available, would you like to borrow this item? (y/n)\n", m.title);
					response = input.nextLine();
				}
				while (response.equals("y") && response.equals("n"));
				
				//and then finally, if they answer yes, add to their checked out items and change the Media's availability
				if (response.equals("y"))
				{
					System.out.printf("You have succesfully checked out %s.\n", m.title);
					p.possessions.add(m);
					m.availability = false;
				}
				else if (response.equals("n")) //nothing changes, give them some message
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
			//HOWEVER: if user already has the item checked out, they CANNOT put it on hold
			if (findPosession(m.title)) //if the user already has current item
			{
				System.out.printf("Sorry, you already have %s checked out. You cannot currently put this on hold.\n", m.title);
			}
			else
			{
				do
				{
					System.out.printf("Sorry, %s is not available, would you like to put this item on hold? (y/n)\n", m.title);
					response = input.nextLine();
				}
				while (response.equals("y") && response.equals("n"));
			
				//and then finally, if they answer yes, add them to queue, if no, do nothing
				if (response.equals("y"))
				{
					System.out.printf("You have succesfully been added to the queue for %s.\n", m.title);
					m.hold.enQueue(p);
				}
				else if (response.equals("n")) //nothing changes, give them some message
				{
					System.out.printf("%s has not been added to the queue.\n", m.title);
				}
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
	
	//helper methods
	private Person findPerson(int target)
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
	private Media findMedia(String target)
	{
		for (int i = 0; i < this.collection.size(); i++)
		{
			if (this.collection.get(i).title.equals(target))
			{
				return this.collection.get(i);
			}
		}
		
		return null; //no match found
	}
	private boolean findPosession(String target)
	{
		for (int i = 0; i < this.currentUser.possessions.size(); i++)
		{
			if (this.currentUser.possessions.get(i).title.equals(target))
			{
				return true; //match found
			}
		}
		return false;
	}
	private static Person createPerson() 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nCreate a library card:");
		System.out.print("\nWhat is your name? ");
		String name = sc.nextLine();
		System.out.print("\nHow old are you? ");
		short age = sc.nextShort();
		sc.nextLine();
		
		return new Person(name, age);
	}
	
	//methods to hardcode collection & people
	public static void populateCollection(ArrayList<Media> col)
	{
		col.add(new Novel("Book1", "Pub1", "GenreA", true, new Queue(),"Author 1", 500)); //first Novel **reminder, to talk about the "new Queue()" tomorrow
		col.add(new Novel("Book2", "Pub2", "GenreA", true, new Queue(),"Author 2", 700)); //Book 2
		col.add(new Audiobooks("Audiobook1", "Pub3", "GenreB", true, new Queue(),"Author 3", 1200)); //AudioBook 1
		col.add(new VideoGames("Game1", "Ubisoft", "GenreC", true, new Queue(),"Teen", "PS4")); //Game 1 **do we want to change the "rating" to a char?
		col.add(new Novel("Book3", "Pub1", "GenreD", true, new Queue(), "Author 1", 400)); //Book 3
		col.add(new Audiobooks("Audiobook2", "Pub3", "GenreE", true, new Queue(),"Author 4", 7420));
		col.add(new Audiobooks("Audiobook2", "Pub4", "GenreD", true, new Queue(),"Author 5", 5600));
		col.add(new VideoGames("Game2", "Microsoft", "GenreF", true, new Queue(),"18+", "XBox"));
		col.add(new Novel("Book4", "Pub5", "GenreH", true, new Queue(),"Author 6", 250));
		col.add(new VideoGames("Game3", "Nintendo", "GenreG", true, new Queue(),"E", "Nintendo Switch"));
	}
	public static void populatePeople(ArrayList<Person> peop)
	{
		peop.add(new Person("Alex", (short) 10));
		peop.add(new Person("Bailey", (short) 15));
		peop.add(new Person("Charles", (short) 20));
	}
	public static void sortByMedia(ArrayList<Media> m){
	    String s1 = "";
	    String s2 = "";
	    String s3 = "";
	    for(int i = 0; i< m.size(); i++){
	        if(m.get(i).id == 1){
	         s1 += m.get(i).printTitle() + "\n";
             }
             else if(m.get(i).id == 2){
                 s2 += m.get(i).printTitle() + "\n";
             }
             else{
                 s3 += m.get(i).printTitle() + "\n";
             }
             
         }
         
         String ans = "Audiobooks:\n" + s1 + "Novels:\n" + s2 + "Video Games:\n" + s3;
         System.out.println(ans);
        }
        
	public static void main(String[] args)
	{
		 
		Scanner sc = new Scanner(System.in);
		
		Library JMPL = new Library("John McCrae Public Library", "123 Internet Road");
		
		
		//hard code 10 pre-existing Media inside the library
		populateCollection(JMPL.collection);
		//hard code 3 Person inside the library
		populatePeople(JMPL.people);
		
		System.out.printf("Welcome to %s!\n--------------\nDo you already have an account? (y/n)\n", JMPL.name);
		String answer = sc.nextLine();
		
		//repeat until valid answer is given
		while (!(answer.equals("y")) && !(answer.equals("n")))
		{
			System.out.println("Please answer 'y' or 'n'.");
			answer = sc.nextLine();
		}
			
		if (answer.equals("y")) //they already have an account, ie. cardNum
		{
			System.out.println("Great! Please enter your library card number.");
			answer = sc.nextLine();
			
			while (JMPL.findPerson(Integer.parseInt(answer)) == null)
			{
				System.out.println("No person was found under this card number, please try again. If you need to create an account, type 'exit'");
				answer = sc.nextLine();
				
				if (answer.equals("exit"))
				{
					break; //exit loop
				}
			}
			if (JMPL.findPerson(Integer.parseInt(answer)) != null) //a match was found
			{
				JMPL.currentUser = JMPL.findPerson(Integer.parseInt(answer));
			}
		}
		
		//at this point of the code "answer" will equal one of three things:
		//yes: they have an account and are logged in
		//no: they do NOT have an account
		//exit: they do NOT have an account
		if (answer.equals("n") || answer.equals("exit"))
		{
			JMPL.currentUser = createPerson();
			JMPL.people.add(JMPL.currentUser);
			System.out.printf("Welcome %s, your library card number is: %d", JMPL.currentUser, JMPL.currentUser.cardNum);
		}
		
		//now everyone should be "logged in" and have an account
		
		do
		{
			System.out.println("If you would ever like to end the program, please type 'end'.");
			System.out.println("Would you like to see the items you currently have checked out ('current') or check out a new item ('search')?"); //**this feels really weirdly worded, anyone can change this if they feel like it
			answer = sc.nextLine();
			
			while (!(answer.equals("current")) && !(answer.equals("search")) && !(answer.equals("end")))
			{
				System.out.println("Please enter a valid term.");
				answer = sc.nextLine();
			}
		
			//"access library"
			if (answer.equals("search"))
			{
				//print the entire collection, ask user to pick one, put in "checked out"
				JMPL.printCollection();
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=\nWhich item would you like?");
				answer = sc.nextLine();
				
				while (JMPL.findMedia(answer) == null)
				{
					System.out.println("No item was found under this title, please try again.");
					answer = sc.nextLine();
				}
				
				JMPL.checkOut(JMPL.findMedia(answer), JMPL.currentUser); //will do the rest of the work
			}
			else if (answer.equals("current"))
			{
				System.out.println(JMPL.currentUser);
				
				if (!(JMPL.currentUser.possessions.isEmpty())) //is not empty
				{
					System.out.println("Would you like to reutrn one of your items? (y/n)");
					answer = sc.nextLine();
					
					while (!(answer.equals("y")) && !(answer.equals("n")))
					{
						System.out.println("Please enter 'y' or 'n'.");
						answer = sc.nextLine();
					}
					
					if (answer.equals("yes"))
					{
						for (int i = 0; i < JMPL.currentUser.possessions.size(); i++)
						{
							System.out.println((i + 1) + ": " + JMPL.currentUser.possessions.get(i) + "\n--------------");
						}
						
						System.out.println("Please enter the number that corresponds with the item you would like to return");;
						int answerNum = sc.nextInt();
						sc.nextLine(); //scanner bug
						
						while (answerNum < 0 || answerNum > JMPL.currentUser.possessions.size())
						{
							System.out.println("Please enter a valid number");
							answerNum = sc.nextInt();
						}
						
						JMPL.returnBook(JMPL.currentUser.possessions.get(answerNum - 1), JMPL.currentUser); //returns item
					}
					else
					{
						System.out.println("Come back to return items later.");
					}
				}
				else
				{
					System.out.println("You have no items currently checked out.");
				}
			}
			
		} while (!(answer.equals("end")));
	}
}
