package com.gms.xms.txndb.vo.webship.login;

import com.gms.xms.txndb.vo.webship.WebshipVo;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from WebshipLoginVo
 * <p>
 * Author DatTV Date Jul 7, 2015 3:44:58 PM
 */
public class WebshipLoginVo extends WebshipVo {
    private static final long serialVersionUID = 6670651758370566306L;

    private Boolean inactive;
    private Long webshipAdminId;
    private Integer adminFunction;

    @JsonIgnore
    private Long parentCustomerCode;

    @JsonIgnore
    private Long parentWebshipId;

    @Override
    public String toString() {
        return "WebshipLoginVo [inactive=" + inactive + ", webshipAdminId=" + webshipAdminId + ", adminFunction=" + adminFunction + ", parentCustomerCode=" + parentCustomerCode + ", parentWebshipId=" + parentWebshipId + "]";
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Long getWebshipAdminId() {
        return webshipAdminId;
    }

    public void setWebshipAdminId(Long webshipAdminId) {
        this.webshipAdminId = webshipAdminId;
    }

    public Integer getAdminFunction() {
        return adminFunction;
    }

    public void setAdminFunction(Integer adminFunction) {
        this.adminFunction = adminFunction;
    }

    public Long getParentCustomerCode() {
        return parentCustomerCode;
    }

    public void setParentCustomerCode(Long parentCustomerCode) {
        this.parentCustomerCode = parentCustomerCode;
    }

    public Long getParentWebshipId() {
        return parentWebshipId;
    }

    public void setParentWebshipId(Long parentWebshipId) {
        this.parentWebshipId = parentWebshipId;
    }
}