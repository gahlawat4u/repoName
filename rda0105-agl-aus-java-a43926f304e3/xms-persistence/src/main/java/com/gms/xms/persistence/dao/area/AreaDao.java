package com.gms.xms.persistence.dao.area;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AreaFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.area.AreaVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AreaDao
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class AreaDao extends BaseDao<AreaVo> {
    public List<AreaVo> selectAll() throws DaoException {
        return this.selectList(null, "Area.selectAll");
    }

    public void insert(Map<String, String> context, AreaVo Area) throws DaoException {
        insert(context, Area, "Area.insert");
    }

    public void update(Map<String, String> context, AreaVo Area) throws DaoException {
        update(context, Area, "Area.update");
    }

    public void delete(Map<String, String> context, Integer AreaId) throws DaoException {
        delete(context, AreaId, "Area.delete");
    }

    public List<AreaVo> selectByFilter(AreaFilter filter) throws DaoException {
        return this.selectList(filter, "Area.selectByFilter");
    }

    public long countByFilter(AreaFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Area.countByFilter");
    }

    public long countByName(AreaVo area) throws DaoException {
        return (long) this.selectObject(area, "Area.countAreaByName");
    }
}
