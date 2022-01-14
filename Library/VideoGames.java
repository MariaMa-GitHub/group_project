/**
 * Video games subclass
 * Bob Yang
 * Jan 14 2022
 */

public class VideoGames extends Media
{
    String compatibility;
    int rating;
    
    //default constructor
    public VideoGames(){
        super();
        this.rating = 0;
        this.compatibility = "N/A";
    }
    //constructor that inherits from Media superclass
    public VideoGames(String t, String pub, String g, boolean a, Queue hold, int r, String c){
        super(t, pub, g, a, hold, 3);
        this.rating = r;
        this.compatibility = c;
    }
    //to string method
    public String toString(){
       return super.toString() + String.format("Rating: %s\ncompatibility: %s", this.rating, this.compatibility);
    }
    //equals method
    public boolean equals(VideoGames v){
        if(this.rating == v.rating && this.compatibility == v.compatibility && this.publisher == v.publisher && this.genre == v.genre){
            return true;
        }
        else{
            return false;
        }
    }
}
