package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

public class CheckRemoteSurchargeVo extends BaseVo {
    private static final long serialVersionUID = -2261525174432272226L;

    private Integer id;
    private Long countryId;
    private String countryName;
    private Integer isCityName;
    private Integer isPostalCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getIsCityName() {
        return isCityName;
    }

    public void setIsCityName(Integer isCityName) {
        this.isCityName = isCityName;
    }

    public Integer getIsPostalCode() {
        return isPostalCode;
    }

    public void setIsPostalCode(Integer isPostalCode) {
        this.isPostalCode = isPostalCode;
    }

    @Override
    public String toString() {
        return "CheckRemoteSurchargeVo [id=" + id + ", countryId=" + countryId + ", countryName=" + countryName + ", isCityName=" + isCityName + ", isPostalCode=" + isPostalCode + "]";
    }
}
