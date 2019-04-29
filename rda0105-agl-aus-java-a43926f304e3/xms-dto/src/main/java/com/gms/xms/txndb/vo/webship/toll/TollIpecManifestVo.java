package com.gms.xms.txndb.vo.webship.toll;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * @author TANDT
 */
public class TollIpecManifestVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -7719534432651661473L;

    private Long ipecManifestId;
    private Long manifestIdentifier;
    private Date createDate;
    private String shipmentId;
    private String uploadFile;
    private Integer status;
    private String voidId;

    public Long getIpecManifestId() {
        return ipecManifestId;
    }

    public void setIpecManifestId(Long ipecManifestId) {
        this.ipecManifestId = ipecManifestId;
    }

    public Long getManifestIdentifier() {
        return manifestIdentifier;
    }

    public void setManifestIdentifier(Long manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVoidId() {
        return voidId;
    }

    public void setVoidId(String voidId) {
        this.voidId = voidId;
    }

    @Override
    public String toString() {
        return "TollIpecManifestVo [ipecManifestId=" + ipecManifestId + ", manifestIdentifier=" + manifestIdentifier + ", createDate=" + createDate + ", shipmentId=" + shipmentId + ", uploadFile=" + uploadFile + ", status=" + status + ", voidId=" + voidId + "]";
    }

}