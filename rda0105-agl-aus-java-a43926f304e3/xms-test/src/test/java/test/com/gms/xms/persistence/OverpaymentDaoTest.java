package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.OverpaymentDao;
import org.junit.Test;

public class OverpaymentDaoTest {

    @Test
    public void test() throws DaoException {
        OverpaymentDao daoTest = new OverpaymentDao();
        System.out.println(daoTest.selectCreditTypeByCustomerPaymentId(351081L));
    }
}