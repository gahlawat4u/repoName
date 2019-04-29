package com.gms.xms.txndb.vo;

import java.util.Date;


/**
 * Posted from AccessorialDetailVo
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class AccessorialDetailUpdateVo extends AccessorialDetailVo {
    private static final long serialVersionUID = 2009963382315309180L;

    private Date oldApplyStartDate;

    public Date getOldApplyStartDate() {
        return oldApplyStartDate;
    }

    public void setOldApplyStartDate(Date oldApplyStartDate) {
        this.oldApplyStartDate = oldApplyStartDate;
    }
}