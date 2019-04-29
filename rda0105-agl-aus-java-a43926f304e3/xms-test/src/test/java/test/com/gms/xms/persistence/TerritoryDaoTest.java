package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.admin.TerritoryDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TerritoryDaoTest {

    @Test
    public void test() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        TerritoryDao territorDao = new TerritoryDao();
        Long id = 2L;
        territorDao.delete(context, id);
    }
}