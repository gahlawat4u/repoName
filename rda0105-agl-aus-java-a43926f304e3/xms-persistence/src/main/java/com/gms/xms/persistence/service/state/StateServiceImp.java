package com.gms.xms.persistence.service.state;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.StateDao;
import com.gms.xms.txndb.vo.StateVo;

import java.util.List;

/**
 * Posted from StateServiceImp
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public class StateServiceImp implements IStateService {
    private StateDao stateDao = new StateDao();

    @Override
    public List<StateVo> getAllState() throws Exception {
        List<StateVo> stateVos = stateDao.selectAllState();
        return stateVos;
    }

    @Override
    public List<StateVo> getStateListByCountryIdList(List<String> countryIdList) throws Exception {
        List<StateVo> stateVos = stateDao.selectStateByCountryIdList(countryIdList);
        return stateVos;
    }

    @Override
    public List<StateVo> getStateListByCountryId(Long countryId) throws DaoException {
        List<StateVo> stateVos = stateDao.selectStateByCountryId(countryId);
        return stateVos;
    }

    @Override
    public Long getStateRecordCount(Long countryId) throws DaoException {
        Long recordCount = stateDao.selectStateRecordCountByCountryId(countryId);
        return recordCount;
    }
}
