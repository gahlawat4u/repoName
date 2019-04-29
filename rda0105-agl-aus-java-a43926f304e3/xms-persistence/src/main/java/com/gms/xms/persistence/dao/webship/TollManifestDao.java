package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.toll.TollManifestVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from Sep 23, 2016 6:09:47 PM
 * <p>
 * Author huynd
 */
public class TollManifestDao extends BaseDao<Object> {
    public TollManifestDao() {
        super();
    }

    public TollManifestDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void updateTollManifestStatus(Map<String, String> context, TollManifestVo tollManifestVo) throws DaoException {
        update(context, tollManifestVo, "TollManifest.updateTollManifestStatus");
    }

    public void insertTollManifestIdentifier(Map<String, String> context, TollManifestVo tollManifestVo) throws DaoException {
        insert(context, tollManifestVo, "TollManifest.insertTollManifestIdentifier");
    }

    public TollManifestVo getManifestByMainShipment(String shipmentId) throws DaoException {
        return (TollManifestVo) selectObject(shipmentId, "TollManifest.getManifestByMainShipment");
    }

}
