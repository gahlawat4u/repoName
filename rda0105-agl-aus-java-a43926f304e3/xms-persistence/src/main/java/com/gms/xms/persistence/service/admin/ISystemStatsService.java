package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.SystemStatsListFilter;
import com.gms.xms.txndb.vo.DhlCountryVo;
import com.gms.xms.txndb.vo.admin.LoginLogVo;
import com.gms.xms.txndb.vo.admin.SystemStatsListVo;
import com.gms.xms.txndb.vo.admin.WebshipLogVo;

import java.util.List;

/**
 * Posted from ISystemStatsService
 * <p>
 * Author TANDT
 */
public interface ISystemStatsService {
    public List<SystemStatsListVo> selectSystemStatsList(SystemStatsListFilter filter) throws DaoException;

    public Long selectSystemStatsListCount(SystemStatsListFilter filter) throws DaoException;

    public List<DhlCountryVo> selectAll(SystemStatsListFilter filter) throws DaoException;

    public Long selectCountAll(SystemStatsListFilter filter) throws DaoException;

    public Long selectByFilterCount(SystemStatsListFilter filter) throws DaoException;

    public List<LoginLogVo> selectByFilter(SystemStatsListFilter filter) throws DaoException;

    public List<WebshipLogVo> selectWebshipLogByFilter(SystemStatsListFilter filter) throws DaoException;

    public Long selectWebshipLogCountByFilter(SystemStatsListFilter filter) throws DaoException;
}
