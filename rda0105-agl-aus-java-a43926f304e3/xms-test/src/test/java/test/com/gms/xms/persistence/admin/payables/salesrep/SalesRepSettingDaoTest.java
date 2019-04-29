package test.com.gms.xms.persistence.admin.payables.salesrep;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepSettingFilter;
import com.gms.xms.persistence.dao.admin.payables.salesrep.SalesRepSettingDao;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepSettingVo;

/**
 * Posted from SalesRepSettingDaoTest
 * <p>
 * Author dattrinh Mar 21, 2016 1:39:50 PM
 */
public class SalesRepSettingDaoTest {
    public static void main(String[] args) throws DaoException {
        SalesRepSettingDao dao = new SalesRepSettingDao();
        SalesRepSettingFilter filter = new SalesRepSettingFilter();
        filter.setFranchiseList("101,102");
        System.out.println(dao.getSalesRepSettingsByFilter(filter));
        System.out.println("Sales Rep Setting details");
        SalesRepSettingVo salesRepSettingVo = dao.getSalesRepSettingsBySaleRepId(27L);
        System.out.println(salesRepSettingVo);
        System.out.println("New sales rep setting:");
        SalesRepSettingVo newSalesRepSetting = dao.getNewSalesRepSettingByUserId(43L);
        System.out.println(newSalesRepSetting);
    }
}
