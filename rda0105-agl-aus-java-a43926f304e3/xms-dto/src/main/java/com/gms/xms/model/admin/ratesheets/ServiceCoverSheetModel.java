/**
 *
 */
package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ServiceCoverSheetModel
 *
 * @author HungNT - @since Oct 9, 2015
 */
public class ServiceCoverSheetModel extends BaseModel {
    private static final long serialVersionUID = 8388894907076844302L;

    private String serviceId;
    private String serviceName;
    private String coverSheetId;
    private String fileName;
    private String inboundCoverSheetId;
    private String inboundFileName;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCoverSheetId() {
        return coverSheetId;
    }

    public void setCoverSheetId(String coverSheetId) {
        this.coverSheetId = coverSheetId;
    }

    public String getInboundCoverSheetId() {
        return inboundCoverSheetId;
    }

    public void setInboundCoverSheetId(String inboundCoverSheetId) {
        this.inboundCoverSheetId = inboundCoverSheetId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getInboundFileName() {
        return inboundFileName;
    }

    public void setInboundFileName(String inboundFileName) {
        this.inboundFileName = inboundFileName;
    }

    @Override
    public String toString() {
        return "ServiceCoverSheetModel [serviceId=" + serviceId + ", serviceName=" + serviceName + ", coverSheetId=" + coverSheetId + ", fileName=" + fileName + ", inboundCoverSheetId=" + inboundCoverSheetId + ", inboundFileName=" + inboundFileName + "]";
    }
}
