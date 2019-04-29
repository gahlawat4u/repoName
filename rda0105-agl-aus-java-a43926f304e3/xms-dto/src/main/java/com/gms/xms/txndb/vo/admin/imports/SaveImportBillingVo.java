package com.gms.xms.txndb.vo.admin.imports;

import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;

import java.util.List;

/**
 * Posted from May 25, 2016 2:46:10 PM
 * <p>
 * Author huynd
 */
public class SaveImportBillingVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private AddressVo senderAddress;
    private AddressVo receiverAddress;
    private ShipmentBillingVo billingBaseCharge;
    private List<ShipmentBillingVo> billingAccessorials;
    private Long customerCode;

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

    public ShipmentBillingVo getBillingBaseCharge() {
        return billingBaseCharge;
    }

    public void setBillingBaseCharge(ShipmentBillingVo billingBaseCharge) {
        this.billingBaseCharge = billingBaseCharge;
    }

    public List<ShipmentBillingVo> getBillingAccessorials() {
        return billingAccessorials;
    }

    public void setBillingAccessorials(List<ShipmentBillingVo> billingAccessorials) {
        this.billingAccessorials = billingAccessorials;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public String toString() {
        return "SaveImportBillingVo [senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", billingBaseCharge=" + billingBaseCharge + ", billingAccessorials=" + billingAccessorials + ", customerCode=" + customerCode + "]";
    }
}
