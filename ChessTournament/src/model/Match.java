package model;

import java.util.List;

public class Match {
    private int matchId;
    private List<Player> playerList;
    private Player winner;
    private int match = 1;
    
    public Match() {
        this.matchId = match++;
    }
    public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    public List<Player> getPlayerList() {
        return playerList;
    }
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    
}
