package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.PackageDao;
import com.gms.xms.txndb.vo.webship.PackageVo;
import org.junit.Test;

import java.util.List;

public class PackageDaoServiceTest {

    @Test
    public void test() throws DaoException {
        PackageDao packageDaoService = new PackageDao();
        List<PackageVo> packageVos = packageDaoService.getPackageList();
        PackageVo packageVoById = packageDaoService.getPackageById(1);
        List<PackageVo> packageVosByShipmentId = packageDaoService.getPackageListByShipmentTypeId(18);

        System.out.println(packageVos);
        System.out.println(packageVoById);
        System.out.println(packageVosByShipmentId);
    }
}
