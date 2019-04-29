/**
 *
 */
package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from ServiceCoverSheetVo
 *
 * @author HungNT - @since Oct 9, 2015
 */
public class ServiceCoverSheetVo extends BaseVo {
    private static final long serialVersionUID = -5351609224435290296L;

    private Integer serviceId;
    private String serviceName;
    private Long coverSheetId;
    private String fileName;
    private Long inboundCoverSheetId;
    private String inboundFileName;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getCoverSheetId() {
        return coverSheetId;
    }

    public void setCoverSheetId(Long coverSheetId) {
        this.coverSheetId = coverSheetId;
    }

    public Long getInboundCoverSheetId() {
        return inboundCoverSheetId;
    }

    public void setInboundCoverSheetId(Long inboundCoverSheetId) {
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
        return "ServiceCoverSheetVo [serviceId=" + serviceId + ", serviceName=" + serviceName + ", coverSheetId=" + coverSheetId + ", fileName=" + fileName + ", inboundCoverSheetId=" + inboundCoverSheetId + ", inboundFileName=" + inboundFileName + "]";
    }
}
