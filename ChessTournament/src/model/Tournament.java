package model;

import java.time.LocalDate;
import java.util.List;

public class Tournament {
    private int tournamentId;
    private String tournamentName;
    private LocalDate starDate;
    private LocalDate endDate;
    private List<Round> roundList;

    private int tournament = 1;

    
    public Tournament(String tournamentName, LocalDate starDate, LocalDate endDate, List<Round> roundList) {
        this.tournamentId = tournament++;
        this.tournamentName = tournamentName;
        this.starDate = starDate;
        this.endDate = endDate;
        this.roundList = roundList;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }

    public int getTournament() {
        return tournament;
    }

    public void setTournament(int tournament) {
        this.tournament = tournament;
    }
    
} 