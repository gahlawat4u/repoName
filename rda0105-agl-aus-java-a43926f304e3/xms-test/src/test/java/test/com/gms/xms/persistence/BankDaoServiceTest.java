package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.receivables.BankFilter;
import com.gms.xms.persistence.dao.BankDao;
import com.gms.xms.txndb.vo.BankVo;
import org.junit.Test;

import java.util.List;

public class BankDaoServiceTest {

    @Test
    public void getListOfBanksTest() throws DaoException {
        BankFilter filter = new BankFilter();
        filter.setUserLevelId(1);
        BankDao bankDao = new BankDao();
        List<BankVo> bankVos = bankDao.getBankList(filter);
        System.out.println("Number of banks is " + bankVos);
    }
}