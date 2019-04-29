package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.toll.TollManifestVo;
import com.gms.xms.txndb.vo.webship.toll.TollIpecManifestVo;

import java.util.Map;

/**
 * @author TANDT
 */
public class TollIpecManifestDao extends BaseDao<TollIpecManifestVo> {

    public TollIpecManifestVo selectByShipment(String shipmentId) throws DaoException {
        return select(shipmentId, "TollIpecManifest.selectByShipment");
    }

    public void updateTollIpecManifestStatus(Map<String, String> context, TollIpecManifestVo tollIpecManifestVo) throws DaoException {
        update(context, tollIpecManifestVo, "TollIpecManifest.updateTollIpecManifestStatus");
    }

    public void insertTollIpecManifestIdentifier(Map<String, String> context, TollIpecManifestVo tollIpecManifestVo) throws DaoException {
        insert(context, tollIpecManifestVo, "TollIpecManifest.insertTollIpecManifestIdentifier");
    }

    public TollIpecManifestVo getManifestByMainShipment(String shipmentId) throws DaoException {
        return (TollIpecManifestVo) selectObject(shipmentId, "TollIpecManifest.getManifestByMainShipment");
    }
}
