package com.gms.xms.persistence.service.supply;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.SupplyDao;
import com.gms.xms.txndb.vo.SupplyVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SupplyServiceImp
 * <p>
 * Author DatTV Date Jul 15, 2015 5:18:38 PM
 */
public class SupplyServiceImp implements ISupplyService {

    private SupplyDao supplyDao = new SupplyDao();

    @Override
    public List<SupplyVo> selectAll() throws DaoException {
        return supplyDao.selectAll();
    }

    @Override
    public List<SupplyVo> selectByServiceId(Integer serviceId) throws DaoException {
        return supplyDao.selectByServiceId(serviceId);
    }

    @Override
    public SupplyVo selectById(Integer supplyId) throws DaoException {
        return supplyDao.selectById(supplyId);
    }

    @Override
    public List<SupplyVo> selectWithLocalization() throws DaoException {
        SupplyDao dao = new SupplyDao();
        return dao.selectWithLocalization();
    }

    @Override
    public void insert(Map<String, String> context, SupplyVo supplyVo) throws DaoException {
        SupplyDao dao = new SupplyDao();
        dao.insert(context, supplyVo);
    }

    @Override
    public void update(Map<String, String> context, SupplyVo supplyVo) throws DaoException {
        SupplyDao dao = new SupplyDao();
        dao.update(context, supplyVo);
    }

    @Override
    public void delete(Map<String, String> context, SupplyVo supplyVo) throws DaoException {
        SupplyDao dao = new SupplyDao();
        dao.delete(context, supplyVo);

    }
}
