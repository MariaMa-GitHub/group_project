/**
 * Library class: 
 * The library can buy new books for its collection, and people (Person class) are able to check books out, 
 * or if they're unavailable, put them on hold.
 * 
 * Bashir Kadri
 * Jan 11, 2022
 */
import java.util.ArrayList;

public class Library 
{
	ArrayList<Person> people;
	ArrayList<Media> collection;
	Person currentUser; //**new variable
	String name;
	String address;
	static int totalUsers = 0;
	
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
            Person nextPerson = m.hold.deQueue();
            nextPerson.possessions.add(m);
            nextPerson.requests.remove(m);
            m.availability = false;
        }
        
        //and finally, edit Person's cart
        p.possessions.remove(m);
    }
	
	//helper methods
	public Person findPerson(int target)
	{
		for (Person person : this.people)
		{
			if (person.cardNum == target)
			{
				return person;
			}
		}
		return null; //no match found
	}
	public Media findMedia(String target)
	{
		for (Media item : this.collection)
		{
			if (item.title.equals(target))
			{
				return item;
			}
		}
		return null; //no match found
	}

    public String printFilteredCollection(String chosenGenre){
        int counter = 0;
        String output = chosenGenre + " items:\n";
        for (Media m : this.collection)
        {
            if ( m.genre.equals(chosenGenre))
            {
                counter++;
                output += m.title + "\n";
            }
        }
        if(counter == 0)
        {
            return null;
        }
        else
        {
            return output;
        }
    }
    public static String sortByMedia(ArrayList<Media> m){
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

         String ans = "\nAUDIOBOOKS:\n" + s1 + "\nNOVELS:\n" + s2 + "\nVIDEO GAMES:\n" + s3;
         return ans;
    }
	public Person createPerson(String name, short age) //just creating a secondary method for now, once everything is done we can delete the original and rename this
	{
		Person p = new Person(name, age);
		this.people.add(p);
		return p;
	}
	//methods to hardcode collection & people
	public static void populateCollection(ArrayList<Media> col)
    {  
        col.add(new Novel("Goblet of Fire", "Bloomsbury Publishing", "Fantasy", "J. K. Rowling", 500)); 
        col.add(new Novel("The Lightning Thief", "Miramax Books", "Fantasy", "Rick Riordan", 700)); 
        col.add(new Audiobooks("Dune", "Holtzbrinck Publishing Group", "Fantasy", "Frank Herbert", 1200)); 
        col.add(new VideoGames("It Takes Two", "Hazelight Studios", "Action", 13, "PS4")); 
        col.add(new Novel("Pride and Prejudice", "Modern Library", "Romance", "Jane Austen", 400)); 
        col.add(new Audiobooks("Outlander", "Recorded Books", "Fantasy", "Diana Gabaldon", 7420));
        col.add(new Audiobooks("One Plus One", "Penguin Audio", "Romance", "Jojo Moyes", 5600));
        col.add(new VideoGames("Red Dead Redemption 2", "Rockstar Games", "Action", 18, "XBox"));
        col.add(new Novel("Sherlock Holmes", "Bramhall House", "Mystery", "William S. Baring-Gould", 250));
        col.add(new VideoGames("Super Mario Bros", "Nintendo", "Platformer", 0, "Nintendo Switch"));
    }
    public static void populatePeople(ArrayList<Person> peop)
    {
        peop.add(new Person("Alex", (short) 10));
        peop.add(new Person("Bailey", (short) 15));
        peop.add(new Person("Charles", (short) 20));
    }
	
	public static void main(String[] args)
	{
		Library JMPL = new Library("John McCrae Public Library", "123 Internet Road");
		
		//hard code 10 pre-existing Media inside the library
		populateCollection(JMPL.collection);
		//hard code 3 Person inside the library
		populatePeople(JMPL.people);
		
		new GUI(JMPL);
	}
}
