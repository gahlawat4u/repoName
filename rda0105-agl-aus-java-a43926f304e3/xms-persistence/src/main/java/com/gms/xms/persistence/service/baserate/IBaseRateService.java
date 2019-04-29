package com.gms.xms.persistence.service.baserate;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.account.customers.manage.SaveCustomerBaseRateVo;
import com.gms.xms.txndb.vo.admin.customerprofile.baserate.SaveCustomerProfileBaseRateVo;

import java.util.Map;

/**
 * Posted from Apr 7, 2016 9:24:26 AM
 * <p>
 * Author dattrinh
 */
public interface IBaseRateService {
    public void saveCustBaseRates(Map<String, String> context, SaveCustomerBaseRateVo customerBaseRateVo) throws DaoException;

    public void saveCustBaseRates(Map<String, String> context, SaveCustomerBaseRateVo customerBaseRateVo, SqlSessionClient sessionClient) throws DaoException;

    public void saveFranBaseRates(Map<String, String> context, SaveCustomerBaseRateVo franchiseBaseRateVo) throws DaoException;

    public void saveFranBaseRates(Map<String, String> context, SaveCustomerBaseRateVo franchiseBaseRateVo, SqlSessionClient sessionClient) throws DaoException;

    public void saveCustProfileBaseRate(Map<String, String> context, SaveCustomerProfileBaseRateVo profileBaseRateVo) throws Exception;

    public void saveCustProfileBaseRate(Map<String, String> context, SaveCustomerProfileBaseRateVo profileBaseRateVo, SqlSessionClient sessionClient) throws Exception;
}
