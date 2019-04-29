package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.MenuFilter;
import com.gms.xms.persistence.dao.admin.MenuDao;
import com.gms.xms.txndb.vo.admin.MenuVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from MenuServiceImp
 * <p>
 * Author TANDT 21-10-2015
 */
public class MenuServiceImp implements IMenuService {

    @Override
    public List<MenuVo> selectAllMenu() throws DaoException {
        MenuDao dao = new MenuDao();
        return dao.selectAllMenu();
    }

    @Override
    public List<MenuVo> selectAllMenuById(MenuFilter filter) throws DaoException {
        MenuDao dao = new MenuDao();
        return dao.selectAllMenuById(filter);
    }

    @Override
    public MenuVo selectMenuDetailById(MenuFilter filter) throws DaoException {
        MenuDao dao = new MenuDao();
        return dao.selectMenuDetailById(filter);
    }

    @Override
    public void updateMenu(Map<String, String> context, MenuVo menuVo) throws DaoException {
        MenuDao dao = new MenuDao();
        dao.update(context, menuVo);
    }

    public List<MenuVo> prepareMenu(Integer isCollector, Long userLevelId, String otherLevel, Integer parentId) throws DaoException {
        MenuDao dao = new MenuDao();
        MenuVo vo = new MenuVo();
        vo.setUserLevelId(userLevelId);
        vo.setOtherLevel(otherLevel);
        vo.setParentId(parentId);
        vo.setShowCollector(isCollector);
        List<MenuVo> result = dao.selectAvailMenuItemByParentId(vo);
        for (MenuVo vo1 : result) {
            vo = new MenuVo();
            vo.setUserLevelId(userLevelId);
            vo.setOtherLevel(otherLevel);
            vo.setParentId(vo1.getMenuId());
            vo.setShowCollector(isCollector);
            vo1.setMenuChilds(prepareMenuItems(vo));
        }
        return result;
    }

    private List<MenuVo> prepareMenuItems(MenuVo menuVo) throws DaoException {
        List<MenuVo> result = null;
        MenuDao dao = new MenuDao();
        List<MenuVo> menuVos = dao.selectAvailMenuItemByParentId(menuVo);
        if (menuVos != null) {
            for (MenuVo vo1 : menuVos) {
                MenuVo vo = new MenuVo();
                vo.setUserLevelId(menuVo.getUserLevelId());
                vo.setOtherLevel(menuVo.getOtherLevel());
                vo.setParentId(vo1.getMenuId());
                vo.setShowCollector(menuVo.getShowCollector());
                vo1.setMenuChilds(prepareMenuItems(vo));
            }
        }
        result = menuVos;
        return result;
    }

}
