package com.gms.xms.weblib.controller;

import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from AdminJsonBaseController.java
 * <p>
 * Author dattrinh 1:59:51 PM
 */
public class AdminJsonBaseController extends JsonBaseController {

    private static final long serialVersionUID = 1L;
    private List<FranchiseInfoModel> franchises;
    private List<String> pageSizes;
    private Integer adminLevel;

    protected void prepareFranchises() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        this.setFranchises(franchiseInfoModels);
    }

    protected String buildFranchiseCodeList(String franCode) throws Exception {
        String franchiseList = "";
        if (StringUtils.isBlank(franCode) || "All".equalsIgnoreCase(franCode)) {
            if (franchises == null) {
                prepareFranchises();
            }
            for (FranchiseInfoModel franchise : franchises) {
                franchiseList += franchise.getCode() + ",";
            }
            franchiseList = franchiseList.substring(0, franchiseList.length() - 1);
        } else {
            franchiseList = franCode;
        }

        return franchiseList;
    }

    protected List<String> buildFranchiseList(String franCode) throws Exception {
        List<String> franchiseCodeList = new ArrayList<String>();
        if ("All".equalsIgnoreCase(franCode)) {
            if (franchises == null) {
                prepareFranchises();
            }
            for (FranchiseInfoModel franchise : franchises) {
                franchiseCodeList.add(franchise.getCode());
            }
        } else {
            franchiseCodeList.add(franCode);
        }

        return franchiseCodeList;
    }

    protected void determineAdminLevel() throws Exception {
        Integer levelOfAdmin = null;
        IUserService userService = new UserServiceImp();
        try {
            Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
            Integer level = userService.getUserLevel(userLevelId).intValue();
            levelOfAdmin = level;
        } catch (Exception e) {
            throw new Exception(e);
        }
        this.setAdminLevel(levelOfAdmin);
    }

    protected void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public Integer getAdminLevel() {
        return adminLevel;
    }

    private void setAdminLevel(Integer adminLevel) {
        this.adminLevel = adminLevel;
    }
}
