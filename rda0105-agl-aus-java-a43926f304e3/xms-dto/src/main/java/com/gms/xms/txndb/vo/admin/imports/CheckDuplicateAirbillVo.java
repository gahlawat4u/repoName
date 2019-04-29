package com.gms.xms.txndb.vo.admin.imports;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import com.gms.xms.txndb.vo.invoicing.DuplicateAirbillVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from Jul 23, 2016 3:17:00 PM
 * <p>
 * Author huynd
 */
public class CheckDuplicateAirbillVo extends DuplicateAirbillVo {

    private static final long serialVersionUID = 1L;

    private String airbillNumberX;
    private Date invoiceDate;
    private Long senderAddressId;
    private Long receiverAddressId;

    public String getAirbillNumberX() {
        return airbillNumberX;
    }

    public void setAirbillNumberX(String airbillNumberX) {
        this.airbillNumberX = airbillNumberX;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(Long senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public Long getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Long receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    @Override
    public String toString() {
        return "CheckDuplicateAirbillVo [airbillNumberX=" + airbillNumberX + ", invoiceDate=" + invoiceDate + ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId + "]";
    }
}
