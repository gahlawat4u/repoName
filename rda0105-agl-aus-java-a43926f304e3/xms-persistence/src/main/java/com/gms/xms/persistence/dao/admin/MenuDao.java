package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.MenuFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.MenuVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from MenuDao
 * <p>
 * Author TANDT 21-10-2015
 */
public class MenuDao extends BaseDao<MenuVo> {
    public List<MenuVo> selectAllMenu() throws DaoException {
        return this.selectList(null, "Menu.selectAllMenu");
    }

    public List<MenuVo> selectAllMenuById(MenuFilter filter) throws DaoException {
        return this.selectList(filter, "Menu.selectAllMenuById");
    }

    public MenuVo selectMenuDetailById(MenuFilter filter) throws DaoException {
        return this.select(filter, "Menu.selectMenuDetailById");
    }

    public List<MenuVo> selectAvailMenuItemByParentId(MenuVo vo) throws DaoException {
        return this.selectList(vo, "Menu.selectAvailMenuItemByParentId");
    }

    public void update(Map<String, String> context, MenuVo menuVo) throws DaoException {
        this.update(context, menuVo, "Menu.update");
    }

    public List<MenuVo> getMenusByParentId(int parentId) throws DaoException {
        return selectList(parentId, "Menu.getMenusByParentId");
    }
}
