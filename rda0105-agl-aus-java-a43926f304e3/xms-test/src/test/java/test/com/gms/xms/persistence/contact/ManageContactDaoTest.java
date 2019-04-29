package test.com.gms.xms.persistence.contact;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.contact.ManageContactFilter;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.contact.ManageContactDao;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.account.contact.ManageContactVo;

import java.util.List;

public class ManageContactDaoTest {

    public static void main(String[] args) throws DaoException {
        ManageContactDao dao = new ManageContactDao();
        ManageContactFilter filter = new ManageContactFilter();
        filter.setProspectList("1,2,3,4,5,6,7,8,9,10,2746");
        filter.setCompanyName("PTY");
        filter.setContactName("a");
        filter.setAddress1("o");
        filter.setPhone("0");
        filter.setPostalCode("6");
        filter.setSaleStage("0");
        List<ManageContactVo> manageContactVos = dao.getManageContactByFilter(filter);
        long recordCount = dao.countManageContactByFilter(filter);
        String userId = "1";
        UserDao userDao = new UserDao();
        UserVo userVo = userDao.getUserById(userId);
        List<Long> prospectIdList = dao.getProspectByLogin(userVo);
        String result = "";
        for (Long prospect : prospectIdList) {
            result += String.valueOf(prospect) + ",";
        }
        result = result.substring(0, result.length() - 1);
        System.out.println("Record count: " + recordCount);
        System.out.println("Result list:\n" + manageContactVos);
        System.out.println("Result prospectIdList:\n" + result);
    }
}
