package test.com.gms.xms.service.admin.invoicing;

import com.gms.xms.persistence.service.admin.invoicing.IViewEditInvoiceService;
import com.gms.xms.persistence.service.admin.invoicing.ViewEditInvoiceServiceImp;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.AirbillInfoVo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from InvoiceinfoServiceTest.java
 * <p>
 * Author TANDT
 */
public class InvoiceinfoServiceTest {
    @Test
    public void invoiceinfoServiceTest() throws Exception {
        IViewEditInvoiceService service = new ViewEditInvoiceServiceImp();
        // InvoiceInfoVo invoiceInfoVo = new InvoiceInfoVo();
        // invoiceInfoVo = service.selectInvoiceInfoById(1219L);
        List<AirbillInfoVo> shipmentInvoiceDetailVo = new ArrayList<AirbillInfoVo>();
        shipmentInvoiceDetailVo = service.selectAirbillList(1219L);
        System.out.println(shipmentInvoiceDetailVo);
    }
}