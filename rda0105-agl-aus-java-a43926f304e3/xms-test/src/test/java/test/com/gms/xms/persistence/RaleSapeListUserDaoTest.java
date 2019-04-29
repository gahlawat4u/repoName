package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.txndb.vo.UserVo;
import org.junit.Test;

import java.util.List;

public class RaleSapeListUserDaoTest {
    @Test
    public void test() throws DaoException {
        UserDao userDao = new UserDao();
        List<UserVo> userVos = userDao.getRemainingSaleRepsByFranchises("101,102");
        System.out.println("Number of banks is " + userVos);
    }
}
