package com.gms.xms.model.admin.systemstats;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 30, 2016 11:20:09 AM
 * <p>
 * Author dattrinh
 */
public class RecentImportModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String importDate;
    private String carrierName;
    private String importCount;

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getImportCount() {
        return importCount;
    }

    public void setImportCount(String importCount) {
        this.importCount = importCount;
    }

    @Override
    public String toString() {
        return "RecentImportModel [importDate=" + importDate + ", carrierName=" + carrierName + ", importCount=" + importCount + "]";
    }
}
