package com.gms.xms.persistence.service.country;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.CountryVo;

import java.util.List;

/**
 * Posted from ICountryService
 * <p>
 * Author HungNT Date Jul 10, 2015
 */
public interface ICountryService {
    public List<CountryVo> getCountryList() throws Exception;

    public CountryVo selectCountryByCountryName(String countryName) throws DaoException;

    public List<CountryVo> getSearchAirbilCountryList() throws DaoException;

    public List<CountryVo> getWebshipCountryList() throws DaoException;

    public CountryVo getCountryByCountryId(Long countryId) throws DaoException;
}
