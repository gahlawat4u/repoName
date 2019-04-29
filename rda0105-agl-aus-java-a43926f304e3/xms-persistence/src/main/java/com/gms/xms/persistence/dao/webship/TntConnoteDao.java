package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TntConnoteFilter;
import com.gms.xms.filter.webship.TntConnoteShipmentInfoFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.tnt.TntConnoteShipmentInfoVo;
import com.gms.xms.txndb.vo.tnt.TntConnoteVo;
import com.gms.xms.txndb.vo.tnt.TntDomConnoteShipmentInfoVo;

import java.util.List;
import java.util.Map;

public class TntConnoteDao extends BaseDao<Object> {
    public TntConnoteDao() {
        super();
    }

    public TntConnoteDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public String selectConnumberByShipmentId(TntConnoteFilter tntConnoteFilter) throws DaoException {
        return (String) selectObject(tntConnoteFilter, "TntConnote.selectConnumberByShipmentId");
    }

    public void insertTntConnote(Map<String, String> context, TntConnoteFilter tntConnoteFilter) throws DaoException {
        insert(context, tntConnoteFilter, "TntConnote.insertTntConnote");
    }

    public void deleteByShipmentId(Map<String, String> context, long shipmentId) throws DaoException {
        delete(context, shipmentId, "TntConnote.deleteByShipmentId");
    }

    public void updateTntTransmissionConnote(Map<String, String> context, TntConnoteVo tntConnoteVo) throws DaoException {
        update(context, tntConnoteVo, "TntConnote.updateTntTransmissionConnote");
    }

    @SuppressWarnings("unchecked")
    public List<TntConnoteShipmentInfoVo> selectTntDomConnoteInfo(Long customerCode) throws DaoException {
        return (List<TntConnoteShipmentInfoVo>)(Object) selectObjectList(customerCode,"TntConnote.selectTntDomConnoteInfo");
    }

    @SuppressWarnings("unchecked")
    public Long countTntDomConnoteInfo(Long customerCode) throws DaoException {
        return (Long) selectObject(customerCode,"TntConnote.countTntDomConnoteInfo");
    }

    @SuppressWarnings("unchecked")
    public List<TntDomConnoteShipmentInfoVo> selectTntConnoteSameInfoDay(Long shipmentid) throws DaoException {
        return (List<TntDomConnoteShipmentInfoVo>) (Object) selectObjectList(shipmentid,"TntConnote.selectTntConnoteInDay");
    }

    public void updateTntWManifestInfoConnote(Map<String, String> context, TntConnoteShipmentInfoFilter tntConnoteShipmentInfoFilter) throws DaoException {
        update(context, tntConnoteShipmentInfoFilter, "TntConnote.updateTntWManifestInfoConnote");
    }
}
