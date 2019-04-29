package com.gms.xms.persistence.service.tollIpec;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.TollIpecManifestDao;
import com.gms.xms.txndb.vo.webship.toll.TollIpecManifestVo;

/**
 * @author TANDT
 */
public class TollIpectManifestServiceImp implements ITollIpecManifestService {
    TollIpecManifestDao dao = new TollIpecManifestDao();

    @Override
    public TollIpecManifestVo selectByShipment(String shipmentId) throws DaoException {
        return dao.selectByShipment(shipmentId);
    }

}
