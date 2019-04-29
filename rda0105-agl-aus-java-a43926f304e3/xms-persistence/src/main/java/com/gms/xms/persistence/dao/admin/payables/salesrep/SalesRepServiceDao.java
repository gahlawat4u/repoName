package com.gms.xms.persistence.dao.admin.payables.salesrep;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.SalesRepServiceVo;

import java.util.Map;

/**
 * Posted from SalesRepServiceDao
 * <p>
 * Author dattrinh Mar 22, 2016 3:46:24 PM
 */
public class SalesRepServiceDao extends BaseDao<SalesRepServiceVo> {
    public SalesRepServiceDao() {
        super();
    }

    public SalesRepServiceDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public SalesRepServiceVo selectByFilter(SalesRepServiceVo salesRepServiceVo) throws DaoException {
        return this.select(salesRepServiceVo, "SalesRepService.selectByFilter");
    }

    public void insertSalesRepService(Map<String, String> context, SalesRepServiceVo salesRepServiceVo) throws DaoException {
        this.insert(context, salesRepServiceVo, "SalesRepService.insertSalesRepService");
    }

    public void updateSalesRepService(Map<String, String> context, SalesRepServiceVo salesRepServiceVo) throws DaoException {
        this.update(context, salesRepServiceVo, "SalesRepService.updateSalesRepService");
    }

    public void deleteSalesRepService(Map<String, String> context, SalesRepServiceVo salesRepServiceVo) throws DaoException {
        this.delete(context, salesRepServiceVo, "SalesRepService.deleteSalesRepService");
    }
}
