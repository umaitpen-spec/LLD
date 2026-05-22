package DBRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Floor;
import model.Lift;

public class DBRepo {
    private Map<Integer,Floor> floorList = new HashMap<>();
    private Map<Integer,Lift> liftList = new HashMap<>();

    public void addFloor(Floor floor)
    {
        floorList.put(floor.getFloorId(),floor);
    }

    public void addLift(Lift lift)
    {
        liftList.put(lift.getLiftId(),lift);
    }

    public List<Floor> getFloorList()
    {
        return new ArrayList<>(floorList.values());
    }

    public List<Lift> getLiftList()
    {
        return new ArrayList<>(liftList.values());
    }

    public Floor getFloorById(int floorId) {
        return floorList.get(floorId);
    }

    public Lift getLiftById(int liftno) {
        return liftList.get(liftno);
    }
}
