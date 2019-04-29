package com.gms.xms.txndb.vo;

/**
 * Posted from CreditType
 * <p>
 * Author DatTV Date May 22, 2015 5:15:39 PM
 */
public class CreditType {
    private Integer id;
    private String name;

    public CreditType() {

    }

    public CreditType(Integer id, String name) {
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
        return "CreditType [id=" + id + ", name=" + name + "]";
    }
}