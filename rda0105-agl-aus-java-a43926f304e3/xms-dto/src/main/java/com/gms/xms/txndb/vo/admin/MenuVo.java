package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.List;

/**
 * Posted from MenuVo
 * <p>
 * Author TANDT 21-10-2015
 */
public class MenuVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private Integer srNo;
    private Long userLevelId;
    private String otherLevel;
    private Integer showCollector;
    private Integer hidden;
    private String url;
    private Long localizationId;
    private List<MenuVo> menuChilds;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public Long getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Long userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getOtherLevel() {
        return otherLevel;
    }

    public void setOtherLevel(String otherLevel) {
        this.otherLevel = otherLevel;
    }

    public Integer getShowCollector() {
        return showCollector;
    }

    public void setShowCollector(Integer showCollector) {
        this.showCollector = showCollector;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }

    public List<MenuVo> getMenuChilds() {
        return menuChilds;
    }

    public void setMenuChilds(List<MenuVo> menuChilds) {
        this.menuChilds = menuChilds;
    }

    @Override
    public String toString() {
        return "MenuVo [menuId=" + menuId + ", menuName=" + menuName + ", parentId=" + parentId + ", srNo=" + srNo + ", userLevelId=" + userLevelId + ", otherLevel=" + otherLevel + ", showCollector=" + showCollector + ", hidden=" + hidden + ", url=" + url + ", localizationId=" + localizationId + ", menuChilds=" + menuChilds + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hidden == null) ? 0 : hidden.hashCode());
        result = prime * result + ((localizationId == null) ? 0 : localizationId.hashCode());
        result = prime * result + ((menuChilds == null) ? 0 : menuChilds.hashCode());
        result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
        result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
        result = prime * result + ((otherLevel == null) ? 0 : otherLevel.hashCode());
        result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
        result = prime * result + ((showCollector == null) ? 0 : showCollector.hashCode());
        result = prime * result + ((srNo == null) ? 0 : srNo.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((userLevelId == null) ? 0 : userLevelId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MenuVo other = (MenuVo) obj;
        if (hidden == null) {
            if (other.hidden != null)
                return false;
        } else if (!hidden.equals(other.hidden))
            return false;
        if (localizationId == null) {
            if (other.localizationId != null)
                return false;
        } else if (!localizationId.equals(other.localizationId))
            return false;
        if (menuChilds == null) {
            if (other.menuChilds != null)
                return false;
        } else if (!menuChilds.equals(other.menuChilds))
            return false;
        if (menuId == null) {
            if (other.menuId != null)
                return false;
        } else if (!menuId.equals(other.menuId))
            return false;
        if (menuName == null) {
            if (other.menuName != null)
                return false;
        } else if (!menuName.equals(other.menuName))
            return false;
        if (otherLevel == null) {
            if (other.otherLevel != null)
                return false;
        } else if (!otherLevel.equals(other.otherLevel))
            return false;
        if (parentId == null) {
            if (other.parentId != null)
                return false;
        } else if (!parentId.equals(other.parentId))
            return false;
        if (showCollector == null) {
            if (other.showCollector != null)
                return false;
        } else if (!showCollector.equals(other.showCollector))
            return false;
        if (srNo == null) {
            if (other.srNo != null)
                return false;
        } else if (!srNo.equals(other.srNo))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        if (userLevelId == null) {
            if (other.userLevelId != null)
                return false;
        } else if (!userLevelId.equals(other.userLevelId))
            return false;
        return true;
    }
}
