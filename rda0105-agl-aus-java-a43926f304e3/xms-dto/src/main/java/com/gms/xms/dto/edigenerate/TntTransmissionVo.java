package com.gms.xms.dto.edigenerate;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Sep 22, 2016 11:44:51 AM
 * <p>
 * Author dattrinh
 */
public class TntTransmissionVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long transId;
    private Double transIdentifier;
    private Long manifestIdentifier;
    private Integer manifestCount;
    private String uploadFile;
    private Integer status;

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public Double getTransIdentifier() {
        return transIdentifier;
    }

    public void setTransIdentifier(Double transIdentifier) {
        this.transIdentifier = transIdentifier;
    }

    public Long getManifestIdentifier() {
        return manifestIdentifier;
    }

    public void setManifestIdentifier(Long manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    public Integer getManifestCount() {
        return manifestCount;
    }

    public void setManifestCount(Integer manifestCount) {
        this.manifestCount = manifestCount;
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

    @Override
    public String toString() {
        return "TntTransmissionVo [transId=" + transId + ", transIdentifier=" + transIdentifier + ", manifestIdentifier=" + manifestIdentifier + ", manifestCount=" + manifestCount + ", uploadFile=" + uploadFile + ", status=" + status + "]";
    }
}
