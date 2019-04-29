package com.gms.xms.model;

public class HtsGoodModel extends BaseModel {
    private static final long serialVersionUID = -8063302107220515644L;

    private String htsGoodId;
    private String code;
    private String description;

    public String getHtsGoodId() {
        return htsGoodId;
    }

    public void setHtsGoodId(String htsGoodId) {
        this.htsGoodId = htsGoodId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HtsGoodModel [htsGoodId=" + htsGoodId + ", code=" + code + ", description=" + description + "]";
    }
}
