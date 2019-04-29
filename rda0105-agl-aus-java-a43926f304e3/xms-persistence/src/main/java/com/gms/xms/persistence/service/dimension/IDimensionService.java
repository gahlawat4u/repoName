package com.gms.xms.persistence.service.dimension;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.DimensionFilter;
import com.gms.xms.txndb.vo.webship.DimensionVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IDimensionService
 * <p>
 * Author DatTV Date Jul 14, 2015 11:13:41 AM
 */
public interface IDimensionService {
    public DimensionVo getById(Integer id) throws DaoException;

    public List<DimensionVo> getByWebshipId(Integer webshipId) throws DaoException;

    public void insert(Map<String, String> context, DimensionVo dimension) throws DaoException;

    public void update(Map<String, String> context, DimensionVo dimension) throws DaoException;

    public void deleteById(Map<String, String> context, Integer id) throws DaoException;

    public List<DimensionVo> selectByFilter(DimensionFilter filter) throws DaoException;

    public Long countByFilter(DimensionFilter filter) throws DaoException;
}
