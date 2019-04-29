package com.gms.xms.txndb.vo;

/**
 * Posted from PickupFee
 * <p>
 * Author DatTV Sep 22, 2015
 */
public class PickupFee {
    private Integer id;
    private String name;

    public PickupFee() {

    }

    public PickupFee(Integer id, String name) {
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
        return "AdjustmentType [id=" + id + ", name=" + name + "]";
    }
}