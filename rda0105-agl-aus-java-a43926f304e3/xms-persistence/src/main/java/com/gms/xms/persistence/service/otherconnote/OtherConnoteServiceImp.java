package com.gms.xms.persistence.service.otherconnote;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.OtherConnoteFilter;
import com.gms.xms.persistence.dao.webship.OtherConnoteDao;

import java.util.Map;

public class OtherConnoteServiceImp implements IOtherConnoteService {
    private OtherConnoteDao dao = new OtherConnoteDao();

    @Override
    public String getConnNumber(OtherConnoteFilter otherConnoteFilter) throws DaoException {
        return dao.selectConnumberByShipmentId(otherConnoteFilter);
    }

    @Override
    public void insertConnote(Map<String, String> context, OtherConnoteFilter otherConnoteFilter) throws DaoException {
        dao.insertConnote(context, otherConnoteFilter);
    }
}
