package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.BaseVo;

public class AddWeightBreakVo extends BaseVo {
    private static final long serialVersionUID = 6293870886432475574L;

    private Integer currentIndex;
    private Integer globalIndex;
    private Integer weight;
    private Double currentWeight;
    private Double nextWeight;
    private String htmlString;

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Integer getGlobalIndex() {
        return globalIndex;
    }

    public void setGlobalIndex(Integer globalIndex) {
        this.globalIndex = globalIndex;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Double getNextWeight() {
        return nextWeight;
    }

    public void setNextWeight(Double nextWeight) {
        this.nextWeight = nextWeight;
    }

    @Override
    public String toString() {
        return "AddWeightBreakVo [globalIndex=" + globalIndex + ", weight=" + weight + ", currentWeight=" + currentWeight + ", nextWeight=" + nextWeight + "]";
    }

    public String getHtmlString() {
        return htmlString;
    }

    public void setHtmlString(String htmlString) {
        this.htmlString = htmlString;
    }
}
