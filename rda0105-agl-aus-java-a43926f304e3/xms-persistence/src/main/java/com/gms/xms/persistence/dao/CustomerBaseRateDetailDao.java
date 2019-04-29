package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CustomerBaseRateDetailVo;

import java.util.Map;

/**
 * File Name: CustomerBaseRateDetailDao.java <br/>
 * Author: TANDT <br/>
 * Create Date: 14-03-2016 <br/>
 * Project Name: xms-persistence <br/>
 * package Name: com.gms.xms.persistence.dao <br/>
 */
public class CustomerBaseRateDetailDao extends BaseDao<CustomerBaseRateDetailVo> {

    public CustomerBaseRateDetailDao() {
        super();
    }

    public CustomerBaseRateDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void delete(Map<String, String> context, Long customerBaseRateId) throws DaoException {
        delete(context, customerBaseRateId, "CustomerBaseRateDetail.delete");
    }

    public void insert(Map<String, String> context, CustomerBaseRateDetailVo customerBaseRateDetail) throws DaoException {
        insert(context, customerBaseRateDetail, "CustomerBaseRateDetail.insert");
    }

    public void deleteByCustomerCode(Map<String, String> context, String customerCode) throws DaoException {
        delete(context, customerCode, "CustomerBaseRateDetail.deleteByCustomerCode");
    }
}
