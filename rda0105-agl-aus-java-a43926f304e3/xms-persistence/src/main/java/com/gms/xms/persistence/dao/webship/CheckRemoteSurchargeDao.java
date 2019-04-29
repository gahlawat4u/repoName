package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;

public class CheckRemoteSurchargeDao extends BaseDao<Object> {
    public String getCheckRemoteData(Long countryId) throws DaoException {
        return (String) selectObject(countryId, "CheckRemoteSurcharge.getCheckRemoteData");
    }
}
