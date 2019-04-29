package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from MenuModel
 * <p>
 * Author TANDT 21-10-2015
 */
public class MenuModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private String menuId;
    private String menuName;
    private String parentId;
    private String srNo;
    private String userLevelId;
    private String otherLevel;
    private String showCollector;
    private String hidden;
    private String url;
    private String localizationId;
    private List<MenuModel> menuChilds;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(String userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getOtherLevel() {
        return otherLevel;
    }

    public void setOtherLevel(String otherLevel) {
        this.otherLevel = otherLevel;
    }

    public String getShowCollector() {
        return showCollector;
    }

    public void setShowCollector(String showCollector) {
        this.showCollector = showCollector;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }

    public List<MenuModel> getMenuChilds() {
        return menuChilds;
    }

    public void setMenuChilds(List<MenuModel> menuChilds) {
        this.menuChilds = menuChilds;
    }

    @Override
    public String toString() {
        return "MenuModel [menuId=" + menuId + ", menuName=" + menuName + ", parentId=" + parentId + ", srNo=" + srNo + ", userLevelId=" + userLevelId + ", otherLevel=" + otherLevel + ", showCollector=" + showCollector + ", hidden=" + hidden + ", url=" + url + ", localizationId=" + localizationId + ", menuChilds=" + menuChilds + "]";
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
        MenuModel other = (MenuModel) obj;
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
