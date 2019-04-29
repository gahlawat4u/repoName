package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.AddressBookDetailDao;
import com.gms.xms.txndb.vo.webship.AddressBookDetailFilter;
import com.gms.xms.txndb.vo.webship.AddressBookDetailVo;
import org.junit.Test;

import java.util.List;

public class AddressDetailDaoTest {

    @Test
    public void selectByFilterTest() throws DaoException {
        AddressBookDetailDao addressDetailDao = new AddressBookDetailDao();
        AddressBookDetailFilter filter = new AddressBookDetailFilter();
        filter.setCustomerCode(10000001L);
        filter.setWebshipId(3027L);
        // filter.setCompanyName("");
        filter.setContactName("Molly");
        List<AddressBookDetailVo> addressDetailVos = addressDetailDao.selectByFilter(filter);
        System.out.println(addressDetailVos);
    }
}