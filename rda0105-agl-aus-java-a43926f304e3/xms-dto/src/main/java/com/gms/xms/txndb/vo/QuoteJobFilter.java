package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from WebshipQuoteLogFilter
 * <p>
 * Author HungNT Date Mar 30, 2015
 */
public class QuoteJobFilter {

    private long customerCode;
    private long webshipId;
    private long quoteId;
    private Date startDate;
    private Date endDate;
    private String quoteNumber;
    private String airbillNumber;
    private int startRecord;
    private int recordSize;

    public long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(long customerCode) {
        this.customerCode = customerCode;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getStartDate() {
        return startDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(long webshipId) {
        this.webshipId = webshipId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(long quoteId) {
        this.quoteId = quoteId;
    }
}
