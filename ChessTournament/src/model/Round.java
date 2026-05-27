package model;

import java.util.List;

public class Round {
    private int roundId;
    private List<Match> matchList;
    private int round = 1;
    
    public Round(List<Match> matchList) {
        this.roundId = round++;
        this.matchList = matchList;
    }
    public int getRoundId() {
        return roundId;
    }
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }
    public List<Match> getMatchList() {
        return matchList;
    }
    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }    
}
