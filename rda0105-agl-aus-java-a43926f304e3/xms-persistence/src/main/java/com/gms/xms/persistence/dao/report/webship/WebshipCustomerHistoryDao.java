package com.gms.xms.persistence.dao.report.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipCustomerHistoryDao.java
 * <p>
 * Author dattrinh 11:27:53 AM
 */
public class WebshipCustomerHistoryDao extends BaseDao<Object> {
    public WebshipCustomerHistoryDao() {
        super();
    }

    public WebshipCustomerHistoryDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void prepareWebshipCustomerHistoryPeriod(Map<String, String> context, WebshipCustomerHistoryFilter filter) throws DaoException {
        this.insert(context, filter, "WebshipCustomerHistory.prepareWebshipCustomerHistoryPeriod");
    }

    public void prepareWebshipCustomerHistoryReport(Map<String, String> context, WebshipCustomerHistoryFilter filter) throws DaoException {
        this.insert(context, filter, "WebshipCustomerHistory.prepareWebshipCustomerHistoryReport");
    }

    public List<Map<String, String>> getWebshipCustomerHistoryReport(WebshipCustomerHistoryFilter filter) throws DaoException {
        return this.selectList(filter, "WebshipCustomerHistory.getWebshipCustomerHistoryReport");
    }

    public long countWebshipCustomerHistoryReport(WebshipCustomerHistoryFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "WebshipCustomerHistory.countWebshipCustomerHistoryReport");
    }

    public boolean checkWebshipCustomerHistoryReport(WebshipCustomerHistoryFilter filter) throws DaoException {
        long result = (long) this.selectObject(filter, "WebshipCustomerHistory.checkWebshipCustomerHistoryReport");
        return (result > 0);
    }
}
