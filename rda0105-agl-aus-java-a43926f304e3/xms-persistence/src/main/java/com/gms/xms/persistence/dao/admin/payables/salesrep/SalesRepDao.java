package com.gms.xms.persistence.dao.admin.payables.salesrep;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.SalesRepVo;

import java.util.Map;

/**
 * Posted from SalesRepDao
 * <p>
 * Author dattrinh Mar 22, 2016 3:10:28 PM
 */
public class SalesRepDao extends BaseDao<SalesRepVo> {
    public SalesRepDao() {
        super();
    }

    public SalesRepDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void insertSalesRep(Map<String, String> context, SalesRepVo salesRepVo) throws DaoException {
        this.insert(context, salesRepVo, "SalesRep.insertSalesRep");
    }

    public void updateSalesRep(Map<String, String> context, SalesRepVo salesRepVo) throws DaoException {
        this.update(context, salesRepVo, "SalesRep.updateSalesRep");
    }

    public Long getSaleRepIdByCustomerCode(String customerCode) throws DaoException {
        return (Long) selectObject(customerCode, "SalesRep.getSaleRepIdByCustomerCode");
    }
}
