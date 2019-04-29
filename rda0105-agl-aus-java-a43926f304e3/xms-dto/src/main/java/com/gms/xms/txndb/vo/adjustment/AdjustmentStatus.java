package com.gms.xms.txndb.vo.adjustment;

/**
 * Posted from AdjustmentStatus
 * <p>
 * Author DatTV Date May 26, 2015 10:57:51 AM
 */
public class AdjustmentStatus {
    private Integer id;
    private String name;

    public AdjustmentStatus() {

    }

    public AdjustmentStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "AdjustmentStatus [id=" + id + ", name=" + name + "]";
    }
}
