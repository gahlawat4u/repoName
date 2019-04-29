package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.area.AreaDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AreaDaoTest {

    @Test
    public void test() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        AreaDao areaDao = new AreaDao();
        Integer id = (int) 2;
        areaDao.delete(context, id);
    }
}