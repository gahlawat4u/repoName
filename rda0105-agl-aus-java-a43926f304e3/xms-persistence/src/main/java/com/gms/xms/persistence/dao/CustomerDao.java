package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CustomerDetailVo;
import com.gms.xms.txndb.vo.CustomerFilter;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.account.customers.CustomerListEntryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerDao
 * <p>
 * Author DatTV Date Apr 18, 2015 9:39:42 AM
 */
public class CustomerDao extends BaseDao<CustomerVo> {
    public CustomerDao() {
        super();
    }

    public CustomerDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public Integer countByCode(Long customerCode) throws DaoException {
        return (Integer) selectObject(customerCode, "Customer.countByCode");
    }

    /**
     * Get customer's minimum base charge by customer code
     *
     * @param {@link Long} customerCode
     * @return {@link Float}
     * @throws DaoException
     */
    public CustomerVo selectMinimumBaseChargeByCustomerCode(Long customerCode) throws DaoException {
        return select(customerCode, "Customer.selectMinimumBaseChargeByCustomerCode");
    }

    /**
     * Get List CustomerCode by Login Is Admin
     *
     * @return
     * @throws DaoException
     */
    public List<CustomerVo> selectCustomerCodeByLoginIsAdmin() throws DaoException {
        return selectList(null, "Customer_SelectCustomerCodeByLoginIsAdmin");
    }

    /**
     * Get List CustomerCode by Login Is FranchiseAccount
     *
     * @return
     * @throws DaoException
     */
    public List<CustomerVo> selectCustomerCodeByLoginIsFranchiseAccount(UserVo userVo) throws DaoException {
        return selectList(userVo, "Customer_SelectCustomerCodeByLoginIsFranchiseAccount");
    }

    /**
     * Get List CustomerCode by Login Is Sale Manager
     *
     * @return
     * @throws DaoException
     */
    public List<CustomerVo> selectCustomerCodeByLoginIsSaleManager(UserVo userVo) throws DaoException {
        return selectList(userVo, "Customer_SelectCustomerCodeByLoginIsSaleManager");
    }

    /**
     * Get List CustomerCode by Login Is Sale Rep
     *
     * @return
     * @throws DaoException
     */
    public List<CustomerVo> selectCustomerCodeByLoginIsSaleRep(UserVo userVo) throws DaoException {
        return selectList(userVo, "Customer_SelectCustomerCodeByLoginIsSaleRep");
    }

    /**
     * Get List CustomerCode by Login Is Sale Telemarketer
     *
     * @return
     * @throws DaoException
     */
    public List<CustomerVo> selectCustomerCodeByLoginIsSaleTelemarketer(UserVo userVo) throws DaoException {
        return selectList(userVo, "Customer_SelectCustomerCodeByLoginIsSaleTelemarketer");
    }

    @SuppressWarnings("unchecked")
    public List<CustomerDetailVo> searchCustomers(CustomerFilter filter) throws DaoException {
        return (List<CustomerDetailVo>) (Object) selectObjectList(filter, "Customer.searchCustomers");
    }

    public Long searchCustomersCount(CustomerFilter filter) throws DaoException {
        return (Long) selectObject(filter, "Customer.searchCustomersCount");
    }

    @SuppressWarnings("unchecked")
    public List<CustomerListEntryVo> selectCustomerList(CustomerFilter filter) throws DaoException {
        return (List<CustomerListEntryVo>) (Object) selectObjectList(filter, "Customer.selectCustomerList");
    }

    public Long countCustomerList(CustomerFilter filter) throws DaoException {
        return (Long) selectObject(filter, "Customer.countCustomerList");
    }

    public void updateCustomer(Map<String, String> context, CustomerVo customerVo) throws DaoException {
        this.update(context, customerVo, "Customer.updateCustomer");
    }

    public void updateActiveDateByCustomerCode(Map<String, String> context, CustomerVo customerVo) throws DaoException {
        this.update(context, customerVo, "Customer.updateActiveDateByCustomerCode");
    }

    public CustomerVo selectByCode(String customerCode) throws DaoException {
        return this.select(customerCode, "Customer.selectByCode");
    }

    public List<CustomerVo> selectByFranchises(List<String> franchiseCodeList) throws DaoException {
        return this.selectList(franchiseCodeList, "Customer.selectByFranchises");
    }

    public CustomerDetailVo getCustomerDetailByCode(String customerCode) throws DaoException {
        return (CustomerDetailVo) this.selectObject(customerCode, "Customer.getCustomerDetailByCode");
    }

    public Long getLastestCustCodeByFranCode(Long franchiseCode) throws DaoException {
        return (Long) this.selectObject(franchiseCode, "Customer.getLastestCustCodeByFranCode");
    }

    public List<CustomerVo> selectOwnerByFranchises(CustomerFilter filter) throws DaoException {
        return this.selectList(filter, "Customer.selectOwnerByFranchises");
    }

    public CustomerVo selectCustomerByFilter(ManageFranchiseFilter filter) throws DaoException {
        return this.select(filter, "Customer.selectCustomerByFilter");
    }

    public List<CustomerVo> getQuotedCustomersByFranchises(String franchiseList) throws DaoException {
        return this.selectList(franchiseList, "Customer.getQuotedCustomersByFranchises");
    }

    public List<CustomerVo> getCustomersByFranchise(String franchiseCode) throws DaoException {
        return this.selectList(franchiseCode, "Customer.getCustomersByFranchise");
    }

    public Long selectSaleRepByCustomerCode(String customerCode) throws DaoException {
        return (Long) selectObject(customerCode, "Customer.selectSaleRepByCustomerCode");
    }

    public Integer checkAglWarranty(Long customerCode) throws DaoException {
        return (Integer) selectObject(customerCode, "Customer.checkAglWarranty");
    }

    public void insertCustomer(Map<String, String> context, CustomerVo customer) throws DaoException {
        this.insert(context, customer, "Customer.insertCustomer");
    }

    public Double getCustomerBaseChargeByCode(String customerCode) throws DaoException {
        return (Double) this.selectObject(customerCode, "Customer.getCustomerBaseChargeByCode");
    }

    public Long getUserIdByCustomerCode(String customerCode) throws DaoException {
        return (Long) selectObject(customerCode, "Customer.getUserIdByCustomerCode");
    }

	public void updateCustomerProfileImage(Map<String, String> context,CustomerFilter filter) throws DaoException{
		this.update(context, filter, "Customer.updateCustomerProfileImage");
	}
	
	 public CustomerVo getCustomerProfileImage(String customerCode) throws DaoException {
	        return this.select(customerCode, "Customer.getCustomerProfileImage");
	    }
}