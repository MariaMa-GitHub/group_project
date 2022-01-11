
/**
 * Write a description of class Media here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    
    
}
