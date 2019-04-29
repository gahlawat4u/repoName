package com.gms.xms.txndb.vo;

public class HtsGoodVo extends BaseVo {
    private static final long serialVersionUID = 3837968759068045750L;

    private Integer htsGoodId;
    private String code;
    private String description;

    public Integer getHtsGoodId() {
        return htsGoodId;
    }

    public void setHtsGoodId(Integer htsGoodId) {
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
        return "HtsGoodVo [htsGoodId=" + htsGoodId + ", code=" + code + ", description=" + description + "]";
    }
}
