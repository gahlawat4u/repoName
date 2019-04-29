package test.com.gms.xms.persistence.statistics;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ProspectDao;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.txndb.vo.UserVo;

/**
 * Posted from Aug 17, 2016 11:55:11 AM
 * <p>
 * Author dattrinh
 */
public class ProspectDaoTest {
    public static void main(String[] args) {
        ProspectDao dao = new ProspectDao();
        try {
            UserDao userDao = new UserDao();
            UserVo userVo = new UserVo();
            userVo = userDao.getUserById("74");
            System.out.println(userVo);
            System.out.println(dao.getProspectByUser(userVo));
            System.out.println(userDao.getByParentId(74L));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
