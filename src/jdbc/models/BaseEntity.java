package jdbc.models;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    private long id;
    private int version;

    public BaseEntity(long id, int version) {
        this.id = id;
        this.version = version;
    }

    public BaseEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
