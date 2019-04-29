package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Posted from AccessorialDetailVo
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class AccessorialDetailVo extends BaseVo {
    private static final long serialVersionUID = -5290362799970565048L;

    private Long accessorialId;

    private BigDecimal defaultCharge;

    private Date applyStartDate;

    private BigDecimal defaultChargeCarrier;

    public Long getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(Long accessorialId) {
        this.accessorialId = accessorialId;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(BigDecimal defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getApplyStartDate() {
        return applyStartDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setApplyStartDate(Date applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getDefaultChargeCarrier() {
        return defaultChargeCarrier;
    }

    public void setDefaultChargeCarrier(BigDecimal defaultChargeCarrier) {
        this.defaultChargeCarrier = defaultChargeCarrier;
    }

    @Override
    public String toString() {
        return "AccessorialDetailVo [accessorialId=" + accessorialId + ", defaultCharge=" + defaultCharge + ", applyStartDate=" + applyStartDate + ", defaultChargeCarrier=" + defaultChargeCarrier + "]";
    }
}