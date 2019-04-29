package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.WebshipFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailFilter;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.login.WebshipResetFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipDao
 * <p>
 * Author DatTV Date Jul 7, 2015 4:19:23 PM
 */
public class WebshipDao extends BaseDao<WebshipVo> {
    public WebshipDao() {
        super();
    }

    public WebshipDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public WebshipLoginVo checkLogin(WebshipLoginVo webshipLogin) throws DaoException {
        return (WebshipLoginVo) select(webshipLogin, "Webship.checkLogin");
    }

    public WebshipLoginVo getWebshipLoginById(Long webshipId) throws DaoException {
        return (WebshipLoginVo) select(webshipId, "Webship.getWebshipLoginById");
    }

    public void changePassword(Map<String, String> context, WebshipVo webship) throws DaoException {
        update(context, webship, "Webship.changePassword");
    }

    public List<WebshipDetailVo> selectChildrenByFilter(WebshipDetailFilter filter) throws DaoException {
        return selectList(filter, "Webship.selectChildrenByFilter");
    }

    public List<WebshipVo> selectByFilter(WebshipFilter filter) throws DaoException {
        return this.selectList(filter, "Webship.selectByFilter");
    }

    public WebshipVo selectByResetCode(String resetPasswordCode) throws DaoException {
        return this.select(resetPasswordCode, "Webship.selectWebshipByResetCode");
    }

    public long countByFilter(WebshipFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Webship.countByFilter");
    }

    public WebshipVo selectById(Long webshipId) throws DaoException {
        return this.select(webshipId, "Webship.selectById");
    }

    public void insert(Map<String, String> context, WebshipVo webshipVo) throws DaoException {
        this.insert(context, webshipVo, "Webship.insert");
    }

    public void update(Map<String, String> context, WebshipVo webshipVo) throws DaoException {
        update(context, webshipVo, "Webship.update");
    }

    public WebshipVo checkInfoResetPassword(WebshipResetFilter filter) throws DaoException {
        return this.select(filter, "Webship.checkInfoResetPassword");
    }

    public int checkUser(WebshipVo webshipVo) throws DaoException {
        return (int) this.selectObject(webshipVo, "Webship.checkUser");
    }

    public boolean hasAdminFunction(long webshipId) throws DaoException {
        Integer count = (Integer) selectObject(webshipId, "Webship.checkAdminFunction");
        return count != null && count > 0;
    }
}