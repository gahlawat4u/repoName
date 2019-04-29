package com.gms.xms.persistence.service.dhlcountry;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.DhlCountryDao;
import com.gms.xms.txndb.vo.DhlCountryVo;

public class DhlCountryServiceImp implements IDhlCountryService {
    private DhlCountryDao dao = new DhlCountryDao();

    @Override
    public DhlCountryVo getDhlCountryByCountryName(String dhlCountryName) throws DaoException {
        return dao.selectDhlCountryByCountryName(dhlCountryName);
    }

    @Override
    public DhlCountryVo selectDhlCountryByName(String dhlCountryName) throws DaoException {
        return dao.selectDhlCountryByName(dhlCountryName);
    }

    @Override
    public DhlCountryVo selectDhlCountryByApCode(String dhlApCode) throws DaoException {
        return dao.selectDhlCountryByApCode(dhlApCode);
    }

    @Override
    public DhlCountryVo selectDhlCountryByCountryCode(String countryCode) throws DaoException {
        return dao.selectDhlCountryByCountryCode(countryCode);
    }

    @Override
    public DhlCountryVo selectDhlCountryByDhlCountryId(Long dhlCountryId) throws DaoException {
        return dao.selectDhlCountryByDhlCountryId(dhlCountryId);
    }
}
