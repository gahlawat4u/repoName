package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TollIpecConnoteFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.toll.TollShipmentVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from TollIpecConnoteDao
 * <p>
 * Author @author HungNT Feb 16, 2016
 */
public class TollIpecConnoteDao extends BaseDao<Object> {
    public TollIpecConnoteDao() {
        super();
    }

    public TollIpecConnoteDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public String selectConnumberByShipmentId(TollIpecConnoteFilter tollIpecConnoteFilter) throws DaoException {
        return (String) selectObject(tollIpecConnoteFilter, "TollIpecConnote.selectConnumberByShipmentId");
    }

    public void insertConnote(Map<String, String> context, TollIpecConnoteFilter tollIpecConnoteFilter) throws DaoException {
        insert(context, tollIpecConnoteFilter, "TollIpecConnote.insertTollIpecConnote");
    }

    public void deleteByShipmentId(Map<String, String> context, long shipmentId) throws DaoException {
        delete(context, shipmentId, "TollIpecConnote.deleteByShipmentId");
    }

    public Long countTollIpecConnoteInfo(String customerCode) throws DaoException {
        return (Long) selectObject(customerCode, "TollIpecConnote.countTollIpecConnoteInfo");
    }

    public List<TollShipmentVo> getTollIpecShipmentByCustomerGenerateList(Long customerCode) throws DaoException {
        return selectList(customerCode, "TollIpecConnote.getTollIpecShipmentByCustomerGenerateList");
    }

    public void updateConnoteGenManifest(Map<String, String> context, String shipmentId) throws DaoException {
        update(context, shipmentId, "TollIpecConnote.updateTollIpecConnoteGenManifest");
    }
}
