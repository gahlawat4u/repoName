package com.gms.xms.persistence.service.customergroup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.CustomerGroupFilter;
import com.gms.xms.txndb.vo.customergroup.CustomerGroupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ICustomerGroupService
 * <p>
 * Author DatTV Sep 10, 2015
 */
public interface ICustomerGroupService {
    public List<CustomerGroupVo> selectAll() throws DaoException;

    public List<CustomerGroupVo> selectByFilter(CustomerGroupFilter filter) throws DaoException;

    public long countByFilter(CustomerGroupFilter filter) throws DaoException;

    public void delete(Map<String, String> context, Integer CustomerGroupId) throws DaoException;

    public void insert(Map<String, String> context, CustomerGroupVo CustomerGroup) throws DaoException;

    public void update(Map<String, String> context, CustomerGroupVo CustomerGroup) throws DaoException;

    long countByName(CustomerGroupVo customerGroup) throws DaoException;

}
