package com.gms.xms.txndb.vo.admin.downloadbilling;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from Jun 3, 2016 10:23:14 AM
 * <p>
 * Author huynd
 */
public class DownloadLogVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String fileName;
    private Date downloadDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }

    @Override
    public String toString() {
        return "DownloadLogVo [id=" + id + ", fileName=" + fileName + ", downloadDate=" + downloadDate + "]";
    }
}
