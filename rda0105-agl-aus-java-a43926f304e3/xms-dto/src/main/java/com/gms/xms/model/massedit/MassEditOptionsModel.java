package com.gms.xms.model.massedit;

/**
 * Posted from Jul 15, 2016 9:31:43 AM
 * <p>
 * Author huynd
 */
public class MassEditOptionsModel {
    private String id;
    private String name;

    public MassEditOptionsModel(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MassEditOptionsModel [id=" + id + ", name=" + name + "]";
    }
}
