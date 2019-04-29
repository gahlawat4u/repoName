package com.gms.xms.persistence.service.webshipgroup;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.WebshipGroupFilter;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IWebshipGroupService
 * <p>
 * Author DatTV Sep 3, 2015
 */
public interface IWebshipGroupService {
    public List<WebshipGroupVo> selectAll() throws DaoException;

    public List<WebshipGroupVo> selectByFilter(WebshipGroupFilter filter) throws DaoException;

    public WebshipGroupVo selectById(Integer webshipGroupId) throws DaoException;

    public long countByFilter(WebshipGroupFilter filter) throws DaoException;

    public void delete(Map<String, String> context, Integer webshipGroupId) throws DaoException;

    public void insert(Map<String, String> context, WebshipGroupVo webshipGroup) throws DaoException;

    public void update(Map<String, String> context, WebshipGroupVo webshipGroup) throws DaoException;

    public long countByName(WebshipGroupVo webshipGroup) throws DaoException;
}
