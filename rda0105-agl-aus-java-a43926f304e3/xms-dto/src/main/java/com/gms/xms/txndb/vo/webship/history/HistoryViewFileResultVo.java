package com.gms.xms.txndb.vo.webship.history;

import com.gms.xms.txndb.vo.BaseVo;

import java.io.InputStream;

/**
 * Posted from HistoryViewAirbillResultVo
 * <p>
 * Author TanDT Date Jul 11, 2015
 */
public class HistoryViewFileResultVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 880687917676650556L;

    private String fileName;
    private InputStream stream;
    private String pathFileView;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getPathFileView() {
        return pathFileView;
    }

    public void setPathFileView(String pathFileView) {
        this.pathFileView = pathFileView;
    }

    @Override
    public String toString() {
        return "HistoryViewAirbillResultVo [fileName=" + fileName + ", pathFileView=" + pathFileView + "]";
    }

}