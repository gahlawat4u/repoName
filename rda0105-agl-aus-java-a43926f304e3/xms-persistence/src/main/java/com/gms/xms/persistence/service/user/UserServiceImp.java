package com.gms.xms.persistence.service.user;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.CryptUtils;
import com.gms.xms.filter.account.users.manage.UserFilter;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.UserLevelDao;
import com.gms.xms.txndb.vo.UserLevelVo;
import com.gms.xms.txndb.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from UserServiceImp
 * <p>
 * Author DatTV Date Aug 14, 2015 11:31:41 AM
 */
public class UserServiceImp implements IUserService {
    private UserDao userDao = new UserDao();

    @Override
    public UserVo getUserById(String userId) throws DaoException {
        return userDao.getUserById(userId);
    }

    @Override
    public List<UserVo> getCollectors() throws DaoException {
        return userDao.getCollectors();
    }

    @Override
    public List<UserVo> getSaleReps(List<String> franchiseCodeList) throws DaoException {
        return userDao.getSaleReps(franchiseCodeList);
    }

    @Override
    public List<UserVo> getManagedUsersByUserId(UserFilter filter) throws DaoException {
        return userDao.selectByFilter(filter);
    }

    @Override
    public long countManagedUsersByUserId(UserFilter filter) throws DaoException {
        return userDao.countByFilter(filter);
    }

    @Override
    public List<UserLevelVo> getUserLevelsByUserId(Long userId) throws DaoException {
        UserLevelDao levelDao = new UserLevelDao();
        return levelDao.getUserLevelsByUserId(userId);
    }

    @Override
    public void insertUser(Map<String, String> context, UserVo user) throws DaoException {
        userDao.insert(context, user);
    }

    @Override
    public void updateUser(Map<String, String> context, UserVo user) throws DaoException {
        userDao.update(context, user);
    }

    @Override
    public List<UserVo> getCollectorsByFranchises(String franchiseList) throws DaoException {
        return userDao.getCollectorsByFranchises(franchiseList);
    }

    @Override
    public List<UserVo> getSaleRepsByFranchises(String franchiseList) throws DaoException {
        return userDao.getSaleRepsByFranchises(franchiseList);
    }

    @Override
    public Double getUserLevel(Integer userLevelId) throws Exception {
        UserLevelDao userLevelDao = new UserLevelDao();
        UserLevelVo userLevelVo = userLevelDao.selectById(userLevelId);
        if (userLevelVo != null) {
            return userLevelVo.getUserLevelCode();
        } else {
            throw new Exception("Invalid user level id.");
        }
    }

    @Override
    public List<UserVo> getRemainingSaleRepsByFranchises(String franchiseList) throws DaoException {
        return userDao.getRemainingSaleRepsByFranchises(franchiseList);
    }

    @Override
    public void deleteById(Map<String, String> context, Long userId) throws DaoException {
        userDao.deleteById(context, userId);
    }

    @Override
    public List<UserVo> getGenerateReportUsers(String franchiseList) throws DaoException {
        return userDao.getGenerateReportUsers(franchiseList);
    }

    @Override
    public UserVo checkLogin(String franchiseCode, String username, String password) throws Exception {
        UserVo filter = new UserVo();
        filter.setUserCode(Long.valueOf(franchiseCode));
        filter.setUserName(username);
        String realPassword = CryptUtils.Encrypt(password, AppConstants.APP_SETTINGS.getEncryptionKey());
        filter.setPassword(realPassword);
        return userDao.checkLogin(filter);
    }
}
