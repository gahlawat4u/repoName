package com.gms.xms.model.admin.imports;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from Jun 7, 2016 3:41:40 PM
 * <p>
 * Author huynd
 */
public class BillingResultModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String noLines;
    private String noAirbill;
    private List<String> errorList;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNoLines() {
        return noLines;
    }

    public void setNoLines(String noLines) {
        this.noLines = noLines;
    }

    public String getNoAirbill() {
        return noAirbill;
    }

    public void setNoAirbill(String noAirbill) {
        this.noAirbill = noAirbill;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    @Override
    public String toString() {
        return "BillingResultModel [fileName=" + fileName + ", noLines=" + noLines + ", noAirbill=" + noAirbill + ", errorList=" + errorList + "]";
    }
}
