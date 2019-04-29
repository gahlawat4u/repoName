package com.gms.xms.txndb.vo.admin.systemstats;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 30, 2016 11:20:09 AM
 * <p>
 * Author dattrinh
 */
public class RecentImportVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String importDate;
    private String carrierName;
    private Long importCount;

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

    public Long getImportCount() {
        return importCount;
    }

    public void setImportCount(Long importCount) {
        this.importCount = importCount;
    }

    @Override
    public String toString() {
        return "RecentImportVo [importDate=" + importDate + ", carrierName=" + carrierName + ", importCount=" + importCount + "]";
    }
}
