package com.gms.xms.dto.edigenerate;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Sep 22, 2016 11:44:51 AM
 * <p>
 * Author dattrinh
 */
public class TntTransmissionParamVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Double transStart;
    private Double transEnd;
    private Long manifestStart;
    private Long manifestEnd;
    private Long manifestCount;

    public Double getTransStart() {
        return transStart;
    }

    public void setTransStart(Double transStart) {
        this.transStart = transStart;
    }

    public Double getTransEnd() {
        return transEnd;
    }

    public void setTransEnd(Double transEnd) {
        this.transEnd = transEnd;
    }

    public Long getManifestStart() {
        return manifestStart;
    }

    public void setManifestStart(Long manifestStart) {
        this.manifestStart = manifestStart;
    }

    public Long getManifestEnd() {
        return manifestEnd;
    }

    public void setManifestEnd(Long manifestEnd) {
        this.manifestEnd = manifestEnd;
    }

    public Long getManifestCount() {
        return manifestCount;
    }

    public void setManifestCount(Long manifestCount) {
        this.manifestCount = manifestCount;
    }

    @Override
    public String toString() {
        return "TntTransmissionParamVo [transStart=" + transStart + ", transEnd=" + transEnd + ", manifestStart=" + manifestStart + ", manifestEnd=" + manifestEnd + ", manifestCount=" + manifestCount + "]";
    }
}
