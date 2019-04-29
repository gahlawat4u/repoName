package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.MenuFilter;
import com.gms.xms.txndb.vo.admin.MenuVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IMenuService
 * <p>
 * Author TANDT 21-10-2015
 */
public interface IMenuService {
    public List<MenuVo> selectAllMenu() throws DaoException;

    public List<MenuVo> selectAllMenuById(MenuFilter filter) throws DaoException;

    public MenuVo selectMenuDetailById(MenuFilter filter) throws DaoException;

    public void updateMenu(Map<String, String> context, MenuVo menuVo) throws DaoException;

    public List<MenuVo> prepareMenu(Integer isCollector, Long userLevelId, String otherLevel, Integer parentId) throws DaoException;

}
