package com.gms.xms.model.admin.imports;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from May 7, 2016 9:06:46 AM
 * <p>
 * Author huynd
 */
public class BillingDataModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private List<BillingRowDataModel> rowData;
    private String fileName;
    private String downloadFileId;
    private String importDate;

    public List<BillingRowDataModel> getRowData() {
        return rowData;
    }

    public void setRowData(List<BillingRowDataModel> rowData) {
        this.rowData = rowData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadFileId() {
        return downloadFileId;
    }

    public void setDownloadFileId(String downloadFileId) {
        this.downloadFileId = downloadFileId;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "BillingDataModel [rowData=" + rowData + ", fileName=" + fileName + ", downloadFileId=" + downloadFileId + ", importDate=" + importDate + "]";
    }

}
