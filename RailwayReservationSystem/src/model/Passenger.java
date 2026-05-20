package model;

import enumeration.BerthType;
import enumeration.Gender;

public class Passenger {
    private int passengerID;
    private String passengerName;
    private int age;
    private Gender gender;
    private BerthType berthPreference;
    private Seat seat;

    private static int passenger = 1;
    public Passenger(String passengerName, 
                    int age, 
                    Gender gender,
                    BerthType berthPreference,
                    Seat seat) {
        this.passengerID = passenger++;
        this.passengerName = passengerName;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.seat = seat;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setBerthPreference(BerthType berthPreference) {
        this.berthPreference = berthPreference;
    }
    
    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public BerthType getBerthPreference() {
        return berthPreference;
    }
    
    public static int getPassenger() {
        return passenger;
    }
    public static void setPassenger(int passenger) {
        Passenger.passenger = passenger;
    }
    @Override
    public String toString() {
        return "[passengerID=" + passengerID
                + ", passengerName=" + passengerName
                + ", age=" + age
                + ", gender=" + gender
                + ", berthPreference=" + berthPreference
                + ", seatType=" + (seat != null ?seat.getSeatType():null)
                + ", seatNumber=" + (seat != null ? seat.getSeatId() : null)
                + "]";
    }
}
