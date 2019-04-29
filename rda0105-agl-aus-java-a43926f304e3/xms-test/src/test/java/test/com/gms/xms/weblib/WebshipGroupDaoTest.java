package test.com.gms.xms.weblib;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.WebshipGroupDao;
import org.junit.Test;

/**
 * Posted from WebshipGroupDaoTest
 * <p>
 * Author DatTV Sep 3, 2015
 */
public class WebshipGroupDaoTest {

    @Test
    public void selectAllTest() throws DaoException {
        WebshipGroupDao webshipGroupDao = new WebshipGroupDao();
        System.out.println(webshipGroupDao.selectAll());
    }
}