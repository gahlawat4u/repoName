package com.gms.xms.txndb.vo.franchisepayable.sc;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from FranchisePayableSCTechFeeTotalVo
 * <p>
 * Author DatTV Dec 9, 2015
 */
public class FranchisePayableSCTechFeeTotalVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Double intlShipmentFee;
    private Double domShipmentFee;
    private Long intlShipmentCount;
    private Long domShipmentCount;

    @Override
    public String toString() {
        return "FranchisePayableSCTechFeeTotalVo [intlShipmentFee=" + intlShipmentFee + ", domShipmentFee=" + domShipmentFee + ", intlShipmentCount=" + intlShipmentCount + ", domShipmentCount=" + domShipmentCount + "]";
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getIntlShipmentFee() {
        return intlShipmentFee;
    }

    public void setIntlShipmentFee(Double intlShipmentFee) {
        this.intlShipmentFee = intlShipmentFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDomShipmentFee() {
        return domShipmentFee;
    }

    public void setDomShipmentFee(Double domShipmentFee) {
        this.domShipmentFee = domShipmentFee;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getIntlShipmentCount() {
        return intlShipmentCount;
    }

    public void setIntlShipmentCount(Long intlShipmentCount) {
        this.intlShipmentCount = intlShipmentCount;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getDomShipmentCount() {
        return domShipmentCount;
    }

    public void setDomShipmentCount(Long domShipmentCount) {
        this.domShipmentCount = domShipmentCount;
    }
}
