package com.gms.xms.model;

/**
 * Posted from CustomerAccessorialModel
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class CustomerAccessorialModel extends BaseModel {
    private static final long serialVersionUID = -2110419418109122943L;

    private String customerCode;

    private String accessorialid;

    private String amount;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAccessorialid() {
        return accessorialid;
    }

    public void setAccessorialid(String accessorialid) {
        this.accessorialid = accessorialid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CustomerAccessorialModel [customerCode=" + customerCode + ", accessorialid=" + accessorialid + ", amount=" + amount + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessorialid == null) ? 0 : accessorialid.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
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
        CustomerAccessorialModel other = (CustomerAccessorialModel) obj;
        if (accessorialid == null) {
            if (other.accessorialid != null)
                return false;
        } else if (!accessorialid.equals(other.accessorialid))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        return true;
    }
}
