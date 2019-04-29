package test.com.gms.xms.persistence.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.StatementInvoiceFilter;
import com.gms.xms.persistence.dao.invoicing.StatementDao;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementCustomerVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementInvoiceVo;

import java.util.List;

/**
 * Posted from StatementDaoTest
 * <p>
 * Author dattrinh Mar 16, 2016 11:40:31 AM
 */
public class StatementDaoTest {
    public static void main(String[] args) throws DaoException {
        StatementDao dao = new StatementDao();
        String franchiseList = "100,101";
        List<StatementCustomerVo> customers = dao.getCustomerByFranchises(franchiseList);
        System.out.println(customers);
        System.out.println(dao.getBillingAddressByCustCode("10200089"));
        StatementInvoiceFilter filter = new StatementInvoiceFilter();
        filter.setCustomerCode("10200089");
        List<StatementInvoiceVo> invoiceVos = dao.getInvoiceByCustCode(filter);
        System.out.println(invoiceVos);
        System.out.println("Invoice counter: " + dao.countInvoiceByCustCode(filter));
        System.out.println("Summary: " + dao.sumInvoiceByCustCode(filter));
    }
}
