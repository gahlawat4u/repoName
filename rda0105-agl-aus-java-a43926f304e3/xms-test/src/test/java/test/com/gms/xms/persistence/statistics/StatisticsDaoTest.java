package test.com.gms.xms.persistence.statistics;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.dto.statistics.StatSalesGraphTotalVo;
import com.gms.xms.filter.statistics.StatisticsFilter;
import com.gms.xms.persistence.dao.ProspectDao;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.statistics.StatisticsDao;
import com.gms.xms.txndb.vo.UserVo;

import java.util.List;

/**
 * Posted from Aug 17, 2016 11:55:11 AM
 * <p>
 * Author dattrinh
 */
public class StatisticsDaoTest {
    public static void main(String[] args) {
        StatisticsDao dao = new StatisticsDao();
        try {
            UserDao userDao = new UserDao();
            UserVo userVo = new UserVo();
            userVo = userDao.getUserById("30");
            ProspectDao prospectDao = new ProspectDao();
            List<String> prospects = prospectDao.getProspectByUser(userVo);
            String prospectList = AppUtils.array2String(prospects);
            StatisticsFilter filter = new StatisticsFilter();
            filter.setUserId(26L);
            filter.setUserLevel(6);
            filter.setProspectList(prospectList);
            List<StatSalesGraphTotalVo> graphTotalVos = dao.getSalesGraphTotal(filter);
            System.out.println(dao.getSalesGraph(filter));
            System.out.println(graphTotalVos);
            int[] revPct = new int[]{0, 5, 15, 20, 30, 75, 100};
            double[] rev = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            for (StatSalesGraphTotalVo graph : graphTotalVos) {
                rev[graph.getSaleState()] = graph.getRevenue();
            }
            double amount = 0.0;
            for (int i = 0; i <= 6; i++) {
                amount += (rev[i] * revPct[i]) / 100;
            }
            System.out.println(amount);
            filter.setStatus(0);
            System.out.println(dao.getSalesRepByTeleMarketer(filter));
            filter.setMonth(4);
            System.out.println(dao.getSalesRepBySalePerson(filter));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
