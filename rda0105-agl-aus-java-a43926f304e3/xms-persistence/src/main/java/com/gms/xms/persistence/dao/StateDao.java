package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdministrationFilter;
import com.gms.xms.txndb.vo.StateVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from StateDao
 * <p>
 * Author HungNT Date May 13, 2015
 */
public class StateDao extends BaseDao<StateVo> {

    /**
     * Get all state
     *
     * @return list of all {@link StateVo}
     * @throws DaoException
     */
    public List<StateVo> selectAllState() throws DaoException {
        return selectList(new StateVo(), "State.selectAllState");
    }

    public List<StateVo> selectStateByCountryIdList(List<String> countryIdList) throws DaoException {
        return selectList(countryIdList, "State.selectStateByCountryIdList");
    }

    /**
     * Get list of state by country id
     *
     * @param {@link Long} countryId
     * @return list of {@link StateVo}
     * @throws DaoException
     */
    public List<StateVo> selectStateByCountryId(Long countryId) throws DaoException {
        return selectList(countryId, "State.selectStateByCountryId");
    }

    /**
     * Get total record count of state by country id
     *
     * @param {@link Long} countryId
     * @return {@link Integer} record count
     * @throws DaoException
     */
    public Long selectStateRecordCountByCountryId(Long countryId) throws DaoException {
        return (Long) selectObject(countryId, "State.selectStateRecordCountByCountryId");
    }

    public long selectListStateByCountryIdCount(AdministrationFilter filter) throws DaoException {
        return (long) selectObject(filter, "State.selectListStateByCountryIdCount");
    }

    public List<StateVo> selectListStateByCountryId(AdministrationFilter filter) throws DaoException {
        return selectList(filter, "State.selectListStateByCountryId");
    }

    public Integer checkInsert(StateVo stateVo) throws DaoException {
        return (int) selectObject(stateVo, "State.checkInsert");
    }

    public Integer checkDelete(StateVo stateVo) throws DaoException {
        return (int) selectObject(stateVo, "State.checkDelete");
    }

    public void insert(Map<String, String> context, StateVo stateVo) throws DaoException {
        this.insert(context, stateVo, "State.insert");
    }

    public void update(Map<String, String> context, StateVo stateVo) throws DaoException {
        this.update(context, stateVo, "State.update");
    }

    public StateVo selectStateById(Long id) throws DaoException {
        return this.select(id, "State.selectStateById");
    }

    public void delete(Map<String, String> context, Long id) throws DaoException {
        this.delete(context, id, "State.delete");
    }

}
