package com.gms.xms.persistence.service.franchise;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.FranchiseDetailFilter;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.NoteDao;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.persistence.dao.franchise.FranchiseDetailDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerBaseRateVo;
import com.gms.xms.txndb.vo.account.franchises.FranchiseDetailVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from FranchiseServiceImp
 * <p>
 * Author HungNT Date Jul 27, 2015
 */
public class FranchiseServiceImp implements IFranchiseService {
    private FranchiseDao dao = new FranchiseDao();

    @Override
    public FranchiseVo getFranchiseInfoByCode(String franchiseCode) throws DaoException {
        return dao.selectFranchiseByFranchiseCode(franchiseCode);
    }

    @Override
    public List<FranchiseInfoVo> getFranchiseListManagedByUser(String userId) throws DaoException {
        UserDao userDao = new UserDao();
        UserVo userVo = userDao.getUserById(userId);
        return dao.getFranchiseListManagedByUser(userVo);
    }

    @Override
    public List<FranchiseDetailVo> getFranchises(FranchiseDetailFilter filter) throws DaoException {
        FranchiseDetailDao detailDao = new FranchiseDetailDao();
        return detailDao.selectByFilter(filter);
    }

    @Override
    public long countFranchises(FranchiseDetailFilter filter) throws DaoException {
        FranchiseDetailDao detailDao = new FranchiseDetailDao();
        return detailDao.countByFilter(filter);
    }

    @Override
    public FranchiseVo selectFranchiseByFilter(ManageFranchiseFilter filter) throws DaoException {
        FranchiseDao dao = new FranchiseDao();
        return dao.selectFranchiseByFilter(filter);
    }

    @Override
    public FranchiseVo selectFranchiseByFranchiseCode(String franchiseCode) throws DaoException {
        FranchiseDao dao = new FranchiseDao();
        return dao.selectFranchiseByFranchiseCode(franchiseCode);
    }

    @Override
    public FranchiseVo selectFranchiseByFranchiseCodeExt(String franchiseCode) throws DaoException {
        FranchiseDao dao = new FranchiseDao();
        return dao.selectFranchiseByFranchiseCodeExt(franchiseCode);
    }

    @Override
    public void update(Map<String, String> context, FranchiseVo franchise) throws DaoException {
        FranchiseDao dao = new FranchiseDao();
        dao.update(context, franchise);
    }

    @Override
    public void update(Map<String, String> context, FranchiseVo franchise, SqlSessionClient sessionClient) throws DaoException {
        FranchiseDao dao = new FranchiseDao();
        dao.update(context, franchise);
    }

    @Override
    public void insert(Map<String, String> context, FranchiseVo franchise) throws DaoException {
        FranchiseDao dao = new FranchiseDao();
        dao.insert(context, franchise);
    }

    @Override
    public void updateManageFranchise(Map<String, String> context, FranchiseVo franchise, ManageCustomerAddressVo customerVo, CustomerCollectionVo collectionVo, ManageCustomerBaseRateVo customerBaseRateVo) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            FranchiseDao franchiseDao = new FranchiseDao(sessionClient);
            franchiseDao.update(context, franchise);
            if (customerVo != null) {
                CustomerAddressDao customerAddressDao = new CustomerAddressDao(sessionClient);
                CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao(sessionClient);
                CustomerAddressVo addressVo = customerAddressDao.selectByCode(String.valueOf(franchise.getFranchiseCode()));
                if (customerVo.getAddress() != null) {
                    if (addressVo != null) {
                        customerAddressDao.updateCustomerAddress(context, customerVo.getAddress());
                    } else {
                        customerAddressDao.insertCustomerAddress(context, customerVo.getAddress());
                    }
                }
                CustomerBillingAddressVo billingAddressVo = billingAddressDao.selectBillingCustomerByCode(franchise.getFranchiseCode());
                if (customerVo.getBillingAddress() != null) {
                    if (billingAddressVo != null) {
                        billingAddressDao.updateCustomerBillingAddress(context, customerVo.getBillingAddress());
                    } else {
                        billingAddressDao.insertCustomerBillingAddress(context, customerVo.getBillingAddress());
                    }
                }
            }
            if (collectionVo.getUserId() != null && collectionVo.getUserType() != null) {
                CustomerCollectionDao collectionDao = new CustomerCollectionDao(sessionClient);
                collectionDao.update(context, collectionVo);
            }
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll-back transaction
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void updateManageFranchise(Map<String, String> context, FranchiseVo franchise, ManageCustomerAddressVo customerVo, CustomerCollectionVo collectionVo, ManageCustomerBaseRateVo customerBaseRateVo, SqlSessionClient sessionClient) throws Exception {
        sessionClient.startTransaction();
        try {
            FranchiseDao franchiseDao = new FranchiseDao(sessionClient);
            franchiseDao.update(context, franchise);
            if (customerVo != null) {
                CustomerAddressDao customerAddressDao = new CustomerAddressDao(sessionClient);
                CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao(sessionClient);
                CustomerAddressVo addressVo = customerAddressDao.selectByCode(String.valueOf(franchise.getFranchiseCode()));
                if (customerVo.getAddress() != null) {
                    if (addressVo != null) {
                        customerAddressDao.updateCustomerAddress(context, customerVo.getAddress());
                    } else {
                        customerAddressDao.insertCustomerAddress(context, customerVo.getAddress());
                    }
                }
                CustomerBillingAddressVo billingAddressVo = billingAddressDao.selectBillingCustomerByCode(franchise.getFranchiseCode());
                if (customerVo.getBillingAddress() != null) {
                    if (billingAddressVo != null) {
                        billingAddressDao.updateCustomerBillingAddress(context, customerVo.getBillingAddress());
                    } else {
                        billingAddressDao.insertCustomerBillingAddress(context, customerVo.getBillingAddress());
                    }
                }
            }
            if (collectionVo.getUserId() != null && collectionVo.getUserType() != null) {
                CustomerCollectionDao collectionDao = new CustomerCollectionDao(sessionClient);
                collectionDao.update(context, collectionVo);
            }
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll-back transaction
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void insertManageFranchise(Map<String, String> context, FranchiseVo franchise, ManageCustomerAddressVo customerVo, CustomerCollectionVo collectionVo, ManageCustomerBaseRateVo customerBaseRateVo, NoteVo noteVo) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            FranchiseDao franchiseDao = new FranchiseDao(sessionClient);
            franchiseDao.insert(context, franchise);
            if (customerVo != null) {
                CustomerAddressDao customerAddressDao = new CustomerAddressDao(sessionClient);
                CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao(sessionClient);
                if (customerVo.getAddress() != null) {
                    customerVo.getAddress().setUserType(1);
                    customerVo.getAddress().setCustomerCode(franchise.getFranchiseCode());
                    customerAddressDao.insertCustomerAddress(context, customerVo.getAddress());
                }
                if (customerVo.getBillingAddress() != null) {
                    customerVo.getBillingAddress().setUsertype(1);
                    customerVo.getBillingAddress().setCustomerCode(franchise.getFranchiseCode());
                    billingAddressDao.insertCustomerBillingAddress(context, customerVo.getBillingAddress());
                }
            }
            if (collectionVo != null) {
                collectionVo.setUserId(franchise.getId());
                collectionVo.setUserType(1);
                CustomerCollectionDao collectionDao = new CustomerCollectionDao(sessionClient);
                CustomerCollectionVo collectionVo2 = collectionDao.selectCollectionByUser(collectionVo);
                if (collectionVo2 == null) {
                    collectionDao.insert(context, collectionVo);
                } else {
                    collectionDao.update(context, collectionVo);
                }
            }

            if (noteVo != null && StringUtils.isNotEmpty(noteVo.getNote())) {
                noteVo.setAccountNo(franchise.getFranchiseCode());
                noteVo.setUserId(franchise.getId());
                noteVo.setNoteType((byte) 1);
                Date date = new Date();
                noteVo.setModifyDate(date);
                noteVo.setCheck(false);
                noteVo.setCurrent((byte) 0);
                noteVo.setPaymentId(0L);
                noteVo.setInvoiceCode("");
                NoteDao noteDao = new NoteDao(sessionClient);
                noteDao.insert(context, noteVo);
            }
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Roll-back transaction
            sessionClient.rollback();
            throw e;
        }
    }
    
    @Override
    public void updateFranchiseProfileImage(Map<String, String> context, FranchiseVo franchise) throws DaoException {
    	FranchiseDao dao = new FranchiseDao();
          dao.updateFranchiseProfileImage(context ,franchise);
    }
    
    @Override
    public FranchiseVo selectByFranchiseCode(String franchiseCode) throws DaoException {
    	FranchiseDao dao = new FranchiseDao();
    	return dao.selectByFranchiseCode(franchiseCode);
    }
   
}