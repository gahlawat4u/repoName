package com.gms.xms.txndb.vo.toll;

import com.gms.xms.txndb.vo.BaseVo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Posted from Sep 23, 2016 6:02:30 PM
 * <p>
 * Author huynd
 */
public class TollManifestVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long manifestId;
    private BigDecimal manifestIdentifier;
    private Date createDate;
    private String shipmentId;
    private String uploadFile;
    private Integer status;
    private String voidId;

    public Long getManifestId() {
        return manifestId;
    }

    public void setManifestId(Long manifestId) {
        this.manifestId = manifestId;
    }

    public BigDecimal getManifestIdentifier() {
        return manifestIdentifier;
    }

    public void setManifestIdentifier(BigDecimal manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVoidId() {
        return voidId;
    }

    public void setVoidId(String voidId) {
        this.voidId = voidId;
    }

    @Override
    public String toString() {
        return "TollManifestVo [manifestId=" + manifestId + ", manifestIdentifier=" + manifestIdentifier + ", createDate=" + createDate + ", shipmentId=" + shipmentId + ", uploadFile=" + uploadFile + ", status=" + status + ", voidId=" + voidId + "]";
    }
}
