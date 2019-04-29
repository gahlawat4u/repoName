package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.webship.ship.UsedRateWebshipDao;
import com.gms.xms.txndb.vo.webship.ship.UsedRateWebshipFilter;
import com.gms.xms.txndb.vo.webship.ship.UsedRateWebshipVo;
import org.junit.Test;

public class UsedRateWebshipTest {

    @Test
    public void test() throws DaoException {
        UsedRateWebshipVo usedRateWebshipVo = new UsedRateWebshipVo();
        UsedRateWebshipDao dao = new UsedRateWebshipDao();
        UsedRateWebshipFilter filter = new UsedRateWebshipFilter();

        filter.setBound(0);
        filter.setContent(0);
        filter.setCustomerCode(10000001L);
        filter.setShipType(216);
        filter.setWeight(1D);
        filter.setZone("SEQLD-SYD1");
        usedRateWebshipVo = dao.selectUsedRateWebship(filter);

        System.out.println(usedRateWebshipVo);
    }
}