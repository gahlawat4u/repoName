package test.com.gms.xms.persistence.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AirbillLabelFilter;
import com.gms.xms.persistence.dao.admin.AirbillLabelDao;

/**
 * Posted from Aug 27, 2016 6:35:29 PM
 * <p>
 * Author dattrinh
 */
public class AirbillLabelDaoTest {
    public static void main(String[] args) throws DaoException {
        AirbillLabelDao dao = new AirbillLabelDao();
        AirbillLabelFilter filter = new AirbillLabelFilter();
        filter.setPeriodType(3);
        filter.setSearchDate("01/07/2016");
        filter.setFranchiseList("100");
        System.out.println(dao.getAirbillLabels(filter));
    }
}
