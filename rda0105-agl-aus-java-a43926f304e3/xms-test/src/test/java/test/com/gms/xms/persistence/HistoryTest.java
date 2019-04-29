package test.com.gms.xms.persistence;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.Date;

public class HistoryTest {

    @Test
    public void test() throws DaoException {
        try {
            Date currentTime = new Date();
            String currentTimeOutput = DateUtils.convertDateToString(currentTime, "hh:mm", SystemSettingCache.getInstance().getValueByKey("Standard Timezone"));
            System.out.println(currentTimeOutput);

            System.out.println(StringUtils.right("10000001", 5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}