package serialization;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private long tel;

    public Contact() {
    }

    public Contact(String name, long tel) {
        this.name = name;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return String.format("%s : %10d",name,tel);
    }
}
