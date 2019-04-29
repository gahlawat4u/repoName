package com.gms.xms.model.admin.imports;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.ShipmentBillingModel;
import com.gms.xms.model.webship.AddressModel;

import java.util.List;

/**
 * Posted from May 25, 2016 2:46:10 PM
 * <p>
 * Author huynd
 */
public class SaveImportBillingModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private AddressModel senderAddress;
    private AddressModel receiverAddress;
    private ShipmentBillingModel billingBaseCharge;
    private List<ShipmentBillingModel> billingAccessorials;
    private String customerCode;

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

    public ShipmentBillingModel getBillingBaseCharge() {
        return billingBaseCharge;
    }

    public void setBillingBaseCharge(ShipmentBillingModel billingBaseCharge) {
        this.billingBaseCharge = billingBaseCharge;
    }

    public List<ShipmentBillingModel> getBillingAccessorials() {
        return billingAccessorials;
    }

    public void setBillingAccessorials(List<ShipmentBillingModel> billingAccessorials) {
        this.billingAccessorials = billingAccessorials;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public String toString() {
        return "SaveImportBillingModel [senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", billingBaseCharge=" + billingBaseCharge + ", billingAccessorials=" + billingAccessorials + ", customerCode=" + customerCode + "]";
    }

}
