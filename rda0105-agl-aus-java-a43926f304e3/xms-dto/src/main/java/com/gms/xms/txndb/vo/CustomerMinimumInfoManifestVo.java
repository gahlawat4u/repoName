package com.gms.xms.txndb.vo;

/**
 * Created by thinhdd on 2/13/2017.
 */
public class CustomerMinimumInfoManifestVo extends BaseVo {
    private Long customerCode;
    private String postalCode;

    public CustomerMinimumInfoManifestVo(Long customerCode, String postalCode) {
        this.customerCode = customerCode;
        this.postalCode = postalCode;
    }

    public CustomerMinimumInfoManifestVo() {
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
