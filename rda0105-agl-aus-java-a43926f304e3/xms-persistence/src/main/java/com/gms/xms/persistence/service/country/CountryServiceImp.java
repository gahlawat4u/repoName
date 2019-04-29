package com.gms.xms.persistence.service.country;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.txndb.vo.CountryVo;

import java.util.List;

/**
 * Posted from CountryService
 * <p>
 * Author HungNT Date Jul 10, 2015
 */
public class CountryServiceImp implements ICountryService {
    private CountryDao dao = new CountryDao();

    @Override
    public List<CountryVo> getCountryList() throws Exception {
        List<CountryVo> countryVos = dao.getCountryList();
        return countryVos;
    }

    @Override
    public List<CountryVo> getSearchAirbilCountryList() throws DaoException {
        List<CountryVo> countryVos = dao.getSearchAirbilCountryList();
        return countryVos;
    }

    @Override
    public List<CountryVo> getWebshipCountryList() throws DaoException {
        List<CountryVo> countryVos = dao.getWebshipCountryList();
        return countryVos;
    }

    @Override
    public CountryVo selectCountryByCountryName(String countryName) throws DaoException {
        CountryVo countryVo = dao.selectCountryByCountryName(countryName);
        return countryVo;
    }

    @Override
    public CountryVo getCountryByCountryId(Long countryId) throws DaoException {
        return dao.getCountryById(countryId);
    }
}
