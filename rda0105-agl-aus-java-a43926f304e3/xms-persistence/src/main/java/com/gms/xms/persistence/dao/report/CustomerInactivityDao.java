package com.gms.xms.persistence.dao.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.inactivity.CustomerInactivityFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.customer.inactivity.CustomerInactivityVo;

import java.util.List;

/**
 * Posted from CustomerInactivityDao.java
 * <p>
 * Author dattrinh 4:55:05 PM
 */
public class CustomerInactivityDao extends BaseDao<CustomerInactivityVo> {
    public CustomerInactivityDao() {
        super();
    }

    public CustomerInactivityDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerInactivityVo> getInactivityReport(CustomerInactivityFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerInactivity.getInactivityReport");
    }

    public long getInactivityCount(CustomerInactivityFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerInactivity.getInactivityCount");
    }
}
