package enumeration;

public enum BerthType {
    LOWER(1),
    MIDDLE(2),
    UPPER(3),
    RAC(4),
    N0PREFERENCE(0);
    private int value;

    BerthType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BerthType fromValue(int value) {
        for (BerthType g : BerthType.values()) {
            if (g.value == value) {
                return g;
            }
        }
        throw new IllegalArgumentException("Invalid gender");
    }
}
