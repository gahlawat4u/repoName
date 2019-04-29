package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateExtVo;

import java.util.List;

/**
 * Posted from CustomerProfileBaseRateDetailDao
 * <p>
 * Author TANDT 29-10-2015
 */
public class CustomerProfileBaseRateExtDao extends BaseDao<CustomerProfileBaseRateExtVo> {

    public List<CustomerProfileBaseRateExtVo> selectBaseRateByShipment(CustomerProfileFilter filter) throws DaoException {
        return selectList(filter, "CustomerProfileBaseRateExt.selectBaseRateByShipment");
    }
}
