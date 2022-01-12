
/**
 * Person Class: a person that can borrow and return library items by using a library card 
 *
 * @author Maria Ma
 * @version January 11, 2022
 */

import java.util.Scanner;
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
    
    // default constructor
    public Person() {
        this.name = "";
        this.age = 0;
        this.cardNum = 0;
        this.possessions = new ArrayList<Media>();
    }
    // 2-parameter constructor (name and age)
    public Person(String name, short age) {
        this.name = name;
        this.age = age;
        this.cardNum = Libray.total_users;
        this.possessions = new ArrayList<Media>();
    }
    
    // borrow one single item
    public void borrowItem(Media item) {
        // check if person can still borrow items (check if person has reached the limit)
        if (possessions.size() >= MAX_POSSESSIONS) {
            // error message
            System.out.println(String.format("To the owner of the card %d:", this.cardNum));
            System.out.println("You have attained the maximum of items that one can borrow from the library.");
        }
        else {
            // check item availability
            if (item.availability == true) {
                // item is taken
                item.availability = false;
                // person gets item
                possessions.add(item);
            }
        }
    }
    // borrow multiple items
    public void borrowItem(ArrayList<Media> items) {
        // check if person can still borrow items (check if person has reached the limit)
        if (possessions.size() + items.size() > MAX_POSSESSIONS) {
            // error message
            System.out.println(String.format("To the owner of the card %d:", this.cardNum));
            System.out.println("You have attained the maximum of items that one can borrow from the library.");
        }
        else {
            // borrow each item
            for (Media item : items) {
                borrowItem(item);
            }
        }
    }
    
    // get a list of all the items that the person has borrowed
    public String getPossessionList() {
        String itemList = "";
        for (Media item : possessions) {
            itemList += item.title + ", ";
        }
        return (itemList + " ").replace(",  ", "");
    }
    
    // output information about person
    public String toString() {
        return String.format("Information about %s:\n\n- Age: %d\n- Library Card Number: %d\n- Possessions: %s", this.name,
        this.age, this.cardNum, getPossessionList());
    }

    // TEST METHOD
    public static void main(String[] args) {
        
        ArrayList<Media> possessions = new ArrayList<Media>();
        for (int i = 1; i <= 3; i++) {
            possessions.add(new Media(i));
        }
        
        Person person = new Person("Maria", (short)17);
        
        person.borrowItem(possessions);
        
        System.out.println(person.getPossessionList());
        
    }
    
}
