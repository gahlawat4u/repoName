package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.WebshipGroupFilter;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipGroupDao
 * <p>
 * Author DatTV Sep 1, 2015
 */
public class WebshipGroupDao extends BaseDao<WebshipGroupVo> {
    public List<WebshipGroupVo> selectAll() throws DaoException {
        return this.selectList(null, "WebshipGroup.selectAll");
    }

    public WebshipGroupVo selectById(Integer webshipGroupId) throws DaoException {
        return this.select(webshipGroupId, "WebshipGroup.selectById");
    }

    public void insert(Map<String, String> context, WebshipGroupVo webshipGroup) throws DaoException {
        insert(context, webshipGroup, "WebshipGroup.insert");
    }

    public void update(Map<String, String> context, WebshipGroupVo webshipGroup) throws DaoException {
        update(context, webshipGroup, "WebshipGroup.update");
    }

    public void delete(Map<String, String> context, Integer webshipGroupId) throws DaoException {
        delete(context, webshipGroupId, "WebshipGroup.delete");
    }

    public List<WebshipGroupVo> selectByFilter(WebshipGroupFilter filter) throws DaoException {
        return this.selectList(filter, "WebshipGroup.selectByFilter");
    }

    public long countByFilter(WebshipGroupFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "WebshipGroup.countByFilter");
    }

    public long countByName(WebshipGroupVo webshipGroup) throws DaoException {
        return (long) this.selectObject(webshipGroup, "WebshipGroup.countWebshipGroupByName");
    }
}