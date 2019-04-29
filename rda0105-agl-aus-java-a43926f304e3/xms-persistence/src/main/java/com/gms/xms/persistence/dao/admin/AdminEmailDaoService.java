package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from AdminEmailDaoService
 * <p>
 * Author TANDT Date Aug 13, 2015 11:11:11 AM
 */
public class AdminEmailDaoService {

    private static final Log log = LogFactory.getLog(AdminEmailDaoService.class);

    // Prepare data for customer aging report.
    public void deleteAdminEmail(Map<String, String> context, Integer id) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            AdminEmailDao adminEmailDao = new AdminEmailDao();
            AdminEmailSettingDao adminEmailSettingDao = new AdminEmailSettingDao();
            adminEmailDao.delete(context, id);
            adminEmailSettingDao.delete(context, id);
            sessionClient.endTransaction();
        } catch (DaoException ex) {
            log.error(ex);
            sessionClient.rollback();
            throw ex;
        }
    }
}
