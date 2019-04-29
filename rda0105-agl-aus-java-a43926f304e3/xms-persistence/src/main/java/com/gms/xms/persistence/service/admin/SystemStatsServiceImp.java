package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.SystemStatsListFilter;
import com.gms.xms.persistence.dao.DhlCountryDao;
import com.gms.xms.persistence.dao.admin.LoginLogDao;
import com.gms.xms.persistence.dao.admin.SystemStatsListDao;
import com.gms.xms.persistence.dao.admin.WebshipLogDao;
import com.gms.xms.txndb.vo.DhlCountryVo;
import com.gms.xms.txndb.vo.admin.LoginLogVo;
import com.gms.xms.txndb.vo.admin.SystemStatsListVo;
import com.gms.xms.txndb.vo.admin.WebshipLogVo;

import java.util.List;

/**
 * Posted from SystemStatsServiceImp
 * <p>
 * Author TANDT
 */
public class SystemStatsServiceImp implements ISystemStatsService {

    @Override
    public List<SystemStatsListVo> selectSystemStatsList(SystemStatsListFilter filter) throws DaoException {
        SystemStatsListDao dao = new SystemStatsListDao();
        return dao.selectSystemStatsList(filter);
    }

    @Override
    public List<DhlCountryVo> selectAll(SystemStatsListFilter filter) throws DaoException {
        DhlCountryDao dao = new DhlCountryDao();
        return dao.selectAll(filter);
    }

    @Override
    public Long selectCountAll(SystemStatsListFilter filter) throws DaoException {
        DhlCountryDao dao = new DhlCountryDao();
        return dao.selectCountAll(filter);
    }

    @Override
    public Long selectSystemStatsListCount(SystemStatsListFilter filter) throws DaoException {
        SystemStatsListDao dao = new SystemStatsListDao();
        return dao.selectSystemStatsListCount(filter);
    }

    @Override
    public Long selectByFilterCount(SystemStatsListFilter filter) throws DaoException {
        LoginLogDao dao = new LoginLogDao();
        return dao.selectByFilterCount(filter);
    }

    @Override
    public List<LoginLogVo> selectByFilter(SystemStatsListFilter filter) throws DaoException {
        LoginLogDao dao = new LoginLogDao();
        return dao.selectByFilter(filter);
    }

    @Override
    public List<WebshipLogVo> selectWebshipLogByFilter(SystemStatsListFilter filter) throws DaoException {
        WebshipLogDao dao = new WebshipLogDao();
        return dao.selectWebshipLogByFilter(filter);
    }

    @Override
    public Long selectWebshipLogCountByFilter(SystemStatsListFilter filter) throws DaoException {
        WebshipLogDao dao = new WebshipLogDao();
        return dao.selectWebshipLogCountByFilter(filter);
    }

}
