package com.gms.xms.weblib.controller.admin.territory;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.admin.TerritoryFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.TerritoryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.ITerritoryService;
import com.gms.xms.persistence.service.admin.TerritoryServiceImp;
import com.gms.xms.txndb.vo.admin.TerritoryVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from TerritoryListController
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class TerritoryListController extends JsonBaseController {
    private static final long serialVersionUID = -7945740360436049333L;

    private Paging<TerritoryModel> territories;
    private TerritoryModel territoryModel;
    private String isEdit;

    private List<String> listPageSize = this.buildPageSizeList();
    private String pageSize;
    private String page;

    public String show() {
        try {
            loadTerritory();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void loadTerritory() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        int pSize = 0;
        try {
            pSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int iPage = 0;
        try {
            iPage = Integer.parseInt(this.page);
        } catch (Exception ex) {
            iPage = 1;
        }

        ITerritoryService territoryService = new TerritoryServiceImp();
        TerritoryFilter filter = new TerritoryFilter();
        long recordCount = territoryService.countByFilter(filter);
        // Set paging info
        Paging<TerritoryModel> paging = new Paging<TerritoryModel>(iPage, nLinks, pSize, recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get data of page
        List<TerritoryVo> territoryVos = territoryService.selectByFilter(filter);
        List<TerritoryModel> territoryModels = ModelUtils.createListModelFromVo(territoryVos, TerritoryModel.class);
        paging.setRecords(territoryModels);
        this.setTerritories(paging);
    }

    public String add() {
        try {
            if (territoryModel != null) {
                System.out.println("Number of banks is " + territoryModel);

                if (validTerritory()) {
                    ITerritoryService territoryService = new TerritoryServiceImp();
                    TerritoryVo territoryVo = ModelUtils.createVoFromModel(territoryModel, TerritoryVo.class);
                    System.out.println("Number of banks is " + territoryVo);
                    territoryService.insert(this.getAddInfoMap(), territoryVo);
                    loadTerritory();
                    return "home";
                } else {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return "success";
                }
            } else {
                return "success";
            }

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String edit() {
        try {

            if (isEdit != null) {
                if (validTerritory()) {
                    ITerritoryService territoryService = new TerritoryServiceImp();
                    TerritoryVo territoryVo = ModelUtils.createVoFromModel(territoryModel, TerritoryVo.class);
                    System.out.println("Number of banks is " + territoryVo);
                    territoryService.update(this.getAddInfoMap(), territoryVo);
                    loadTerritory();
                    return "home";
                } else {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return "success";
                }
            } else {
                return "success";
            }

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String delete() {
        try {
            if (territoryModel.getTerritoryId() != null) {
                ITerritoryService territoryService = new TerritoryServiceImp();
                territoryService.delete(this.getAddInfoMap(), Long.parseLong(territoryModel.getTerritoryId()));
                loadTerritory();
                return "home";
            } else {
                return "success";
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    private boolean validTerritory() throws Exception {
        if (this.territoryModel == null) {
            return false;
        } else {
            if (StringUtils.isBlank(this.territoryModel.getTerritoryName())) {
                this.addFieldError("territoryModel.territoryName", "Please Enter Territory");
            } else {
                ITerritoryService territoryService = new TerritoryServiceImp();
                TerritoryVo territoryVo = ModelUtils.createVoFromModel(this.territoryModel, TerritoryVo.class);
                if (territoryService.countByName(territoryVo) > 0) {
                    this.addFieldError("territoryModel.territoryName", "Input Territory already exists.");
                    return false;
                }
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public TerritoryModel getTerritoryModel() {
        return territoryModel;
    }

    public void setTerritoryModel(TerritoryModel territoryModel) {
        this.territoryModel = territoryModel;
    }

    public Paging<TerritoryModel> getTerritories() {
        return territories;
    }

    public void setTerritories(Paging<TerritoryModel> territories) {
        this.territories = territories;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

}
