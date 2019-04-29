package com.gms.xms.weblib.controller.admin.menu;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.admin.MenuFilter;
import com.gms.xms.model.UserLevelModel;
import com.gms.xms.model.admin.MenuModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.MenuDao;
import com.gms.xms.persistence.service.admin.AdministrationServiceImp;
import com.gms.xms.persistence.service.admin.IAdministrationService;
import com.gms.xms.persistence.service.admin.IMenuService;
import com.gms.xms.persistence.service.admin.MenuServiceImp;
import com.gms.xms.txndb.vo.admin.MenuVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from MenuController
 * <p>
 * Author TANDT 21-10-2015
 */
public class MenuController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    private MenuModel menuModel;
    private String menuId;
    private List<UserLevelModel> levelModels;
    private String isEditMenu;
    private List<MenuModel> menus;

    public String show() {
        try {
            prepareMenus();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String showUserLevel() {
        try {
            IAdministrationService service = new AdministrationServiceImp();
            levelModels = ModelUtils.createListModelFromVo(service.selectForMenuEditor(), UserLevelModel.class);
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String showDetail() {
        try {
            if (isEditMenu != null) {
                if (validMenu()) {
                    IMenuService service = new MenuServiceImp();
                    service.updateMenu(this.getAddInfoMap(), ModelUtils.createVoFromModel(menuModel, MenuVo.class));
                } else {
                    return "success";
                }
                prepareMenus();
                return "home";
            } else {
                prepareMenuDetail();
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String updateLocalize() {
        try {
            return "success";
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    private boolean validMenu() throws Exception {
        if (menuModel.getHidden().equals("false")) {
            menuModel.setHidden("0");
        } else if (menuModel.getHidden().equals("true")) {
            menuModel.setHidden("1");
        }
        if (StringUtils.isEmpty(menuModel.getMenuName().trim())) {
            setErrorMessage("Name is empty.");
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void prepareMenuDetail() throws Exception {
        if (menuId != null) {
            IMenuService service = new MenuServiceImp();
            MenuFilter filter = new MenuFilter();
            filter.setMenuId(Integer.parseInt(menuId));
            menuModel = ModelUtils.createModelFromVo(service.selectMenuDetailById(filter), MenuModel.class);
        } else {
            setErrorMessage("Please choice a menu.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Please choice a menu.");
        }
    }

    private void prepareMenus() throws Exception {
        List<MenuVo> menuVos = buildMenu(0);
        List<MenuModel> menuModels = ModelUtils.createListModelFromVo(menuVos, MenuModel.class);
        this.setMenus(menuModels);
    }

    protected List<MenuVo> buildMenu(int parentId) throws Exception {
        MenuDao menuDao = new MenuDao();
        List<MenuVo> menuVos = menuDao.getMenusByParentId(parentId);
        for (MenuVo menuVo : menuVos) {
            if (menuVo.getParentId() != null) {
                menuVo.setMenuChilds(buildMenu(menuVo.getMenuId()));
            }
        }
        return menuVos;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<UserLevelModel> getLevelModels() {
        return levelModels;
    }

    public void setLevelModels(List<UserLevelModel> levelModels) {
        this.levelModels = levelModels;
    }

    public String getIsEditMenu() {
        return isEditMenu;
    }

    public void setIsEditMenu(String isEditMenu) {
        this.isEditMenu = isEditMenu;
    }

    public List<MenuModel> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuModel> menus) {
        this.menus = menus;
    }
}