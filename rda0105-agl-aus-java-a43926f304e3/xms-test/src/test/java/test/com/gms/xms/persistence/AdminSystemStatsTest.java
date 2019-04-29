package test.com.gms.xms.persistence;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.admin.SystemStatsListFilter;
import com.gms.xms.persistence.dao.admin.WebshipLogDao;
import org.junit.Test;

public class AdminSystemStatsTest {

    @Test
    public void test() throws DaoException {
        SystemStatsListFilter filter = new SystemStatsListFilter();
        filter.setAirbillNumber("12");
        filter.setToDate(DateUtils.convertStringToDate("06-10-2015".concat(" 00:00:00"), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null));
        filter.setFromDate(DateUtils.convertStringToDate("07-06-2015".concat(" 00:00:00"), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null));
        WebshipLogDao dao = new WebshipLogDao();

        System.out.println(dao.selectWebshipLogCountByFilter(filter));
    }

}