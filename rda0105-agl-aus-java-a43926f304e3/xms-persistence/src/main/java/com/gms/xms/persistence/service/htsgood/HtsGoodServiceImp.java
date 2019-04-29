package com.gms.xms.persistence.service.htsgood;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.HtsGoodDao;
import com.gms.xms.txndb.vo.HtsGoodVo;

import java.util.List;

public class HtsGoodServiceImp implements IHtsGoodService {
    private HtsGoodDao dao = new HtsGoodDao();

    @Override
    public List<HtsGoodVo> getHtsGoodByDescription(String description) throws DaoException {
        return dao.getHtsGoodListByDescription(description);
    }

    @Override
    public HtsGoodVo getHtsGoodById(Integer htsGoodId) throws DaoException {
        return dao.getHtsGoodById(htsGoodId);
    }

    @Override
    public List<HtsGoodVo> selectHtsGoodByIdOrCode(String codeBinded) throws DaoException {
        return dao.selectHtsGoodByIdOrCode(codeBinded);
    }
}
