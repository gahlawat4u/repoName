package com.gms.xms.dto.delivery.dhl;

/**
 * Posted from DhlTrackingRequestVo
 * <p>
 * Author dattrinh Jan 19, 2016 11:28:08 AM
 */
public class DhlTrackingRequestVo {
    private String langCode;
    private String airbillNumber;
    private String levelOfDetail;

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getLevelOfDetail() {
        return levelOfDetail;
    }

    public void setLevelOfDetail(String levelOfDetail) {
        this.levelOfDetail = levelOfDetail;
    }

    @Override
    public String toString() {
        return "DhlTrackingRequestVo [langCode=" + langCode + ", airbillNumber=" + airbillNumber + ", levelOfDetail=" + levelOfDetail + "]";
    }
}
