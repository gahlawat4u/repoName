package com.gms.xms.persistence.service.accessorial;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.surchargelist.SurchargeListVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IAccessorialSerivce
 * <p>
 * Author HungNT Date Jul 27, 2015
 */
public interface IAccessorialSerivce {
    public List<AccessorialVo> getAccessorialListByFilter(AccessorialFilter accessorialFilter) throws DaoException;

    public AccessorialVo getAccessorialByFilter(AccessorialFilter accessorialFilter) throws DaoException;

    public AccessorialVo selectById(Long accessorialId) throws DaoException;

    public List<SurchargeListVo> getSurchargeList(AccessorialFilter filter) throws DaoException;

    public Integer getSurchargeListRecordCount(AccessorialFilter filter) throws DaoException;

    public void addAccessorial(Map<String, String> context, AccessorialVo accessorialVo) throws DaoException;

    public void updateAccessorial(Map<String, String> context, AccessorialVo accessorialVo) throws DaoException;

    public List<SurchargeListVo> selectAccessorialListByCarrier(AccessorialVo accessorialVo) throws DaoException;

}
