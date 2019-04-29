package com.gms.xms.txndb.vo;

import java.math.BigDecimal;

/**
 * Posted from CustomerProfileAccessorialVo
 * <p>
 * Author DangDh Oct 26, 2016
 */
public class CustomerProfileAccessorialVo extends BaseVo {

    private static final long serialVersionUID = -1996801656342086572L;
    private Long profileId;
    private Long accessorialId;
    private BigDecimal amount;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(Long accessorialId) {
        this.accessorialId = accessorialId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CustomerProfileAccessorialVo [profileId=" + profileId + ", accessorialId=" + accessorialId + ", amount=" + amount + "]";
    }

}
