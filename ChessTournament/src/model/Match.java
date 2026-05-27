package model;

public class Match {
    private int matchId;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player winner;
    private int match = 1;
    
    public Player getWhitePlayer() {
        return whitePlayer;
    }
    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }
    public Player getBlackPlayer() {
        return blackPlayer;
    }
    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }
    public Match() {
        this.matchId = match++;
    }
    public int getMatchId() {
        return matchId;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    @Override
    public String toString() {
        return "Match [whitePlayer=" + whitePlayer.getPlayerName() + ", blackPlayer=" + blackPlayer.getPlayerName() 
        + ", winner=" + (winner==null) != null?null:winner.getPlayerName() + "]";
    }

    
}
