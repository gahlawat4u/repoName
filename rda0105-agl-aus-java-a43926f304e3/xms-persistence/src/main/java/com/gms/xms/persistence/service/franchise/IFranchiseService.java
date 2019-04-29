package com.gms.xms.persistence.service.franchise;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.FranchiseDetailFilter;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.NoteVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerBaseRateVo;
import com.gms.xms.txndb.vo.account.franchises.FranchiseDetailVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IFranchiseService
 * <p>
 * Author HungNT Date Jul 27, 2015
 */
public interface IFranchiseService {
    public FranchiseVo getFranchiseInfoByCode(String franchiseCode) throws DaoException;

    public List<FranchiseInfoVo> getFranchiseListManagedByUser(String userId) throws DaoException;

    public List<FranchiseDetailVo> getFranchises(FranchiseDetailFilter filter) throws DaoException;

    public long countFranchises(FranchiseDetailFilter filter) throws DaoException;

    public FranchiseVo selectFranchiseByFilter(ManageFranchiseFilter filter) throws DaoException;

    public FranchiseVo selectFranchiseByFranchiseCode(String franchiseCode) throws DaoException;

    public void update(Map<String, String> context, FranchiseVo franchise) throws DaoException;

    public void update(Map<String, String> context, FranchiseVo franchise, SqlSessionClient sessionClient) throws DaoException;

    public void updateManageFranchise(Map<String, String> context, FranchiseVo franchise, ManageCustomerAddressVo customerVo, CustomerCollectionVo collectionVo, ManageCustomerBaseRateVo customerBaseRateVo) throws Exception;

    public void updateManageFranchise(Map<String, String> context, FranchiseVo franchise, ManageCustomerAddressVo customerVo, CustomerCollectionVo collectionVo, ManageCustomerBaseRateVo customerBaseRateVo, SqlSessionClient sessionClient) throws Exception;

    public void insert(Map<String, String> context, FranchiseVo franchise) throws DaoException;

    public void insertManageFranchise(Map<String, String> context, FranchiseVo franchise, ManageCustomerAddressVo customerVo, CustomerCollectionVo collectionVo, ManageCustomerBaseRateVo customerBaseRateVo, NoteVo noteVo) throws Exception;

    public FranchiseVo selectFranchiseByFranchiseCodeExt(String franchiseCode) throws DaoException;
    
    public void updateFranchiseProfileImage(Map<String, String> context, FranchiseVo franchise) throws DaoException;
    
    public FranchiseVo selectByFranchiseCode(String franchiseCode) throws DaoException;
}
