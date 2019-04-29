package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.txndb.vo.BaseVo;

public class CarrierCoverSheetVo extends BaseVo {
    private static final long serialVersionUID = 3696322625229823882L;

    private Integer carrierId;

    private Long coverSheetId;

    private Long inboundCoverSheetId;

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    public Long getCoverSheetId() {
        return coverSheetId;
    }

    public void setCoverSheetId(Long coverSheetId) {
        this.coverSheetId = coverSheetId;
    }

    public Long getInboundCoverSheetId() {
        return inboundCoverSheetId;
    }

    public void setInboundCoverSheetId(Long inboundCoverSheetId) {
        this.inboundCoverSheetId = inboundCoverSheetId;
    }

    @Override
    public String toString() {
        return "CarrierCoverSheetVo [carrierId=" + carrierId + ", coverSheetId=" + coverSheetId + ", inboundCoverSheetId=" + inboundCoverSheetId + "]";
    }
}