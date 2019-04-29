package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateDetailVo;

import java.util.Map;

/**
 * Posted from CustomerProfileBaseRateDetailDao
 * <p>
 * Author TANDT 29-10-2015
 */
public class CustomerProfileBaseRateDetailDao extends BaseDao<CustomerProfileBaseRateDetailVo> {
    public CustomerProfileBaseRateDetailDao() {
        super();
    }

    public CustomerProfileBaseRateDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public CustomerProfileBaseRateDetailVo selectByZoneAndShipmentId(CustomerProfileFilter filter) throws DaoException {
        return select(filter, "CustomerProfileBaseRateDetail.selectByZoneAndShipmentId");
    }

    public void insert(Map<String, String> context, CustomerProfileBaseRateDetailVo customerProfileBaseRateDetailVo) throws DaoException {
        insert(context, customerProfileBaseRateDetailVo, "CustomerProfileBaseRateDetail.insert");
    }

    public void deleteByProfileId(Map<String, String> context, Long profileId) throws DaoException {
        delete(context, profileId, "CustomerProfileBaseRateDetail.deleteByProfileId");
    }
}
