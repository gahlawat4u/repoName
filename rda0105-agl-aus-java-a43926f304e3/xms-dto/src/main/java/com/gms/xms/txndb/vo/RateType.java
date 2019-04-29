package com.gms.xms.txndb.vo;

/**
 * Posted from RateType
 * <p>
 * Author DatTV Sep 21, 2015
 */
public class RateType {
    private Integer id;
    private String name;

    public RateType() {

    }

    public RateType(Integer id, String name) {
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