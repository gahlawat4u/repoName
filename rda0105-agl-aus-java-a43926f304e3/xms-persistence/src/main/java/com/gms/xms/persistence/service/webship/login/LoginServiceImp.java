package com.gms.xms.persistence.service.webship.login;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailFilter;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;

import java.util.List;

/**
 * Posted from LoginServiceImp
 * <p>
 * Author DatTV Date Jul 9, 2015 4:45:59 PM
 */
public class LoginServiceImp implements ILoginService {
    private WebshipDao webshipDao = new WebshipDao();

    @Override
    public WebshipLoginVo checkLogin(WebshipLoginVo webshipLogin) throws DaoException {
        return webshipDao.checkLogin(webshipLogin);
    }

    @Override
    public WebshipLoginVo getWebshipLoginById(Long webshipId) throws DaoException {
        return webshipDao.getWebshipLoginById(webshipId);
    }

    @Override
    public List<WebshipDetailVo> selectChildrenByFilter(WebshipDetailFilter filter) throws DaoException {
        return webshipDao.selectChildrenByFilter(filter);
    }
}