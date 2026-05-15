package model;

public abstract class Vechile {
    private String vno = null;

    public Vechile(String vno) {
        this.vno = vno;
    }

    public String getVno() {
        return vno;
    }

    public void setVno(String vno) {
        this.vno = vno;
    }
}
