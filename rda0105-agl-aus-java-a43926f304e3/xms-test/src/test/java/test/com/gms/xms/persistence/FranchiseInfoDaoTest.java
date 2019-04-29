package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import org.junit.Test;

public class FranchiseInfoDaoTest {

    @Test
    public void getListOfBanksTest() throws DaoException {

        FranchiseDao franchiseDao = new FranchiseDao();
        FranchiseInfoVo franchiseVo = franchiseDao.getFranchiseInfoByCode("101");
        FranchiseInfoVo parentFranchiseVo = franchiseDao.getParentFranchiseByCode("101");
        // assertTrue(bankVos.size() > 0);
        System.out.println("franchiseVo " + franchiseVo);
        System.out.println("parentFranchiseVo " + parentFranchiseVo);
    }
}