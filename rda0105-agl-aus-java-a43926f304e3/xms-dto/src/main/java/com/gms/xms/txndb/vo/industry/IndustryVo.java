package com.gms.xms.txndb.vo.industry;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from IndustryVo
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class IndustryVo extends BaseVo {

    private static final long serialVersionUID = 1547909998617084316L;

    private Integer industryId;
    private String industryName;

    @Override
    public String toString() {
        return "IndustryVo [industryId=" + industryId + ", industryName=" + industryName + "]";
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }
}
