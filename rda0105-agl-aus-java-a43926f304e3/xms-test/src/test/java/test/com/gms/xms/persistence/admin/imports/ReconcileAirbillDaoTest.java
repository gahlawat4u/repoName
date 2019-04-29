package test.com.gms.xms.persistence.admin.imports;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.imports.reconcileairbill.ReconcileAirbillFilter;
import com.gms.xms.persistence.dao.admin.imports.ReconcileAirbillDao;

/**
 * Posted from ReconcileAirbillDaoTest
 * <p>
 * Author dattrinh Mar 15, 2016 9:42:22 AM
 */
public class ReconcileAirbillDaoTest {
    public static void main(String[] args) throws DaoException {
        ReconcileAirbillDao dao = new ReconcileAirbillDao();
        ReconcileAirbillFilter filter = new ReconcileAirbillFilter();
        filter.setFranchiseList("101,102");
        filter.setAirbillList("'INX4585229','INX4585243','INX4595785'");
        filter.setUserLevel(3);
        System.out.println(dao.countReconcileAirbillByFilter(filter));
        System.out.println(dao.sumReconcileAirbillByFilter(filter));
        System.out.println(dao.getReconcileAirbillByFilter(filter));
    }
}
