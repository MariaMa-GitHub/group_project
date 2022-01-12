public class Audiobooks extends Media
{
    String author;
    int length;
    
    public Audiobooks(){
        super();
        this.author = "TBD";
        this.length = 0;
    }
    public Audiobooks(String t, String pub, String g, boolean a, Queue hold, String author, int l){
        super(t, pub, g, a, hold);
        this.author = author;
        this.length = l;
    }
    public String toString(){
       return super.toString() + String.format("Author: %s\nLength: %d", this.author, this.length);
    }
}