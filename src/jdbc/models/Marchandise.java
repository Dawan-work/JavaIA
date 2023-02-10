package jdbc.models;

import java.io.Serializable;

public class Marchandise extends BaseEntity implements Serializable {
    private String designation;
    private double cout;

    public Marchandise() {
    }

    public Marchandise(long id, int version, String designation, double cout) {
        super(id, version);
        this.designation = designation;
        this.cout = cout;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }
}
