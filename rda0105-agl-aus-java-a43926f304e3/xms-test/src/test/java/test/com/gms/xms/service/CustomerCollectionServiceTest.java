package test.com.gms.xms.service;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.service.customer.CustomerCollectionServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerCollectionService;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from CustomerCollectionServiceTest
 * <p>
 * Author DatTV Sep 29, 2015
 */
public class CustomerCollectionServiceTest {
    @Test
    public void selectByCustCodeTest() throws Exception {
        ICustomerCollectionService collectionService = new CustomerCollectionServiceImp();
        System.out.println(collectionService.selectByCustCode("10000002"));
    }

    @Test
    public void updateTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        CustomerCollectionVo collectionVo = new CustomerCollectionVo();
        collectionVo.setUserId(1259L);
        collectionVo.setFreightCreditLimit(10000.00);
        ICustomerCollectionService collectionService = new CustomerCollectionServiceImp();
        collectionService.update(context, collectionVo);
    }
}