package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CustomerAddressFilter;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.CustomerCodeFilter;
import com.gms.xms.txndb.vo.CustomerFranchiseInfoVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerAddressDao
 * <p>
 * Author DatTV Date Apr 7, 2015 4:39:01 PM
 */
public class CustomerAddressDao extends BaseDao<CustomerAddressVo> {

    public CustomerAddressDao() {
        super();
    }

    public CustomerAddressDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CustomerAddressVo> getByName(CustomerAddressFilter filter) throws DaoException {
        return selectList(filter, "CustomerAddress.selectByName");
    }

    public CustomerAddressVo selectByCode(String customerCode) throws DaoException {
        return select(customerCode, "CustomerAddress.selectByCode");
    }

    public CustomerAddressVo selectByCustomerCode(Long customerCode) throws DaoException {
        return select(customerCode, "CustomerAddress.selectByCustomerCode");
    }

    public Integer resetPasswordByCodeNameMail(Map<String, String> context, CustomerAddressFilter filter) throws DaoException {
        return update(context, filter, "CustomerAddress.resetPassword");
    }

    public CustomerAddressVo getByCode(String customerCode) throws DaoException {
        return select(customerCode, "CustomerAddress.selectByCode");
    }

    public CustomerAddressVo selectCustomerAddressByCode(CustomerAddressVo customerAddressVo) throws DaoException {
        return (CustomerAddressVo) selectObject(customerAddressVo, "CustomerAddress.selectCustomerAddressByCode");
    }

    public void insertCustomerAddress(Map<String, String> context, CustomerAddressVo addressVo) throws DaoException {
        if (addressVo.getUserType() == null) {
            addressVo.setUserType(1);
        }
        this.insert(context, addressVo, "CustomerAddress.insertCustomerAddress");
    }

    public void updateCustomerAddress(Map<String, String> context, CustomerAddressVo addressVo) throws DaoException {
        this.update(context, addressVo, "CustomerAddress.updateCustomerAddress");
    }

    public CustomerFranchiseInfoVo selectCustomerAndFranchiseInfo(CustomerCodeFilter customerCodeFilter) throws DaoException {
        return (CustomerFranchiseInfoVo) selectObject(customerCodeFilter, "CustomerAddress.selectCustomerAndFranchiseInfo");
    }
}
