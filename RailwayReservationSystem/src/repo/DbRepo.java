package repo;

import enumeration.SeatType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Seat;
import model.Ticket;

public class DbRepo {
    private Map<Integer,Seat> cbLower = new HashMap<>();
    private Map<Integer,Seat> cbMiddle = new HashMap<>();
    private Map<Integer,Seat> cbUpper = new HashMap<>();
    private Map<Integer,Seat> sideUpper = new HashMap<>();
    private Map<Integer,Seat> rac = new HashMap<>();
    private Map<Integer,Seat> wt = new HashMap<>();
    private Map<Integer,Ticket> tkList = new HashMap<>();

    public DbRepo(int ctNum,int racNum,int wtNum)
    {
        createCFSeat(ctNum);
        createsideUpper(ctNum);
        createRACSeat(racNum);
        createwt(wtNum);
    }

    public Map<Integer, Seat> getCbLower() {
        return cbLower;
    }

    public void setCbLower(Map<Integer, Seat> cbLower) {
        this.cbLower = cbLower;
    }

    public Map<Integer, Seat> getCbMiddle() {
        return cbMiddle;
    }

    public void setCbMiddle(Map<Integer, Seat> cbMiddle) {
        this.cbMiddle = cbMiddle;
    }

    public Map<Integer, Seat> getCbUpper() {
        return cbUpper;
    }

    public void setCbUpper(Map<Integer, Seat> cbUpper) {
        this.cbUpper = cbUpper;
    }

    public Map<Integer, Seat> getRac() {
        return rac;
    }

    public void setRac(Map<Integer, Seat> rac) {
        this.rac = rac;
    }

    public Map<Integer, Seat> getWt() {
        return wt;
    }

    public void setWt(Map<Integer, Seat> wt) {
        this.wt = wt;
    }

    public Map<Integer, Ticket> getTkList() {
        return tkList;
    }

    public void setTkList(Map<Integer, Ticket> tkList) {
        this.tkList = tkList;
    }

    private void createRACSeat(int racNum) {
        for(int i=1;i<=racNum;i++)
        {
            Seat seat = new Seat(i, null, SeatType.RAC);
            rac.put(i,seat);
        }
    }

    private void createwt(int wtNum) {
        for(int i=1;i<=wtNum;i++)
        {
            Seat seat = new Seat(i, null, SeatType.WT);
            wt.put(i,seat);
        }
    }

    private void createCFSeat(int ctNum)
    {
        for(int i=1;i<=ctNum;i++)
        {
            Seat seat1 = new Seat(i, null, SeatType.CFLOWER);
            cbLower.put(i,seat1);
            Seat seat2 = new Seat(i, null, SeatType.CFMIDDLE);
            cbMiddle.put(i,seat2);
            Seat seat3 = new Seat(i, null, SeatType.CFUPPER);
            cbUpper.put(i,seat3);
        }
    }

    public List<Seat> getAllSeat()
    {
        List<Seat> allSeats = new ArrayList<>();

        allSeats.addAll(cbLower.values());
        allSeats.addAll(cbMiddle.values());
        allSeats.addAll(cbUpper.values());
        allSeats.addAll(rac.values());
        allSeats.addAll(wt.values());

        return allSeats;
    }

    public List<Seat> getAllCBSeat()
    {
        List<Seat> allCBSeats = new ArrayList<>();

        allCBSeats.addAll(cbLower.values());
        allCBSeats.addAll(cbMiddle.values());
        allCBSeats.addAll(cbUpper.values());
        allCBSeats.addAll(sideUpper.values());
        return allCBSeats;
    }

    public List<Seat> getAllRACSeat()
    {
        List<Seat> allRACSeats = new ArrayList<>();
        allRACSeats.addAll(rac .values());
        return allRACSeats;
    }
    
    public List<Seat> getAllWTSeat()
    {
        List<Seat> allWTSeats = new ArrayList<>();
        allWTSeats.addAll(wt.values());
        return allWTSeats;
    }

    private void createsideUpper(int ctNum) {
        for(int i=1;i<=ctNum;i++)
        {
            Seat seat = new Seat(i, null, SeatType.SIDEUPPER);
            sideUpper.put(i,seat);
        }
    }

    public Ticket getTicketById(int tkId) {
        return tkList.get(tkId);
    }

    public void removeTicket(Ticket ticket) {
        tkList.remove(ticket.getTicketID());
    }
}
