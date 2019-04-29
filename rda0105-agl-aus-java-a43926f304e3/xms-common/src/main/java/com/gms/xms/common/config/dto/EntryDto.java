package com.gms.xms.common.config.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class EntryDto {

    private String key;
    private List<String> keys;
    private List<String> values;

    public String getKey() {
        return key;
    }

    public List<String> getKeys() {
        return keys;
    }

    @XmlElementWrapper(name = "key")
    @XmlElement(name = "value")
    public void setKeys(List<String> keys) {
        this.keys = keys;
        if (this.keys != null && this.keys.size() > 0) {
            key = this.keys.get(0);
        } else {
            key = null;
        }
    }

    public List<String> getValues() {
        return values;
    }

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "value")
    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "EntryDto [key=" + key + ", values=" + values + "]";
    }
}
