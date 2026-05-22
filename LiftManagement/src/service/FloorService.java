package service;

import DBRepo.DBRepo;
import model.Floor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class FloorService {
    DBRepo dbRepo;

    public FloorService(int noOfFloor,DBRepo dbRepo) {
        this.dbRepo = dbRepo;
        for(int i=0;i<=noOfFloor;i++)            
        {
            Floor floor  = new Floor("F"+i);
            dbRepo.addFloor(floor);
        }
    }

    Floor getFloorById(int dstFloor) {
        return dbRepo.getFloorById(dstFloor);
    }


}
