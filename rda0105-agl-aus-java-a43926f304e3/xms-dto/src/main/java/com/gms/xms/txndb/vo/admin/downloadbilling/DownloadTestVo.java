package com.gms.xms.txndb.vo.admin.downloadbilling;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from Jun 3, 2016 10:04:47 AM
 * <p>
 * Author huynd
 */
public class DownloadTestVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileSize;
    private Date downloadTime;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    @Override
    public String toString() {
        return "DownloadTestVo [fileName=" + fileName + ", fileSize=" + fileSize + ", downloadTime=" + downloadTime + "]";
    }
}
