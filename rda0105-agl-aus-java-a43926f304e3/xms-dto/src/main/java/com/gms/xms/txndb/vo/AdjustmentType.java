package com.gms.xms.txndb.vo;

public class AdjustmentType {
    private Integer id;
    private String name;

    public AdjustmentType() {

    }

    public AdjustmentType(Integer id, String name) {
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