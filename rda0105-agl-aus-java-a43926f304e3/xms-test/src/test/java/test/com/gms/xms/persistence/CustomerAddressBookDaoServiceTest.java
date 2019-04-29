package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookFilter;
import org.junit.Test;

public class CustomerAddressBookDaoServiceTest {

    @Test
    public void test() throws DaoException {
        CustomerAddressBookFilter filter = new CustomerAddressBookFilter();
        filter.setCustomerCode(10000001L);
        filter.setWebshipId(3027L);
        // long count = addressBookDaoService.getCustomerAddressBookRecordCount(filter);
        filter.setStartRecord(0);
        filter.setRecordSize(10);
        // List<CustomerAddressBookVo> addressBookVo = addressBookDaoService.getCustomerAddressBook(filter);
        // System.out.println("Record count:" + count);
        // System.out.println("AddressBookVo Result:" + addressBookVo);
    }
}