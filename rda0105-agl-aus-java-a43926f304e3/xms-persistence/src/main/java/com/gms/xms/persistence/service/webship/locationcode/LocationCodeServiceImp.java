package com.gms.xms.persistence.service.webship.locationcode;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.LocationCodeDao;
import com.gms.xms.txndb.vo.LocationCodeVo;

import java.util.List;

/**
 * Posted from LocationCodeServiceImp
 * <p>
 * Author HungNT Date Jul 22, 2015
 */
public class LocationCodeServiceImp implements ILocationCodeService {
    private LocationCodeDao dao = new LocationCodeDao();

    @Override
    public List<LocationCodeVo> getLocationCodeList() throws DaoException {
        return dao.selectAllLocationCode();
    }

    @Override
    public LocationCodeVo getLocationCodeById(Integer locationCodeId) throws DaoException {
        return dao.selectLocationCodeById(locationCodeId);
    }
}
