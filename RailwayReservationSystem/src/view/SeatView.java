package view;

import java.util.List;
import java.util.Scanner;
import model.Seat;
import service.SeatService;

public class SeatView {

    SeatService seatService;
    Scanner sc;

    public SeatView(SeatService seatService, Scanner sc) {
        this.seatService = seatService;
        this.sc = sc;
    }

    public void printAllAvailableTicket() {
        List<Seat> availableSeat = seatService.getAllAvailableSeat();
        for(Seat seat:availableSeat)
            System.out.println(seat);
    }

    public void printAllBookeddTicket() {
        List<Seat> availableSeat = seatService.getAllBookedSeat();
        for(Seat seat:availableSeat)
            System.out.println(seat);
    }
}
