package com.gms.xms.txndb.vo;

import java.util.Date;

/**
 * Posted from AirbillAdjustmentVo
 * <p>
 * Author DatTV Date May 12, 2015 2:00:32 PM
 */
public class AirbillPausingDeductVo extends BaseVo {
    private static final long serialVersionUID = 9020422414191761951L;

    private String airbillNumber;

    private Long pausingDay;

    private Date startDate;

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Long getPausingDay() {
        return pausingDay;
    }

    public void setPausingDay(Long pausingDay) {
        this.pausingDay = pausingDay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "AirbillPausingDeductVo [airbillNumber=" + airbillNumber + ", pausingDay=" + pausingDay + ", startDate=" + startDate + "]";
    }

}