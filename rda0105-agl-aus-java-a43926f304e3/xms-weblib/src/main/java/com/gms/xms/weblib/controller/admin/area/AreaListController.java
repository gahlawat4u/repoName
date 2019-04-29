package com.gms.xms.weblib.controller.admin.area;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.admin.AreaFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.area.AreaModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.area.AreaServiceImp;
import com.gms.xms.persistence.service.area.IAreaService;
import com.gms.xms.txndb.vo.area.AreaVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from AreaListController
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class AreaListController extends JsonBaseController {
    private static final long serialVersionUID = -7945740360436049333L;

    private AreaFilter areaFilter;
    private Paging<AreaModel> areaList;
    private AreaModel areaModel;
    private String isEdit;
    private String areaId;
    private String pageSize;
    private String page;
    private List<String> listPageSize = this.buildPageSizeList();

    public String show() {
        try {
            loadArea();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void loadArea() throws Exception {
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

        IAreaService AreaService = new AreaServiceImp();
        AreaFilter filter = new AreaFilter();
        long recordCount = AreaService.countByFilter(filter);
        // Set paging info
        Paging<AreaModel> paging = new Paging<AreaModel>(iPage, nLinks, pSize, recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get data of page
        List<AreaVo> AreaVos = AreaService.selectByFilter(filter);
        List<AreaModel> areaModels = ModelUtils.createListModelFromVo(AreaVos, AreaModel.class);
        paging.setRecords(areaModels);
        this.setAreaList(paging);
    }

    public String add() {
        try {
            if (areaModel != null) {
                System.out.println("Number of banks is " + areaModel);

                if (validArea()) {
                    IAreaService AreaService = new AreaServiceImp();
                    AreaVo AreaVo = ModelUtils.createVoFromModel(areaModel, AreaVo.class);
                    System.out.println("Number of banks is " + AreaVo);
                    AreaService.insert(this.getAddInfoMap(), AreaVo);
                    loadArea();
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
                if (validArea()) {
                    IAreaService AreaService = new AreaServiceImp();
                    AreaVo AreaVo = ModelUtils.createVoFromModel(areaModel, AreaVo.class);
                    AreaService.update(this.getAddInfoMap(), AreaVo);
                    loadArea();
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
            if (areaModel.getAreaId() != null) {
                IAreaService areaService = new AreaServiceImp();
                areaService.delete(this.getAddInfoMap(), Integer.parseInt(areaModel.getAreaId()));
                loadArea();
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

    private boolean validArea() throws Exception {
        if (areaModel == null) {
            return false;
        }
        if (StringUtils.isBlank(areaModel.getAreaName())) {
            addFieldError("areaModel.areaName", "Please Enter Area Name");
        } else {
            IAreaService service = new AreaServiceImp();
            AreaVo areaVo = ModelUtils.createVoFromModel(this.areaModel, AreaVo.class);
            if (service.countByName(areaVo) > 0) {
                this.addFieldError("areaModel.areaName", "Input Area Name already exists.");
                return false;
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public AreaFilter getAreaFilter() {
        return areaFilter;
    }

    public void setAreaFilter(AreaFilter areaFilter) {
        this.areaFilter = areaFilter;
    }

    public Paging<AreaModel> getAreaList() {
        return areaList;
    }

    public void setAreaList(Paging<AreaModel> areaList) {
        this.areaList = areaList;
    }

    public AreaModel getAreaModel() {
        return areaModel;
    }

    public void setAreaModel(AreaModel areaModel) {
        this.areaModel = areaModel;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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
