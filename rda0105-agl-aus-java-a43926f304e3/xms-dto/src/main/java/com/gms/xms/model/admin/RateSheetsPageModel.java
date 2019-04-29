package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;

/**
 * Posted from RateSheetsPageModel
 *
 * @author HungNT - @since Oct 1, 2015
 */
public class RateSheetsPageModel extends BaseModel {
    private static final long serialVersionUID = -8075356571066487614L;

    private String sheetId;
    private String rateSheetName;
    private String carrierName;
    private String serviceName;
    private String type;
    private String importDate;
    private String totalCells;

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
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

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getTotalCells() {
        return totalCells;
    }

    public void setTotalCells(String totalCells) {
        this.totalCells = totalCells;
    }

    @Override
    public String toString() {
        return "RateSheetsPageModel [rateSheetName=" + rateSheetName + ", carrierName=" + carrierName + ", serviceName=" + serviceName + ", type=" + type + ", importDate=" + importDate + ", totalCells=" + totalCells + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrierName == null) ? 0 : carrierName.hashCode());
        result = prime * result + ((importDate == null) ? 0 : importDate.hashCode());
        result = prime * result + ((rateSheetName == null) ? 0 : rateSheetName.hashCode());
        result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
        result = prime * result + ((sheetId == null) ? 0 : sheetId.hashCode());
        result = prime * result + ((totalCells == null) ? 0 : totalCells.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RateSheetsPageModel other = (RateSheetsPageModel) obj;
        if (carrierName == null) {
            if (other.carrierName != null)
                return false;
        } else if (!carrierName.equals(other.carrierName))
            return false;
        if (importDate == null) {
            if (other.importDate != null)
                return false;
        } else if (!importDate.equals(other.importDate))
            return false;
        if (rateSheetName == null) {
            if (other.rateSheetName != null)
                return false;
        } else if (!rateSheetName.equals(other.rateSheetName))
            return false;
        if (serviceName == null) {
            if (other.serviceName != null)
                return false;
        } else if (!serviceName.equals(other.serviceName))
            return false;
        if (sheetId == null) {
            if (other.sheetId != null)
                return false;
        } else if (!sheetId.equals(other.sheetId))
            return false;
        if (totalCells == null) {
            if (other.totalCells != null)
                return false;
        } else if (!totalCells.equals(other.totalCells))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
