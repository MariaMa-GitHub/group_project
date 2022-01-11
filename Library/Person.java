
/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class Person {
    
    String name;
    short age;
    int cardNum;
    ArrayList<Media> possessions;
    
    public Person() {
        this.name = "";
        this.age = 0;
        this.cardNum = 0;
        this.possessions = new ArrayList<Media>();
    }
    public Person(String name, short age) {
        this.name = name;
        this.age = age;
        this.cardNum = 0;
        this.possessions = new ArrayList<Media>();
    }
    
    public void getLibraryCard(Library library) {
        
        library.people.add(this);
        cardNum = library.people.size();
        
    }

    public static void main(String[] args) {
        
    }
    
}
