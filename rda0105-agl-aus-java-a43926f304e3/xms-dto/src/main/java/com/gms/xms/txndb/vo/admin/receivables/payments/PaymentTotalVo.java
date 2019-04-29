package com.gms.xms.txndb.vo.admin.receivables.payments;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from PaymentTotalVo
 * <p>
 * Author dattrinh Mar 19, 2016 9:10:09 PM
 */
public class PaymentTotalVo extends BaseVo {

    private static final long serialVersionUID = -1586670846354556470L;

    private Double total;
    private Double totalReceived;

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(Double totalReceived) {
        this.totalReceived = totalReceived;
    }

    @Override
    public String toString() {
        return "PaymentTotalVo [total=" + total + ", totalReceived=" + totalReceived + "]";
    }
}
