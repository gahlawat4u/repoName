package com.gms.xms.persistence.service.dhlcountry;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.DhlCountryVo;

public interface IDhlCountryService {
    public DhlCountryVo getDhlCountryByCountryName(String dhlCountryName) throws DaoException;

    public DhlCountryVo selectDhlCountryByName(String dhlCountryName) throws DaoException;

    public DhlCountryVo selectDhlCountryByApCode(String dhlApCode) throws DaoException;

    public DhlCountryVo selectDhlCountryByCountryCode(String countryCode) throws DaoException;

    public DhlCountryVo selectDhlCountryByDhlCountryId(Long dhlCountryId) throws DaoException;
}
