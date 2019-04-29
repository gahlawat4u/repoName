package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import org.junit.Test;

public class FranchiseDaoTest {

    @Test
    public void testFranchiseDao() throws DaoException {
        FranchiseDao dao = new FranchiseDao();
        ManageFranchiseFilter filter = new ManageFranchiseFilter();
        filter.setFranchiseCode(10000001L);
        System.out.println(dao.selectFranchiseByFilter(filter));
    }
}