package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.txndb.vo.CustomerBaseRateDetailVo;
import com.gms.xms.txndb.vo.CustomerBaseRateFilter;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import org.junit.Test;

import java.util.List;

/**
 * Posted from CustomerBaseRateDaoTest
 * <p>
 * Author DatTV Sep 17, 2015
 */
public class CustomerBaseRateDaoTest {

    @Test
    public void getCustomerBaseRateDetailTest() throws DaoException {
        CustomerBaseRateDao baseRateDao = new CustomerBaseRateDao();
        CustomerBaseRateFilter filter = new CustomerBaseRateFilter();
        filter.setCustomerCode(10000003L);
        filter.setShipmentTypeId(1);
        filter.setContent(1);
        filter.setBound(0);
        List<CustomerBaseRateVo> baseRateVos = baseRateDao.getBaseRateDetailByFilter(filter);
        for (CustomerBaseRateVo customerBaseRateVo : baseRateVos) {
            System.out.println("=== BASE ==");
            System.out.println(customerBaseRateVo);
            System.out.println("=== DETAILS ==");
            for (CustomerBaseRateDetailVo detail : customerBaseRateVo.getCustomerBaseRateDetails()) {
                System.out.println(detail);
            }
        }
        System.out.println();
    }
}