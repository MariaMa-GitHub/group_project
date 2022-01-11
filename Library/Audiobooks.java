
/**
 * Audiobooks subclass
 *
 * @Bob Yang
 * @Jan 11, 2022
 */
public class Audiobooks //extends Media
{
    String author;
    int length;
    
    public Audiobooks(){
        //super();
        
        this.author = "TBD";
        this.length = 0;
    }
    public Audiobooks(String a, int l){
        //super();
        
        this.author = a;
        this.length = l;
        
    }
}
