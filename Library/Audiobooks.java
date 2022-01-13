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
        super(t, pub, g, a, hold, 1);
        this.author = author;
        this.length = l;
    }
    public String toString(){
       return super.toString() + String.format("Author: %s\nLength: %d", this.author, this.length);
    }
    public boolean equals(Audiobooks v){
        if(this.author == v.author && this.length == v.length && this.publisher == v.publisher && this.genre == v.genre){
            return true;
        }
        else{
            return false;
        }
    }
}