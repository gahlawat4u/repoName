package com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill;

import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;

import java.util.List;

/**
 * @author TANDT
 */
public class EditAirbillResultVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 2448829291166870735L;
    private AddressVo senderAddress;
    private AddressVo receiverAddress;
    private ShipmentBillingVo shipmentBilling;
    private List<ShipmentBillingVo> shipmentBillings;

    public AddressVo getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressVo senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressVo getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(AddressVo receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public ShipmentBillingVo getShipmentBilling() {
        return shipmentBilling;
    }

    public void setShipmentBilling(ShipmentBillingVo shipmentBilling) {
        this.shipmentBilling = shipmentBilling;
    }

    public List<ShipmentBillingVo> getShipmentBillings() {
        return shipmentBillings;
    }

    public void setShipmentBillings(List<ShipmentBillingVo> shipmentBillings) {
        this.shipmentBillings = shipmentBillings;
    }

    @Override
    public String toString() {
        return "EditAirbillResultVo [senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", shipmentBilling=" + shipmentBilling + ", shipmentBillings=" + shipmentBillings + "]";
    }

}
