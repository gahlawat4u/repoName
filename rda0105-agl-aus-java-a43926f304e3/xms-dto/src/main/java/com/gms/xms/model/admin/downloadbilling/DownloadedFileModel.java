package com.gms.xms.model.admin.downloadbilling;

import com.gms.xms.model.BaseModel;

import java.util.Map;

/**
 * Posted from Jun 3, 2016 9:50:27 AM
 * <p>
 * Author huynd
 */
public class DownloadedFileModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Map<String, Long> files;

    public Map<String, Long> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Long> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "DownloadedFileModel [files=" + files + "]";
    }
}
