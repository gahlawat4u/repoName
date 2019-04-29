package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.AirbillAdjustmentDetailDao;
import com.gms.xms.persistence.dao.AirbillAdjustmentTotalDao;
import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentDetailFilter;
import org.junit.Test;

import java.util.Calendar;

public class AirbillAdjustmentDetailDaoTest {

    @Test
    public void selectDetailByFilterTest() throws DaoException {
        AirbillAdjustmentDetailFilter filter = new AirbillAdjustmentDetailFilter();
        Calendar cal = Calendar.getInstance();
        cal.set(2014, 10, 01, 0, 0, 0); // 01-11-2014
        filter.setRequestDateFrom(cal.getTime());
        cal.set(2014, 11, 12, 0, 0, 0); // 12-12-2014
        filter.setRequestDateTo(cal.getTime());
        cal.set(2014, 11, 01, 0, 0, 0); // 01-12-2014
        filter.setResponseDateFrom(cal.getTime());
        cal.set(2015, 0, 1, 0, 0, 0); // 01-01-2015
        filter.setResponseDateTo(cal.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(filter);

        AirbillAdjustmentDetailDao dao = new AirbillAdjustmentDetailDao();
        System.out.println(dao.selectDetailByFilter(filter));

        AirbillAdjustmentTotalDao totalDao = new AirbillAdjustmentTotalDao();
        System.out.println(totalDao.totalDetailByFilter(filter));
    }
}