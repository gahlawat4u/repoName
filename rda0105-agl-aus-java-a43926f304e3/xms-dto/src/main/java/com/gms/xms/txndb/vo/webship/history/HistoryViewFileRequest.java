package com.gms.xms.txndb.vo.webship.history;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from HistoryViewFileRequest
 * <p>
 * Author TanDT Date Jul 11, 2015
 */
public class HistoryViewFileRequest extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 880687917676650556L;

    private Long shipmentId;
    private String serverPathImage;
    private String fileName;
    private String tmpFileOutputServer;
    private String serverPathBarcode;

    public String getServerPathBarcode() {
        return serverPathBarcode;
    }

    public void setServerPathBarcode(String serverPathBarcode) {
        this.serverPathBarcode = serverPathBarcode;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getServerPathImage() {
        return serverPathImage;
    }

    public void setServerPathImage(String serverPathImage) {
        this.serverPathImage = serverPathImage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTmpFileOutputServer() {
        return tmpFileOutputServer;
    }

    public void setTmpFileOutputServer(String tmpFileOutputServer) {
        this.tmpFileOutputServer = tmpFileOutputServer;
    }

    @Override
    public String toString() {
        return "HistoryViewAirbillRequest [shipmentId=" + shipmentId + ", serverPathImage=" + serverPathImage + ", fileName=" + fileName + ", tmpFileOutputServer=" + tmpFileOutputServer + "]";
    }

}