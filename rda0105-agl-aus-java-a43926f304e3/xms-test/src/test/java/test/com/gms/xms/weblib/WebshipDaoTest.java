package test.com.gms.xms.weblib;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailFilter;
import com.gms.xms.txndb.vo.webship.login.WebshipDetailVo;
import org.junit.Test;

import java.util.List;

public class WebshipDaoTest {

    @Test
    public void selectChildsTest() throws DaoException {
        WebshipDao webshipDao = new WebshipDao();
        WebshipDetailFilter filter = new WebshipDetailFilter();
        filter.setCustomerCode("10000001");
        filter.setName("test");
        List<WebshipDetailVo> childs = webshipDao.selectChildrenByFilter(filter);
        System.out.println(childs);
    }
}