package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.OverpaymentDao;
import com.gms.xms.persistence.dao.OverpaymentInfoDao;
import com.gms.xms.txndb.vo.OverpaymentVo;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoVo;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OverpaymentInfoDaoTest {

    @Test
    public void selectByFilterTest() throws DaoException {
        OverpaymentInfoDao overpaymentInfoDao = new OverpaymentInfoDao();
        OverpaymentInfoFilter filter = new OverpaymentInfoFilter();
        List<String> franchiseList = Arrays.asList(new String[]{"100", "101", "102"});
        filter.setFranchiseList(franchiseList);
        filter.setStartRecord(0);
        filter.setRecordSize(10);
        List<OverpaymentInfoVo> list = overpaymentInfoDao.selectByFilter(filter);
        for (OverpaymentInfoVo overpayment : list) {
            System.out.println(overpayment);
        }
    }

    @Test
    public void countByFilterTest() throws DaoException {
        OverpaymentInfoDao overpaymentInfoDao = new OverpaymentInfoDao();
        OverpaymentInfoFilter filter = new OverpaymentInfoFilter();
        List<String> franchiseList = Arrays.asList(new String[]{"100", "101", "102"});
        filter.setFranchiseList(franchiseList);
        filter.setStartRecord(0);
        filter.setRecordSize(10);
        long count = overpaymentInfoDao.countByFilter(filter);
        System.out.println("Count: " + count);
    }

    @Test
    public void sumByFilterTest() throws DaoException {
        OverpaymentInfoDao overpaymentInfoDao = new OverpaymentInfoDao();
        OverpaymentInfoFilter filter = new OverpaymentInfoFilter();
        List<String> franchiseList = Arrays.asList(new String[]{"100", "101", "102"});
        filter.setFranchiseList(franchiseList);
        filter.setStartRecord(0);
        filter.setRecordSize(10);
        BigDecimal total = overpaymentInfoDao.sumByFilter(filter);
        System.out.println("Sum: " + total);
    }

    @Test
    public void selectByCustCode() throws DaoException {
        OverpaymentDao overpaymentDao = new OverpaymentDao();
        List<OverpaymentVo> list = overpaymentDao.selectByCustomerCode(10100510L);
        for (OverpaymentVo overpaymentVo : list) {
            System.out.println(overpaymentVo);
        }
    }
}