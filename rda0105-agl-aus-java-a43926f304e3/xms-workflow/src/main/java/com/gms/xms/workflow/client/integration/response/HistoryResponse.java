package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.ShipmentDetailVo;
import com.gms.xms.txndb.vo.ShipmentNoteVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;

import java.util.List;

/**
 * Posted from HistoryResponse
 * <p>
 * Author TanDT Date Apr 18, 2015
 */
public class HistoryResponse extends BaseResponse {

    private ShipmentVo scheduleCollectionShipmentVo;
    private ShipmentTypeVo carrierByServiceTypeShipmentTypeVo;
    private ShipmentVo shipmentInfoByShipIDShipmentVo;
    private Integer voidStatus;
    private ShipmentVo shipmentForHistoryDetail;
    private List<ShipmentDetailVo> AccessorialColection;
    private Integer countShipment;
    private Integer statusUpdateAddress;
    private Integer statusUpdateScheduleCollection;
    private Integer statusInsertAddress;
    private Integer statusInsertNode;
    private Integer statusInsertScheduleCollection;
    private List<ShipmentVo> shipmentVos;
    private Integer statusSendAirbill;
    private List<ShipmentNoteVo> shipmentNoteVos;
    private String awbBarcode;

    public Integer getStatusInsertNode() {
        return statusInsertNode;
    }

    public void setStatusInsertNode(Integer statusInsertNode) {
        this.statusInsertNode = statusInsertNode;
    }

    public String getAwbBarcode() {
        return awbBarcode;
    }

    public void setAwbBarcode(String awbBarcode) {
        this.awbBarcode = awbBarcode;
    }

    public List<ShipmentNoteVo> getShipmentNoteVos() {
        return shipmentNoteVos;
    }

    public Integer getStatusSendAirbill() {
        return statusSendAirbill;
    }

    public void setStatusSendAirbill(Integer statusSendAirbill) {
        this.statusSendAirbill = statusSendAirbill;
    }

    public void setShipmentNoteVos(List<ShipmentNoteVo> shipmentNoteVos) {
        this.shipmentNoteVos = shipmentNoteVos;
    }

    private List<ShipmentNoteVo> shipmentNoteVo;

    public List<ShipmentNoteVo> getShipmentNoteVo() {
        return shipmentNoteVo;
    }

    public void setShipmentNoteVo(List<ShipmentNoteVo> shipmentNoteVo) {
        this.shipmentNoteVo = shipmentNoteVo;
    }

    public Integer getStatusInsertAddress() {
        return statusInsertAddress;
    }

    public void setStatusInsertAddress(Integer statusInsertAddress) {
        this.statusInsertAddress = statusInsertAddress;
    }

    public Integer getStatusInsertScheduleCollection() {
        return statusInsertScheduleCollection;
    }

    public void setStatusInsertScheduleCollection(Integer statusInsertScheduleCollection) {
        this.statusInsertScheduleCollection = statusInsertScheduleCollection;
    }

    public Integer getStatusUpdateAddress() {
        return statusUpdateAddress;
    }

    public void setStatusUpdateAddress(Integer statusUpdateAddress) {
        this.statusUpdateAddress = statusUpdateAddress;
    }

    public Integer getStatusUpdateScheduleCollection() {
        return statusUpdateScheduleCollection;
    }

    public void setStatusUpdateScheduleCollection(Integer statusUpdateScheduleCollection) {
        this.statusUpdateScheduleCollection = statusUpdateScheduleCollection;
    }

    public ShipmentVo getScheduleCollectionShipmentVo() {
        return scheduleCollectionShipmentVo;
    }

    public void setScheduleCollectionShipmentVo(ShipmentVo scheduleCollectionShipmentVo) {
        this.scheduleCollectionShipmentVo = scheduleCollectionShipmentVo;
    }

    public ShipmentTypeVo getCarrierByServiceTypeShipmentTypeVo() {
        return carrierByServiceTypeShipmentTypeVo;
    }

    public void setCarrierByServiceTypeShipmentTypeVo(ShipmentTypeVo carrierByServiceTypeShipmentTypeVo) {
        this.carrierByServiceTypeShipmentTypeVo = carrierByServiceTypeShipmentTypeVo;
    }

    public ShipmentVo getShipmentInfoByShipIDShipmentVo() {
        return shipmentInfoByShipIDShipmentVo;
    }

    public void setShipmentInfoByShipIDShipmentVo(ShipmentVo shipmentInfoByShipIDShipmentVo) {
        this.shipmentInfoByShipIDShipmentVo = shipmentInfoByShipIDShipmentVo;
    }

    public Integer getVoidStatus() {
        return voidStatus;
    }

    public void setVoidStatus(Integer voidStatus) {
        this.voidStatus = voidStatus;
    }

    public ShipmentVo getShipmentForHistoryDetail() {
        return shipmentForHistoryDetail;
    }

    public void setShipmentForHistoryDetail(ShipmentVo shipmentForHistoryDetail) {
        this.shipmentForHistoryDetail = shipmentForHistoryDetail;
    }

    public List<ShipmentDetailVo> getAccessorialColection() {
        return AccessorialColection;
    }

    public void setAccessorialColection(List<ShipmentDetailVo> accessorialColection) {
        AccessorialColection = accessorialColection;
    }

    public Integer getCountShipment() {
        return countShipment;
    }

    public void setCountShipment(Integer countShipment) {
        this.countShipment = countShipment;
    }

    public List<ShipmentVo> getShipmentVos() {
        return shipmentVos;
    }

    public void setShipmentVos(List<ShipmentVo> shipmentVos) {
        this.shipmentVos = shipmentVos;
    }

    @Override
    public String toString() {
        return "HistoryResponse [scheduleCollectionShipmentVo=" + scheduleCollectionShipmentVo + ", carrierByServiceTypeShipmentTypeVo=" + carrierByServiceTypeShipmentTypeVo + ", shipmentInfoByShipIDShipmentVo=" + shipmentInfoByShipIDShipmentVo + ", voidStatus=" + voidStatus + ", shipmentForHistoryDetail=" + shipmentForHistoryDetail + ", AccessorialColection=" + AccessorialColection + ", countShipment=" + countShipment + ", statusUpdateAddress=" + statusUpdateAddress
                + ", statusUpdateScheduleCollection=" + statusUpdateScheduleCollection + ", statusInsertAddress=" + statusInsertAddress + ", statusInsertNode=" + statusInsertNode + ", statusInsertScheduleCollection=" + statusInsertScheduleCollection + ", shipmentVos=" + shipmentVos + ", statusSendAirbill=" + statusSendAirbill + ", shipmentNoteVos=" + shipmentNoteVos + ", awbBarcode=" + awbBarcode + ", shipmentNoteVo=" + shipmentNoteVo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((AccessorialColection == null) ? 0 : AccessorialColection.hashCode());
        result = prime * result + ((awbBarcode == null) ? 0 : awbBarcode.hashCode());
        result = prime * result + ((carrierByServiceTypeShipmentTypeVo == null) ? 0 : carrierByServiceTypeShipmentTypeVo.hashCode());
        result = prime * result + ((countShipment == null) ? 0 : countShipment.hashCode());
        result = prime * result + ((scheduleCollectionShipmentVo == null) ? 0 : scheduleCollectionShipmentVo.hashCode());
        result = prime * result + ((shipmentForHistoryDetail == null) ? 0 : shipmentForHistoryDetail.hashCode());
        result = prime * result + ((shipmentInfoByShipIDShipmentVo == null) ? 0 : shipmentInfoByShipIDShipmentVo.hashCode());
        result = prime * result + ((shipmentNoteVo == null) ? 0 : shipmentNoteVo.hashCode());
        result = prime * result + ((shipmentNoteVos == null) ? 0 : shipmentNoteVos.hashCode());
        result = prime * result + ((shipmentVos == null) ? 0 : shipmentVos.hashCode());
        result = prime * result + ((statusInsertAddress == null) ? 0 : statusInsertAddress.hashCode());
        result = prime * result + ((statusInsertNode == null) ? 0 : statusInsertNode.hashCode());
        result = prime * result + ((statusInsertScheduleCollection == null) ? 0 : statusInsertScheduleCollection.hashCode());
        result = prime * result + ((statusSendAirbill == null) ? 0 : statusSendAirbill.hashCode());
        result = prime * result + ((statusUpdateAddress == null) ? 0 : statusUpdateAddress.hashCode());
        result = prime * result + ((statusUpdateScheduleCollection == null) ? 0 : statusUpdateScheduleCollection.hashCode());
        result = prime * result + ((voidStatus == null) ? 0 : voidStatus.hashCode());
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
        HistoryResponse other = (HistoryResponse) obj;
        if (AccessorialColection == null) {
            if (other.AccessorialColection != null)
                return false;
        } else if (!AccessorialColection.equals(other.AccessorialColection))
            return false;
        if (awbBarcode == null) {
            if (other.awbBarcode != null)
                return false;
        } else if (!awbBarcode.equals(other.awbBarcode))
            return false;
        if (carrierByServiceTypeShipmentTypeVo == null) {
            if (other.carrierByServiceTypeShipmentTypeVo != null)
                return false;
        } else if (!carrierByServiceTypeShipmentTypeVo.equals(other.carrierByServiceTypeShipmentTypeVo))
            return false;
        if (countShipment == null) {
            if (other.countShipment != null)
                return false;
        } else if (!countShipment.equals(other.countShipment))
            return false;
        if (scheduleCollectionShipmentVo == null) {
            if (other.scheduleCollectionShipmentVo != null)
                return false;
        } else if (!scheduleCollectionShipmentVo.equals(other.scheduleCollectionShipmentVo))
            return false;
        if (shipmentForHistoryDetail == null) {
            if (other.shipmentForHistoryDetail != null)
                return false;
        } else if (!shipmentForHistoryDetail.equals(other.shipmentForHistoryDetail))
            return false;
        if (shipmentInfoByShipIDShipmentVo == null) {
            if (other.shipmentInfoByShipIDShipmentVo != null)
                return false;
        } else if (!shipmentInfoByShipIDShipmentVo.equals(other.shipmentInfoByShipIDShipmentVo))
            return false;
        if (shipmentNoteVo == null) {
            if (other.shipmentNoteVo != null)
                return false;
        } else if (!shipmentNoteVo.equals(other.shipmentNoteVo))
            return false;
        if (shipmentNoteVos == null) {
            if (other.shipmentNoteVos != null)
                return false;
        } else if (!shipmentNoteVos.equals(other.shipmentNoteVos))
            return false;
        if (shipmentVos == null) {
            if (other.shipmentVos != null)
                return false;
        } else if (!shipmentVos.equals(other.shipmentVos))
            return false;
        if (statusInsertAddress == null) {
            if (other.statusInsertAddress != null)
                return false;
        } else if (!statusInsertAddress.equals(other.statusInsertAddress))
            return false;
        if (statusInsertNode == null) {
            if (other.statusInsertNode != null)
                return false;
        } else if (!statusInsertNode.equals(other.statusInsertNode))
            return false;
        if (statusInsertScheduleCollection == null) {
            if (other.statusInsertScheduleCollection != null)
                return false;
        } else if (!statusInsertScheduleCollection.equals(other.statusInsertScheduleCollection))
            return false;
        if (statusSendAirbill == null) {
            if (other.statusSendAirbill != null)
                return false;
        } else if (!statusSendAirbill.equals(other.statusSendAirbill))
            return false;
        if (statusUpdateAddress == null) {
            if (other.statusUpdateAddress != null)
                return false;
        } else if (!statusUpdateAddress.equals(other.statusUpdateAddress))
            return false;
        if (statusUpdateScheduleCollection == null) {
            if (other.statusUpdateScheduleCollection != null)
                return false;
        } else if (!statusUpdateScheduleCollection.equals(other.statusUpdateScheduleCollection))
            return false;
        if (voidStatus == null) {
            if (other.voidStatus != null)
                return false;
        } else if (!voidStatus.equals(other.voidStatus))
            return false;
        return true;
    }

}
