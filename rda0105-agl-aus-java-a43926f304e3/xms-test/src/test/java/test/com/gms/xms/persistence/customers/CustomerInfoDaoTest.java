package test.com.gms.xms.persistence.customers;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.customers.CustomerInfoDao;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerFilter;
import org.junit.Test;

public class CustomerInfoDaoTest {
    @Test
    public void selectByBasicCustomerFilterTest() throws DaoException {
        CustomerInfoDao customerInfoDao = new CustomerInfoDao();
        BasicCustomerFilter filter = new BasicCustomerFilter();
        filter.setFranchiseCode("100");
        System.out.println(customerInfoDao.selectByBasicCustomerFilter(filter));
    }

    @Test
    public void getCustomerAccountSetupInfoTest() throws DaoException {
        CustomerInfoDao customerInfoDao = new CustomerInfoDao();
        System.out.println(customerInfoDao.getCustomerAccountSetupInfo("10000002"));
    }

    @Test
    public void getManageCustomerAddressInfoTest() throws DaoException {
        CustomerInfoDao customerInfoDao = new CustomerInfoDao();
        System.out.println(customerInfoDao.getManageCustomerAddressInfo("10000002"));
    }
}
