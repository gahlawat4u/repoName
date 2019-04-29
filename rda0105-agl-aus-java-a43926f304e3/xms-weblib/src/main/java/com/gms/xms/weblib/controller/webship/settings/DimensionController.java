package com.gms.xms.weblib.controller.webship.settings;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.DimensionModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.dimension.DimensionServiceImp;
import com.gms.xms.persistence.service.dimension.IDimensionService;
import com.gms.xms.txndb.vo.webship.DimensionFilter;
import com.gms.xms.txndb.vo.webship.DimensionVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * Posted from DimensionController
 * <p>
 * Author DatTV Date Jul 14, 2015 11:19:48 AM
 */
public class DimensionController extends JsonBaseController {

    private static final long serialVersionUID = -1772865810721634077L;

    private DimensionModel dimension;
    private Paging<DimensionModel> dimensionList;
    private String id;
    private String page;
    private String pageSize;
    private List<String> pageSizeList;

    public String show() {
        // Check login
        WebshipLoginVo loginVo = this.getWebshipLoginInfo();
        if (loginVo == null) {
            setErrorMessage("You don't have permission to do this action");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return "success";
        }

        // Prepare page size list
        pageSizeList = this.buildPageSizeList();

        try {
            loadDimensionList();
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }

        return "success";
    }

    public void delete() {
        // Check selected dimension id for deleting
        if (StringUtils.isBlank(id)) {
            setErrorMessage("You must choose a dimension to delete");
            setErrorCode(ErrorCode.ACTION_ERROR);
        } else {
            try {
                IDimensionService dimensionService = new DimensionServiceImp();
                dimensionService.deleteById(this.getAddInfoMap(), Integer.valueOf(id));
            } catch (Exception e) {
                setErrorMessage(e.getMessage());
                setErrorCode(ErrorCode.ACTION_ERROR);
                log.error(e);
            }
        }
    }

    public String add() {
        // Check form request
        if (dimension == null) {
            return "success";
        }
        // Check input
        if (!validateDimension()) {
            setErrorCode(ErrorCode.FIELD_ERROR);
            return "success";
        }

        // Insert dimension
        try {
            IDimensionService dimensionService = new DimensionServiceImp();
            DimensionVo dimensionVo = ModelUtils.createVoFromModel(dimension, DimensionVo.class);
            dimensionVo.setWebshipId(Integer.valueOf(this.getWebshipLoginInfo().getWebshipId().toString()));
            dimensionService.insert(this.getAddInfoMap(), dimensionVo);
            addActionMessage("The dimension was added successfully");
            dimension = null;
        } catch (Exception e) {
            addActionError(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    public String edit() {
        IDimensionService dimensionService = new DimensionServiceImp();

        // Check edit request
        if (dimension == null) {
            try {
                DimensionVo dimensionVo = dimensionService.getById(Integer.valueOf(id));
                dimension = ModelUtils.createModelFromVo(dimensionVo, DimensionModel.class);
            } catch (Exception e) {
                addActionError(e.getMessage());
                setErrorCode(ErrorCode.ACTION_ERROR);
                log.error(e);
            }

            return "success";
        }

        // Check input
        if (!validateDimension()) {
            setErrorCode(ErrorCode.FIELD_ERROR);
            return "success";
        }

        // Update dimension
        try {
            DimensionVo dimensionVo = ModelUtils.createVoFromModel(dimension, DimensionVo.class);
            dimensionService.update(this.getAddInfoMap(), dimensionVo);
            addActionMessage("The dimension was updated successfully");
        } catch (Exception e) {
            addActionError(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }
        return "success";
    }

    private boolean validateDimension() {
        if (StringUtils.isBlank(dimension.getName())) {
            addFieldError("dimension.name", "Name cannot be empty.");
        }
        if (StringUtils.isBlank(dimension.getL())) {
            addFieldError("dimension.l", "Length cannot be empty.");
        } else if (!NumberUtils.isNumber(dimension.getL())) {
            addFieldError("dimension.l", "Length must be a number.");
        }
        if (StringUtils.isBlank(dimension.getH())) {
            addFieldError("dimension.h", "Height cannot be empty.");
        } else if (!NumberUtils.isNumber(dimension.getH())) {
            addFieldError("dimension.h", "Height must be a number.");
        }
        if (StringUtils.isBlank(dimension.getW())) {
            addFieldError("dimension.w", "Width cannot be empty.");
        } else if (!NumberUtils.isNumber(dimension.getW())) {
            addFieldError("dimension.w", "Width must be a number.");
        }

        return !hasActionErrors() && !hasFieldErrors();
    }

    private void loadDimensionList() throws Exception {
        IDimensionService dimensionService = new DimensionServiceImp();
        DimensionFilter filter = this.buildFilter();
        List<DimensionVo> dimensionVos = dimensionService.selectByFilter(filter);
        List<DimensionModel> dimensionModels = ModelUtils.createListModelFromVo(dimensionVos, DimensionModel.class);
        dimensionList.setRecords(dimensionModels);
    }

    private DimensionFilter buildFilter() {
        IDimensionService dimensionService = new DimensionServiceImp();
        DimensionFilter filter = new DimensionFilter();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pageSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int page = 0;
        try {
            page = Integer.parseInt(this.page);
        } catch (Exception ex) {
            page = 1;
        }

        Long webshipId = this.getWebshipLoginInfo().getWebshipId();
        filter.setWebshipId(Integer.valueOf(webshipId.toString()));
        long recordCount = 0;
        try {
            recordCount = dimensionService.countByFilter(filter);
        } catch (Exception ex) {
            recordCount = 0;
        }

        Paging<DimensionModel> paging = new Paging<DimensionModel>(page, nLinks, pageSize, recordCount);
        this.setDimensionList(paging);

        filter.setRecordSize(paging.getPageSize());
        filter.setStartRecord((paging.getCurrentPage() - 1) * paging.getPageSize());
        return filter;
    }

    public DimensionModel getDimension() {
        return dimension;
    }

    public void setDimension(DimensionModel dimension) {
        this.dimension = dimension;
    }

    public Paging<DimensionModel> getDimensionList() {
        return dimensionList;
    }

    public void setDimensionList(Paging<DimensionModel> dimensionList) {
        this.dimensionList = dimensionList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getPageSizeList() {
        return pageSizeList;
    }

    public void setPageSizeList(List<String> pageSizeList) {
        this.pageSizeList = pageSizeList;
    }
}