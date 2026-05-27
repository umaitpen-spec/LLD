package model;

public enum Status {
    READ(1),
    UNREAD(2),
    SENT(3),
    DELETE(4);

    private final int value;

    Status(int value) {
        this.value = value;
    }

      public int getValue() {
        return value;
    }
    
    public static Status getValue(int stat)
    {
        for(Status status:Status.values())
            if(status.value == stat)
                return status;

        throw new IllegalArgumentException("Invalid status: " + stat);
    }
}
