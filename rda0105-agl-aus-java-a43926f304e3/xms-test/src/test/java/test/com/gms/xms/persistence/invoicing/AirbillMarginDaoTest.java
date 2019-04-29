package test.com.gms.xms.persistence.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.AirbillMarginFilter;
import com.gms.xms.persistence.dao.invoicing.AirbillMarginDao;
import com.gms.xms.txndb.vo.invoicing.AirbillMarginVo;

import java.util.Calendar;
import java.util.List;

/**
 * Posted from AirbillMarginDaoTest
 * <p>
 * Author dattrinh Mar 14, 2016 11:05:50 AM
 */
public class AirbillMarginDaoTest {
    public static void main(String[] args) throws DaoException {
        AirbillMarginDao dao = new AirbillMarginDao();
        AirbillMarginFilter filter = new AirbillMarginFilter();
        filter.setFranchiseList("101,102");
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 0, 18);
        filter.setStartDate(cal.getTime());
        filter.setEndDate(cal.getTime());
        List<AirbillMarginVo> AirbillMarginVos = dao.getAirbillMarginByFilter(filter);
        long recordCount = dao.countAirbillMarginByFilter(filter);
        AirbillMarginVo summary = dao.sumAirbillMarginByFilter(filter);
        System.out.println("Record count: " + recordCount);
        System.out.println("Summary record: " + summary);
        System.out.println("Result list:\n" + AirbillMarginVos);
    }
}
