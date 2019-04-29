package com.gms.xms.persistence.service.user;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.users.manage.UserFilter;
import com.gms.xms.txndb.vo.UserLevelVo;
import com.gms.xms.txndb.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IUserService
 * <p>
 * Author DatTV Date Aug 14, 2015 11:30:55 AM
 */
public interface IUserService {
    public UserVo getUserById(String userId) throws DaoException;

    public List<UserVo> getCollectors() throws DaoException;

    public List<UserVo> getCollectorsByFranchises(String franchiseList) throws DaoException;

    public List<UserVo> getSaleReps(List<String> franchiseCodeList) throws DaoException;

    public List<UserVo> getSaleRepsByFranchises(String franchiseList) throws DaoException;

    public List<UserVo> getManagedUsersByUserId(UserFilter filter) throws DaoException;

    public long countManagedUsersByUserId(UserFilter filter) throws DaoException;

    public List<UserLevelVo> getUserLevelsByUserId(Long userId) throws DaoException;

    public void insertUser(Map<String, String> context, UserVo user) throws DaoException;

    public void updateUser(Map<String, String> context, UserVo user) throws DaoException;

    public Double getUserLevel(Integer userLevelId) throws Exception;

    public List<UserVo> getRemainingSaleRepsByFranchises(String franchiseList) throws DaoException;

    public void deleteById(Map<String, String> context, Long userId) throws DaoException;

    public List<UserVo> getGenerateReportUsers(String franchiseList) throws DaoException;

    public UserVo checkLogin(String franchiseCode, String username, String password) throws Exception;
}
