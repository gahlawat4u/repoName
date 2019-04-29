package test.com.gms.xms.persistence.user;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.users.manage.UserFilter;
import com.gms.xms.persistence.dao.UserDao;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void selectByFilterTest() throws DaoException {
        UserDao userDao = new UserDao();
        UserFilter filter = new UserFilter();
        filter.setFranchiseList("100,101,102");
        filter.setUserLevelId(3);
        filter.setUserId(201L);
        filter.setUserCode("100");
        System.out.println(userDao.selectByFilter(filter));
    }

    @Test
    public void getCollectorsByFranchisesTest() throws Exception {
        UserDao userDao = new UserDao();
        System.out.println(userDao.getCollectorsByFranchises("100,101"));
    }
}
