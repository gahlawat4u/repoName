package test.com.gms.xms.persistence;

import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.PackageModel;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import org.junit.Test;

import java.util.List;

public class AddressDaoTest {

    @Test
    public void test() throws Exception {
        AddressVo addressVo = new AddressVo();
        addressVo.setAddressId(5713);
        addressVo.setPhone("phone");
        addressVo.setContactName("contactName");
        addressVo.setEmail("Email");
        addressVo.setCity("City");
        addressVo.setPostalCode("121313");
        addressVo.setState("aaaaa");
        IPackageService service = new PackageServiceImp();
        List<PackageVo> packageVos = service.getPackageListByShipmentTypeId(1);
        List<PackageModel> packageList = ModelUtils.createListModelFromVo(packageVos, PackageModel.class);
        System.out.print(packageList);

    }

}