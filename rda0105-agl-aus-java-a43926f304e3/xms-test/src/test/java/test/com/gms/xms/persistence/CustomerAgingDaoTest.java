package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.customeraging.CustomerAgingDao;
import com.gms.xms.persistence.dao.customeraging.CustomerAgingTaskDaoService;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerAgingDaoTest {

    private CustomerAgingDao customerAgingDao = new CustomerAgingDao();
    private CustomerAgingTaskDaoService customerAgingTaskDaoService = new CustomerAgingTaskDaoService();

    @Test
    public void prepareDataForCustomerAgingReportTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        CustomerAgingFilter filter = new CustomerAgingFilter();
        filter.setFranchiseCodeList("101");
        filter.setPage(1);
        filter.setPageSize(20);
        filter.setOrderField("customer_code");
        filter.setOrderType(0);
        filter.setRptTxnId("Test-Rpt-Txn-Id-02");
        filter.setEndDate(Calendar.getInstance().getTime());
        customerAgingTaskDaoService.prepareDataForCustomerAgingReport(context, filter);
        List<CustomerAgingVo> result = customerAgingDao.selectByFilter(filter);
        System.out.println(result);
    }
}