package com.gms.xms.txndb.vo;

/**
 * Posted from May 12, 2016 3:52:07 PM
 * <p>
 * Author huynd
 */
public class CustomerAddressForMassEditVo extends CustomerAddressVo {

    private static final long serialVersionUID = 1L;

    private Integer senderAddressId;

    public Integer getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(Integer senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    @Override
    public String toString() {
        return "CustomerAddressForMassEditVo [senderAddressId=" + senderAddressId + "]";
    }

}
