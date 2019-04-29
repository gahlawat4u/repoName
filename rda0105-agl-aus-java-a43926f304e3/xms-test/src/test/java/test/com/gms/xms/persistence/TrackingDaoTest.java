package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.TrackingDao;
import com.gms.xms.txndb.vo.DeliveredTrackingListFilter;
import com.gms.xms.txndb.vo.TrackingVo;
import org.junit.Test;

import java.util.List;

public class TrackingDaoTest {

    @Test
    public void test() throws DaoException {
        TrackingDao trackingDao = new TrackingDao();
        DeliveredTrackingListFilter filter = new DeliveredTrackingListFilter();
        filter.setIsAll("all");
        filter.setShipmentId(183532L);
        List<TrackingVo> trackingVos = trackingDao.getTrackingBySm(filter);
        System.out.println(trackingVos);
    }
}     