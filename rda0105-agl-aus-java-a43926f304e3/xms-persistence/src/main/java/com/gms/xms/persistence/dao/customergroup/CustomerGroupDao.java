package com.gms.xms.persistence.dao.customergroup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.CustomerGroupFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.customergroup.CustomerGroupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerGroupDao
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class CustomerGroupDao extends BaseDao<CustomerGroupVo> {
    public List<CustomerGroupVo> selectAll() throws DaoException {
        return this.selectList(null, "CustomerGroup.selectAll");
    }

    public void insert(Map<String, String> context, CustomerGroupVo CustomerGroup) throws DaoException {
        insert(context, CustomerGroup, "CustomerGroup.insert");
    }

    public void update(Map<String, String> context, CustomerGroupVo CustomerGroup) throws DaoException {
        update(context, CustomerGroup, "CustomerGroup.update");
    }

    public void delete(Map<String, String> context, Integer CustomerGroupId) throws DaoException {
        delete(context, CustomerGroupId, "CustomerGroup.delete");
    }

    public List<CustomerGroupVo> selectByFilter(CustomerGroupFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerGroup.selectByFilter");
    }

    public long countByFilter(CustomerGroupFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "CustomerGroup.countByFilter");
    }

    public long countByName(CustomerGroupVo customerGroup) throws DaoException {
        return (long) this.selectObject(customerGroup, "CustomerGroup.countCustomerGroupByName");
    }
}
