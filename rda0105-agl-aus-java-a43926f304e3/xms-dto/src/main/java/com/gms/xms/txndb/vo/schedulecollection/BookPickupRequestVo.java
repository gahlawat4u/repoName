package com.gms.xms.txndb.vo.schedulecollection;

import java.util.List;

import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.DhlCountryVo;
import com.gms.xms.txndb.vo.LocationCodeVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentVo;

public class BookPickupRequestVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -845185100059379652L;
    private ScheduleCollectionVo scheduleCollection;
    private AddressVo senderAddress;
    private AddressVo pickupAddress;
    private ShipmentVo shipment;
    private LocationCodeVo locationCode;
    private DhlCountryVo dhlCountry;
    private PieceVo pieceVo;
    private List<PieceVo> pieceVos;
    private AddressVo receiverAddress;

    public List<PieceVo> getPieceVos() {
		return pieceVos;
	}

	public void setPieceVos(List<PieceVo> pieceVos) {
		this.pieceVos = pieceVos;
	}

	public AddressVo getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(AddressVo receiverAddress) {
		this.receiverAddress = receiverAddress;
	}



    public PieceVo getPieceVo() {
        return pieceVo;
    }

    public void setPieceVo(PieceVo pieceVo) {
        this.pieceVo = pieceVo;
    }

    public ScheduleCollectionVo getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionVo scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public AddressVo getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressVo senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressVo getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(AddressVo pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public ShipmentVo getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentVo shipment) {
        this.shipment = shipment;
    }

    public LocationCodeVo getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(LocationCodeVo locationCode) {
        this.locationCode = locationCode;
    }

    public DhlCountryVo getDhlCountry() {
        return dhlCountry;
    }

    public void setDhlCountry(DhlCountryVo dhlCountry) {
        this.dhlCountry = dhlCountry;
    }

    @Override
	public String toString() {
		return "BookPickupRequestVo [scheduleCollection=" + scheduleCollection + ", senderAddress=" + senderAddress
				+ ", pickupAddress=" + pickupAddress + ", shipment=" + shipment + ", locationCode=" + locationCode
				+ ", dhlCountry=" + dhlCountry + ", pieceVo=" + pieceVo + ", pieceVos=" + pieceVos
				+ ", receiverAddress=" + receiverAddress + "]";
	}

}
