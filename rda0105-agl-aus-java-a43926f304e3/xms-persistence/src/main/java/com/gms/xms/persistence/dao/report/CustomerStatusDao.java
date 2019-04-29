package com.gms.xms.persistence.dao.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.status.CustomerStatusFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerStatusDao
 * <p>
 * Author DatTV Nov 5, 2015
 */
public class CustomerStatusDao extends BaseDao<Object> {
    public CustomerStatusDao() {
        super();
    }

    public CustomerStatusDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<Map<String, String>> getWeeklyReport(CustomerStatusFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerStatus.getWeeklyReport");
    }

    public List<Map<String, String>> getMonthlyReport(CustomerStatusFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerStatus.getMonthlyReport");
    }

    public long checkWeeklyReport(CustomerStatusFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerStatus.checkWeeklyReport");
    }

    public long checkMonthlyReport(CustomerStatusFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerStatus.checkMonthlyReport");
    }

    public void prepareWeeklyReport(Map<String, String> context, CustomerStatusFilter filter) throws DaoException {
        this.insert(context, filter, "CustomerStatus.prepareWeeklyReport");
    }

    public void prepareMonthlyReport(Map<String, String> context, CustomerStatusFilter filter) throws DaoException {
        this.insert(context, filter, "CustomerStatus.prepareMonthlyReport");
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> sumWeeklyReport(CustomerStatusFilter filter) throws DaoException {
        return (Map<String, String>) this.select(filter, "CustomerStatus.sumWeeklyReport");
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> sumMonthlyReport(CustomerStatusFilter filter) throws DaoException {
        return (Map<String, String>) this.select(filter, "CustomerStatus.sumMonthlyReport");
    }
}
