package test.com.gms.xms.persistence.customer.baserate;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.admin.customer.baserate.ManageCustBaseRateDao;
import com.gms.xms.txndb.vo.admin.customer.baserate.CustBaseRateDetailByFilter;
import com.gms.xms.txndb.vo.admin.customer.baserate.CustBaseRateVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Apr 5, 2016 10:13:50 AM
 * <p>
 * Author dattrinh
 */
public class ManageCustBaseRateDaoTest {
    public static void main(String[] args) throws DaoException {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        System.out.println(dao.getServiceTypeByCarrier(1L));
        CustBaseRateVo baseRateVo = new CustBaseRateVo();
        baseRateVo.setCustomerCode("10200008");
        baseRateVo.setShipmentTypeId(1);
        baseRateVo.setContent(1);
        baseRateVo.setBound(0);
        System.out.println(dao.getCustBaseRateByFilter(baseRateVo));
        CustBaseRateDetailByFilter filter = new CustBaseRateDetailByFilter();
        filter.setCustomerBaseRateId(7765L);
        List<String> zones = new ArrayList<String>();
        zones.add("1");
        zones.add("2");
        zones.add("3");
        zones.add("4");
        zones.add("5");
        zones.add("6");
        filter.setZones(zones);
        System.out.println(dao.getCustBaseRateDetailByFilter(filter));
        System.out.println(dao.getZonesBySheetId(243L));
    }
}
