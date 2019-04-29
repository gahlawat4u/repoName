package com.gms.xms.persistence.service.industry;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.IndustryFilter;
import com.gms.xms.txndb.vo.industry.IndustryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IIndustryService
 * <p>
 * Author DatTV Sep 10, 2015
 */
public interface IIndustryService {
    public List<IndustryVo> selectAll() throws DaoException;

    public List<IndustryVo> selectByFilter(IndustryFilter filter) throws DaoException;

    public long countByFilter(IndustryFilter filter) throws DaoException;

    public void delete(Map<String, String> context, Integer IndustryId) throws DaoException;

    public void insert(Map<String, String> context, IndustryVo Industry) throws DaoException;

    public void update(Map<String, String> context, IndustryVo Industry) throws DaoException;

    public long countByName(IndustryVo industry) throws DaoException;

}
