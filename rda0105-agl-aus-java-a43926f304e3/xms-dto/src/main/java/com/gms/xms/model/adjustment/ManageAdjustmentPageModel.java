package com.gms.xms.model.adjustment;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ManageAdjustmentPageModel
 * <p>
 * Author HungNT Date May 28, 2015
 */
public class ManageAdjustmentPageModel extends BaseModel {
    private static final long serialVersionUID = 2900271862533525050L;

    private String status;
    private String page;
    private String recordSize;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(String recordSize) {
        this.recordSize = recordSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((page == null) ? 0 : page.hashCode());
        result = prime * result + ((recordSize == null) ? 0 : recordSize.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        ManageAdjustmentPageModel other = (ManageAdjustmentPageModel) obj;
        if (page == null) {
            if (other.page != null)
                return false;
        } else if (!page.equals(other.page))
            return false;
        if (recordSize == null) {
            if (other.recordSize != null)
                return false;
        } else if (!recordSize.equals(other.recordSize))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ManageAdjustmentPageModel [status=" + status + ", page=" + page + ", recordSize=" + recordSize + "]";
    }
}
