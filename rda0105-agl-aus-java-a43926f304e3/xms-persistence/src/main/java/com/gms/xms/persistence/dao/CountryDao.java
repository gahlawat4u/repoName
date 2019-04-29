package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.country.CountryFilter;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CountryDaoService
 * <p>
 * Author DatTV Date Mar 24, 2015
 */
public class CountryDao extends BaseDao<CountryVo> {
    /**
     * Gets the list of countries
     *
     * @return List of countries
     * @throws DaoException
     */
    public List<CountryVo> getCountryList() throws DaoException {
        return selectList(new CountryVo(), "Country.getCountryList");
    }

    public List<CountryVo> getWebshipCountryList() throws DaoException {
        return selectList(new CountryVo(), "Country.selectWebshipCountryList");
    }

    public List<CountryVo> getSearchAirbilCountryList() throws DaoException {
        return selectList(new CountryVo(), "Country.getSearchAirbilCountryList");
    }

    /**
     * Gets the country by id
     *
     * @param countryId
     * @return The country with specified id
     * @throws DaoException
     */
    public CountryVo getCountryById(Long countryId) throws DaoException {
        return select(countryId, "getCountryById");
    }

    /**
     * Get country by country name
     *
     * @param {@link String} countryName
     * @return country {@link CountryVo}
     * @throws DaoException
     */
    public CountryVo selectCountryByCountryName(String countryName) throws DaoException {
        return select(countryName, "Country.selectCountryByCountryName");
    }

    public String selectCountryDhl(Long countryId) throws DaoException {
        return (String) selectObject(countryId, "Country.selectCountryTimeZone");
    }

    /**
     * Insert new country to the database
     *
     * @param country
     * @throws DaoException
     */
    public void addCountry(Map<String, String> context, CountryVo country) throws DaoException {
        insert(context, country, "addCountry");
    }

    public List<CountryVo> getCustomerAddressCountryIdList(List<String> countryNameList) throws DaoException {
        return selectList(countryNameList, "Country.getCustomerAddressCountryIdList");
    }

    public CountryVo selectCountryDetail(Long countryId) throws DaoException {
        return select(countryId, "Country.selectCountryDetail");
    }

    public void update(Map<String, String> context, CountryVo country) throws DaoException {
        insert(context, country, "Country.update");
    }

    public Integer checkCountryByName(String countryName) throws DaoException {
        return (Integer) this.selectObject(countryName, "Country.checkCountryByName");
    }

    public void updateIsShow(Map<String, String> context, CountryVo country) throws DaoException {
        this.update(context, country, "Country.updateIsShow");
    }

    public Integer checkCountryEU(ShipmentInfoVo shipmentInfoVo) throws DaoException {
        return (Integer) selectObject(shipmentInfoVo, "Country.checkCountryEU");
    }

    public Long getCountryIdByCountryCode(String countryCode) throws DaoException {
        return (Long) selectObject(countryCode, "Country.getCountryIdByCountryCode");
    }

    public Long getCountryIdByCountryName(String countryName) throws DaoException {
        return (Long) selectObject(countryName, "Country.getCountryIdByCountryName");
    }

    public Long getCountryIdByCityName(CountryFilter filter) throws DaoException {
        return (Long) selectObject(filter, "Country.getCountryIdByCityName");
    }

}