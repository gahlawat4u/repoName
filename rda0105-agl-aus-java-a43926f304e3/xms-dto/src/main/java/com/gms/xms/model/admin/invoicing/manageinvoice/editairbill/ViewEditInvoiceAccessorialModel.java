package com.gms.xms.model.admin.invoicing.manageinvoice.editairbill;

import com.gms.xms.model.BaseModel;

/**
 * @author TANDT
 */
public class ViewEditInvoiceAccessorialModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 6367757931522233653L;
    private String accessorialId;
    private String accessorialCode;
    private String accessorialName;
    private String applyStartDate;
    private String isQuoteAble;
    private String typeId;
    private String defaultCharge;

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public String getAccessorialCode() {
        return accessorialCode;
    }

    public void setAccessorialCode(String accessorialCode) {
        this.accessorialCode = accessorialCode;
    }

    public String getAccessorialName() {
        return accessorialName;
    }

    public void setAccessorialName(String accessorialName) {
        this.accessorialName = accessorialName;
    }

    public String getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(String applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public String getIsQuoteAble() {
        return isQuoteAble;
    }

    public void setIsQuoteAble(String isQuoteAble) {
        this.isQuoteAble = isQuoteAble;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(String defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    @Override
    public String toString() {
        return "ViewEditInvoiceAccessorialDetailModel [accessorialId=" + accessorialId + ", accessorialCode=" + accessorialCode + ", accessorialName=" + accessorialName + ", applyStartDate=" + applyStartDate + ", isQuoteAble=" + isQuoteAble + ", typeId=" + typeId + ", defaultCharge=" + defaultCharge + "]";
    }

}
