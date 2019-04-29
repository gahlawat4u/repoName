package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.users.manage.UserFilter;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepSettingFilter;
import com.gms.xms.txndb.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from UserDaoService.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:22:31 PM
 */
public class UserDao extends BaseDao<Object> {

    public UserVo getUserById(String userId) throws DaoException {
        return (UserVo) select(userId, "User.getUserById");
    }

    public List<UserVo> getCollectors() throws DaoException {
        return selectList(null, "User.getCollectors");
    }

    public List<UserVo> getCollectorsByFranchises(String franchiseList) throws DaoException {
        return selectList(franchiseList, "User.getCollectorsByFranchises");
    }

    public List<UserVo> getSaleReps(List<String> franchiseCodeList) throws DaoException {
        return selectList(franchiseCodeList, "User.getSaleReps");
    }

    public List<UserVo> getSaleRepsByFranchises(String franchiseList) throws DaoException {
        return selectList(franchiseList, "User.getSaleRepsByFranchises");
    }

    public List<UserVo> selectByFilter(UserFilter filter) throws DaoException {
        return this.selectList(filter, "User.selectByFilter");
    }

    public long countByFilter(UserFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "User.countByFilter");
    }

    public void insert(Map<String, String> context, UserVo user) throws DaoException {
        this.insert(context, user, "User.insert");
    }

    public void update(Map<String, String> context, UserVo user) throws DaoException {
        this.update(context, user, "User.update");
    }

    public List<UserVo> getRemainingSaleRepsByFranchises(String franchiseList) throws DaoException {
        return selectList(franchiseList, "User.getRemainingSaleRepsByFranchises");
    }

    public List<UserVo> getExistingSalesReps(SalesRepSettingFilter filter) throws DaoException {
        return selectList(filter, "User.getExistingSalesReps");
    }

    public void deleteById(Map<String, String> context, Long userId) throws DaoException {
        this.delete(context, userId, "User.deleteById");
    }

    public List<UserVo> getGenerateReportUsers(String franchiseList) throws DaoException {
        return selectList(franchiseList, "User.getGenerateReportUsers");
    }

    public List<UserVo> getGenerateReportSalesReps(SalesRepSettingFilter filter) throws DaoException {
        return selectList(filter, "User.getGenerateReportSalesReps");
    }

    public List<Long> getByParentId(Long parentUserId) throws DaoException {
        return selectList(parentUserId, "User.getByParentId");
    }

    public UserVo checkLogin(UserVo filter) throws DaoException {
        return (UserVo) select(filter, "User.checkLogin");
    }
}