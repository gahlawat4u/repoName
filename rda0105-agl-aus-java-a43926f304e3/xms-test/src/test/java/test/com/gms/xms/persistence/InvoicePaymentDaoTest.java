package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.InvoicePaymentDao;
import com.gms.xms.txndb.vo.InvoicePaymentVo;
import com.gms.xms.txndb.vo.NoteVo;
import org.junit.Test;

import java.util.List;

/**
 * Posted from InvoicePaymentDaoTest
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class InvoicePaymentDaoTest {
    @Test
    public void selectByInvoiceCodeTest() throws DaoException {
        InvoicePaymentDao dao = new InvoicePaymentDao();
        List<InvoicePaymentVo> invoicePaymentVos = dao.selectByInvoiceCode("10100011MF25");
        for (InvoicePaymentVo invoicePaymentVo : invoicePaymentVos) {
            System.out.println(invoicePaymentVo.getInvoicePaymentId() + "\t\t" + invoicePaymentVo.getCustomerPayment().getCheque() + "\t\t" + invoicePaymentVo.getAmount() + "\t\t" + invoicePaymentVo.getCustomerPayment().getPaymentDate());
            for (NoteVo note : invoicePaymentVo.getCustomerPayment().getNotes()) {
                System.out.println("\t\t\t\t\t\t" + note.getNote());
            }
        }
    }
}
