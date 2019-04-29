package com.gms.xms.filter.admin;

import com.gms.xms.filter.BaseFilter;
import com.gms.xms.txndb.vo.UserLevelVo;

import java.util.Date;
import java.util.List;

/**
 * @author TanDt
 */
public class AdministrationFilter extends BaseFilter {
    private Integer permissionId;
    private Integer userLevelId;
    private List<UserLevelVo> userLevelVos;
    private Long countryId;
    // Filter props for country list.
    private String countryCode;
    private String countryName;
    private String displayName;
    private String otherName;
    private Double gst;
    private Date modifiedDate;
    private Integer isShow;
    private String apCode;
    private String apZone;
    // Filter props for state list.
    private Long id;
    private String stateName;
    private String stateCode;
    private String cityName;
    private String cityCode;
    private Long postalCodeFrom;
    private Long postalCodeTo;
    private String dhlZoneCode;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public List<UserLevelVo> getUserLevelVos() {
        return userLevelVos;
    }

    public void setUserLevelVos(List<UserLevelVo> userLevelVos) {
        this.userLevelVos = userLevelVos;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getApCode() {
        return apCode;
    }

    public void setApCode(String apCode) {
        this.apCode = apCode;
    }

    public String getApZone() {
        return apZone;
    }

    public void setApZone(String apZone) {
        this.apZone = apZone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Long getPostalCodeFrom() {
        return postalCodeFrom;
    }

    public void setPostalCodeFrom(Long postalCodeFrom) {
        this.postalCodeFrom = postalCodeFrom;
    }

    public Long getPostalCodeTo() {
        return postalCodeTo;
    }

    public void setPostalCodeTo(Long postalCodeTo) {
        this.postalCodeTo = postalCodeTo;
    }

    public String getDhlZoneCode() {
        return dhlZoneCode;
    }

    public void setDhlZoneCode(String dhlZoneCode) {
        this.dhlZoneCode = dhlZoneCode;
    }
}
