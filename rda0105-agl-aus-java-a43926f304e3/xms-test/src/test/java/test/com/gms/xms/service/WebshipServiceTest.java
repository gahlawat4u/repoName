package test.com.gms.xms.service;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.WebshipFilter;
import com.gms.xms.persistence.service.webship.IWebshipService;
import com.gms.xms.persistence.service.webship.WebshipServiceImp;
import org.junit.Test;

/**
 * Posted from WebshipServiceTest
 * <p>
 * Author DatTV Sep 29, 2015
 */
public class WebshipServiceTest {
    @Test
    public void selectByFilterTest() throws Exception {
        IWebshipService service = new WebshipServiceImp();
        WebshipFilter filter = new WebshipFilter();
        filter.setCustomerCode("10000002");
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(service.selectByFilter(filter));
    }

    @Test
    public void countByFilterTest() throws DaoException {
        IWebshipService service = new WebshipServiceImp();
        WebshipFilter filter = new WebshipFilter();
        filter.setCustomerCode("10000002");
        System.out.println("Count: " + service.countByFilter(filter));
    }
}