package model;

import enumeration.SeatType;
import java.util.List;

public class Seat {
    private int seatId;

    private boolean isBooked;
    private List<Passenger> passenger;
    private SeatType seatType;

    //private static int seat = 1;
    public Seat(int seatId,List<Passenger> passenger, SeatType seatType) {
        this.seatId = seatId;
        this.passenger = passenger;
        this.seatType = seatType;
    }
    
    public boolean isBooked() {
        return isBooked;
    }
    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
    public List<Passenger> getPassenger() {
        return passenger;
    }
    public void setPassenger(List<Passenger> passenger) {
        this.passenger = passenger;
    }
    public SeatType getSeatType() {
        return seatType;
    }
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "[seat No= "+ seatId+"passenger=" + passenger + ", seatType=" + seatType + "]";
    }
}
