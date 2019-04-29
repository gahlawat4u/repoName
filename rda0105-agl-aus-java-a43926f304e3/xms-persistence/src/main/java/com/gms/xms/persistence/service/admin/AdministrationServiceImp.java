package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdministrationFilter;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.dao.DhlCountryDao;
import com.gms.xms.persistence.dao.StateDao;
import com.gms.xms.persistence.dao.UserLevelDao;
import com.gms.xms.persistence.dao.admin.CountryListDao;
import com.gms.xms.persistence.dao.admin.PermissionDao;
import com.gms.xms.persistence.dao.admin.PermissionResultDao;
import com.gms.xms.persistence.dao.admin.UserLevelPermissionDao;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.DhlCountryVo;
import com.gms.xms.txndb.vo.StateVo;
import com.gms.xms.txndb.vo.UserLevelVo;
import com.gms.xms.txndb.vo.admin.administration.CountryListVo;
import com.gms.xms.txndb.vo.admin.administration.PermissionResultVo;
import com.gms.xms.txndb.vo.admin.administration.UserLevelPermissionVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SystemStatsServiceImp
 * <p>
 * Author TANDT
 */
public class AdministrationServiceImp implements IAdministrationService {

    @Override
    public List<PermissionResultVo> selectPermissionAll(AdministrationFilter filter) throws DaoException {
        PermissionResultDao dao = new PermissionResultDao();
        return dao.selectPermissionAll(filter);
    }

    @Override
    public Long selectPermissionAllCount(AdministrationFilter filter) throws DaoException {
        PermissionDao dao = new PermissionDao();
        return dao.selectPermissionAllCount(filter);
    }

    @Override
    public void deleteUserLevelPermission(Map<String, String> context, UserLevelPermissionVo vo) throws DaoException {
        UserLevelPermissionDao dao = new UserLevelPermissionDao();
        dao.deleteUserLevelPermission(context, vo);
    }

    @Override
    public void insertUserLevelPermission(Map<String, String> context, UserLevelPermissionVo vo) throws DaoException {
        UserLevelPermissionDao dao = new UserLevelPermissionDao();
        dao.insertUserLevelPermission(context, vo);
    }

    @Override
    public List<UserLevelVo> selectUserLevelForPermission() throws DaoException {
        UserLevelDao dao = new UserLevelDao();
        return dao.selectForPermission();
    }

    @Override
    public Long selectCountryListCount(AdministrationFilter filter) throws DaoException {
        CountryListDao dao = new CountryListDao();
        return dao.selectCountryListCount(filter);
    }

    @Override
    public List<CountryListVo> selectCountryList(AdministrationFilter filter) throws DaoException {
        CountryListDao dao = new CountryListDao();
        return dao.selectCountryList(filter);
    }

    @Override
    public CountryVo selectCountryById(Long id) throws DaoException {
        CountryDao dao = new CountryDao();
        return dao.getCountryById(id);
    }

    @Override
    public CountryVo selectCountryDetail(Long countryId) throws DaoException {
        CountryDao dao = new CountryDao();
        return dao.selectCountryDetail(countryId);
    }

    @Override
    public void updateCountry(Map<String, String> context, CountryVo countryVo) throws DaoException {
        CountryDao dao = new CountryDao();
        DhlCountryDao dhlDao = new DhlCountryDao();
        DhlCountryVo dhlCountryVo = dhlDao.selectDhlCountryByCountryName(countryVo.getCountryName());
        if (dhlCountryVo == null) {
            dhlCountryVo = new DhlCountryVo();
            dhlCountryVo = countryVo.getDhlCountry();
            dhlCountryVo.setDhlApCode(countryVo.getCountryCode());
            dhlCountryVo.setDhlApZone("0");
            dhlCountryVo.setDhlCountryName(countryVo.getCountryName());
            dhlCountryVo.setDhlRegion(countryVo.getDhlCountry().getDhlRegion());
            dhlCountryVo.setDhlTimeZone(countryVo.getDhlCountry().getDhlTimeZone());
            dhlDao.insert(context, dhlCountryVo);
        } else {
            dhlCountryVo.setDhlApCode(countryVo.getCountryCode());
            dhlCountryVo.setDhlRegion(countryVo.getDhlCountry().getDhlRegion());
            dhlCountryVo.setDhlTimeZone(countryVo.getDhlCountry().getDhlTimeZone());
            dhlDao.update(context, dhlCountryVo);
        }
        dao.update(context, countryVo);
    }

    @Override
    public Integer checkCountryByName(String countryName) throws DaoException {
        CountryDao dao = new CountryDao();
        return dao.checkCountryByName(countryName);
    }

    @Override
    public void addCountry(Map<String, String> context, CountryVo countryVo) throws DaoException {
        CountryDao dao = new CountryDao();
        dao.addCountry(context, countryVo);
    }

    @Override
    public void updateIsShow(Map<String, String> context, CountryVo country) throws DaoException {
        CountryDao dao = new CountryDao();
        dao.updateIsShow(context, country);
    }

    @Override
    public List<StateVo> selectListStateByCountryId(AdministrationFilter filter) throws DaoException {
        StateDao dao = new StateDao();
        return dao.selectListStateByCountryId(filter);
    }

    @Override
    public Long selectListStateByCountryIdCount(AdministrationFilter filter) throws DaoException {
        StateDao dao = new StateDao();
        return dao.selectListStateByCountryIdCount(filter);
    }

    @Override
    public Integer checkInsertState(StateVo stateVo) throws DaoException {
        StateDao dao = new StateDao();
        return dao.checkInsert(stateVo);
    }

    @Override
    public void insertState(Map<String, String> context, StateVo stateVo) throws DaoException {
        StateDao dao = new StateDao();
        dao.insert(context, stateVo);
    }

    @Override
    public Integer checkDeleteState(StateVo stateVo) throws DaoException {
        StateDao dao = new StateDao();
        return dao.checkDelete(stateVo);
    }

    @Override
    public void updateState(Map<String, String> context, StateVo stateVo) throws DaoException {
        StateDao dao = new StateDao();
        dao.update(context, stateVo);
    }

    @Override
    public void deleteState(Map<String, String> context, Long id) throws DaoException {
        StateDao dao = new StateDao();
        dao.delete(context, id);
    }

    @Override
    public List<UserLevelVo> selectForMenuEditor() throws DaoException {
        UserLevelDao dao = new UserLevelDao();
        return dao.selectForMenuEditor();
    }
}
