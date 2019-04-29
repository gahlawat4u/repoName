package com.gms.xms.persistence.service.customerprofile;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateDetailVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateExtVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateVo;
import com.gms.xms.txndb.vo.admin.customerprofile.manage.AddNewCutomerProfileVo;
import com.gms.xms.txndb.vo.admin.customerprofile.manage.SaveCustomerProfileVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ICustomerProfileService
 * <p>
 * Author DatTV Oct 12, 2015
 */
public interface ICustomerProfileService {
    public List<CustomerProfileVo> selectByFranchiseCode(String franchiseCode) throws DaoException;

    public CustomerProfileVo selectByProfileId(Long profileId) throws DaoException;

    public List<CustomerProfileVo> selectCustomerProfilesByFilter(CustomerProfileFilter filter) throws DaoException;

    public void updateCustomerProfiles(Map<String, String> context, CustomerProfileVo customerProfileVo) throws DaoException;

    public void updateCustomerProfiles(Map<String, String> context, CustomerProfileVo customerProfileVo, SqlSessionClient sessionClient) throws DaoException;

    public void insertCustomerProfiles(Map<String, String> context, CustomerProfileVo customerProfileVo) throws DaoException;

    public void insertCustomerProfiles(Map<String, String> context, CustomerProfileVo customerProfileVo, SqlSessionClient sessionClient) throws DaoException;

    public Integer checkProfileName(CustomerProfileFilter filter) throws DaoException;

    public CustomerProfileBaseRateVo selectByShipmentTypeProfileId(CustomerProfileFilter filter) throws DaoException;

    public CustomerProfileBaseRateDetailVo selectByZoneAndShipmentId(CustomerProfileFilter filter) throws DaoException;

    public List<CustomerProfileBaseRateExtVo> selectBaseRateByShipment(CustomerProfileFilter filter) throws DaoException;

    public List<CustomerProfileBaseRateVo> selectByCarrierAndProfileId(CustomerProfileFilter filter) throws DaoException;

    public void addSaveCustomerProfiles(Map<String, String> context, AddNewCutomerProfileVo addNewCutomerProfileVo) throws Exception;

    public void updateSaveCustomerProfiles(Map<String, String> context, SaveCustomerProfileVo saveCustomerProfile) throws Exception;
}
