package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.SendCreditNoteBillingInfoVo;

/**
 * Posted from SendCreditNoteBillingInfoDao
 * <p>
 * Author HungNT Date May 23, 2015
 */
public class SendCreditNoteBillingInfoDao extends BaseDao<SendCreditNoteBillingInfoVo> {
    public SendCreditNoteBillingInfoVo selectSendCreditNoteBillingInfo(Long customerCode) throws DaoException {
        return select(customerCode, "SendCreditNoteBillingInfo.selectSendCreditNoteBillingInfo");
    }
}
