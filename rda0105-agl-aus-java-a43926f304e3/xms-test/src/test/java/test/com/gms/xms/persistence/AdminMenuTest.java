package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.MenuFilter;
import com.gms.xms.model.admin.MenuModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.IMenuService;
import com.gms.xms.persistence.service.admin.MenuServiceImp;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AdminMenuTest {

    @Test
    public void test() throws DaoException {

        try {
            MenuModel menuModel = new MenuModel();
            menuModel.setMenuId("0");
            menuModel.setMenuName("Root");
            menuModel = orderMenu(menuModel, "0");
            System.out.println(menuModel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private MenuModel orderMenu(MenuModel menuModelN, String parentId) throws Exception {
        MenuFilter filter = new MenuFilter();
        filter.setMenuId(Integer.parseInt(parentId));
        IMenuService service = new MenuServiceImp();
        List<MenuModel> listMenuChild = new ArrayList<MenuModel>();
        listMenuChild = ModelUtils.createListModelFromVo(service.selectAllMenuById(filter), MenuModel.class);
        if (listMenuChild.size() > 0) {
            for (MenuModel model : listMenuChild) {
                this.orderMenu(model, model.getMenuId());
            }
            menuModelN.setMenuChilds(listMenuChild);
        }
        return menuModelN;
    }
}