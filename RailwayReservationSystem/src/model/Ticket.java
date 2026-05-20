package model;

import java.util.List;

public class Ticket {
    private int ticketID;
    private List<Passenger> passList;
    
    private static int ticket = 1000;
    public Ticket(List<Passenger> passList) {
        this.ticketID = ticket++;
        this.passList = passList;
    }

    public int getTicketID() {
        return ticketID;
    }

    public List<Passenger> getPassList() {
        return passList;
    }

    public void setPassList(List<Passenger> passList) {
        this.passList = passList;
    }

    @Override
    public String toString() {
        return "Ticket [ticketID=" + ticketID + ", passList=" + passList + "]";
    }
    
    
}
