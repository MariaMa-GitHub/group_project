public class VideoGames extends Media
{
    String rating, compatability;
    
    public VideoGames(){
        super();
        this.rating = "TBD";
        this.compatability = "N/A";
    }
    public VideoGames(String t, String pub, String g, boolean a, Queue hold, String r, String c){
        super(t, pub, g, a, hold, 3);
        this.rating = r;
        this.compatability = c;
    }
    public String toString(){
       return super.toString() + String.format("Rating: %s\ncompatability: %s", this.rating, this.compatability);
    }
}
