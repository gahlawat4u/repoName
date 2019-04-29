package com.gms.xms.txndb.vo.account.customers;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonNumber2DecimalStringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CustomerListEntryVo
 * <p>
 * Author DatTV Sep 7, 2015
 */
public class CustomerListEntryVo extends BaseVo {

    private static final long serialVersionUID = -2557733647168134804L;

    private String customerCode;
    private String customerName;
    private Double mtd;
    private Double ytd;
    private Date lastShipmentDate;

    @Override
    public String toString() {
        return "CustomerListEntryVo [customerCode=" + customerCode + ", customerName=" + customerName + ", mtd=" + mtd + ", ytd=" + ytd + ", lastShipmentDate=" + lastShipmentDate + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonSerialize(using = JsonNumber2DecimalStringSerializer.class)
    public Double getMtd() {
        return mtd;
    }

    public void setMtd(Double mtd) {
        this.mtd = mtd;
    }

    @JsonSerialize(using = JsonNumber2DecimalStringSerializer.class)
    public Double getYtd() {
        return ytd;
    }

    public void setYtd(Double ytd) {
        this.ytd = ytd;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getLastShipmentDate() {
        return lastShipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setLastShipmentDate(Date lastShipmentDate) {
        this.lastShipmentDate = lastShipmentDate;
    }
}
