package com.gms.xms.persistence.service.customergroup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.CustomerGroupFilter;
import com.gms.xms.persistence.dao.customergroup.CustomerGroupDao;
import com.gms.xms.txndb.vo.customergroup.CustomerGroupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerGroupServiceImp
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class CustomerGroupServiceImp implements ICustomerGroupService {

    @Override
    public List<CustomerGroupVo> selectByFilter(CustomerGroupFilter filter) throws DaoException {
        CustomerGroupDao customerGroupDao = new CustomerGroupDao();
        return customerGroupDao.selectByFilter(filter);
    }

    public List<CustomerGroupVo> selectAll() throws DaoException {
        CustomerGroupDao customerGroupDao = new CustomerGroupDao();
        return customerGroupDao.selectAll();
    }

    public long countByFilter(CustomerGroupFilter filter) throws DaoException {
        CustomerGroupDao customerGroupDao = new CustomerGroupDao();
        return customerGroupDao.countByFilter(filter);
    }

    public void delete(Map<String, String> context, Integer AreaId) throws DaoException {
        CustomerGroupDao customerGroupDao = new CustomerGroupDao();
        customerGroupDao.delete(context, AreaId);
    }

    public void insert(Map<String, String> context, CustomerGroupVo Area) throws DaoException {
        CustomerGroupDao customerGroupDao = new CustomerGroupDao();
        customerGroupDao.insert(context, Area);
    }

    public void update(Map<String, String> context, CustomerGroupVo Area) throws DaoException {
        CustomerGroupDao customerGroupDao = new CustomerGroupDao();
        customerGroupDao.update(context, Area);
    }

    public long countByName(CustomerGroupVo customerGroup) throws DaoException {
        CustomerGroupDao customerGroupDao = new CustomerGroupDao();
        return customerGroupDao.countByName(customerGroup);
    }
}
