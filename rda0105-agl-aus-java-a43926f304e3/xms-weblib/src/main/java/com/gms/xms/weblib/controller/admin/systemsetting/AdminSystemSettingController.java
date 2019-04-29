package com.gms.xms.weblib.controller.admin.systemsetting;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.AdminSystemSettingFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.SystemSettingDefaultValueModel;
import com.gms.xms.model.UserLevelModel;
import com.gms.xms.model.admin.SystemSettingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.AdminSystemSettingServiceImp;
import com.gms.xms.persistence.service.admin.AdministrationServiceImp;
import com.gms.xms.persistence.service.admin.IAdminSystemSettingService;
import com.gms.xms.persistence.service.admin.IAdministrationService;
import com.gms.xms.txndb.vo.SystemSettingDefaultValueVo;
import com.gms.xms.txndb.vo.SystemSettingVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Posted from AdminEmailSettingController
 * <p>
 * Author TANDT
 */
public class AdminSystemSettingController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;

    private List<String> pageSizes;
    private SystemSettingModel systemSettingModel;
    private Paging<SystemSettingModel> systemSettingListModel;
    private List<SystemSettingModel> systemSettingModels;
    private List<UserLevelModel> userLevelList;
    private List<SystemSettingDefaultValueModel> listValueDefault;
    private HashMap<String, List<SystemSettingDefaultValueModel>> viewListSystemSetting;
    private String settingId;
    private String idSetting;
    private String isEdit;
    private String[] multiValue;

    public String show() {
        try {
            setPage("1");
            setOrderType("0");
            setOrderField("setting_name");
            pageSizes = this.buildPageSizeList();
            doSearch();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String search() {
        try {
            pageSizes = this.buildPageSizeList();
            doSearch();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String edit() {
        try {
            if (isEdit != null) {
                if (multiValue != null) {
                    if (multiValue.length > 1) {
                        String valueForSetting = "";
                        for (int i = 0; i < multiValue.length; i++) {
                            valueForSetting = valueForSetting.concat(",").concat(multiValue[i]);
                        }
                        valueForSetting = StringUtils.substring(valueForSetting, 1);
                        systemSettingModel.setSettingValue(valueForSetting);
                    }
                }
                if (validSystemSetting()) {
                    IAdminSystemSettingService service = new AdminSystemSettingServiceImp();
                    SystemSettingVo systemSettingVo = ModelUtils.createVoFromModel(systemSettingModel, SystemSettingVo.class);
                    service.update(this.getAddInfoMap(), systemSettingVo);
                    SystemSettingCache.getInstance().reload();
                    pageSizes = this.buildPageSizeList();
                    doSearch();
                    return "home";
                } else {
                    return "home";
                }
            } else {
                checkDataSource();
                prepareListUserLevel();
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

    private boolean validSystemSetting() {
        if (StringUtils.isEmpty(systemSettingModel.getSettingName().trim())) {
            setErrorMessage("Setting Name is Empty.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        if (StringUtils.isEmpty(systemSettingModel.getSettingValue().trim())) {
            setErrorMessage("Setting Value is Empty.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        if (StringUtils.isEmpty(systemSettingModel.getDescription().trim())) {
            setErrorMessage("Descript is Empty.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        return true;
    }

    private void checkDataSource() throws Exception {
        IAdminSystemSettingService serviceN = new AdminSystemSettingServiceImp();
        SystemSettingModel systemSettingModelN = ModelUtils.createModelFromVo(serviceN.getSystemSettingById(Integer.parseInt(settingId)), SystemSettingModel.class);
        this.setSystemSettingModel(systemSettingModelN);
        if (StringUtils.isNotEmpty(systemSettingModel.getListValueDefault())) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<SystemSettingDefaultValueModel> listResult = mapper.readValue(systemSettingModel.getListValueDefault(), new TypeReference<List<SystemSettingDefaultValueModel>>() {
                });
                HashMap<String, List<SystemSettingDefaultValueModel>> hashMapDefaulValue = new HashMap<String, List<SystemSettingDefaultValueModel>>();
                List<SystemSettingDefaultValueModel> listDefaulValueSettingN = new ArrayList<SystemSettingDefaultValueModel>();
                for (SystemSettingDefaultValueModel model : listResult) {
                    if (model.getDataSource() != null) {
                        SystemSettingDefaultValueVo systemSettingDefaultValueVoN = new SystemSettingDefaultValueVo();
                        systemSettingDefaultValueVoN = ModelUtils.createVoFromModel(model, SystemSettingDefaultValueVo.class);
                        IAdminSystemSettingService service = new AdminSystemSettingServiceImp();
                        hashMapDefaulValue.put(model.getTextDisplay(), ModelUtils.createListModelFromVo(service.selectAllSystemSetting(systemSettingDefaultValueVoN), SystemSettingDefaultValueModel.class));
                    } else {
                        listDefaulValueSettingN.add(model);
                    }
                }
                if (listDefaulValueSettingN.size() > 0) {
                    hashMapDefaulValue.put("notDataSource", listDefaulValueSettingN);
                }

                this.setViewListSystemSetting(hashMapDefaulValue);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private void doSearch() {
        IAdminSystemSettingService service = new AdminSystemSettingServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        try {
            AdminSystemSettingFilter filter = this.buildFilter();
            long recordCount = service.getSystemSettingCount();

            // Set paging info
            Paging<SystemSettingModel> paging = new Paging<SystemSettingModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            filter.setPageSize(paging.getPageSize());

            // Get data of page
            List<SystemSettingVo> systemSettingVos = service.getSystemSettingAll(filter);
            List<SystemSettingModel> systemSettingModels = ModelUtils.createListModelFromVo(systemSettingVos, SystemSettingModel.class);
            paging.setRecords(systemSettingModels);
            this.setSystemSettingListModel(paging);
            prepareListUserLevel();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
    }

    private AdminSystemSettingFilter buildFilter() throws Exception {
        AdminSystemSettingFilter filter = new AdminSystemSettingFilter();
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "setting_name" : this.getOrderField());
        return filter;
    }

    private void prepareListUserLevel() throws Exception {
        IAdministrationService service = new AdministrationServiceImp();
        List<UserLevelModel> userLevelListN = new ArrayList<UserLevelModel>();
        userLevelListN = ModelUtils.createListModelFromVo(service.selectUserLevelForPermission(), UserLevelModel.class);
        for (UserLevelModel model : userLevelListN) {
            model.setUserLevelName(model.getUserLevelCode().concat(" : ").concat(model.getUserLevelName()));
        }
        this.setUserLevelList(userLevelListN);
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

    public SystemSettingModel getSystemSettingModel() {
        return systemSettingModel;
    }

    public void setSystemSettingModel(SystemSettingModel systemSettingModel) {
        this.systemSettingModel = systemSettingModel;
    }

    public Paging<SystemSettingModel> getSystemSettingListModel() {
        return systemSettingListModel;
    }

    public void setSystemSettingListModel(Paging<SystemSettingModel> systemSettingListModel) {
        this.systemSettingListModel = systemSettingListModel;
    }

    public List<SystemSettingModel> getSystemSettingModels() {
        return systemSettingModels;
    }

    public void setSystemSettingModels(List<SystemSettingModel> systemSettingModels) {
        this.systemSettingModels = systemSettingModels;
    }

    public String getIdSetting() {
        return idSetting;
    }

    public void setIdSetting(String idSetting) {
        this.idSetting = idSetting;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getSettingId() {
        return settingId;
    }

    public void setSettingId(String settingId) {
        this.settingId = settingId;
    }

    public List<UserLevelModel> getUserLevelList() {
        return userLevelList;
    }

    public void setUserLevelList(List<UserLevelModel> userLevelList) {
        this.userLevelList = userLevelList;
    }

    public List<SystemSettingDefaultValueModel> getListValueDefault() {
        return listValueDefault;
    }

    public void setListValueDefault(List<SystemSettingDefaultValueModel> listValueDefault) {
        this.listValueDefault = listValueDefault;
    }

    public HashMap<String, List<SystemSettingDefaultValueModel>> getViewListSystemSetting() {
        return viewListSystemSetting;
    }

    public void setViewListSystemSetting(HashMap<String, List<SystemSettingDefaultValueModel>> viewListSystemSetting) {
        this.viewListSystemSetting = viewListSystemSetting;
    }

    public String[] getMultiValue() {
        return multiValue;
    }

    public void setMultiValue(String[] multiValue) {
        this.multiValue = multiValue;
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