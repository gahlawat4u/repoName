package com.gms.xms.workflow.client.integration.response;

import java.util.List;

/**
 * Posted from SendCreditNotesResponse
 * <p>
 * Author HungNT Date May 21, 2015
 */
public class SendCreditNotesResponse extends BaseResponse {
    private List<String> dateList;

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }
}
