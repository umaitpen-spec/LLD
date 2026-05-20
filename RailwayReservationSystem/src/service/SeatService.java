package service;

import java.util.List;
import java.util.stream.Collectors;
import model.Seat;
import repo.DbRepo;

public class SeatService {

    DbRepo dbRepo;
    public SeatService(DbRepo dbRepo) {
        this.dbRepo = dbRepo;
    }

    public List<Seat> getAllAvailableSeat() {

        return dbRepo.getAllSeat()
                    .stream()
                    .filter(a->!a.isBooked())
                    .collect(Collectors.toList());

    }

    public List<Seat> getAllBookedSeat() {
        return dbRepo.getAllSeat()
                    .stream()
                    .filter(a->a.isBooked())
                    .collect(Collectors.toList());
    }

    List<Seat> getAllAvailableCBSeat() {
        return dbRepo.getAllCBSeat()
                    .stream()
                    .filter(a->!a.isBooked())
                    .collect(Collectors.toList());
    }

    public List<Seat> getAllAvailableRACSeat() {
        return dbRepo.getAllRACSeat()
                    .stream()
                    .filter(a->!a.isBooked())
                    .collect(Collectors.toList());
    }

    List<Seat> getAllAvailableWTSeat() {
        return dbRepo.getAllWTSeat()
                    .stream()
                    .filter(a->!a.isBooked())
                    .collect(Collectors.toList());
    }

}
