package service;

import DBRepo.DBRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Floor;
import model.Lift;

public class LiftService {

    DBRepo dbRepo;
    FloorService flSrc;
    public LiftService(int no,DBRepo dbRepo,FloorService flSrc) {
        this.dbRepo = dbRepo;
        this.flSrc = flSrc;
        Floor gFloor = dbRepo.getFloorList().get(0);
        for(int i=1;i<=no;i++)
        {            
            Lift lift  = new Lift("L"+i,gFloor,0);
            dbRepo.addLift(lift);        
        }
    }

    public void displayLiftDetails() {
        System.out.println("Lift Details");
        List<Lift> liftList = dbRepo.getLiftList()
                        .stream().filter(a->a.getCurrFloor() != null)
                        .collect(Collectors.toList());

        for(Lift lift:liftList)
            System.out.println(lift);
    }

    public void assignLift(int srcFloor, int dstFloor) {
        Floor src = dbRepo.getFloorById(srcFloor);
        List<Lift> liftList = getLiftWithSrc(src);
        Lift assignedLift = null;
        int minDiff = 100;
        boolean isForward = (srcFloor-dstFloor > 0);
        for(Lift lift:liftList)
        {
            int diff = Math.abs(srcFloor-lift.getCurrFloor().getFloorId());
            if(minDiff > diff)
            {
                minDiff = diff;
                assignedLift = lift;
            }
            else if(minDiff == diff)
            {
                if(isForward == lift.isForward())
                    assignedLift = lift;
            }
        }
        Floor dfloor = flSrc.getFloorById(dstFloor);
        assignedLift.setCurrFloor(dfloor);
        System.out.println("assignedLift:"+assignedLift.getLiftName());
        displayLiftDetails();
    }

    public void restrictLift(Lift lift, int sfl, int dfl) {

        List<Floor> flList = new ArrayList<>();
        for(int i=sfl;i<=dfl;i++)   
            flList.add(dbRepo.getFloorById(i));
        Floor fl = dbRepo.getFloorById(0);
        if(!flList.contains(fl))
            flList.add(fl);
        lift.setFloorList(flList);
    }

    public List<Lift> getLiftDetails() {
        return dbRepo.getLiftList();
    }

    @SuppressWarnings("unlikely-arg-type")
    public List<Lift> getLiftWithSrc(Floor srcfl)
    {
        return  dbRepo.getLiftList()
                    .stream()
                    .filter(a->a.getFloorList() != null && a.getFloorList().contains(srcfl))
                .collect(Collectors.toList());
    }

    public void assignLiftByStops(int srcFloor, int dstFloor) {
        Floor src = dbRepo.getFloorById(srcFloor);
        List<Lift> liftList = getLiftWithSrc(src);
        Lift assignedLift = null;
        int minDiff = 100;
        for(Lift lift:liftList)
        {
            int diff = lift.getFloorList().size();
            if(minDiff > diff)
            {
                minDiff = diff;
                assignedLift = lift;
            }
        }
        Floor dfloor = flSrc.getFloorById(dstFloor);
        assignedLift.setCurrFloor(dfloor);
        System.out.println("assignedLift:"+assignedLift.getLiftName());
        displayLiftDetails();
    }

    public void addCapacityToLifts(Lift lift, int capacity) {
        lift.setCapacity(capacity);
    }

    public void addLiftUnderMaintenance(int liftno) {
        Lift lift = dbRepo.getLiftById(liftno);
        lift.setCurrFloor(null);
    }
}
