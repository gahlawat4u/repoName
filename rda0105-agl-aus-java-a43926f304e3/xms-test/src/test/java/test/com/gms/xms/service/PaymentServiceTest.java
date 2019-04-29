package test.com.gms.xms.service;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.service.payment.IPaymentService;
import com.gms.xms.persistence.service.payment.PaymentServiceImp;
import org.junit.Test;

/**
 * Posted from PaymentServiceTest
 * <p>
 * Author DatTV Sep 29, 2015
 */
public class PaymentServiceTest {
    @Test
    public void selectByInvoiceCodeTest() throws DaoException {
        IPaymentService paymentService = new PaymentServiceImp();
        System.out.println(paymentService.selectByInvoiceCode("10200568OC31"));
    }
}