package test.com.gms.xms.persistence.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.RepairAirbillErrorFilter;
import com.gms.xms.persistence.dao.invoice.RepairAirbillErrorDao;
import com.gms.xms.txndb.vo.invoicing.AirbillErrorVo;

import java.util.Calendar;
import java.util.List;

/**
 * Posted from RepairAirbillErrorDaoTest
 * <p>
 * Author dattrinh Mar 7, 2016 4:50:46 PM
 */
public class RepairAirbillErrorDaoTest {
    public static void main(String[] args) {
        RepairAirbillErrorDao dao = new RepairAirbillErrorDao();
        RepairAirbillErrorFilter filter = new RepairAirbillErrorFilter();
        filter.setCarrier(1L);
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 6, 1);
        filter.setFromDate(cal.getTime());
        cal.set(2016, 0, 1);
        filter.setToDate(cal.getTime());
        try {
            List<AirbillErrorVo> airbillErrorVos = dao.getAirbillErrorByFilter(filter);
            System.out.println(airbillErrorVos);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
