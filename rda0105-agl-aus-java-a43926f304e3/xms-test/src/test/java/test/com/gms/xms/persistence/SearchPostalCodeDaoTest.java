package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.webship.SearchPostalCodeDao;
import com.gms.xms.txndb.vo.webship.ship.SearchPostalCodeFilter;
import org.junit.Test;

/**
 * Posted from SearchPostalCodeDaoTest
 * <p>
 * Author DatTV Aug 25, 2015
 */
public class SearchPostalCodeDaoTest {

    @Test
    public void searchPostalCodeTest() throws DaoException {
        SearchPostalCodeDao postalCodeDao = new SearchPostalCodeDao();
        SearchPostalCodeFilter filter = new SearchPostalCodeFilter();
        filter.setCityName("BA");
        filter.setCountryId(16L);
        System.out.println(postalCodeDao.searchCityByNameOrPostalCode(filter));
    }
}