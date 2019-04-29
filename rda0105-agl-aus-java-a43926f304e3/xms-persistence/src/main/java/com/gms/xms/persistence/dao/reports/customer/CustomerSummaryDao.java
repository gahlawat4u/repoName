package com.gms.xms.persistence.dao.reports.customer;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.customer.CustomerSummaryFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerSummaryDao
 * <p>
 * Author DatTV Sep 15, 2015
 */
public class CustomerSummaryDao extends BaseDao<Object> {
    public CustomerSummaryDao() {
        super();
    }

    public CustomerSummaryDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<Map<String, String>> selectByFilter(CustomerSummaryFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerSummary.selectByFilter");
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> sumByFilter(CustomerSummaryFilter filter) throws DaoException {
        return (Map<String, String>) this.select(filter, "CustomerSummary.sumByFilter");
    }

    public long countByFilter(CustomerSummaryFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerSummary.countByFilter");
    }

    public void prepareDataForCustomerSummary(Map<String, String> context, CustomerSummaryFilter filter) throws DaoException {
        this.insert(context, filter, "CustomerSummary.prepareDataForCustomerSummary");
    }
}
