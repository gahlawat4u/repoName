package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.DimensionFilter;
import com.gms.xms.txndb.vo.webship.DimensionVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from DimensionDaoService
 * <p>
 * Author DatTV Date Apr 2, 2015
 */
public class DimensionDao extends BaseDao<DimensionVo> {

    /**
     * Gets dimension by id
     *
     * @param id
     * @return {@link DimensionVo}
     * @throws DaoException
     */
    public DimensionVo getById(Integer id) throws DaoException {
        return select(id, "Dimension.getById");
    }

    /**
     * Gets list of dimension by webship id
     *
     * @param webshipId
     * @return List<{@link DimensionVo>}
     * @throws DaoException
     */
    public List<DimensionVo> getByWebshipId(int webshipId) throws DaoException {
        return selectList(webshipId, "Dimension.getByWebshipId");
    }

    /**
     * Inserts new dimension into the database
     *
     * @param dimension {@link DimensionVo}
     * @throws DaoException
     */
    public void insert(Map<String, String> context, DimensionVo dimension) throws DaoException {
        insert(context, dimension, "Dimension.insert");
    }

    /**
     * Updates a dimension
     *
     * @param dimension {@link DimensionVo}
     * @throws DaoException
     */
    public void update(Map<String, String> context, DimensionVo dimension) throws DaoException {
        update(context, dimension, "Dimension.update");
    }

    /**
     * Deletes a dimension by id
     *
     * @param id
     * @throws DaoException
     */
    public void deleteById(Map<String, String> context, Integer id) throws DaoException {
        delete(context, id, "Dimension.deleteById");
    }

    public List<DimensionVo> selectByFilter(DimensionFilter filter) throws DaoException {
        return selectList(filter, "Dimension.selectByFilter");
    }

    public Long countByFilter(DimensionFilter filter) throws DaoException {
        return (Long) selectObject(filter, "Dimension.countByFilter");
    }
}