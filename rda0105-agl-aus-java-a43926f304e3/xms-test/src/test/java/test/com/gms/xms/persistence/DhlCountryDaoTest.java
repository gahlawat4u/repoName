package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.persistence.dao.DhlCountryDao;

public class DhlCountryDaoTest {
    public static void main(String[] args) throws DaoException {
        DhlCountryDao dao = new DhlCountryDao();
        SysStatsFilter filter = new SysStatsFilter();
        filter.setPage(1);
        filter.setOrderType(0);
        filter.setOrderField("dhl_ap_code");
        System.out.println(dao.getDhlCountryZones(filter));
    }
}