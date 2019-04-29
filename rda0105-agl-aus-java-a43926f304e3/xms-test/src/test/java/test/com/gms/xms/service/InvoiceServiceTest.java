package test.com.gms.xms.service;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.ManageCustomerInvoiceFilter;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceVo;
import org.junit.Test;

import java.util.List;

/**
 * Posted from InvoiceServiceTest
 * <p>
 * Author DatTV Sep 29, 2015
 */
public class InvoiceServiceTest {
    @Test
    public void getCustomerInvoicesTest() throws Exception {
        ManageCustomerInvoiceFilter filter = new ManageCustomerInvoiceFilter();
        filter.setCustomerCode("10000002");
        filter.setFilterType(0);
        IInvoiceService invoiceService = new InvoiceServiceImp();
        List<ManageCustomerInvoiceVo> invoiceVos = invoiceService.getCustomerInvoices(filter);
        System.out.println(invoiceVos);
        System.out.println("Count: " + invoiceService.getCustomerInvoiceCount(filter));
    }

    @Test
    public void getInvoiceDetailByCodeTest() throws DaoException {
        IInvoiceService invoiceService = new InvoiceServiceImp();
        System.out.println(invoiceService.getByInvoiceDetailCode("10000000OF02"));
    }
}