package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.BillingTypeVo;

import java.util.List;

/**
 * Posted from BillingTypeDaoService
 * <p>
 * Author HungNT Date Mar 25, 2015
 */
public class BillingTypeDao extends BaseDao<BillingTypeVo> {
    /**
     * @return
     * @throws DaoException
     */
    public List<BillingTypeVo> getBillingTypeList() throws DaoException {
        return selectList(new BillingTypeVo(), "BillingType.getBillingTypeList");
    }

    public BillingTypeVo selectBillingTypeById(Integer billingId) throws DaoException {
        return select(billingId, "BillingType.selectBillingTypeById");
    }
}