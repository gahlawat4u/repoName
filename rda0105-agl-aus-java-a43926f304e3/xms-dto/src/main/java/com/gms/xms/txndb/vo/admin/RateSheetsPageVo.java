package com.gms.xms.txndb.vo.admin;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from RateSheetsPageVo
 *
 * @author HungNT - @since Oct 1, 2015
 */
public class RateSheetsPageVo extends BaseVo {
    private static final long serialVersionUID = -1247971951799896873L;

    private Long sheetId;
    private String rateSheetName;
    private String carrierName;
    private String serviceName;
    private String type;
    private Date importDate;
    private Integer totalCells;

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public String getRateSheetName() {
        return rateSheetName;
    }

    public void setRateSheetName(String rateSheetName) {
        this.rateSheetName = rateSheetName;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getImportDate() {
        return importDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Integer getTotalCells() {
        return totalCells;
    }

    public void setTotalCells(Integer totalCells) {
        this.totalCells = totalCells;
    }

    @Override
    public String toString() {
        return "RateSheetsPageVo [rateSheetName=" + rateSheetName + ", carrierName=" + carrierName + ", serviceName=" + serviceName + ", type=" + type + ", importDate=" + importDate + ", totalCells=" + totalCells + "]";
    }
}
