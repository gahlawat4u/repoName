package com.gms.xms.persistence.service.dimension;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.DimensionDao;
import com.gms.xms.txndb.vo.webship.DimensionFilter;
import com.gms.xms.txndb.vo.webship.DimensionVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from DimensionServiceImp
 * <p>
 * Author DatTV Date Jul 14, 2015 11:15:50 AM
 */
public class DimensionServiceImp implements IDimensionService {
    private DimensionDao dimensionDao = new DimensionDao();

    @Override
    public DimensionVo getById(Integer id) throws DaoException {
        return dimensionDao.getById(id);
    }

    @Override
    public List<DimensionVo> getByWebshipId(Integer webshipId) throws DaoException {
        return dimensionDao.getByWebshipId(webshipId);
    }

    @Override
    public void insert(Map<String, String> context, DimensionVo dimension) throws DaoException {
        dimensionDao.insert(context, dimension);
    }

    @Override
    public void update(Map<String, String> context, DimensionVo dimension) throws DaoException {
        dimensionDao.update(context, dimension);
    }

    @Override
    public void deleteById(Map<String, String> context, Integer id) throws DaoException {
        dimensionDao.deleteById(context, id);
    }

    @Override
    public List<DimensionVo> selectByFilter(DimensionFilter filter) throws DaoException {
        return dimensionDao.selectByFilter(filter);
    }

    @Override
    public Long countByFilter(DimensionFilter filter) throws DaoException {
        return dimensionDao.countByFilter(filter);
    }
}