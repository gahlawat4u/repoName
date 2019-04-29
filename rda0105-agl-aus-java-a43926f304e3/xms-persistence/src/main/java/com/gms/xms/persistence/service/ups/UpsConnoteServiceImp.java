package com.gms.xms.persistence.service.ups;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.UpsConnoteFilter;
import com.gms.xms.persistence.dao.webship.UpsConnoteDao;

import java.util.Map;

/**
 * Created by thinhdd
 * Date 4/7/2017.
 */
public class UpsConnoteServiceImp implements IUpsConnoteService {

    private UpsConnoteDao upsConnoteDao = new UpsConnoteDao();

    @Override
    public String getConnNumber(UpsConnoteFilter connoteFilter) throws DaoException {
        return upsConnoteDao.getConnNumber(connoteFilter);
    }

    @Override
    public void insertUpsConnote(Map<String, String> context, UpsConnoteFilter connoteFilter) throws DaoException {
        upsConnoteDao.insertUpsConnote(context, connoteFilter);
    }
}
