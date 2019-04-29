package test.com.gms.xms.persistence.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.CostDeviationFilter;
import com.gms.xms.persistence.dao.invoicing.CostDeviationDao;
import com.gms.xms.txndb.vo.invoicing.CostDeviationVo;

import java.util.Calendar;
import java.util.List;

/**
 * Posted from CostDeviationDaoTest
 * <p>
 * Author dattrinh Mar 14, 2016 11:05:50 AM
 */
public class CostDeviationDaoTest {
    public static void main(String[] args) throws DaoException {
        CostDeviationDao dao = new CostDeviationDao();
        CostDeviationFilter filter = new CostDeviationFilter();
        filter.setFranchiseList("101,102");
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 0, 1);
        filter.setStartDate(cal.getTime());
        cal.set(2016, 6, 1);
        filter.setEndDate(cal.getTime());
        List<CostDeviationVo> costDeviationVos = dao.getCostDeviationByFilter(filter);
        long recordCount = dao.countCostDeviationByFilter(filter);
        CostDeviationVo summary = dao.sumCostDeviationByFilter(filter);
        System.out.println("Record count: " + recordCount);
        System.out.println("Summary record: " + summary);
        System.out.println("Result list:\n" + costDeviationVos);
    }
}
