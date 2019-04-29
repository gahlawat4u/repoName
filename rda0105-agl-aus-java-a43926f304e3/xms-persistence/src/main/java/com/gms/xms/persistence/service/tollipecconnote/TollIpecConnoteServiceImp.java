package com.gms.xms.persistence.service.tollipecconnote;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TollIpecConnoteFilter;
import com.gms.xms.persistence.dao.webship.TollIpecConnoteDao;

import java.util.Map;

public class TollIpecConnoteServiceImp implements ITollIpecConnoteService {

    @Override
    public String getConnNumber(TollIpecConnoteFilter tollIpecConnoteFilter) throws DaoException {
        TollIpecConnoteDao dao = new TollIpecConnoteDao();
        return dao.selectConnumberByShipmentId(tollIpecConnoteFilter);
    }

    @Override
    public void insertConnote(Map<String, String> context, TollIpecConnoteFilter tollIpecConnoteFilter) throws DaoException {
        TollIpecConnoteDao dao = new TollIpecConnoteDao();
        dao.insertConnote(context, tollIpecConnoteFilter);
    }
}
