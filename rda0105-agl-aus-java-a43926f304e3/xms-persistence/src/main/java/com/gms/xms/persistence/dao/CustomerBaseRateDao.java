package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CustomerBaseRateFilter;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerBaseRateDao
 * <p>
 * Author HungNT Date Apr 22, 2015
 */
public class CustomerBaseRateDao extends BaseDao<CustomerBaseRateVo> {

    public CustomerBaseRateDao() {
        super();
    }

    public CustomerBaseRateDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * Get customer base rate detail by customer code and shipment type id
     *
     * @param {@link CustomerBaseRateFilter} customerBaseRateFilter
     * @return {@link CustomerBaseRateVo} customer base rate
     * @throws DaoException
     */
    public CustomerBaseRateVo selectCustomerBaseRateByCustomerCodeAndShipmentTypeId(CustomerBaseRateFilter customerBaseRateFilter) throws DaoException {
        return select(customerBaseRateFilter, "CustomerBaseRate.selectCustomerBaseRateByCustomerCodeAndShipmentTypeId");
    }

    /**
     * Get customer base rate by zone
     *
     * @param {@link CustomerBaseRateFilter} customerBaseRateFilter
     * @return {@link Float} rate
     * @throws DaoException
     */
    public Double selectCustomerBaseRateByZone(CustomerBaseRateFilter customerBaseRateFilter) throws DaoException {
        return (Double) selectObject(customerBaseRateFilter, "CustomerBaseRate.selectCustomerBaseRateByZone");
    }

    public List<CustomerBaseRateVo> getBaseRateDetailByFilter(CustomerBaseRateFilter filter) throws DaoException {
        return this.selectList(filter, "CustomerBaseRate.getBaseRateDetailByFilter");
    }

    public void update(Map<String, String> context, CustomerBaseRateVo customerBaseRate) throws DaoException {
        update(context, customerBaseRate, "CustomerBaseRate.updateById");
    }

    public void insert(Map<String, String> context, CustomerBaseRateVo customerBaseRate) throws DaoException {
        insert(context, customerBaseRate, "CustomerBaseRate.insert");
    }

    public void delete(Map<String, String> context, Long customerBaseRateId) throws DaoException {
        delete(context, customerBaseRateId, "CustomerBaseRate.delete");
    }

    public void deleteByCustomerCode(Map<String, String> context, String customerCode) throws DaoException {
        delete(context, customerCode, "CustomerBaseRate.deleteByCustomerCode");
    }
}
