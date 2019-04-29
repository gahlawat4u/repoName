package com.gms.xms.persistence.dao.customeraging;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;

import java.util.List;

/**
 * Posted from CustomerAgingDao
 * <p>
 * Author DatTV Date Aug 13, 2015 2:21:57 PM
 */
public class CustomerAgingDao extends BaseDao<CustomerAgingVo> {

    public CustomerAgingDao() {
        super();
    }

    public CustomerAgingDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerAgingVo> selectByFilter(CustomerAgingFilter filter) throws DaoException {
        return selectList(filter, "CustomerAging.selectByFilter");
    }

    public long countByFilter(CustomerAgingFilter filter) throws DaoException {
        return (long) selectObject(filter, "CustomerAging.countByFilter");
    }

    public CustomerAgingVo sumByFilter(CustomerAgingFilter filter) throws DaoException {
        return select(filter, "CustomerAging.sumByFilter");
    }

    public int countByRptTxnId(String rptTxnId) throws DaoException {
        return (int) selectObject(rptTxnId, "CustomerAging.countByRptTxnId");
    }

    public CustomerAgingVo selectByCustomerCode(String customerCode) throws DaoException {
        return select(customerCode, "CustomerAging.selectByCustomerCode");
    }
}
