package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;

public class AddWeightBreakModel extends BaseModel {
    private static final long serialVersionUID = 6293870886432475574L;

    private String currentIndex;
    private String globalIndex;
    private String htmlString;
    private String displayName;
    private String requestWeight;
    private String nextWeight;
    private String currentWeight;
    private String baseRateData;

    public String getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(String currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getGlobalIndex() {
        return globalIndex;
    }

    public void setGlobalIndex(String globalIndex) {
        this.globalIndex = globalIndex;
    }

    public String getHtmlString() {
        return htmlString;
    }

    public void setHtmlString(String htmlString) {
        this.htmlString = htmlString;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRequestWeight() {
        return requestWeight;
    }

    public void setRequestWeight(String requestWeight) {
        this.requestWeight = requestWeight;
    }

    public String getNextWeight() {
        return nextWeight;
    }

    public void setNextWeight(String nextWeight) {
        this.nextWeight = nextWeight;
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getBaseRateData() {
        return baseRateData;
    }

    public void setBaseRateData(String baseRateData) {
        this.baseRateData = baseRateData;
    }
}
