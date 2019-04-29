package com.gms.xms.common.config.dto;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Posted from PaymentType
 * <p>
 * Author DatTV Dec 29, 2015
 */
public class PaymentType {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }
}