package com.gms.xms.persistence.service.webshipgroup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.WebshipGroupFilter;
import com.gms.xms.persistence.dao.WebshipGroupDao;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;

import java.util.List;
import java.util.Map;

public class WebshipGroupServiceImp implements IWebshipGroupService {

    private WebshipGroupDao webshipGroupDao = new WebshipGroupDao();

    @Override
    public List<WebshipGroupVo> selectAll() throws DaoException {
        return webshipGroupDao.selectAll();
    }

    @Override
    public List<WebshipGroupVo> selectByFilter(WebshipGroupFilter filter) throws DaoException {
        return webshipGroupDao.selectByFilter(filter);
    }

    @Override
    public WebshipGroupVo selectById(Integer webshipGroupId) throws DaoException {
        return webshipGroupDao.selectById(webshipGroupId);

    }

    @Override
    public long countByFilter(WebshipGroupFilter filter) throws DaoException {
        return webshipGroupDao.countByFilter(filter);
    }

    @Override
    public void delete(Map<String, String> context, Integer webshipGroupId) throws DaoException {
        webshipGroupDao.delete(context, webshipGroupId);
    }

    @Override
    public void insert(Map<String, String> context, WebshipGroupVo webshipGroup) throws DaoException {
        webshipGroupDao.insert(context, webshipGroup);
    }

    @Override
    public void update(Map<String, String> context, WebshipGroupVo webshipGroupId) throws DaoException {
        webshipGroupDao.update(context, webshipGroupId);
    }

    @Override
    public long countByName(WebshipGroupVo webshipGroup) throws DaoException {
        return webshipGroupDao.countByName(webshipGroup);
    }
}