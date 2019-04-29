package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.txndb.vo.CustomerFilter;
import com.gms.xms.txndb.vo.CustomerVo;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CustomerDaoTest {

    @Test
    public void searchCustomerTest() throws DaoException {
        CustomerDao customerDao = new CustomerDao();
        CustomerFilter filter = new CustomerFilter();
        filter.setFranchiseCodeList(Arrays.asList(new String[]{"100", "101"}));
        filter.setDhlInternationalAccount("3p");
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(customerDao.searchCustomers(filter));
        System.out.println("Count: " + customerDao.searchCustomersCount(filter));
    }

    @Test
    public void listCustomerTest() throws DaoException {
        CustomerDao customerDao = new CustomerDao();
        CustomerFilter filter = new CustomerFilter();
        filter.setFranchiseCodeList(Arrays.asList(new String[]{"100", "101"}));
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(customerDao.selectCustomerList(filter));
        System.out.println("Count: " + customerDao.countCustomerList(filter));
    }

    @Test
    public void getQuotedCustomersByFranchisesTest() throws DaoException {
        CustomerDao customerDao = new CustomerDao();
        List<CustomerVo> customerVos = customerDao.getQuotedCustomersByFranchises("101,102");
        for (CustomerVo customerVo : customerVos) {
            System.out.println(customerVo.getCustomerCode() + " - " + customerVo.getCustomerAddress().getCustomerName());
        }
    }
}