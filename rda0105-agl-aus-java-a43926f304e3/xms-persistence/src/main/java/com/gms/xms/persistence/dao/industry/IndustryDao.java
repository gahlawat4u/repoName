package com.gms.xms.persistence.dao.industry;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.IndustryFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.industry.IndustryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IndustryDao
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class IndustryDao extends BaseDao<IndustryVo> {
    public List<IndustryVo> selectAll() throws DaoException {
        return this.selectList(null, "Industry.selectAll");
    }

    public void insert(Map<String, String> context, IndustryVo Industry) throws DaoException {
        insert(context, Industry, "Industry.insert");
    }

    public void update(Map<String, String> context, IndustryVo Industry) throws DaoException {
        update(context, Industry, "Industry.update");
    }

    public void delete(Map<String, String> context, Integer IndustryId) throws DaoException {
        delete(context, IndustryId, "Industry.delete");
    }

    public List<IndustryVo> selectByFilter(IndustryFilter filter) throws DaoException {
        return this.selectList(filter, "Industry.selectByFilter");
    }

    public long countByFilter(IndustryFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Industry.countByFilter");
    }

    public long countByName(IndustryVo industry) throws DaoException {
        return (long) this.selectObject(industry, "Industry.countIndustryByName");
    }
}
