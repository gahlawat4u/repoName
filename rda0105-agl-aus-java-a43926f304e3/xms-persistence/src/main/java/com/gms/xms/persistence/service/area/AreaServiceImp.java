package com.gms.xms.persistence.service.area;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AreaFilter;
import com.gms.xms.persistence.dao.area.AreaDao;
import com.gms.xms.txndb.vo.area.AreaVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AreaServiceImp
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class AreaServiceImp implements IAreaService {

    @Override
    public List<AreaVo> selectAll() throws DaoException {
        AreaDao areaDao = new AreaDao();
        return areaDao.selectAll();
    }

    @Override
    public List<AreaVo> selectByFilter(AreaFilter filter) throws DaoException {
        AreaDao areaDao = new AreaDao();
        return areaDao.selectByFilter(filter);
    }

    @Override
    public long countByFilter(AreaFilter filter) throws DaoException {
        AreaDao areaDao = new AreaDao();
        return areaDao.countByFilter(filter);
    }

    @Override
    public void delete(Map<String, String> context, Integer AreaId) throws DaoException {
        AreaDao areaDao = new AreaDao();
        areaDao.delete(context, AreaId);
    }

    @Override
    public void insert(Map<String, String> context, AreaVo Area) throws DaoException {
        AreaDao areaDao = new AreaDao();
        areaDao.insert(context, Area);
    }

    @Override
    public void update(Map<String, String> context, AreaVo Area) throws DaoException {
        AreaDao areaDao = new AreaDao();
        areaDao.update(context, Area);
    }

    @Override
    public long countByName(AreaVo area) throws DaoException {
        AreaDao areaDao = new AreaDao();
        return areaDao.countByName(area);
    }
}
