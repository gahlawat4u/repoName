package com.gms.xms.persistence.service.htsgood;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.HtsGoodVo;

import java.util.List;

public interface IHtsGoodService {
    public List<HtsGoodVo> getHtsGoodByDescription(String description) throws DaoException;

    public HtsGoodVo getHtsGoodById(Integer htsGoodId) throws DaoException;

    public List<HtsGoodVo> selectHtsGoodByIdOrCode(String codeBinded) throws DaoException;
}
