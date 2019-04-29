package com.gms.xms.persistence.dao.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.customer.activation.CustomerActivationVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerActivationDao.java
 * <p>
 * Author dattrinh 11:24:25 AM
 */
public class CustomerActivationDao extends BaseDao<CustomerActivationVo> {
    public CustomerActivationDao() {
        super();
    }

    public CustomerActivationDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerActivationVo> getActivationReport(CustomerActivationFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerActivation.getActivationReport");
    }

    public long checkActivationReport(CustomerActivationFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerActivation.checkActivationReport");
    }

    public void prepareActivationReport(Map<String, String> context, CustomerActivationFilter filter) throws DaoException {
        this.insert(context, filter, "CustomerActivation.prepareActivationReport");
    }
}
