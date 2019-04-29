package com.gms.xms.persistence.dao.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.thirdteenweeks.CustomerWeekActivityFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.customer.thirdteenweeks.CustomerWeekActivityVo;

import java.util.List;

/**
 * Posted from CustomerWeekActivityDao.java
 * <p>
 * Author dattrinh 4:29:38 PM
 */
public class CustomerWeekActivityDao extends BaseDao<CustomerWeekActivityVo> {
    public CustomerWeekActivityDao() {
        super();
    }

    public CustomerWeekActivityDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerWeekActivityVo> getWeekActivityReport(CustomerWeekActivityFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerWeekActivity.getWeekActivityReport");
    }

    public long getWeekActivityCount(CustomerWeekActivityFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerWeekActivity.getWeekActivityCount");
    }
}
