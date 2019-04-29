package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.OtherConnoteFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;

import java.util.Map;

/**
 * Posted from OtherConnoteDao
 * <p>
 * Author @author HungNT Feb 19, 2016
 */
public class OtherConnoteDao extends BaseDao<Object> {
    public OtherConnoteDao() {
        super();
    }

    public OtherConnoteDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public String selectConnumberByShipmentId(OtherConnoteFilter otherConnoteFilter) throws DaoException {
        return (String) selectObject(otherConnoteFilter, "OtherConnote.selectConnumberByShipmentId");
    }

    public void insertConnote(Map<String, String> context, OtherConnoteFilter otherConnoteFilter) throws DaoException {
        insert(context, otherConnoteFilter, "OtherConnote.insertOtherConnote");
    }

    public void deleteByShipmentId(Map<String, String> context, long shipmentId) throws DaoException {
        delete(context, shipmentId, "OtherConnote.deleteByShipmentId");
    }
}