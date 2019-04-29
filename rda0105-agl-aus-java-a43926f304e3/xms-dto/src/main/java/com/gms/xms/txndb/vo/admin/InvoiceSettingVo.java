package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from InvoiceSettingVo
 * <p>
 * Author @author HungNT Feb 22, 2016
 */
public class InvoiceSettingVo extends BaseVo {
    private static final long serialVersionUID = 1051549860880600379L;

    private Long invSettingId;
    private String invSettingName;
    private String invSettingValue;
    private Long franchiseCode;
    private Long localizationId;

    public Long getInvSettingId() {
        return invSettingId;
    }

    public void setInvSettingId(Long invSettingId) {
        this.invSettingId = invSettingId;
    }

    public String getInvSettingName() {
        return invSettingName;
    }

    public void setInvSettingName(String invSettingName) {
        this.invSettingName = invSettingName;
    }

    public String getInvSettingValue() {
        return invSettingValue;
    }

    public void setInvSettingValue(String invSettingValue) {
        this.invSettingValue = invSettingValue;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }

    @Override
    public String toString() {
        return "InvoiceSettingVo [invSettingId=" + invSettingId + ", invSettingName=" + invSettingName + ", invSettingValue=" + invSettingValue + ", franchiseCode=" + franchiseCode + ", localizationId=" + localizationId + "]";
    }
}
