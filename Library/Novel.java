/**
 * Novel subclass
 * Bob Yang
 * Jan 14 2022
 */

public class Novel extends Media
{
    String author;
    int pageCount;
    
    //default constructor
    public Novel(){
        super();
        this.author = "TBD";
        this.pageCount = 0;
    }
    //constructor that inherits from Media superclass
    public Novel(String t, String pub, String g, String author, int p){
        super(t, pub, g, 2);
        this.author = author;
        this.pageCount = p;
    }
    //to string method
    public String toString(){
       return super.toString() + String.format("Author: %s\nPage count: %d", this.author, this.pageCount);
    }
    //equals method
    public boolean equals(Novel v){
        if(this.author == v.author && this.pageCount == v.pageCount && this.publisher == v.publisher && this.genre == v.genre){
            return true;
        }
        else{
            return false;
        }
    }
}