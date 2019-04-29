package test.com.gms.xms.persistence.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.admin.SystemStatsDao;

/**
 * Posted from Aug 31, 2016 4:25:45 PM
 * <p>
 * Author dattrinh
 */
public class SysStatDaoTest {
    public static void main(String[] args) throws DaoException {
        SystemStatsDao dao = new SystemStatsDao();
        System.out.println(dao.getFilterCollections("GE233479390AU"));
    }
}
