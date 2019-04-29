package com.gms.xms.persistence.service.webship.locationcode;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.LocationCodeVo;

import java.util.List;

/**
 * Posted from ILocationCodeService
 * <p>
 * Author HungNT Date Jul 22, 2015
 */
public interface ILocationCodeService {
    public List<LocationCodeVo> getLocationCodeList() throws DaoException;

    public LocationCodeVo getLocationCodeById(Integer locationCodeId) throws DaoException;
}
