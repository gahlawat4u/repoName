package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.SessionInfoDao;

import java.util.HashMap;
import java.util.Map;

public class TestSessionInfo {
    public static void main(String[] args) throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        SessionInfoDao daoService = new SessionInfoDao(sessionClient);
        System.out.println(daoService.insert(context));
        sessionClient.rollback();
        sessionClient.endTransaction();
        try {
            System.out.println(daoService.getBySessionId("1q3ob7eav5choh0m7qvn8lnk40"));
        } catch (DaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
