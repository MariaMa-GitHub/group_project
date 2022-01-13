public class Media
{
    //declaring variables
    String title;
    String publisher;
    String genre;
    boolean availability;
    Queue hold;
    int id;
    //default constructor
    public Media(){
        this.title = "";
        this.publisher = "";
        this.genre = "";
        this.availability = false;
        this.hold = null;
        this.id = 0;
    }
    //constructor
    public Media(String title, String publisher, String genre, boolean availability, Queue hold, int id){
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.availability = availability;
        this.hold = hold;
        this.id = id;
    }
    //method for printing out the title of the specified media
    public String printTitle(){
        return this.title;
    }
    //toString method
    public String toString(){
        //variable to get all the people on hold
        String onHold = "";
  
        // CHANGE FROM MARIA
        for (Node current = this.hold.q.head; current != null; current = current.next) {
            //adding the people's names and their card numbers to print it out later
            onHold += current.cargo.name + " (" + current.cargo.cardNum + ")\n";
        }
        
       
       
        String s = "Title: " + this.title + "\nPublisher: " 
        + this.publisher + "\nGenre: " + this.genre + "\nAvailability: " 
        + this.availability + "\nPeople on hold: " + onHold + "\n";
        
        return s;
    }
}