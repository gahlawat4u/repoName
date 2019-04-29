package com.gms.xms.txndb.vo;

/**
 * Posted from SendCreditNoteInfoVo
 * <p>
 * Author HungNT Date May 23, 2015
 */
public class SendCreditNoteBillingInfoVo extends BaseVo {
    private static final long serialVersionUID = 7765237206700389780L;

    private String billingEmail;
    private String franchisePhone;
    private String franchiseEmail;

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getFranchisePhone() {
        return franchisePhone;
    }

    public void setFranchisePhone(String franchisePhone) {
        this.franchisePhone = franchisePhone;
    }

    public String getFranchiseEmail() {
        return franchiseEmail;
    }

    public void setFranchiseEmail(String franchiseEmail) {
        this.franchiseEmail = franchiseEmail;
    }

    @Override
    public String toString() {
        return "SendCreditNoteInfoVo [billingEmail=" + billingEmail + ", franchisePhone=" + franchisePhone + ", franchiseEmail=" + franchiseEmail + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((billingEmail == null) ? 0 : billingEmail.hashCode());
        result = prime * result + ((franchiseEmail == null) ? 0 : franchiseEmail.hashCode());
        result = prime * result + ((franchisePhone == null) ? 0 : franchisePhone.hashCode());
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
        SendCreditNoteBillingInfoVo other = (SendCreditNoteBillingInfoVo) obj;
        if (billingEmail == null) {
            if (other.billingEmail != null)
                return false;
        } else if (!billingEmail.equals(other.billingEmail))
            return false;
        if (franchiseEmail == null) {
            if (other.franchiseEmail != null)
                return false;
        } else if (!franchiseEmail.equals(other.franchiseEmail))
            return false;
        if (franchisePhone == null) {
            if (other.franchisePhone != null)
                return false;
        } else if (!franchisePhone.equals(other.franchisePhone))
            return false;
        return true;
    }
}
