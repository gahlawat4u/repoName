import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.persistence.utils.invoicecode.InvoiceCodeGeneratorFactory;

import java.util.Date;

/**
 * Posted from Aug 5, 2016 3:11:29 PM
 * <p>
 * Author dattrinh
 */
public class TestGenInvoiceCode {
    public static void main(String[] args) {
        System.out.println("Invoice code: " + GenCodeUtils.generateFRInvoiceCode("1230002", new Date(), 3));
        try {
            System.out.println(InvoiceCodeGeneratorFactory.getGenerator().generateInvoiceCode("12400002", new Date()));
            System.out.println(InvoiceCodeGeneratorFactory.getGenerator().generateInvoiceCode("12400001", new Date()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
