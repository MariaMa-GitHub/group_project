public class Novel extends Media
{
    String author;
    int pageCount;
    
    public Novel(){
        super();
        this.author = "TBD";
        this.pageCount = 0;
    }
    public Novel(String t, String pub, String g, boolean a, Queue hold, String author, int p){
        super(t, pub, g, a, hold);
        this.author = author;
        this.pageCount = p;
    }
    public String toString(){
       return super.toString() + String.format("Author: %s\nPage count: %d", this.author, this.pageCount);
    }
}