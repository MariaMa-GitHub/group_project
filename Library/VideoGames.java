public class VideoGames extends Media
{
    String rating, compatibility;
    
    public VideoGames(){
        super();
        this.rating = "TBD";
        this.compatibility = "N/A";
    }
    public VideoGames(String t, String pub, String g, boolean a, Queue hold, String r, String c){
        super(t, pub, g, a, hold);
        this.rating = r;
        this.compatibility = c;
    }
    public String toString(){
       return super.toString() + String.format("Rating: %s\nCompatibility: %s", this.rating, this.compatibility);
    }
}
