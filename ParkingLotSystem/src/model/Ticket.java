package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private int id;
    private Vechile vechile;
    private ParkingSlot slot;
    private double totAmount;
    private LocalDateTime inTime;
    private LocalDateTime outTime;

    public Ticket(int id, Vechile vechile, ParkingSlot slot) {
        this.id = id;
        this.vechile = vechile;
        this.slot = slot;
        this.inTime = LocalDateTime.now();
    }
    public String toString()
    {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%-10s %-10s %-10s %-10s %-20s %-20s %-10s%n",
                this.id,this.vechile.getVno(),
                this.slot.getSlotId(),
                this.slot.getFloor(),
                (inTime == null)?"-":
                this.inTime.toLocalDate() +"  "+
                        this.inTime.toLocalTime().format(formatter),
                (outTime == null)?"-":
                this.outTime.toLocalDate() +"  "+
                this.outTime.toLocalTime().format(formatter),
                (this.getTotAmount() == 0)?"-":this.getTotAmount()
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vechile getVechile() {
        return vechile;
    }

    public void setVechile(Vechile vechile) {
        this.vechile = vechile;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public double getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(double totAmount) {
        this.totAmount = totAmount;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }
}
