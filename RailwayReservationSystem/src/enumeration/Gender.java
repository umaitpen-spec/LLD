package enumeration;

public enum Gender {
    MALE(1),
    FEMALE(2);

    private int value;

    Gender(int value)
    {
        this.value = value;
    }

    public static Gender fromValue(int value)
    {
        for(Gender g:Gender.values())
            if(g.value == value)
                return g;
        throw new IllegalArgumentException("Invalid Gender");
    }
}
