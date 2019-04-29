package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.junit.Test;

import java.util.List;

public class ShipmentTypeDaoServiceTest {

    @Test
    public void test() throws DaoException {
        ShipmentTypeDao shipmentTypeDaoService = new ShipmentTypeDao();

        List<ShipmentTypeVo> shipmentTypeVos = shipmentTypeDaoService.selectAll();

        System.out.println(GsonUtils.toGson(shipmentTypeVos));
    }
}