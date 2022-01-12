
/**
 * Write a description of class Media here.
 *
 * @author (your name)
 * @version (a version number or a date)
 * I don't know if i did it right someone look it over lmao
 */
public class Media
{
    
    String title;
    String publisher;
    String genre;
    boolean availability;
    Queue hold;
    
    public Media(){
        this.title = "";
        this.publisher = "";
        this.genre = "";
        this.availability = false;
        this.hold = null;
    }
    
    public Media(String title, String publisher, String genre, boolean availability, Queue hold){
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.availability = availability;
        this.hold = hold;
    }
    
    public String printTitle(){
        return this.title;
    }
    public String toString(){
        String onHold = "";
  
        // CHANGE FROM MARIA
        for (Node current = this.hold.q.head; current != null; current = current.next) {
            
            onHold += current.cargo.name + " (" + current.cargo.cardNum + ")"\n;
        }
        
       
        
        String s = "Title: " + this.title + "\nPublisher: " 
        + this.publisher + "\nGenre: " + this.genre + "\nAvailability: " 
        + this.availability + "\nPeople on hold: " + onHold + "\n";
        
        return s;
    }
}
