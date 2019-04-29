package com.gms.xms.persistence.service.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.WebshipFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AccountServiceVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.txndb.vo.webship.login.WebshipResetFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from IWebshipService
 * <p>
 * Author DatTV Sep 29, 2015
 */
public interface IWebshipService {
    public List<WebshipVo> selectByFilter(WebshipFilter filter) throws DaoException;

    public long countByFilter(WebshipFilter filter) throws DaoException;

    public void saveCustomerWebshipSettings(Map<String, String> context, CustomerVo customer, List<AccountServiceVo> accountServiceVos) throws DaoException;

    public void saveCustomerWebshipSettings(Map<String, String> context, CustomerVo customer, List<AccountServiceVo> accountServiceVos, SqlSessionClient sessionClient) throws DaoException;

    public void insertWebship(Map<String, String> context, WebshipVo webshipVo) throws DaoException;

    public void updateWebship(Map<String, String> context, WebshipVo webshipVo) throws DaoException;

    public WebshipVo selectWebshipById(Long webshipId) throws DaoException;

    public WebshipVo checkInfoResetPassword(WebshipResetFilter filter) throws DaoException;

    public void saveFranchiseWebshipSettings(Map<String, String> context, FranchiseVo franchise, List<AccountServiceVo> accountServiceVos) throws DaoException;

    public void saveFranchiseWebshipSettings(Map<String, String> context, FranchiseVo franchise, List<AccountServiceVo> accountServiceVos, SqlSessionClient sessionClient) throws DaoException;

}
