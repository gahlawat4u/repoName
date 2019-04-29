package test.com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.service.admin.MenuServiceImp;

public class MenuDaoTest {
    public static void main(String[] args) throws DaoException {
        MenuServiceImp imp = new MenuServiceImp();
        System.out.println(imp.prepareMenu(1, 3L, "3.0", 0));
    }
}
