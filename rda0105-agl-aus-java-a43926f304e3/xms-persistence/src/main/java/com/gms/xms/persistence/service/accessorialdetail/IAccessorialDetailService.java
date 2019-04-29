package com.gms.xms.persistence.service.accessorialdetail;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.AccessorialDetailFilter;
import com.gms.xms.txndb.vo.AccessorialDetailUpdateVo;
import com.gms.xms.txndb.vo.AccessorialDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IAccessorialDetailService
 * <p>
 * Author HungNT Date Sep 16, 2015
 */
public interface IAccessorialDetailService {
    public List<AccessorialDetailVo> getListbyAccessorialId(AccessorialDetailFilter filter) throws DaoException;

    public Integer getRecordCountByAccessorialId(Long accessorialId) throws DaoException;

    public AccessorialDetailVo getAccessorialDetail(AccessorialDetailFilter filter) throws DaoException;

    public void updateAccessorialDetail(Map<String, String> context, AccessorialDetailUpdateVo accessorialDetailUpdateVo) throws DaoException;

    public void addAccessorialDetail(Map<String, String> context, AccessorialDetailVo accessorialDetailVo) throws DaoException;

    public void deleteAccessorialDetail(Map<String, String> context, AccessorialDetailFilter filter) throws DaoException;
}
