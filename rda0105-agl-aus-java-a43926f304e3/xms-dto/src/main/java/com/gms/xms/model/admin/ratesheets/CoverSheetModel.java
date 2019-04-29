package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.BaseModel;

public class CoverSheetModel extends BaseModel {
    private static final long serialVersionUID = 160336820280397997L;

    private String coverSheetId;

    private String fileName;

    private String createDate;

    public String getCoverSheetId() {
        return coverSheetId;
    }

    public void setCoverSheetId(String coverSheetId) {
        this.coverSheetId = coverSheetId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "CoverSheetModel [coverSheetId=" + coverSheetId + ", fileName=" + fileName + ", createDate=" + createDate + "]";
    }
}