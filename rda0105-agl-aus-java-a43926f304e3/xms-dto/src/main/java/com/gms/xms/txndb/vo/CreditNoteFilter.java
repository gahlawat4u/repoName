package com.gms.xms.txndb.vo;

import java.util.List;

/**
 * Posted from CreditNoteFilter
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class CreditNoteFilter extends CreditNoteVo {
    private static final long serialVersionUID = -3540207350822778935L;

    private List<String> franchiseCodeList;
    private List<CustomerVo> listCustomerVos;
    private String franchiseCode;
    private Long customerCode;

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public List<CustomerVo> getListCustomerVos() {
        return listCustomerVos;
    }

    public void setListCustomerVos(List<CustomerVo> listCustomerVos) {
        this.listCustomerVos = listCustomerVos;
    }

    public List<String> getFranchiseCodeList() {
        return franchiseCodeList;
    }

    public void setFranchiseCodeList(List<String> franchiseCodeList) {
        this.franchiseCodeList = franchiseCodeList;
    }

    @Override
    public String toString() {
        return "CreditNoteFilter [franchiseCodeList=" + franchiseCodeList + ", listCustomerVos=" + listCustomerVos + ", franchiseCode=" + franchiseCode + ", customerCode=" + customerCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
        result = prime * result + ((franchiseCodeList == null) ? 0 : franchiseCodeList.hashCode());
        result = prime * result + ((listCustomerVos == null) ? 0 : listCustomerVos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreditNoteFilter other = (CreditNoteFilter) obj;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (franchiseCode == null) {
            if (other.franchiseCode != null)
                return false;
        } else if (!franchiseCode.equals(other.franchiseCode))
            return false;
        if (franchiseCodeList == null) {
            if (other.franchiseCodeList != null)
                return false;
        } else if (!franchiseCodeList.equals(other.franchiseCodeList))
            return false;
        if (listCustomerVos == null) {
            if (other.listCustomerVos != null)
                return false;
        } else if (!listCustomerVos.equals(other.listCustomerVos))
            return false;
        return true;
    }

}
