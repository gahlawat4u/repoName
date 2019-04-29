package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerProfileBaseRateDao
 * <p>
 * Author TANDT 29-10-2015
 */
public class CustomerProfileBaseRateDao extends BaseDao<CustomerProfileBaseRateVo> {
    public CustomerProfileBaseRateDao() {
        super();
    }

    public CustomerProfileBaseRateDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public CustomerProfileBaseRateVo selectByShipmentTypeProfileId(CustomerProfileFilter filter) throws DaoException {
        return select(filter, "CustomerProfileBaseRate.selectByShipmentTypeProfileId");
    }

    public List<CustomerProfileBaseRateVo> selectByCarrierAndProfileId(CustomerProfileFilter filter) throws DaoException {
        return selectList(filter, "CustomerProfileBaseRate.selectByCarrierAndProfileId");
    }

    public void insert(Map<String, String> context, CustomerProfileBaseRateVo customerProfileBaseRateVo) throws DaoException {
        insert(context, customerProfileBaseRateVo, "CustomerProfileBaseRate.insert");
    }

    public void deleteByProfileId(Map<String, String> context, Long profileId) throws DaoException {
        delete(context, profileId, "CustomerProfileBaseRate.deleteByProfileId");
    }
}
