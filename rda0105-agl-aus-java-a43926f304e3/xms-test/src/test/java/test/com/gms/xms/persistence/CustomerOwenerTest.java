package test.com.gms.xms.persistence;

import com.gms.xms.persistence.service.webshipgroup.IWebshipGroupService;
import com.gms.xms.persistence.service.webshipgroup.WebshipGroupServiceImp;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CustomerOwenerTest {

    @Test
    public void searchCustomerTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        IWebshipGroupService service = new WebshipGroupServiceImp();
        WebshipGroupVo webshipGroupVo = new WebshipGroupVo();
        Long id = Long.parseLong("10000005");
        webshipGroupVo.setWebshipGroupName("test");
        webshipGroupVo.setOwnerCustomerId(id);
        service.insert(context, webshipGroupVo);
    }

}
