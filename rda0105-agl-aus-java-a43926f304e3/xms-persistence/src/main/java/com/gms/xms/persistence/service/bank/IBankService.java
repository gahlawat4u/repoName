package com.gms.xms.persistence.service.bank;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.receivables.BankFilter;
import com.gms.xms.txndb.vo.BankVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IBankService
 * <p>
 * Author HoangPH Nov 4, 2015
 */
public interface IBankService {
    public List<BankVo> selectByFilter(BankFilter filter) throws DaoException;

    public long countByFilter(BankFilter filter) throws DaoException;

    public BankVo selectById(Long bankId) throws DaoException;

    public void delete(Map<String, String> context, Long bankId) throws DaoException;

    public void insert(Map<String, String> context, BankVo bank) throws DaoException;

    public void update(Map<String, String> context, BankVo bank) throws DaoException;

    public long countByName(BankVo bank) throws DaoException;
}