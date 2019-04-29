package com.gms.xms.model.industry;

import com.gms.xms.model.BaseModel;

/**
 * Posted from IndustryModel
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class IndustryModel extends BaseModel {

    private static final long serialVersionUID = -3530841195504019185L;

    private String industryId;
    private String industryName;

    @Override
    public String toString() {
        return "IndustryModel [industryId=" + industryId + ", industryName=" + industryName + "]";
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }
}
