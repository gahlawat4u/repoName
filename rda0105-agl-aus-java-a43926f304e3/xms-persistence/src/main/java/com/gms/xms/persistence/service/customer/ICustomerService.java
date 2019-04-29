package com.gms.xms.persistence.service.customer;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerFilter;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerVo;
import com.gms.xms.txndb.vo.account.customers.CustomerListEntryVo;
import com.gms.xms.txndb.vo.account.customers.manage.CustomerAccountSetupVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    public CustomerVo selectByCode(String customerCode) throws DaoException;

    public List<CustomerDetailVo> searchCustomers(CustomerFilter filter) throws DaoException;

    public Long searchCustomersCount(CustomerFilter filter) throws DaoException;

    public List<CustomerListEntryVo> selectCustomerList(CustomerFilter filter) throws DaoException;

    public Long countCustomerList(CustomerFilter filter) throws DaoException;

    public List<BasicCustomerVo> selectByBasicCustomerFilter(BasicCustomerFilter filter) throws DaoException;

    public CustomerAccountSetupVo getCustomerAccountSetupInfo(String customerCode) throws DaoException;

    public ManageCustomerAddressVo getManageCustomerAddressInfo(String customerCode) throws DaoException;

    public ManageCustomerAddressVo getManageFranchiseAddressInfo(String franchiseCode) throws DaoException;

    public void updateCustomer(Map<String, String> context, CustomerVo customer) throws DaoException;

    public void updateCustomer(Map<String, String> context, CustomerVo customer, SqlSessionClient sessionClient) throws DaoException;

    public void insertCustomerAddress(Map<String, String> context, CustomerAddressVo addressVo) throws DaoException;

    public void updateCustomerAddress(Map<String, String> context, CustomerAddressVo addressVo) throws DaoException;

    public void insertCustomerBillingAddress(Map<String, String> context, CustomerBillingAddressVo billingAddressVo) throws DaoException;

    public void updateCustomerBillingAddress(Map<String, String> context, CustomerBillingAddressVo billingAddressVo) throws DaoException;

    public void saveCustomerAddress(Map<String, String> context, ManageCustomerAddressVo manageCustomerAddressVo) throws DaoException;

    public void saveCustomerAddress(Map<String, String> context, ManageCustomerAddressVo manageCustomerAddressVo, SqlSessionClient sessionClient) throws DaoException;

    public List<CustomerVo> selectByLogin(String userId) throws DaoException;

    public CustomerDetailVo getCustomerDetailByCode(String customerCode) throws DaoException;

    public Long getLastestCustCodeByFranCode(Long franchiseCode) throws DaoException;

    public List<CustomerVo> selectOwnerByFranchises(CustomerFilter filter) throws DaoException;

    public CustomerVo selectCustomerByFilter(ManageFranchiseFilter filter) throws DaoException;

    public List<CustomerVo> getQuotedCustomersByFranchises(String franchiseList) throws DaoException;

    public List<CustomerVo> getCustomersByFranchise(String franchiseCode) throws DaoException;
    
    public void updateCustomerProfileImage(Map<String, String> context,CustomerFilter filter) throws DaoException;
    
    public CustomerVo getCustomerProfileImage(String customerCode) throws DaoException;
}
