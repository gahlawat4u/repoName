package com.gms.xms.persistence.service.webship.login;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailFilter;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;

import java.util.List;

/**
 * Posted from ILoginService
 * <p>
 * Author DatTV Date Jul 9, 2015 4:45:55 PM
 */
public interface ILoginService {
    public WebshipLoginVo checkLogin(WebshipLoginVo webshipLogin) throws DaoException;

    public WebshipLoginVo getWebshipLoginById(Long webshipId) throws DaoException;

    public List<WebshipDetailVo> selectChildrenByFilter(WebshipDetailFilter filter) throws DaoException;
}