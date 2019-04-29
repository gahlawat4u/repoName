package com.gms.xms.workflow.client;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CarrierSuburbDao;
import com.gms.xms.txndb.vo.CarrierSuburbFilter;
import com.gms.xms.workflow.client.integration.request.CarrierSuburbRequest;
import com.gms.xms.workflow.client.integration.response.CarrierSuburbResponse;

/**
 * Posted from CarrierSuburbClient
 * <p>
 * Author HungNT Date Apr 21, 2015
 */
public class CarrierSuburbClient {
    public CarrierSuburbResponse getCarrierSuburbCount(CarrierSuburbRequest carrierSuburbRequest) throws DaoException {
        CarrierSuburbFilter carrierSuburbFilter = carrierSuburbRequest.getCarrierSuburbFilter();
        CarrierSuburbDao carrierSuburbDao = new CarrierSuburbDao();

        Integer total = carrierSuburbDao.selectCountrCarrierSuburbBySuburdNameAndPostCode(carrierSuburbFilter);
        CarrierSuburbResponse carrierSuburbResponse = new CarrierSuburbResponse();
        carrierSuburbResponse.setCarrierSuburbCount(total);
        return carrierSuburbResponse;
    }
}
