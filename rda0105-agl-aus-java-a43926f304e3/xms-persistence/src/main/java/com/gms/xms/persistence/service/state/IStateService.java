package com.gms.xms.persistence.service.state;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.StateVo;

import java.util.List;

/**
 * Posted from IStateService
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public interface IStateService {
    public List<StateVo> getAllState() throws Exception;

    public List<StateVo> getStateListByCountryIdList(List<String> countryIdList) throws Exception;

    public List<StateVo> getStateListByCountryId(Long countryId) throws DaoException;

    public Long getStateRecordCount(Long countryId) throws DaoException;
}
