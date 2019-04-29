package com.gms.xms.weblib.controller.admin.languagevalue;

import com.gms.xms.cache.LocalizationCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.languagevalue.LanguageValueFilter;
import com.gms.xms.model.LanguageListModel;
import com.gms.xms.model.LanguageValueModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.LanguageDao;
import com.gms.xms.persistence.service.languagevalue.ILanguageValueService;
import com.gms.xms.persistence.service.languagevalue.LanguageValueServiceImp;
import com.gms.xms.txndb.vo.LanguageListVo;
import com.gms.xms.txndb.vo.LanguageValueVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageValueController extends JsonBaseController {
    private static final long serialVersionUID = 2910822509280639649L;

    private Paging<LanguageValueModel> languageValueList;
    private LanguageValueModel languageValue;

    private List<String> listPageSize;
    private Map<String, String> languageList;

    private String id;
    private String langCode;
    private String original;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    public String show() {
        try {
            this.prepareLanguageList();
            this.listPageSize = this.buildPageSizeList();
            this.buildLanguageValueList();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String getData() {
        try {
            this.buildLanguageValueList();
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
        }
        return "success";
    }

    public String add() {
        try {
            this.prepareLanguageList();
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                if (this.languageValue != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }

                    ILanguageValueService service = new LanguageValueServiceImp();
                    LanguageValueVo languageValueVo = ModelUtils.createVoFromModel(this.languageValue, LanguageValueVo.class);
                    LanguageValueVo languageValueCheck = service.getLanguageValueByKey(languageValueVo);
                    if (languageValueCheck != null) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        this.addFieldError("languageValue", this.getLocalizationText("This language value is existed."));
                        return "success";
                    }
                    service.insertLanguageValue(this.getAddInfoMap(), languageValueVo);
                    LocalizationCache.getInstance().reload();
                    this.buildLanguageValueList();
                    return "saved";
                }
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
        }
        return "success";
    }

    public String edit() {
        try {
            this.prepareLanguageList();
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                ILanguageValueService service = new LanguageValueServiceImp();
                if (this.languageValue != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                    LanguageValueVo languageValueVo = ModelUtils.createVoFromModel(this.languageValue, LanguageValueVo.class);
                    languageValueVo.setMode("PRO");
                    service.updateLanguageValue(this.getAddInfoMap(), languageValueVo);
                    LocalizationCache.getInstance().reload();
                    this.buildLanguageValueList();
                    return "saved";
                } else {
                    if (StringUtils.isBlank(this.getId())) {
                        this.setErrorCode(ErrorCode.ACTION_ERROR);
                        this.setErrorMessage(this.getLocalizationText("Language value id is empty."));
                        return "success";
                    }
                    Integer id = Integer.parseInt(this.getId());
                    LanguageValueVo languageValueVo = service.getLanguageValueById(id);
                    LanguageValueModel languageValueModel = ModelUtils.createModelFromVo(languageValueVo, LanguageValueModel.class);
                    this.setLanguageValue(languageValueModel);
                }
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
        }
        return "success";
    }

    private Boolean validateFields() {
        if (this.languageValue != null) {
            if (StringUtils.isBlank(this.languageValue.getLangCode())) {
                this.addFieldError("languageValue.langCode", this.getLocalizationText("Please select a language."));
            }
            if (StringUtils.isBlank(this.languageValue.getOriginal())) {
                this.addFieldError("languageValue.original", this.getLocalizationText("Original field cannot be empty."));
            }
            if (StringUtils.isBlank(this.languageValue.getDestination())) {
                this.addFieldError("languageValue.destination", this.getLocalizationText("Destination field cannot be empty."));
            }
        }
        if (this.hasFieldErrors()) {
            return false;
        }
        return true;
    }

    private void prepareLanguageList() throws Exception {
        LanguageDao languageDao = new LanguageDao();
        List<LanguageListVo> languageListVos = languageDao.getLanguageListWithCode();
        List<LanguageListModel> languageListModels = ModelUtils.createListModelFromVo(languageListVos, LanguageListModel.class);
        Map<String, String> languageMap = new HashMap<>();
        for (LanguageListModel languageListModel : languageListModels) {
            languageMap.put(languageListModel.getLanguageCode(), languageListModel.getLanguageName());
        }
        this.setLanguageList(languageMap);
    }

    private void buildLanguageValueList() throws Exception {
        ILanguageValueService service = new LanguageValueServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        LanguageValueFilter filter = this.buildFilter();
        Long recordCount = service.getLanguageValueCount(filter);
        Paging<LanguageValueModel> paging = new Paging<LanguageValueModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        List<LanguageValueVo> languageValueVos = service.getLanguageValueList(filter);
        List<LanguageValueModel> languageValueModels = ModelUtils.createListModelFromVo(languageValueVos, LanguageValueModel.class);
        paging.setRecords(languageValueModels);
        this.setLanguageValueList(paging);
    }

    private LanguageValueFilter buildFilter() {
        LanguageValueFilter filter = new LanguageValueFilter();
        // Default sort field is period
        orderField = StringUtils.isBlank(orderField) ? "original" : orderField;
        filter.setOrderField(orderField);
        // Default sort type is 1 (descending)
        orderType = StringUtils.isBlank(orderType) ? "0" : orderType;
        filter.setOrderType(Integer.valueOf(orderType));
        // Set default page
        page = StringUtils.isBlank(page) ? "1" : page;
        filter.setPage(Integer.valueOf(page));
        // Set default page size
        pageSize = StringUtils.isBlank(pageSize) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : pageSize;
        filter.setPageSize(Integer.valueOf(pageSize));
        filter.setLangCode(this.getLangCode());
        filter.setOriginal(this.getOriginal() != null ? this.getOriginal().trim() : this.getOriginal());
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

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Paging<LanguageValueModel> getLanguageValueList() {
        return languageValueList;
    }

    public void setLanguageValueList(Paging<LanguageValueModel> languageValueList) {
        this.languageValueList = languageValueList;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public Map<String, String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(Map<String, String> languageList) {
        this.languageList = languageList;
    }

    public LanguageValueModel getLanguageValue() {
        return languageValue;
    }

    public void setLanguageValue(LanguageValueModel languageValue) {
        this.languageValue = languageValue;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
