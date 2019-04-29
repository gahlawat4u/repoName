package com.gms.xms.persistence.service.customer;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.customers.CustomerInfoDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerFilter;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerVo;
import com.gms.xms.txndb.vo.account.customers.CustomerListEntryVo;
import com.gms.xms.txndb.vo.account.customers.manage.CustomerAccountSetupVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerServiceImp implements ICustomerService {

    private CustomerDao customerDao = new CustomerDao();

    @Override
    public List<CustomerDetailVo> searchCustomers(CustomerFilter filter) throws DaoException {
        return customerDao.searchCustomers(filter);
    }

    @Override
    public Long searchCustomersCount(CustomerFilter filter) throws DaoException {
        return customerDao.searchCustomersCount(filter);
    }

    @Override
    public List<CustomerListEntryVo> selectCustomerList(CustomerFilter filter) throws DaoException {
        return customerDao.selectCustomerList(filter);
    }

    @Override
    public Long countCustomerList(CustomerFilter filter) throws DaoException {
        return customerDao.countCustomerList(filter);
    }

    @Override
    public List<BasicCustomerVo> selectByBasicCustomerFilter(BasicCustomerFilter filter) throws DaoException {
        CustomerInfoDao customerInfoDao = new CustomerInfoDao();
        return customerInfoDao.selectByBasicCustomerFilter(filter);
    }

    @Override
    public CustomerAccountSetupVo getCustomerAccountSetupInfo(String customerCode) throws DaoException {
        CustomerInfoDao customerInfoDao = new CustomerInfoDao();
        return customerInfoDao.getCustomerAccountSetupInfo(customerCode);
    }

    @Override
    public ManageCustomerAddressVo getManageCustomerAddressInfo(String customerCode) throws DaoException {
        CustomerInfoDao customerInfoDao = new CustomerInfoDao();
        return customerInfoDao.getManageCustomerAddressInfo(customerCode);
    }

    @Override
    public ManageCustomerAddressVo getManageFranchiseAddressInfo(String franchiseCode) throws DaoException {
        CustomerInfoDao customerInfoDao = new CustomerInfoDao();
        return customerInfoDao.getManageFranchiseAddressInfo(franchiseCode);
    }

    @Override
    public void updateCustomer(Map<String, String> context, CustomerVo customer) throws DaoException {
        customerDao.updateCustomer(context, customer);
    }

    @Override
    public void updateCustomer(Map<String, String> context, CustomerVo customer, SqlSessionClient sessionClient) throws DaoException {
        CustomerDao customerDao = new CustomerDao(sessionClient);
        customerDao.updateCustomer(context, customer);
    }

    @Override
    public void insertCustomerAddress(Map<String, String> context, CustomerAddressVo addressVo) throws DaoException {
        CustomerAddressDao addressDao = new CustomerAddressDao();
        addressDao.insertCustomerAddress(context, addressVo);
    }

    @Override
    public void updateCustomerAddress(Map<String, String> context, CustomerAddressVo addressVo) throws DaoException {
        CustomerAddressDao addressDao = new CustomerAddressDao();
        addressDao.updateCustomerAddress(context, addressVo);
    }

    @Override
    public void insertCustomerBillingAddress(Map<String, String> context, CustomerBillingAddressVo billingAddressVo) throws DaoException {
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
        billingAddressDao.insertCustomerBillingAddress(context, billingAddressVo);
    }

    @Override
    public void updateCustomerBillingAddress(Map<String, String> context, CustomerBillingAddressVo billingAddressVo) throws DaoException {
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
        billingAddressDao.updateCustomerBillingAddress(context, billingAddressVo);
    }

    @Override
    public void saveCustomerAddress(Map<String, String> context, ManageCustomerAddressVo manageCustomerAddressVo) throws DaoException {
        // Create a new transaction
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        CustomerAddressDao customerAddressDao = new CustomerAddressDao(sessionClient);
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao(sessionClient);
        try {
            CustomerAddressVo addressVo = customerAddressDao.selectByCode(manageCustomerAddressVo.getCustomerCode());
            if (addressVo != null) {
                customerAddressDao.updateCustomerAddress(context, manageCustomerAddressVo.getAddress());
            } else {
                customerAddressDao.insertCustomerAddress(context, manageCustomerAddressVo.getAddress());
            }
            CustomerBillingAddressVo billingAddressVo = billingAddressDao.selectBillingCustomerByCode(Long.valueOf(manageCustomerAddressVo.getCustomerCode()));
            if (billingAddressVo != null) {
                billingAddressDao.updateCustomerBillingAddress(context, manageCustomerAddressVo.getBillingAddress());
            } else {
                billingAddressDao.insertCustomerBillingAddress(context, manageCustomerAddressVo.getBillingAddress());
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll-back transaction
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void saveCustomerAddress(Map<String, String> context, ManageCustomerAddressVo manageCustomerAddressVo, SqlSessionClient sessionClient) throws DaoException {
        // Create a new transaction
        CustomerAddressDao customerAddressDao = new CustomerAddressDao(sessionClient);
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao(sessionClient);
        CustomerAddressVo addressVo = customerAddressDao.selectByCode(manageCustomerAddressVo.getCustomerCode());
        if (addressVo != null) {
            customerAddressDao.updateCustomerAddress(context, manageCustomerAddressVo.getAddress());
        } else {
            customerAddressDao.insertCustomerAddress(context, manageCustomerAddressVo.getAddress());
        }
        CustomerBillingAddressVo billingAddressVo = billingAddressDao.selectBillingCustomerByCode(Long.valueOf(manageCustomerAddressVo.getCustomerCode()));
        if (billingAddressVo != null) {
            billingAddressDao.updateCustomerBillingAddress(context, manageCustomerAddressVo.getBillingAddress());
        } else {
            billingAddressDao.insertCustomerBillingAddress(context, manageCustomerAddressVo.getBillingAddress());
        }
    }

    @Override
    public CustomerVo selectByCode(String customerCode) throws DaoException {
        return customerDao.selectByCode(customerCode);
    }

    @Override
    public List<CustomerVo> selectByLogin(String userId) throws DaoException {
        FranchiseDao franchiseDao = new FranchiseDao();
        UserDao userDao = new UserDao();
        UserVo userVo = userDao.getUserById(userId);
        List<FranchiseInfoVo> franchiseInfoVos = franchiseDao.getFranchiseListManagedByUser(userVo);
        List<String> franchiseCodeList = new ArrayList<String>();
        for (FranchiseInfoVo franchiseInfoVo : franchiseInfoVos) {
            franchiseCodeList.add(franchiseInfoVo.getCode());
        }
        return customerDao.selectByFranchises(franchiseCodeList);
    }

    @Override
    public CustomerDetailVo getCustomerDetailByCode(String customerCode) throws DaoException {
        return customerDao.getCustomerDetailByCode(customerCode);
    }

    @Override
    public Long getLastestCustCodeByFranCode(Long franchiseCode) throws DaoException {
        return customerDao.getLastestCustCodeByFranCode(franchiseCode);
    }

    @Override
    public List<CustomerVo> selectOwnerByFranchises(CustomerFilter filter) throws DaoException {
        return customerDao.selectOwnerByFranchises(filter);
    }

    @Override
    public CustomerVo selectCustomerByFilter(ManageFranchiseFilter filter) throws DaoException {
        CustomerDao dao = new CustomerDao();
        return dao.selectCustomerByFilter(filter);
    }

    @Override
    public List<CustomerVo> getQuotedCustomersByFranchises(String franchiseList) throws DaoException {
        CustomerDao dao = new CustomerDao();
        return dao.getQuotedCustomersByFranchises(franchiseList);
    }

    @Override
    public List<CustomerVo> getCustomersByFranchise(String franchiseCode) throws DaoException {
        CustomerDao dao = new CustomerDao();
        return dao.getCustomersByFranchise(franchiseCode);
    }
    
    @Override
    public void updateCustomerProfileImage(Map<String, String> context,CustomerFilter filter) throws DaoException {
    	 CustomerDao dao = new CustomerDao();
          dao.updateCustomerProfileImage(context ,filter);
    }
    
    @Override
    public CustomerVo getCustomerProfileImage(String customerCode) throws DaoException {
    	 CustomerDao dao = new CustomerDao();
         return dao.getCustomerProfileImage(customerCode);
    }
    
}