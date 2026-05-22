package view;

import java.util.Scanner;
import model.Floor;
import model.Lift;
import service.LiftService;

public class LiftView {
    Scanner sc;
    LiftService lSrc;
    public LiftView(Scanner sc,LiftService lSrc)
    {
        this.sc = sc;
        this.lSrc = lSrc;
    }

    public void assignLift() {
        System.out.print("Enter the Source Floor:");
        int src = sc.nextInt();
        System.out.print("Enter the Destination Floor:");
        int dst = sc.nextInt();
        lSrc.assignLift(src,dst);
    }

     public void restrictLift() {
        for(Lift lift:lSrc.getLiftDetails())
        {
            System.out.println("Enter the details for Lift:"+lift.getLiftName());
            System.out.print("Enter the start floor:");
            int sfl = sc.nextInt();
            System.out.print("Enter the Destination floor:");
            int dfl = sc.nextInt();
            lSrc.restrictLift(lift,sfl,dfl);
            System.out.println("Lift restriction applied Successfully!");
        }
        displayLiftRestrictions();
    }

    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    private void displayLiftRestrictions() {
        System.out.println("All lift Restrictions");
        for(Lift lift:lSrc.getLiftDetails())
        {
            System.out.print("LiftName:"+lift.getLiftName()+"-");
            StringBuilder sb = new StringBuilder();
            for(Floor floor:lift.getFloorList())
            {
                sb.append(floor.getFloorName()+",");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
        }
    }

    public void assignLiftByLeastStops() {
        System.out.print("Enter the Source Floor:");
        int src = sc.nextInt();
        System.out.print("Enter the Destination Floor:");
        int dst = sc.nextInt();
        lSrc.assignLiftByStops(src,dst);
    }

    public void addCapacityToLifts() {
        System.out.println("Enter the details");
        for(Lift lift:lSrc.getLiftDetails())
        {
            System.out.print("Enter the capacity for :"+lift.getLiftName());
            int capacity = sc.nextInt();
            lSrc.addCapacityToLifts(lift,capacity);
            System.out.println("Capacity Added Successfully for "+lift.getLiftName());
        }
    }

    public void addLiftUnderMaintenance() {
        System.out.print("Enter the lift number which is under Maintenance:");
        int liftno = sc.nextInt();
        lSrc.addLiftUnderMaintenance(liftno);
        System.out.println("Lift marked as Under Maintenance");
    }
}
