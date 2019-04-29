package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.franchisepayable.FranchisePayableTaskDaoService;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import org.junit.Test;

import java.util.*;

public class FranchisePayableTaskDaoTest {

    @Test
    public void getListOfBanksTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        FranchisePayableTaskDaoService daoService = new FranchisePayableTaskDaoService();
        FranchisePayableFilter filter = new FranchisePayableFilter();
        String[] list = {"100", "101", "102", "103", "104", "105", "106", "108"};
        Calendar cal = Calendar.getInstance();
        filter.setFranchiseCodeList(Arrays.asList(list));
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, 12);
        filter.setStartDate(cal.getTime());
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, 18);
        filter.setEndDate(cal.getTime());
        filter.setRptTxnId(UUID.randomUUID().toString());
        try {
            daoService.prepareDataForReportFromLive(context, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}