package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdministrationFilter;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.StateVo;
import com.gms.xms.txndb.vo.UserLevelVo;
import com.gms.xms.txndb.vo.admin.administration.CountryListVo;
import com.gms.xms.txndb.vo.admin.administration.PermissionResultVo;
import com.gms.xms.txndb.vo.admin.administration.UserLevelPermissionVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IAdministrationService
 * <p>
 * Author TANDT
 */
public interface IAdministrationService {
    public List<PermissionResultVo> selectPermissionAll(AdministrationFilter filter) throws DaoException;

    public Long selectPermissionAllCount(AdministrationFilter filter) throws DaoException;

    public void deleteUserLevelPermission(Map<String, String> context, UserLevelPermissionVo vo) throws DaoException;

    public void insertUserLevelPermission(Map<String, String> context, UserLevelPermissionVo vo) throws DaoException;

    public List<UserLevelVo> selectUserLevelForPermission() throws DaoException;

    public Long selectCountryListCount(AdministrationFilter filter) throws DaoException;

    public List<CountryListVo> selectCountryList(AdministrationFilter filter) throws DaoException;

    public CountryVo selectCountryById(Long id) throws DaoException;

    public CountryVo selectCountryDetail(Long countryId) throws DaoException;

    public void updateCountry(Map<String, String> context, CountryVo countryVo) throws DaoException;

    public Integer checkCountryByName(String countryName) throws DaoException;

    public void addCountry(Map<String, String> context, CountryVo countryVo) throws DaoException;

    public void updateIsShow(Map<String, String> context, CountryVo country) throws DaoException;

    public List<StateVo> selectListStateByCountryId(AdministrationFilter filter) throws DaoException;

    public Long selectListStateByCountryIdCount(AdministrationFilter filter) throws DaoException;

    public Integer checkInsertState(StateVo stateVo) throws DaoException;

    public void insertState(Map<String, String> context, StateVo stateVo) throws DaoException;

    public Integer checkDeleteState(StateVo stateVo) throws DaoException;

    public void updateState(Map<String, String> context, StateVo stateVo) throws DaoException;

    public void deleteState(Map<String, String> context, Long id) throws DaoException;

    public List<UserLevelVo> selectForMenuEditor() throws DaoException;
}
