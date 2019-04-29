package com.gms.xms.persistence.service.area;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AreaFilter;
import com.gms.xms.txndb.vo.area.AreaVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IAreaService
 * <p>
 * Author DatTV Sep 10, 2015
 */
public interface IAreaService {
    public List<AreaVo> selectAll() throws DaoException;

    public List<AreaVo> selectByFilter(AreaFilter filter) throws DaoException;

    public long countByFilter(AreaFilter filter) throws DaoException;

    public void delete(Map<String, String> context, Integer AreaId) throws DaoException;

    public void insert(Map<String, String> context, AreaVo Area) throws DaoException;

    public void update(Map<String, String> context, AreaVo Area) throws DaoException;

    public long countByName(AreaVo area) throws DaoException;

}
