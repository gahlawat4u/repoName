package test.com.gms.xms.persistence;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.txndb.vo.ServiceVo;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class ServiceDaoServiceTest {

    @Test
    public void test() throws DaoException {
        ServiceDao serviceDaoService = new ServiceDao();
        HashMap<String, Object> map = new HashMap<>();
        map.put("webshipId", 3027L);
        map.put("carrierType", 0);
        List<ServiceVo> serviceVos = serviceDaoService.getServiceListByWebshipId(map);

        List<ServiceVo> serviceVos2 = serviceDaoService.selectAll();

        System.out.println(serviceVos);
        System.out.println(serviceVos2);
    }

    @Test
    public void getActiveServicesTest() throws DaoException {
        ServiceDao serviceDaoService = new ServiceDao();
        String activeCarrierList = AppConstants.APP_SETTINGS.getActiveCarriers();
        List<ServiceVo> serviceVos = serviceDaoService.getActiveServices(activeCarrierList);
        System.out.println(serviceVos);
    }
}