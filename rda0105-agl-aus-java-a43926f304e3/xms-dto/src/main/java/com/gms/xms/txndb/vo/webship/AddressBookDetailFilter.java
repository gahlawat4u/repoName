package com.gms.xms.txndb.vo.webship;

public class AddressBookDetailFilter extends AddressBookDetailVo {

    private static final long serialVersionUID = 8065289619346934980L;

    private Long webshipId;
    private Long customerCode;

    @Override
    public String toString() {
        return "AddressDetailFilter [webshipId=" + webshipId + ", customerCode=" + customerCode + ", toString()=" + super.toString() + "]";
    }

    public Long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(Long webshipId) {
        this.webshipId = webshipId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }
}