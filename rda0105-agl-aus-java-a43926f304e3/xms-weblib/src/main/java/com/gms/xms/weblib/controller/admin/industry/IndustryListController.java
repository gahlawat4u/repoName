package com.gms.xms.weblib.controller.admin.industry;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.IndustryFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.industry.IndustryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.industry.IIndustryService;
import com.gms.xms.persistence.service.industry.IndustryServiceImp;
import com.gms.xms.txndb.vo.industry.IndustryVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from IndustryListController
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class IndustryListController extends JsonBaseController {
    private static final long serialVersionUID = -7945740360436049333L;

    private IndustryFilter industryFilter;
    private Paging<IndustryModel> industryList;
    private IndustryModel industryModel;
    private String isEdit;
    private String industryId;

    private String pageSize;
    private String page;
    private String orderType;
    private String orderField;
    private List<String> listPageSize = this.buildPageSizeList();

    public String show() {
        try {
            // Set default sort
            setPage("1");
            setOrderType("0");
            setOrderField("industry_id");

            loadIndustry();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            loadIndustry();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void loadIndustry() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        IIndustryService IndustryService = new IndustryServiceImp();
        IndustryFilter filter = this.buildFilter();
        long recordCount = IndustryService.countByFilter(filter);
        // Set paging info
        Paging<IndustryModel> paging = new Paging<IndustryModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get data of page
        List<IndustryVo> IndustryVos = IndustryService.selectByFilter(filter);
        List<IndustryModel> industryModels = ModelUtils.createListModelFromVo(IndustryVos, IndustryModel.class);
        paging.setRecords(industryModels);
        this.setIndustryList(paging);
    }

    public String add() {
        try {
            if (industryModel != null) {
                System.out.println("Number of banks is " + industryModel);

                if (validIndustry()) {
                    IIndustryService IndustryService = new IndustryServiceImp();
                    IndustryVo IndustryVo = ModelUtils.createVoFromModel(industryModel, IndustryVo.class);
                    System.out.println("Number of banks is " + IndustryVo);
                    IndustryService.insert(this.getAddInfoMap(), IndustryVo);
                    loadIndustry();
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
                if (validIndustry()) {
                    IIndustryService IndustryService = new IndustryServiceImp();
                    IndustryVo IndustryVo = ModelUtils.createVoFromModel(industryModel, IndustryVo.class);
                    IndustryService.update(this.getAddInfoMap(), IndustryVo);
                    loadIndustry();
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
            if (industryModel.getIndustryId() != null) {
                IIndustryService industryService = new IndustryServiceImp();
                industryService.delete(this.getAddInfoMap(), Integer.parseInt(industryModel.getIndustryId()));
                loadIndustry();
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

    private IndustryFilter buildFilter() throws CustomException {
        IndustryFilter filter = new IndustryFilter();
        Integer page = null;
        try {
            page = StringUtils.isBlank(this.getPage()) ? 1 : Integer.valueOf(this.getPage());
            if (page <= 0) {
                throw new CustomException("The page number cannot be less than 0.");
            }
            filter.setPage(page);
        } catch (Exception e) {
            throw new CustomException("Invalid page number.");
        }
        // Set page size.
        Integer pageSize = null;
        try {
            pageSize = StringUtils.isBlank(this.getPageSize()) ? Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) : Integer.valueOf(this.getPageSize());
            if (pageSize <= 0) {
                throw new CustomException("The page size cannot be less than 0.");
            }
            filter.setPageSize(pageSize);
        } catch (Exception e) {
            throw new CustomException("Invalid page size number.");
        }
        // Set order type.
        Integer order = null;
        try {
            order = StringUtils.isBlank(this.getOrderType()) ? 0 : Integer.valueOf(this.getOrderType());
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            filter.setOrderType(order);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "industry_id" : this.getOrderField());
        return filter;
    }

    private boolean validIndustry() throws Exception {
        if (industryModel == null) {
            return false;
        }
        if (StringUtils.isBlank(industryModel.getIndustryName())) {
            addFieldError("industryModel.industryName", "Please Enter Industry Name");
        } else {
            IIndustryService industryService = new IndustryServiceImp();
            IndustryVo industryVo = ModelUtils.createVoFromModel(this.industryModel, IndustryVo.class);
            if (industryService.countByName(industryVo) > 0) {
                this.addFieldError("industryModel.industryName", "Input Industry Name already exists.");
                return false;
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public IndustryFilter getIndustryFilter() {
        return industryFilter;
    }

    public void setIndustryFilter(IndustryFilter industryFilter) {
        this.industryFilter = industryFilter;
    }

    public Paging<IndustryModel> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(Paging<IndustryModel> industryList) {
        this.industryList = industryList;
    }

    public IndustryModel getIndustryModel() {
        return industryModel;
    }

    public void setIndustryModel(IndustryModel industryModel) {
        this.industryModel = industryModel;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

}
