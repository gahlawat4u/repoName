package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.txndb.vo.CountryVo;
import org.junit.Test;

import java.util.List;

public class CountryDaoServiceTest {

    @Test
    public void test() throws DaoException {
        CountryDao countryDaoService = new CountryDao();
        List<CountryVo> countryVos = countryDaoService.getCountryList();
        CountryVo countryVo = countryDaoService.getCountryById(18L);

        System.out.println("Country List: " + countryVos);
        System.out.println("Country ID 18: " + countryVo);
    }
}