package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.adjustment.AdjustmentRequestFilterVo;
import org.junit.Test;

public class AirbillAdjustmentDaoTest {

    private AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();

    @Test
    public void selectTotalPayableOfAdjustTypeTest() throws DaoException {
        AdjustmentRequestFilterVo filter = new AdjustmentRequestFilterVo();
        filter.setAirbillNumber("IXX500049622");
        filter.setShipmentId(200446L);
        filter.setAdjustType(3);
        filter.setAdjustmentType("Lost Shipment");
        AirbillAdjustmentVo adjustmentVo = adjustmentDao.selectTotalPayableOfAdjustType(filter);
        System.out.println(adjustmentVo);
    }

    @Test
    public void selectTotalAdjustAmountOfAWBTest() throws DaoException {
        AdjustmentRequestFilterVo filter = new AdjustmentRequestFilterVo();
        filter.setAirbillNumber("IXX500049622");
        filter.setShipmentId(200446L);
        AirbillAdjustmentVo adjustmentVo = adjustmentDao.selectTotalAdjustAmountOfAWB(filter);
        System.out.println(adjustmentVo);
    }

    @Test
    public void selectTotalPayableOfAWBTest() throws DaoException {
        AdjustmentRequestFilterVo filter = new AdjustmentRequestFilterVo();
        filter.setAirbillNumber("IXX500049622");
        filter.setShipmentId(200446L);
        AirbillAdjustmentVo adjustmentVo = adjustmentDao.selectTotalPayableOfAWB(filter);
        System.out.println(adjustmentVo);
    }
}