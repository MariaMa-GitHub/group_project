import java.util.ArrayList;

// person class
public class Person {
    
    // limit for borrowing
    final short MAX_POSSESSIONS = 3;
    
    // person's personal info
    String name;
    short age;
    int cardNum;
    ArrayList<Media> possessions;
    ArrayList<Media> requests;
    
    // default constructor
    public Person() {
        this.name = "";
        this.age = 0;
        this.cardNum = 0;
        this.possessions = new ArrayList<Media>();
        this.requests = new ArrayList<Media>();
    }
    // 2-parameter constructor (name and age)
    public Person(String name, short age) {
        this.name = name;
        this.age = age;
        this.cardNum = Library.totalUsers++;
        this.possessions = new ArrayList<Media>();
        this.requests = new ArrayList<Media>();
    }
    
    // check whether person requested something based on the title provided
    public boolean findRequest(Media target) {
        for (Media item : requests) {
            if (item.equals(target)) {
                return true; //match found
            }
        }
        return false;
    }
    
    // check whether person possesses something based on the title provided
    public boolean findPossession(Media target) {
        for (Media item : possessions) {
            if (item.equals(target)) {
                return true; //match found
            }
        }
        return false;
    }
    
    // get a list of all the items that the person has borrowed
    public String getItemList(ArrayList<Media> items) {
        String itemList = "";
        for (Media item : items) {
            itemList += item.title + ", ";
        }
        return (itemList + " ").replace(",  ", "");
    }
    
    // output information about person
    public String toString() {
        return String.format("Information about %s:\n\n- Age: %d\n- Library Card Number: %d\n- Possessions: %s\n- Requests: %s", this.name,
        this.age, this.cardNum, getItemList(this.possessions), getItemList(this.requests));
    }
}