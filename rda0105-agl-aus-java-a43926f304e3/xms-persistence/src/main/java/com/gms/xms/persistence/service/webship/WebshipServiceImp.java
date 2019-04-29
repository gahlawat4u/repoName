package com.gms.xms.persistence.service.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.WebshipFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.AccountServiceDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.txndb.vo.AccountServiceVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.txndb.vo.webship.login.WebshipResetFilter;

import java.util.List;
import java.util.Map;

public class WebshipServiceImp implements IWebshipService {

    @Override
    public List<WebshipVo> selectByFilter(WebshipFilter filter) throws DaoException {
        WebshipDao webshipDao = new WebshipDao();
        return webshipDao.selectByFilter(filter);
    }

    @Override
    public long countByFilter(WebshipFilter filter) throws DaoException {
        WebshipDao webshipDao = new WebshipDao();
        return webshipDao.countByFilter(filter);
    }

    @Override
    public void saveCustomerWebshipSettings(Map<String, String> context, CustomerVo customer, List<AccountServiceVo> accountServiceVos) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        CustomerDao customerDao = new CustomerDao(sessionClient);
        AccountServiceDao accountServiceDao = new AccountServiceDao(sessionClient);
        try {
            // Update customer
            customerDao.updateCustomer(context, customer);
            // Delete account service by customer code
            accountServiceDao.deleteByCustCode(context, customer.getCustomerCode());
            // Insert account service setting list
            for (AccountServiceVo accountServiceVo : accountServiceVos) {
                accountServiceDao.insert(context, accountServiceVo);
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            sessionClient.rollback();
            throw new DaoException(e);
        }
    }

    @Override
    public void saveCustomerWebshipSettings(Map<String, String> context, CustomerVo customer, List<AccountServiceVo> accountServiceVos, SqlSessionClient sessionClient) throws DaoException {
        CustomerDao customerDao = new CustomerDao(sessionClient);
        AccountServiceDao accountServiceDao = new AccountServiceDao(sessionClient);
        // Update customer
        customerDao.updateCustomer(context, customer);
        // Delete account service by customer code
        accountServiceDao.deleteByCustCode(context, customer.getCustomerCode());
        // Insert account service setting list
        for (AccountServiceVo accountServiceVo : accountServiceVos) {
            accountServiceDao.insert(context, accountServiceVo);
        }
    }

    @Override
    public void insertWebship(Map<String, String> context, WebshipVo webshipVo) throws DaoException {
        WebshipDao webshipDao = new WebshipDao();
        webshipDao.insert(context, webshipVo);
    }

    @Override
    public void updateWebship(Map<String, String> context, WebshipVo webshipVo) throws DaoException {
        WebshipDao webshipDao = new WebshipDao();
        webshipDao.update(context, webshipVo);
    }

    @Override
    public WebshipVo selectWebshipById(Long webshipId) throws DaoException {
        WebshipDao webshipDao = new WebshipDao();
        return webshipDao.selectById(webshipId);
    }

    @Override
    public WebshipVo checkInfoResetPassword(WebshipResetFilter filter) throws DaoException {
        WebshipDao webshipDao = new WebshipDao();
        return webshipDao.checkInfoResetPassword(filter);
    }

    @Override
    public void saveFranchiseWebshipSettings(Map<String, String> context, FranchiseVo franchise, List<AccountServiceVo> accountServiceVos) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        FranchiseDao franchiseDao = new FranchiseDao(sessionClient);
        AccountServiceDao accountServiceDao = new AccountServiceDao(sessionClient);
        try {
            // Update franchise
            franchiseDao.update(context, franchise);
            // Delete account service by franchise code
            accountServiceDao.deleteByCustCode(context, franchise.getFranchiseCode());
            // Insert account service setting list
            for (AccountServiceVo accountServiceVo : accountServiceVos) {
                accountServiceDao.insert(context, accountServiceVo);
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            sessionClient.rollback();
            throw new DaoException(e);
        }
    }

    @Override
    public void saveFranchiseWebshipSettings(Map<String, String> context, FranchiseVo franchise, List<AccountServiceVo> accountServiceVos, SqlSessionClient sessionClient) throws DaoException {
        FranchiseDao franchiseDao = new FranchiseDao(sessionClient);
        AccountServiceDao accountServiceDao = new AccountServiceDao(sessionClient);
        try {
            // Update franchise
            franchiseDao.update(context, franchise);
            // Delete account service by franchise code
            accountServiceDao.deleteByCustCode(context, franchise.getFranchiseCode());
            // Insert account service setting list
            for (AccountServiceVo accountServiceVo : accountServiceVos) {
                accountServiceDao.insert(context, accountServiceVo);
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll back transaction
            sessionClient.rollback();
            throw new DaoException(e);
        }
    }
}
