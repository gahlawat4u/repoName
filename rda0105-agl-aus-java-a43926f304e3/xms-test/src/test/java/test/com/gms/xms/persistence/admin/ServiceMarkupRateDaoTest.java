package test.com.gms.xms.persistence.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ratesheets.ServiceMarkupRateFilter;
import com.gms.xms.persistence.dao.admin.ServiceMarkupRateDao;

/**
 * Posted from Apr 9, 2016 10:24:18 AM
 * <p>
 * Author dattrinh
 */
public class ServiceMarkupRateDaoTest {
    public static void main(String[] args) throws DaoException {
        ServiceMarkupRateDao dao = new ServiceMarkupRateDao();
        // Get franchise service markup rate.
        ServiceMarkupRateFilter filter = new ServiceMarkupRateFilter();
        filter.setServiceId(1);
        filter.setCode("10100001");
        System.out.println("DHL markup rate of franchise 10100001 is " + dao.getServiceMarkupRateByFranchiseCode(filter));
        // Get customer markup rate.
        filter.setServiceId(3);
        filter.setCode("10300008");
        System.out.println("TNT Dom markup rate of customer 10300008 is " + dao.getServiceMarkupRateByCustomerCode(filter));
        // Get customer profile markup rate.
        filter.setServiceId(52);
        filter.setCode("13");
        System.out.println("Toll Priority markup rate of profile id 13 is " + dao.getServiceMarkupRateByProfileId(filter));
    }
}
