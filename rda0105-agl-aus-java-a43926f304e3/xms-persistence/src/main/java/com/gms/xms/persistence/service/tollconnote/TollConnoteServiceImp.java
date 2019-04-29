package com.gms.xms.persistence.service.tollconnote;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TollConnoteFilter;
import com.gms.xms.persistence.dao.webship.TollConnoteDao;

import java.util.Map;

public class TollConnoteServiceImp implements ITollConnoteService {
    private TollConnoteDao dao = new TollConnoteDao();

    @Override
    public String getConnNumber(TollConnoteFilter tollConnoteFilter) throws DaoException {
        return dao.selectConnumberByShipmentId(tollConnoteFilter);
    }

    @Override
    public void insertConnote(Map<String, String> context, TollConnoteFilter tollConnoteFilter) throws DaoException {
        dao.insertConnote(context, tollConnoteFilter);
    }

    @Override
    public Long countTollPriorityConnoteInfo(String customerCode) throws DaoException {
        return dao.countTollPriorityConnoteInfo(customerCode);
    }
}
