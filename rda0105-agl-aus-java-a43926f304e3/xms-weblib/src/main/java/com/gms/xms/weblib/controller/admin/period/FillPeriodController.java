package com.gms.xms.weblib.controller.admin.period;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.dto.MonthlyVo;
import com.gms.xms.persistence.dao.period.MonthlyDao;
import com.gms.xms.persistence.dao.period.PeriodDao;
import com.gms.xms.weblib.controller.JsonBaseController;

/**
 * Posted from Sep 19, 2016 12:01:25 PM
 * <p>
 * Author dattrinh
 */
public class FillPeriodController extends JsonBaseController {

    private static final long serialVersionUID = 1L;

    public void fillPeriod() {
        PeriodDao periodDao = new PeriodDao();
        try {
            periodDao.fillPeriod(getAddInfoMap());
        } catch (DaoException e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    public void fillMonthly() {
        MonthlyDao dao = new MonthlyDao();
        try {
            MonthlyVo monthlyVo = dao.getCurrentMonthly();
            if (monthlyVo == null) {
                dao.fillMonthly(this.getAddInfoMap());
            }
        } catch (DaoException e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }
}
