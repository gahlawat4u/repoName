package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from TntDomesticRateSheetVo
 * <p>
 * Author HungNT Date Aug 11, 2015
 */
public class TntDomesticRateSheetVo extends BaseVo {
    private static final long serialVersionUID = -7470737059290860794L;

    private Long carrierDocumentRate;

    public Long getCarrierDocumentRate() {
        return carrierDocumentRate;
    }

    public void setCarrierDocumentRate(Long carrierDocumentRate) {
        this.carrierDocumentRate = carrierDocumentRate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrierDocumentRate == null) ? 0 : carrierDocumentRate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TntDomesticRateSheetVo other = (TntDomesticRateSheetVo) obj;
        if (carrierDocumentRate == null) {
            if (other.carrierDocumentRate != null)
                return false;
        } else if (!carrierDocumentRate.equals(other.carrierDocumentRate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TntDomesticRateSheetVo [carrierDocumentRate=" + carrierDocumentRate + "]";
    }
}
