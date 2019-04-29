package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TollConnoteFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.toll.TollShipmentVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from TollConnoteDao
 * <p>
 * Author @author HungNT Feb 16, 2016
 */
public class TollConnoteDao extends BaseDao<Object> {
    public TollConnoteDao() {
        super();
    }

    public TollConnoteDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public String selectConnumberByShipmentId(TollConnoteFilter tollConnoteFilter) throws DaoException {
        return (String) selectObject(tollConnoteFilter, "TollConnote.selectConnumberByShipmentId");
    }

    public List<TollShipmentVo> getTollShipmentGenerateList(Long serviceId) throws DaoException {
        return selectList(serviceId, "TollConnote.getTollShipmentGenerateList");
    }

    public List<TollShipmentVo> getTollShipmentByCustomerGenerateList(Long customerCode) throws DaoException {
        return selectList(customerCode, "TollConnote.getTollShipmentByCustomerGenerateList");
    }

    public void insertConnote(Map<String, String> context, TollConnoteFilter tollConnoteFilter) throws DaoException {
        insert(context, tollConnoteFilter, "TollConnote.insertTollConnote");
    }

    public void updateConnoteGenManifest(Map<String, String> context, String shipmentId) throws DaoException {
        update(context, shipmentId, "TollConnote.updateTollConnoteGenManifest");
    }

    public void deleteByShipmentId(Map<String, String> context, long shipmentId) throws DaoException {
        delete(context, shipmentId, "TollConnote.deleteByShipmentId");
    }

    public Long countTollPriorityConnoteInfo(String customerCode) throws DaoException {
        return (Long) selectObject(customerCode, "TollConnote.countTollPriorityConnoteInfo");
    }
}
