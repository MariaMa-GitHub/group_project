/**
 * Audiobooks subclass
 * Bob Yang
 * Jan 14 2022
 */

public class Audiobooks extends Media
{
    String author;
    int length;
    
    //default constructor
    public Audiobooks(){
        super();
        this.author = "TBD";
        this.length = 0;
    }
    //constructor that inherits from Media superclass
    public Audiobooks(String t, String pub, String g, boolean a, Queue hold, String author, int l){
        super(t, pub, g, a, hold, 1);
        this.author = author;
        this.length = l;
    }
    //to string method
    public String toString(){
       return super.toString() + String.format("Author: %s\nLength: %d", this.author, this.length);
    }
    //equals method
    public boolean equals(Audiobooks v){
        if(this.author == v.author && this.length == v.length && this.publisher == v.publisher && this.genre == v.genre){
            return true;
        }
        else{
            return false;
        }
    }
}