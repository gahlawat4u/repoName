package com.gms.xms.persistence.dao.customers;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerFilter;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerVo;
import com.gms.xms.txndb.vo.account.customers.manage.CustomerAccountSetupVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;

import java.util.List;

/**
 * Posted from CustomerInfoDao
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class CustomerInfoDao extends BaseDao<BasicCustomerVo> {
    public List<BasicCustomerVo> selectByBasicCustomerFilter(BasicCustomerFilter filter) throws DaoException {
        return selectList(filter, "CustomerInfo.selectByBasicCustomerFilter");
    }

    public CustomerAccountSetupVo getCustomerAccountSetupInfo(String customerCode) throws DaoException {
        return (CustomerAccountSetupVo) selectObject(customerCode, "CustomerInfo.getCustomerAccountSetupInfo");
    }

    public ManageCustomerAddressVo getManageCustomerAddressInfo(String customerCode) throws DaoException {
        return (ManageCustomerAddressVo) selectObject(customerCode, "CustomerInfo.getManageCustomerAddressInfo");
    }

    public ManageCustomerAddressVo getManageFranchiseAddressInfo(String franchiseCode) throws DaoException {
        return (ManageCustomerAddressVo) selectObject(franchiseCode, "CustomerInfo.getManageFranchiseAddressInfo");
    }
}
