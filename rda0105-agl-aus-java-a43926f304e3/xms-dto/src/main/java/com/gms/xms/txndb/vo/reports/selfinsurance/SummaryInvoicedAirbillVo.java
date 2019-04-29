package com.gms.xms.txndb.vo.reports.selfinsurance;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from May 23, 2016 11:26:17 AM
 * <p>
 * Author dattrinh
 */
public class SummaryInvoicedAirbillVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long intlShipmentCount;
    private Long domShipmentCount;
    private Double intlTotalRevenue;
    private Double domTotalRevenue;
    private Double domGst;
    private Double totalRevenueIncGst;

    public Long getIntlShipmentCount() {
        return intlShipmentCount;
    }

    public void setIntlShipmentCount(Long intlShipmentCount) {
        this.intlShipmentCount = intlShipmentCount;
    }

    public Long getDomShipmentCount() {
        return domShipmentCount;
    }

    public void setDomShipmentCount(Long domShipmentCount) {
        this.domShipmentCount = domShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getIntlTotalRevenue() {
        return intlTotalRevenue;
    }

    public void setIntlTotalRevenue(Double intlTotalRevenue) {
        this.intlTotalRevenue = intlTotalRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDomTotalRevenue() {
        return domTotalRevenue;
    }

    public void setDomTotalRevenue(Double domTotalRevenue) {
        this.domTotalRevenue = domTotalRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDomGst() {
        return domGst;
    }

    public void setDomGst(Double domGst) {
        this.domGst = domGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalRevenueIncGst() {
        return totalRevenueIncGst;
    }

    public void setTotalRevenueIncGst(Double totalRevenueIncGst) {
        this.totalRevenueIncGst = totalRevenueIncGst;
    }

    @Override
    public String toString() {
        return "SummaryInvoicedAirbillVo [intlShipmentCount=" + intlShipmentCount + ", domShipmentCount=" + domShipmentCount + ", intlTotalRevenue=" + intlTotalRevenue + ", domTotalRevenue=" + domTotalRevenue + ", domGst=" + domGst + ", totalRevenueIncGst=" + totalRevenueIncGst + "]";
    }
}
