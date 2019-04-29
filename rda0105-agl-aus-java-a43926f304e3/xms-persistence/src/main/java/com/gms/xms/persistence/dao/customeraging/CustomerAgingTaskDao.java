package com.gms.xms.persistence.dao.customeraging;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;

import java.util.Map;

/**
 * Posted from CustomerAgingTaskDao
 * <p>
 * Author DatTV Date Aug 13, 2015 10:53:30 AM
 */
public class CustomerAgingTaskDao extends BaseDao<Object> {

    public CustomerAgingTaskDao() {
        super();
    }

    public CustomerAgingTaskDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void prepareDataForCustomerAgingInvoice(Map<String, String> context, CustomerAgingFilter filter) throws DaoException {
        insert(context, filter, "CustomerAgingTask.prepareDataForCustomerAgingInvoice");
    }

    public void prepareDataForCustomerAging(Map<String, String> context, CustomerAgingFilter filter) throws DaoException {
        insert(context, filter, "CustomerAgingTask.prepareDataForCustomerAging");
    }
}
