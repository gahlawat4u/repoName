package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.admin.ProductCarrierDao;
import com.gms.xms.txndb.vo.admin.ProductCarrierVo;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ProductDaoTest {

    @Test
    public void test() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        ProductCarrierVo productCarrierVo = new ProductCarrierVo();
        ProductCarrierDao productCarrierDao = new ProductCarrierDao();
        productCarrierVo.setProductCarrierName("Name");
        productCarrierDao.insert(context, productCarrierVo);
    }
}