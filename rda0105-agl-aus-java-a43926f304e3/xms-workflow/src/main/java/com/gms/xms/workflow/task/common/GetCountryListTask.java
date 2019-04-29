package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetCountryListTask
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class GetCountryListTask implements Task {
    private static final Log log = LogFactory.getLog(GetCountryListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CountryDao countryDao = new CountryDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            List<CountryVo> countryList = countryDao.getCountryList();
            context.put(Attributes.COUNTRY_LIST_RESULT, GsonUtils.toGson(countryList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}
