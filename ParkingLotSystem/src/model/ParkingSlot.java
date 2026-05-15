package model;

public class ParkingSlot {
    private int slotId;
    private int floor;
    private VechileType type;
    private boolean isAvailable;
    private Vechile vechile;

    public ParkingSlot(int slotId, int floor, VechileType type, boolean isAvailable) {
        this.slotId = slotId;
        this.floor = floor;
        this.type = type;
        this.isAvailable = isAvailable;
        this.vechile = null;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public VechileType getType() {
        return type;
    }

    public void setType(VechileType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Vechile getVechile() {
        return vechile;
    }

    public void setVechile(Vechile vechile) {
        this.vechile = vechile;
    }
}
