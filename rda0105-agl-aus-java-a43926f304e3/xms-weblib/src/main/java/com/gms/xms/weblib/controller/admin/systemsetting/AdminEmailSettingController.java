package com.gms.xms.weblib.controller.admin.systemsetting;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.filter.admin.AdminEmailFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.AdminEmailModel;
import com.gms.xms.model.admin.AdminEmailSettingModel;
import com.gms.xms.model.admin.AdminEmailSettingV2Model;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.AdminEmailServiceImp;
import com.gms.xms.persistence.service.admin.AdminEmailSettingServiceImp;
import com.gms.xms.persistence.service.admin.IAdminEmailService;
import com.gms.xms.persistence.service.admin.IAdminEmailSettingService;
import com.gms.xms.txndb.vo.admin.AdminEmailSettingV2Vo;
import com.gms.xms.txndb.vo.admin.AdminEmailSettingVo;
import com.gms.xms.txndb.vo.admin.AdminEmailVo;
import com.gms.xms.weblib.controller.JsonBaseController;

import java.util.List;

/**
 * Posted from AdminEmailSettingController
 * <p>
 * Author TANDT
 */
public class AdminEmailSettingController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    private String page;
    private String pageSize;
    private List<String> pageSizes;
    private AdminEmailModel adminEmailModel;
    private Paging<AdminEmailSettingV2Model> emailSettingModels;
    private AdminEmailSettingModel adminEmailSettingModel;
    private String idSetting;
    private String isEdit;

    public String show() {
        try {
            pageSizes = this.buildPageSizeList();
            doSearch();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String add() {
        try {
            if (adminEmailModel != null) {
                if (validAdminEmail()) {
                    IAdminEmailService service = new AdminEmailServiceImp();
                    AdminEmailVo adminEmailVo = ModelUtils.createVoFromModel(adminEmailModel, AdminEmailVo.class);
                    service.insert(this.getAddInfoMap(), adminEmailVo);
                    if (adminEmailVo.getId() != null) {
                        IAdminEmailSettingService adminEmailSettingService = new AdminEmailSettingServiceImp();

                        AdminEmailSettingVo adminEmailSettingVo = new AdminEmailSettingVo();
                        adminEmailSettingVo.setAdminEmailId(adminEmailVo.getId());
                        adminEmailSettingVo.setEmailSettingId(1);
                        adminEmailSettingService.insert(this.getAddInfoMap(), adminEmailSettingVo);

                        AdminEmailSettingVo adminEmailSettingVo2 = new AdminEmailSettingVo();
                        adminEmailSettingVo2.setAdminEmailId(adminEmailVo.getId());
                        adminEmailSettingVo2.setEmailSettingId(2);
                        adminEmailSettingService.insert(this.getAddInfoMap(), adminEmailSettingVo2);

                        AdminEmailSettingVo adminEmailSettingVo3 = new AdminEmailSettingVo();
                        adminEmailSettingVo3.setAdminEmailId(adminEmailVo.getId());
                        adminEmailSettingVo3.setEmailSettingId(3);
                        adminEmailSettingService.insert(this.getAddInfoMap(), adminEmailSettingVo3);
                        pageSizes = this.buildPageSizeList();
                        this.setPageSize("5");
                        doSearch();
                        return "home";
                    }

                } else {
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
                if (validAdminEmail()) {
                    IAdminEmailService service = new AdminEmailServiceImp();
                    AdminEmailVo adminEmailVo = ModelUtils.createVoFromModel(adminEmailModel, AdminEmailVo.class);
                    service.update(this.getAddInfoMap(), adminEmailVo);
                    pageSizes = this.buildPageSizeList();
                    this.setPageSize("5");
                    doSearch();
                    return "home";
                } else {
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

    public String updateStatus() {
        try {
            if (adminEmailSettingModel != null) {
                IAdminEmailSettingService service = new AdminEmailSettingServiceImp();
                service.updateStatus(this.getAddInfoMap(), ModelUtils.createVoFromModel(adminEmailSettingModel, AdminEmailSettingVo.class));
                pageSizes = this.buildPageSizeList();
                this.setPageSize("5");
                doSearch();
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

    public String delete() {
        try {
            if (adminEmailModel != null) {
                IAdminEmailService service = new AdminEmailServiceImp();
                service.delete(this.getAddInfoMap(), Integer.parseInt(adminEmailModel.getId()));
                if (idSetting != null) {
                    IAdminEmailSettingService adminEmailSettingService = new AdminEmailSettingServiceImp();
                    adminEmailSettingService.delete(this.getAddInfoMap(), Integer.parseInt(idSetting));
                    pageSizes = this.buildPageSizeList();
                    this.setPageSize("5");
                    doSearch();
                    return "home";
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

    public String adminEmailSettingAdd() {
        try {
            IAdminEmailService service = new AdminEmailServiceImp();
            if (adminEmailModel != null) {
                AdminEmailVo adminEmailVo = ModelUtils.createVoFromModel(adminEmailModel, AdminEmailVo.class);
                service.insert(this.getAddInfoMap(), adminEmailVo);
                pageSizes = this.buildPageSizeList();
                this.setPageSize("5");
                doSearch();
                return "home";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void doSearch() {
        IAdminEmailService service = new AdminEmailServiceImp();
        AdminEmailFilter filter = null;
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

        try {
            filter = this.buildFilter();
            long recordCount = service.selectAdminEmailSettingCount(filter);

            // Set paging info
            Paging<AdminEmailSettingV2Model> paging = new Paging<AdminEmailSettingV2Model>(iPage, nLinks, pSize, recordCount);
            filter.setPage(paging.getCurrentPage());
            filter.setPageSize(paging.getPageSize());

            // Get data of page
            List<AdminEmailSettingV2Vo> adminEmailVos = service.selectAdminEmailSetting(filter);
            List<AdminEmailSettingV2Model> adminEmailModels = ModelUtils.createListModelFromVo(adminEmailVos, AdminEmailSettingV2Model.class);
            paging.setRecords(adminEmailModels);
            this.setEmailSettingModels(paging);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
    }

    private boolean validAdminEmail() throws DaoException {
        if (!EmailUtils.isValidEmail(adminEmailModel.getEmail().trim())) {
            setErrorMessage("Email is not valid.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        } else {
            IAdminEmailService service = new AdminEmailServiceImp();
            if (service.checkEmail(adminEmailModel.getEmail()) > 0) {
                setErrorMessage("Email existing.");
                setErrorCode(ErrorCode.ACTION_ERROR);
                return false;
            }
        }
        return true;
    }

    private AdminEmailFilter buildFilter() throws Exception {
        AdminEmailFilter filter = new AdminEmailFilter();
        return filter;
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

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public Paging<AdminEmailSettingV2Model> getEmailSettingModels() {
        return emailSettingModels;
    }

    public void setEmailSettingModels(Paging<AdminEmailSettingV2Model> emailSettingModels) {
        this.emailSettingModels = emailSettingModels;
    }

    public AdminEmailModel getAdminEmailModel() {
        return adminEmailModel;
    }

    public void setAdminEmailModel(AdminEmailModel adminEmailModel) {
        this.adminEmailModel = adminEmailModel;
    }

    public String getIdSetting() {
        return idSetting;
    }

    public void setIdSetting(String idSetting) {
        this.idSetting = idSetting;
    }

    public AdminEmailSettingModel getAdminEmailSettingModel() {
        return adminEmailSettingModel;
    }

    public void setAdminEmailSettingModel(AdminEmailSettingModel adminEmailSettingModel) {
        this.adminEmailSettingModel = adminEmailSettingModel;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

}