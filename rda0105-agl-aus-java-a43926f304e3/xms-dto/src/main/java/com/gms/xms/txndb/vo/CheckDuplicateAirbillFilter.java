package com.gms.xms.txndb.vo;

/**
 * Posted from Jul 23, 2016 3:45:17 PM
 * <p>
 * Author huynd
 */
public class CheckDuplicateAirbillFilter extends ShipmentBillingFilter {

    private static final long serialVersionUID = 1L;

    private Long carrier;
    private String cri;

    public Long getCarrier() {
        return carrier;
    }

    public void setCarrier(Long carrier) {
        this.carrier = carrier;
    }

    public String getCri() {
        return cri;
    }

    public void setCri(String cri) {
        this.cri = cri;
    }

    @Override
    public String toString() {
        return "CheckDuplicateAirbillFilter [carrier=" + carrier + ", cri=" + cri + "]";
    }
}