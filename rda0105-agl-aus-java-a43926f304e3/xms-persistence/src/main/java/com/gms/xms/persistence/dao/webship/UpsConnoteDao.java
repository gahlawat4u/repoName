package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.UpsConnoteFilter;
import com.gms.xms.persistence.dao.BaseDao;

import java.util.Map;

/**
 * Created by thinhdd
 * Date 4/7/2017.
 */
public class UpsConnoteDao extends BaseDao<Object> {
    public String getConnNumber(UpsConnoteFilter connoteFilter) throws DaoException {
        return (String) selectObject(connoteFilter, "UpsConnote.selectConnumberByShipmentId");
    }

    public void insertUpsConnote(Map<String, String> context, UpsConnoteFilter connoteFilter) throws DaoException {
        insert(context, connoteFilter, "UpsConnote.insertUpsConnote");
    }
}
