package com.gms.xms.model.admin.invoicing.manageinvoice.editairbill;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.ShipmentBillingModel;
import com.gms.xms.model.webship.AddressModel;

import java.util.List;

/**
 * @author TANDT
 */
public class EditAirbillResultModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -3535762331839056863L;
    private AddressModel senderAddress;
    private AddressModel receiverAddress;
    private ShipmentBillingModel shipmentBilling;
    private List<ShipmentBillingModel> shipmentBillings;

    public AddressModel getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressModel senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressModel getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(AddressModel receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public ShipmentBillingModel getShipmentBilling() {
        return shipmentBilling;
    }

    public void setShipmentBilling(ShipmentBillingModel shipmentBilling) {
        this.shipmentBilling = shipmentBilling;
    }

    public List<ShipmentBillingModel> getShipmentBillings() {
        return shipmentBillings;
    }

    public void setShipmentBillings(List<ShipmentBillingModel> shipmentBillings) {
        this.shipmentBillings = shipmentBillings;
    }

    @Override
    public String toString() {
        return "EditAirbillResultModel [senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", shipmentBilling=" + shipmentBilling + ", shipmentBillings=" + shipmentBillings + "]";
    }

}
