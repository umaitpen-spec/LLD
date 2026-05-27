package model;

public class Player {
    private int playerId;
    private String playerName;
    private int rating;
    private String country;
    private int player = 1;
    
    public Player(String playerName, String country) {
        this.playerId = player++;
        this.playerName = playerName;
        this.country = country;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    
}
