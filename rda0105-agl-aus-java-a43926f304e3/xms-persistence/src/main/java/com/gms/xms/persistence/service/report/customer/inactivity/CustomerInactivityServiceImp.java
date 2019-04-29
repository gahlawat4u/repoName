package com.gms.xms.persistence.service.report.customer.inactivity;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.inactivity.CustomerInactivityFilter;
import com.gms.xms.persistence.dao.report.CustomerInactivityDao;
import com.gms.xms.txndb.vo.reports.customer.inactivity.CustomerInactivityVo;

import java.util.List;

/**
 * Posted from CustomerInactivityServiceImp.java
 * <p>
 * Author dattrinh 8:50:24 AM
 */
public class CustomerInactivityServiceImp implements ICustomerInactivityService {
    private CustomerInactivityDao inactivityDao = new CustomerInactivityDao();

    @Override
    public List<CustomerInactivityVo> getInactivityReport(CustomerInactivityFilter filter) throws DaoException {
        return inactivityDao.getInactivityReport(filter);
    }

    @Override
    public long getInactivityCount(CustomerInactivityFilter filter) throws DaoException {
        return inactivityDao.getInactivityCount(filter);
    }
}
