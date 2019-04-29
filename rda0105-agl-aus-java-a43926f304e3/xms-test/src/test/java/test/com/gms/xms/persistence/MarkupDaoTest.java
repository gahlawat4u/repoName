package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.MarkupFilter;
import com.gms.xms.persistence.dao.markup.MarkupDao;
import org.junit.Test;

public class MarkupDaoTest {

    @Test
    public void selectByFilterTest() throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        MarkupFilter filter = new MarkupFilter();
        filter.setCustomerCode("10000002");
        filter.setAmount("0");
        filter.setCode("dhl");
        filter.setDescription("dhl");
        filter.setTypeName("fixed");
        filter.setServiceName("dhl");
        filter.setModifiedDate("");
        System.out.println(markupDao.selectByFilter(filter));
    }

    @Test
    public void countByFilterTest() throws DaoException {
        MarkupDao markupDao = new MarkupDao();
        MarkupFilter filter = new MarkupFilter();
        filter.setCustomerCode("10000002");
        filter.setAmount("0");
        filter.setCode("dhl");
        filter.setDescription("dhl");
        filter.setTypeName("fixed");
        filter.setServiceName("dhl");
        System.out.println(markupDao.countByFilter(filter));
    }
}