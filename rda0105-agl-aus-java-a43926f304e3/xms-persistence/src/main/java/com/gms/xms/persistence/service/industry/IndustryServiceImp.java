package com.gms.xms.persistence.service.industry;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.IndustryFilter;
import com.gms.xms.persistence.dao.industry.IndustryDao;
import com.gms.xms.txndb.vo.industry.IndustryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IndustryServiceImp
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class IndustryServiceImp implements IIndustryService {

    @Override
    public List<IndustryVo> selectAll() throws DaoException {
        IndustryDao industryDao = new IndustryDao();
        return industryDao.selectAll();
    }

    @Override
    public List<IndustryVo> selectByFilter(IndustryFilter filter) throws DaoException {
        IndustryDao industryDao = new IndustryDao();
        return industryDao.selectByFilter(filter);
    }

    @Override
    public long countByFilter(IndustryFilter filter) throws DaoException {
        IndustryDao industryDao = new IndustryDao();
        return industryDao.countByFilter(filter);
    }

    @Override
    public void delete(Map<String, String> context, Integer IndustryId) throws DaoException {
        IndustryDao industryDao = new IndustryDao();
        industryDao.delete(context, IndustryId);
    }

    @Override
    public void insert(Map<String, String> context, IndustryVo Industry) throws DaoException {
        IndustryDao industryDao = new IndustryDao();
        industryDao.insert(context, Industry);
    }

    @Override
    public void update(Map<String, String> context, IndustryVo Industry) throws DaoException {
        IndustryDao industryDao = new IndustryDao();
        industryDao.update(context, Industry);
    }

    @Override
    public long countByName(IndustryVo industry) throws DaoException {
        IndustryDao industryDao = new IndustryDao();
        return industryDao.countByName(industry);
    }
}
