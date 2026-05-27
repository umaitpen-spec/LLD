import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.Match;
import model.Player;
import model.Round;
import model.Tournament;

public class ChessVw {

    Scanner sc;
    private Map<Integer,Player> playerList = new HashMap<>();
    private Map<Integer,Match> matchList = new HashMap<>();
    private Map<Integer,Tournament> tournList = new HashMap<>();
    private Map<Integer,Round> roundList = new HashMap<>();

    public ChessVw(Scanner sc) {
        this.sc = sc;
    }

    void createPlayer() {
        System.out.println("Enter the Player detials:");
        System.out.print("Enter the Name:");
        String name = sc.next();
        System.out.print("Enter the Country Name:");
        String countryName = sc.next();
        Player player = new Player(name,countryName);
        playerList.put(player.getPlayerId(), player);
        System.out.println("Player created Successfully!!!");

    }

    public void RegisterPlayer() {
      
        viewTouramentList();
        System.out.print("Enter the Tournment Id:");
        int tId = sc.nextInt();
        viewPlayerList();
        Tournament tournament = getTourmentById(tId);
        if(tournament == null)
            System.out.println("Invalid Tournament");
        else
        {
            if(tournament.getRoundList() == null)
            {
                System.out.print("Enter the Player Id:");
                int pId = sc.nextInt();
                Player player = getPlayerById(pId);
                if(player == null)
                    System.out.println("Player Not Valid!!!");
                else
                {
                    List<Player> playerListTou = tournament.getPlayerList();
                    if(playerListTou == null)
                    {
                        playerListTou = new ArrayList<>();
                        tournament.setPlayerList(playerListTou);
                    }
                    playerListTou.add(player);
                }
            }
            else
            {
                System.out.println("Cannot Add Players. Since the tournament is scheduled/completed");
            }
        }
    }

    public void createRounds() {
        viewTouramentList();
        System.out.println("Enter the TournamentId u want to create Round(Once created cannot be modified):");
        int tId = sc.nextInt();
        Tournament tournament = getTourmentById(tId);
        if(tournament == null)
            System.out.println("Invalid Tournament");
        else
        {
            if(tournament.getRoundList() == null)
            {
                List<Player> rplayerList = tournament.getPlayerList();
                int totPlayers = rplayerList.size();
                //Round> roundList = new ArrayList<>();
                List<Match> matchList1 = new ArrayList<>();
                if(totPlayers % 2 == 1)
                {
                    System.out.println("Players cannot be in ODD");
                    return;
                }
                for(int i=0;i<totPlayers;i++)
                {
                    Match match = new Match();
                    Player whitePlayer = rplayerList.get(i++);
                    Player blackPlayer = rplayerList.get(i);
                    match.setBlackPlayer(blackPlayer);
                    match.setWhitePlayer(whitePlayer);
                    matchList1.add(match);
                }
                Round round = new Round(matchList1);
                List<Round> roundList1 = tournament.getRoundList();
                if(roundList1 == null)
                    roundList1 = new ArrayList<>();
                roundList1.add(round);
                System.out.println("Rounds created!!!");
            }
            else
            {
                System.out.println("Round already Created. Cant create again");
            }
        }
    }

    public void recordResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recordResult'");
    }

    public void displayAllMatchesByPlayer() {

        for(Player player:playerList.values())
        {
            List<Match> mList = matchList.values().stream()
                        .filter(a->a.getBlackPlayer().equals(player) ||
                                a.getWhitePlayer().equals(player))
                        .collect(Collectors.toList());
            for(Match match:mList)
                System.out.println(match);
        }
      
    }

    public void declareWinner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'declareWinner'");
    }

    public void createTournment() {
        System.out.println("Provide Tournament Details!!");
        System.out.print("Enter the Tournament Name:");
        String name = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-DD-YYYY");
       
        System.out.print("Enter the startDate(MM-DD-YYYY):");
        String startDate = sc.next();
        LocalDate startD = LocalDate.parse(startDate,formatter);
        
        System.out.print("Enter the startDate(MM-DD-YYYY):");
        String endDate = sc.next();
        LocalDate endD = LocalDate.parse(endDate,formatter);


        Tournament tournament = new Tournament(name, startD, endD, null);
        tournList.put(tournament.getTournamentId(),tournament);
    }

    private void viewPlayerList() {
        System.out.println("Player Lists");
        for(Player player:playerList.values())
            System.out.println(player);
    }

    private void viewTouramentList() {
        System.out.println("Tournament Lists");
        for(Tournament tournament:tournList.values())
            System.out.println(tournament);
    }

    private Tournament getTourmentById(int tId) {
        for(Tournament tournament:tournList.values())
            if(tournament.getTournamentId() == tId)
                return tournament;
        return null;
    }

    private Player getPlayerById(int pId) {
        for(Player player:playerList.values())
            if(player.getPlayerId() == pId)
                return player;
        return null;
    }
}
