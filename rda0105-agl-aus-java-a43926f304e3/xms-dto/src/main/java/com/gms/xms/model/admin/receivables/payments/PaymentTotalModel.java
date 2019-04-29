package com.gms.xms.model.admin.receivables.payments;

import com.gms.xms.model.BaseModel;

/**
 * Posted from PaymentTotalModel
 * <p>
 * Author dattrinh Mar 19, 2016 9:12:06 PM
 */
public class PaymentTotalModel extends BaseModel {

    private static final long serialVersionUID = -5417446269273694533L;

    private String total;
    private String totalReceived;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(String totalReceived) {
        this.totalReceived = totalReceived;
    }

    @Override
    public String toString() {
        return "PaymentTotalModel [total=" + total + ", totalReceived=" + totalReceived + "]";
    }
}
