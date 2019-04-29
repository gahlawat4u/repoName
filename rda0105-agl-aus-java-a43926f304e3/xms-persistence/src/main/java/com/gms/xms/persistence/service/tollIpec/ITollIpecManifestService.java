package com.gms.xms.persistence.service.tollIpec;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.toll.TollIpecManifestVo;

/**
 * @author TANDT
 */
public interface ITollIpecManifestService {
    public TollIpecManifestVo selectByShipment(String shipmentId) throws DaoException;
}
