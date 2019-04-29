package com.gms.xms.persistence.dao.customers;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerProfileDao
 * <p>
 * Author DatTV Oct 12, 2015
 */
public class CustomerProfileDao extends BaseDao<CustomerProfileVo> {
    public CustomerProfileDao() {
        super();
    }

    public CustomerProfileDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerProfileVo> selectByFranchiseCode(String franchiseCode) throws DaoException {
        return this.selectList(franchiseCode, "CustomerProfile.selectByFranchiseCode");
    }

    public CustomerProfileVo selectByProfileId(Long profileId) throws DaoException {
        return this.select(profileId, "CustomerProfile.selectByProfileId");
    }

    public List<CustomerProfileVo> selectCustomerProfilesByFilter(CustomerProfileFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerProfile.selectCustomerProfilesByFilter");
    }

    public void update(Map<String, String> context, CustomerProfileVo customerProfileVo) throws DaoException {
        this.update(context, customerProfileVo, "CustomerProfile.update");
    }

    public void insert(Map<String, String> context, CustomerProfileVo customerProfileVo) throws DaoException {
        this.insert(context, customerProfileVo, "CustomerProfile.insert");
    }

    public Integer checkProfileName(CustomerProfileFilter filter) throws DaoException {
        return (int) selectObject(filter, "CustomerProfile.checkProfileName");
    }
}