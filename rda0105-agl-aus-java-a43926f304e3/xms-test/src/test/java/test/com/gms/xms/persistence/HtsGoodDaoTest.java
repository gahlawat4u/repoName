package test.com.gms.xms.persistence;

import com.gms.xms.model.HtsGoodModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.htsgood.HtsGoodServiceImp;
import com.gms.xms.persistence.service.htsgood.IHtsGoodService;
import com.gms.xms.txndb.vo.HtsGoodVo;
import org.junit.Test;

import java.util.List;

/**
 * @author TANDT
 */
public class HtsGoodDaoTest {

    @Test
    public void test() throws Exception {
        IHtsGoodService service = new HtsGoodServiceImp();
        String codeBinded = "12";
        List<HtsGoodVo> htsGoodVos = service.selectHtsGoodByIdOrCode(codeBinded);
        List<HtsGoodModel> htsGoodModels = ModelUtils.createListModelFromVo(htsGoodVos, HtsGoodModel.class);
        System.out.print(htsGoodModels);

    }

}