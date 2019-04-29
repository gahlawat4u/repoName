package com.gms.xms.workflow.client;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.LocationCodeDao;
import com.gms.xms.txndb.vo.LocationCodeVo;
import com.gms.xms.workflow.client.integration.response.LocationCodeResponse;

import java.util.List;

/**
 * Posted from LocationCodeClient
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class LocationCodeClient {
    public LocationCodeResponse getLocationCodeList() throws DaoException {
        LocationCodeDao locationCodeDao = new LocationCodeDao();
        List<LocationCodeVo> locationCodeVos = locationCodeDao.selectAllLocationCode();

        LocationCodeResponse locationCodeResponse = new LocationCodeResponse();
        locationCodeResponse.setLocationCodeVos(locationCodeVos);
        return locationCodeResponse;
    }
}
