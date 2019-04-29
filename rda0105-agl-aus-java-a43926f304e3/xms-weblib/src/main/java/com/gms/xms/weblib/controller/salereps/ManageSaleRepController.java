package com.gms.xms.weblib.controller.salereps;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.common.utils.FormaterUtils;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepReportFilter;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepSettingFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.payables.salesrep.*;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.service.admin.payables.salesrep.ISalesRepService;
import com.gms.xms.persistence.service.admin.payables.salesrep.SalesRepServiceImp;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepAirbillStatVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepOverviewVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepServiceStatVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepSettingVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.salesrepcommissions.IManageSalesRepsComissionsRender;
import com.gms.xms.workflow.render.salesrepcommissions.ManageSalesRepsCommissionsRenderImp;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Posted from ManageSaleRepController
 * <p>
 * Author dattrinh Mar 21, 2016 2:35:28 PM
 */
public class ManageSaleRepController extends AdminJsonBaseController {

    private static final long serialVersionUID = -7945740360436049333L;

    // Paging properties.
    private String userId;
    private String startDate;
    private String endDate;

    private String pageSize;
    private String page;
    private String orderField;
    private String orderType;

    private String awbPageSize;
    private String awbPage;
    private String awbOrderField;
    private String awbOrderType;

    private String svPageSize;
    private String svPage;
    private String svOrderField;
    private String svOrderType;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    // Models.
    private List<UserModel> existingSalesReps;
    private List<UserModel> generateSalesReps;
    private SalesRepSettingModel salesRep;
    private Paging<SalesRepSettingModel> salesRepReport;
    private SalesRepOverviewModel overview;
    private List<SalesRepServiceStatModel> serviceStats;
    private Paging<SalesRepAirbillStatModel> airbillStats;

    private String salesRepId;

    public String show() {
        try {
            this.preparePageSizes();
            this.prepareExistingSalesReps();
            this.prepareSalesRepsReport();
            prepareGenerateSalesReps();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String searchSalesRepSettings() {
        try {
            this.prepareSalesRepsReport();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String loadSalesRepSettings() {
        try {
            // Validate sales rep id.
            if (StringUtils.isBlank(this.getSalesRepId())) {
                throw new CustomException("Please input sales rep id.");
            }
            // Load sales rep setting by id.
            ISalesRepService salesRepService = new SalesRepServiceImp();
            SalesRepSettingVo salesRepSettingVo = salesRepService.getSalesRepSettingsBySaleRepId(Long.valueOf(this.getSalesRepId()));
            // Get active field.
            Integer active = null;
            if (salesRepSettingVo != null) {
                if (salesRepSettingVo.getTerminateDate() == null) {
                    active = 0;
                } else {
                    if (salesRepSettingVo.getTerminateDate().after(Calendar.getInstance().getTime())) {
                        active = 0;
                    } else {
                        active = 1;
                    }
                }
                salesRepSettingVo.setActive(active);
            }
            // Convert to model.
            SalesRepSettingModel salesRepSettingModel = ModelUtils.createModelFromVo(salesRepSettingVo, SalesRepSettingModel.class);
            this.setSalesRep(salesRepSettingModel);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String createSalesRepSetting() {
        try {
            // Validate user id.
            if (StringUtils.isBlank(this.getUserId())) {
                throw new CustomException("Please input user id.");
            }
            Long userId = Long.valueOf(this.getUserId());
            // Load new sales rep setting by user id.
            ISalesRepService salesRepService = new SalesRepServiceImp();
            SalesRepSettingVo salesRepSettingVo = salesRepService.getNewSalesRepSettingByUserId(userId);
            // Set some default information.
            salesRepSettingVo.setUserId(userId);
            salesRepSettingVo.setPercentPayout(0.0);
            salesRepSettingVo.setUseWebshipContactInformation(false);
            salesRepSettingVo.setSalary(0.0);
            salesRepSettingVo.setVehicleAllowance(0.0);
            salesRepSettingVo.setPhoneAllowance(0.0);
            salesRepSettingVo.setHealthAllowance(0.0);
            salesRepSettingVo.setAllowance1Amount(0.0);
            salesRepSettingVo.setAllowance2Amount(0.0);
            salesRepSettingVo.setAllowance3Amount(0.0);
            salesRepSettingVo.setDeduction1Amount(0.0);
            salesRepSettingVo.setDeduction2Amount(0.0);
            salesRepSettingVo.setDeduction3Amount(0.0);
            salesRepSettingVo.setExcludeRanking(false);
            // Convert to model.
            SalesRepSettingModel salesRepSettingModel = ModelUtils.createModelFromVo(salesRepSettingVo, SalesRepSettingModel.class);
            this.setSalesRep(salesRepSettingModel);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String saveSalesRepSettings() {
        try {
            // Check model.
            if (this.getSalesRep() == null) {
                throw new CustomException("There isn't sales rep setting object to save.");
            }
            // Validate model.
            if (!this.isValidSalesRepSetting(this.getSalesRep())) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                // Save sales rep setting to the database here.
                ISalesRepService salesRepService = new SalesRepServiceImp();
                SalesRepSettingVo salesRepSettingVo = ModelUtils.createVoFromModel(this.getSalesRep(), SalesRepSettingVo.class);
                // Insert new sales rep if sales rep id is null.
                if (salesRepSettingVo.getSalesRepId() == null) {
                    salesRepService.addSalesRepSetting(this.getAddInfoMap(), salesRepSettingVo);
                } else {
                    // Update if sales rep id is not null.
                    salesRepService.editSalesRepSetting(this.getAddInfoMap(), salesRepSettingVo);
                }
                this.addActionMessage("Saved successfully.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String generateReport() {
        try {
            this.preparePageSizes();
            // Get filter.
            SalesRepReportFilter filter = this.buildGenerateReportFilter();
            // Get overview.
            ISalesRepService salesRepService = new SalesRepServiceImp();
            SalesRepOverviewVo overviewVo = salesRepService.getSalesRepOverview(filter);
            SalesRepOverviewModel overviewModel = ModelUtils.createModelFromVo(overviewVo, SalesRepOverviewModel.class);
            this.setOverview(overviewModel);
            // Get sales rep service statistics.
            // Set default settings for paging for sales rep service statistics.
            this.setSvOrderType("0");
            this.setSvOrderField("service_name");
            // Get sales rep service filter.
            SalesRepReportFilter salesRepServiceFilter = this.buildSalesRepServiceFilter(filter);
            List<SalesRepServiceStatVo> serviceStatVos = salesRepService.getSalesRepServiceStats(salesRepServiceFilter);
            List<SalesRepServiceStatModel> serviceStatModels = ModelUtils.createListModelFromVo(serviceStatVos, SalesRepServiceStatModel.class);
            this.setServiceStats(serviceStatModels);
            // Get sales rep airbill statistics.
            // Set default settings for paging for sales rep airbill statistics.
            this.setAwbPage("1");
            this.setAwbPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            this.setAwbOrderType("0");
            this.setAwbOrderField("airbill_number");
            // Get sales rep airbill filter.
            SalesRepReportFilter salesRepAirbillFilter = this.buildSalesRepAirbillFilter(filter);
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            long recordCount = salesRepService.countSalesRepAirbillStats(salesRepAirbillFilter);
            // Build paging object.
            Paging<SalesRepAirbillStatModel> paging = new Paging<SalesRepAirbillStatModel>(salesRepAirbillFilter.getPage(), nLinks, salesRepAirbillFilter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            List<SalesRepAirbillStatVo> airbillStatVos = salesRepService.getSalesRepAirbillStats(salesRepAirbillFilter);
            List<SalesRepAirbillStatModel> airbillStatModels = ModelUtils.createListModelFromVo(airbillStatVos, SalesRepAirbillStatModel.class);
            paging.setRecords(airbillStatModels);
            this.setAirbillStats(paging);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String generateServiceReport() {
        try {
            // Get filter.
            SalesRepReportFilter filter = this.buildGenerateReportFilter();
            // Get sales rep service report.
            ISalesRepService salesRepService = new SalesRepServiceImp();
            SalesRepReportFilter salesRepServiceFilter = this.buildSalesRepServiceFilter(filter);
            List<SalesRepServiceStatVo> serviceStatVos = salesRepService.getSalesRepServiceStats(salesRepServiceFilter);
            List<SalesRepServiceStatModel> serviceStatModels = ModelUtils.createListModelFromVo(serviceStatVos, SalesRepServiceStatModel.class);
            this.setServiceStats(serviceStatModels);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String generateAirbillReport() {
        try {
            // Get filter.
            SalesRepReportFilter filter = this.buildGenerateReportFilter();
            // Get sales rep service report.
            ISalesRepService salesRepService = new SalesRepServiceImp();
            // Get sales rep airbill filter.
            SalesRepReportFilter salesRepAirbillFilter = this.buildSalesRepAirbillFilter(filter);
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            long recordCount = salesRepService.countSalesRepAirbillStats(salesRepAirbillFilter);
            // Build paging object.
            Paging<SalesRepAirbillStatModel> paging = new Paging<SalesRepAirbillStatModel>(salesRepAirbillFilter.getPage(), nLinks, salesRepAirbillFilter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            List<SalesRepAirbillStatVo> airbillStatVos = salesRepService.getSalesRepAirbillStats(salesRepAirbillFilter);
            List<SalesRepAirbillStatModel> airbillStatModels = ModelUtils.createListModelFromVo(airbillStatVos, SalesRepAirbillStatModel.class);
            paging.setRecords(airbillStatModels);
            this.setAirbillStats(paging);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportHtml() {
        try {
            SalesRepReportFilter overviewFilter = this.buildGenerateReportFilter();
            SalesRepReportFilter serviceFilter = this.buildSalesRepServiceFilter(overviewFilter);
            serviceFilter.setPage(null);
            serviceFilter.setPageSize(null);
            SalesRepReportFilter invoiceFilter = this.buildSalesRepAirbillFilter(overviewFilter);
            invoiceFilter.setPage(null);
            invoiceFilter.setPageSize(null);
            IManageSalesRepsComissionsRender render = new ManageSalesRepsCommissionsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/manage_sale_reps_commissions_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlFile(overviewFilter, serviceFilter, invoiceFilter, outputFilePath, realPath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportPdf() {
        try {
            SalesRepReportFilter overviewFilter = this.buildGenerateReportFilter();
            SalesRepReportFilter serviceFilter = this.buildSalesRepServiceFilter(overviewFilter);
            serviceFilter.setPage(null);
            serviceFilter.setPageSize(null);
            SalesRepReportFilter invoiceFilter = this.buildSalesRepAirbillFilter(overviewFilter);
            invoiceFilter.setPage(null);
            invoiceFilter.setPageSize(null);
            IManageSalesRepsComissionsRender render = new ManageSalesRepsCommissionsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/mange_sale_reps_commissons_" + AppUtils.createMessageReference() + ".html";
            String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/mange_sale_reps_commissons_" + AppUtils.createMessageReference() + ".pdf";
            render.generateHtmlViewPdfFile(overviewFilter, serviceFilter, invoiceFilter, outputFilePath);
            AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
            this.setFileName("manage_sale_reps_commissions.pdf");
            this.setStream(new FileInputStream(new File(pdfFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportDetailsHtml() {
        try {
            // Get filter.
            SalesRepReportFilter filter = this.buildGenerateReportFilter();
            // Get sales rep service report.
            ISalesRepService salesRepService = new SalesRepServiceImp();
            // Get sales rep airbill filter.
            SalesRepReportFilter salesRepAirbillFilter = this.buildSalesRepAirbillFilter(filter);
            salesRepAirbillFilter.setPage(null);
            salesRepAirbillFilter.setPageSize(null);
            // Get datas
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/manage_sale_reps_commissions_details_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            List<SalesRepAirbillStatVo> airbillStatVos = salesRepService.getSalesRepAirbillStats(salesRepAirbillFilter);
            List<SalesRepAirbillStatModel> airbillStatModels = ModelUtils.createListModelFromVo(airbillStatVos, SalesRepAirbillStatModel.class);
            IManageSalesRepsComissionsRender render = new ManageSalesRepsCommissionsRenderImp(this.getAddInfoMap());
            render.generateDetailsHtml(airbillStatModels, outputFilePath, realPath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportDetailsXls() {
        try {
            // Get filter.
            SalesRepReportFilter filter = this.buildGenerateReportFilter();
            // Get sales rep service report.
            ISalesRepService salesRepService = new SalesRepServiceImp();
            // Get sales rep airbill filter.
            SalesRepReportFilter salesRepAirbillFilter = this.buildSalesRepAirbillFilter(filter);
            salesRepAirbillFilter.setPage(null);
            salesRepAirbillFilter.setPageSize(null);
            // Get datas
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/manage_sale_reps_commissions_details_" + AppUtils.createMessageReference() + ".xls";
            List<SalesRepAirbillStatVo> airbillStatVos = salesRepService.getSalesRepAirbillStats(salesRepAirbillFilter);
            List<SalesRepAirbillStatModel> airbillStatModels = ModelUtils.createListModelFromVo(airbillStatVos, SalesRepAirbillStatModel.class);
            IManageSalesRepsComissionsRender render = new ManageSalesRepsCommissionsRenderImp(this.getAddInfoMap());
            render.generateDetailsXls(airbillStatModels, outputFilePath);
            this.setFileName("Generate_Report_Details.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean isValidSalesRepSetting(SalesRepSettingModel saleRepSetting) {
        // Validate hire date.
        Date hireDate = null;
        try {
            hireDate = DateUtils.convertStringToDate(saleRepSetting.getHireDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
            this.addFieldError("salesRep.hireDate", "The hire date is invalid date format.");
        }
        if (hireDate == null) {
            this.addFieldError("salesRep.hireDate", "The hire date cannot leave blank.");
        }

        // Validate address 1.
        if (StringUtils.isBlank(saleRepSetting.getAddress1())) {
            this.addFieldError("salesRep.address1", "The address 1 cannot leave blank.");
        }

        // Validate some other field if it's not null.
        // Validate terminate date.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getTerminateDate())) {
                Date terminateDate = DateUtils.convertStringToDate(saleRepSetting.getTerminateDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                if (terminateDate == null) {
                    this.addFieldError("salesRep.terminateDate", "The terminate date is invalid date format.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.terminateDate", "The terminate date is invalid date format.");
        }
        // Validate percent payout.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getPercentPayout())) {
                double percentPayount = FormaterUtils.string2Double(saleRepSetting.getPercentPayout());
                if (percentPayount < 0) {
                    this.addFieldError("salesRep.percentPayout", "The percent payout cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.percentPayout", "The percent payout must be a number.");
        }
        // Validate email.
        if (!StringUtils.isBlank(saleRepSetting.getEmail()) && !EmailUtils.isValidEmail(saleRepSetting.getEmail())) {
            this.addFieldError("salesRep.email", "Invalid email.");
        }
        // Validate salary.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getSalary())) {
                double salary = FormaterUtils.string2Double(saleRepSetting.getSalary());
                if (salary < 0) {
                    this.addFieldError("salesRep.salary", "The salary cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.salary", "The salary must be a number.");
        }
        // Validate vehicle allowance.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getVehicleAllowance())) {
                double vehicleAllowance = FormaterUtils.string2Double(saleRepSetting.getVehicleAllowance());
                if (vehicleAllowance < 0) {
                    this.addFieldError("salesRep.vehicleAllowance", "The vehicle allowance cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.vehicleAllowance", "The vehicle allowance must be a number.");
        }
        // Validate phone allowance.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getPhoneAllowance())) {
                double phoneAllowance = FormaterUtils.string2Double(saleRepSetting.getPhoneAllowance());
                if (phoneAllowance < 0) {
                    this.addFieldError("salesRep.phoneAllowance", "The phone allowance cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.phoneAllowance", "The phone allowance must be a number.");
        }
        // Validate health allowance.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getHealthAllowance())) {
                double healthAllowance = FormaterUtils.string2Double(saleRepSetting.getHealthAllowance());
                if (healthAllowance < 0) {
                    this.addFieldError("salesRep.healthAllowance", "The health allowance cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.healthAllowance", "The health allowance must be a number.");
        }
        // Validate other 1 allowance.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getAllowance1Amount())) {
                double other1Allowance = FormaterUtils.string2Double(saleRepSetting.getAllowance1Amount());
                if (other1Allowance < 0) {
                    this.addFieldError("salesRep.allowance1Amount", "The other 1 allowance amount cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.allowance1Amount", "The other 1 allowance amount must be a number.");
        }
        // Validate other 2 allowance.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getAllowance2Amount())) {
                double other2Allowance = FormaterUtils.string2Double(saleRepSetting.getAllowance2Amount());
                if (other2Allowance < 0) {
                    this.addFieldError("salesRep.allowance2Amount", "The other 2 allowance amount cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.allowance2Amount", "The other 2 allowance amount must be a number.");
        }
        // Validate other 3 allowance.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getAllowance3Amount())) {
                double other3Allowance = FormaterUtils.string2Double(saleRepSetting.getAllowance3Amount());
                if (other3Allowance < 0) {
                    this.addFieldError("salesRep.allowance3Amount", "The other 3 allowance amount cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.allowance3Amount", "The other 3 allowance amount must be a number.");
        }
        // Validate deduction 1.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getDeduction1Amount())) {
                double deduction1 = FormaterUtils.string2Double(saleRepSetting.getDeduction1Amount());
                if (deduction1 < 0) {
                    this.addFieldError("salesRep.deduction1Amount", "The deduction 1 amount cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.deduction1Amount", "The deduction 1 amount must be a number.");
        }
        // Validate deduction 2.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getDeduction2Amount())) {
                double deduction2 = FormaterUtils.string2Double(saleRepSetting.getDeduction2Amount());
                if (deduction2 < 0) {
                    this.addFieldError("salesRep.deduction2Amount", "The deduction 2 amount cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.deduction2Amount", "The deduction 2 amount must be a number.");
        }
        // Validate deduction 3.
        try {
            if (!StringUtils.isBlank(saleRepSetting.getDeduction3Amount())) {
                double deduction3 = FormaterUtils.string2Double(saleRepSetting.getDeduction3Amount());
                if (deduction3 < 0) {
                    this.addFieldError("salesRep.deduction3Amount", "The deduction 3 amount cannot be a negative number.");
                }
            }
        } catch (Exception e) {
            this.addFieldError("salesRep.deduction3Amount", "The deduction 3 amount must be a number.");
        }
        // Valid goal/payout values of all services.
        int index = 0;
        for (SalesRepPayoutGoalModel service : saleRepSetting.getSalesRepServices()) {
            // Validate goal.
            try {
                if (!StringUtils.isBlank(service.getGoal())) {
                    double goal = FormaterUtils.string2Double(service.getGoal());
                    if (goal < 0) {
                        this.addFieldError("salesRep.salesRepServices[" + String.valueOf(index) + "].goal", "The goal cannot be a negative number.");
                    }
                }
            } catch (Exception e) {
                this.addFieldError("salesRep.salesRepServices[" + String.valueOf(index) + "].goal", "The goal must be a number.");
            }
            // Validate payout.
            try {
                if (!StringUtils.isBlank(service.getPayout())) {
                    double payout = FormaterUtils.string2Double(service.getPayout());
                    if (payout < 0) {
                        this.addFieldError("salesRep.salesRepServices[" + String.valueOf(index) + "].payout", "The payout cannot be a negative number.");
                    }
                }
            } catch (Exception e) {
                this.addFieldError("salesRep.salesRepServices[" + String.valueOf(index) + "].payout", "The payout must be a number.");
            }
            index++;
        }
        return !hasFieldErrors();
    }

    protected void prepareSalesRepsReport() throws Exception {
        // Get filter.
        SalesRepSettingFilter filter = this.buildSalesRepSettingFilter();
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        ISalesRepService salesRepService = new SalesRepServiceImp();
        long recordCount = salesRepService.countSalesRepSettingsByFilter(filter);
        // Build paging object.
        Paging<SalesRepSettingModel> paging = new Paging<SalesRepSettingModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of sales rep setting report.
        List<SalesRepSettingVo> salesRepSettingVos = salesRepService.getSalesRepSettingsByFilter(filter);
        // Get name of allowance and deduction.
        for (SalesRepSettingVo salesRepSettingVo : salesRepSettingVos) {
            salesRepSettingVo.setAllowance1Description(this.getLocalizationText(StringUtils.isBlank(salesRepSettingVo.getAllowance1Description()) ? "Other 1 (Undefined): " : salesRepSettingVo.getAllowance1Description() + ": "));
            salesRepSettingVo.setAllowance2Description(this.getLocalizationText(StringUtils.isBlank(salesRepSettingVo.getAllowance2Description()) ? "Other 2 (Undefined): " : salesRepSettingVo.getAllowance2Description() + ": "));
            salesRepSettingVo.setAllowance3Description(this.getLocalizationText(StringUtils.isBlank(salesRepSettingVo.getAllowance3Description()) ? "Other 3 (Undefined): " : salesRepSettingVo.getAllowance3Description() + ": "));
            salesRepSettingVo.setDeduction1Description(this.getLocalizationText(StringUtils.isBlank(salesRepSettingVo.getDeduction1Description()) ? "Ded. 1 (Undefined): " : salesRepSettingVo.getDeduction1Description() + ": "));
            salesRepSettingVo.setDeduction2Description(this.getLocalizationText(StringUtils.isBlank(salesRepSettingVo.getDeduction2Description()) ? "Ded. 2 (Undefined): " : salesRepSettingVo.getDeduction2Description() + ": "));
            salesRepSettingVo.setDeduction3Description(this.getLocalizationText(StringUtils.isBlank(salesRepSettingVo.getDeduction3Description()) ? "Ded. 3 (Undefined): " : salesRepSettingVo.getDeduction3Description() + ": "));
        }
        List<SalesRepSettingModel> salesRepSettingModels = ModelUtils.createListModelFromVo(salesRepSettingVos, SalesRepSettingModel.class);
        paging.setRecords(salesRepSettingModels);
        // Set sales rep setting report.
        this.setSalesRepReport(paging);
    }

    protected void prepareExistingSalesReps() throws Exception {
        UserDao userDao = new UserDao();
        List<UserVo> userVos = userDao.getExistingSalesReps(this.buildSalesRepFilter());
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setExistingSalesReps(userModels);
    }

    protected void prepareGenerateSalesReps() throws Exception {
        UserDao userDao = new UserDao();
        List<UserVo> userVos = userDao.getGenerateReportSalesReps(this.buildSalesRepFilter());
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setGenerateSalesReps(userModels);
    }

    protected SalesRepSettingFilter buildSalesRepFilter() throws Exception {
        SalesRepSettingFilter filter = new SalesRepSettingFilter();
        filter.setFranchiseList((this.buildFranchiseCodeList("All")));
        // Get user id and user level.
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        String userLevel = (String) this.getSession("SESS_XMS_ADMIN_LEVEL");
        filter.setUserId(Long.valueOf(userId));
        filter.setUserLevel(Double.valueOf(userLevel).intValue());
        return filter;
    }

    protected SalesRepSettingFilter buildSalesRepSettingFilter() throws Exception {
        SalesRepSettingFilter filter = this.buildSalesRepFilter();
        // Set page.
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
        return filter;
    }

    protected SalesRepReportFilter buildGenerateReportFilter() throws Exception {
        SalesRepReportFilter filter = new SalesRepReportFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        // Set user id.
        Long userId = null;
        try {
            userId = StringUtils.isBlank(this.getUserId()) ? null : Long.valueOf(this.getUserId());
        } catch (Exception e) {
            throw new CustomException("Invalid user id.");
        }
        if (userId == null) {
            throw new CustomException("No user id to view.");
        }
        filter.setUserId(userId);
        // Set start date.
        Date startDate = null;
        try {
            startDate = DateUtils.convertStringToDate(this.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (startDate == null) {
                throw new CustomException("Please choose start date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid start date.");
        }
        filter.setStartDate(startDate);
        // Set end date.
        Date endDate = null;
        try {
            endDate = DateUtils.convertStringToDate(this.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (endDate == null) {
                throw new CustomException("Please choose end date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid end date.");
        }
        filter.setEndDate(endDate);
        // Check date range.
        if (endDate.before(startDate)) {
            throw new CustomException("Start date must before End date.");
        }
        return filter;
    }

    protected SalesRepReportFilter buildSalesRepServiceFilter(SalesRepReportFilter filter) throws Exception {
        SalesRepReportFilter reportFilter = new SalesRepReportFilter();
        reportFilter.setFranchiseList(filter.getFranchiseList());
        reportFilter.setUserId(filter.getUserId());
        reportFilter.setStartDate(filter.getStartDate());
        reportFilter.setEndDate(filter.getEndDate());
        // Set order type.
        Integer order = null;
        try {
            order = StringUtils.isBlank(this.getSvOrderType()) ? 0 : Integer.valueOf(this.getSvOrderType());
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            reportFilter.setOrderType(order);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        reportFilter.setOrderField(StringUtils.isBlank(this.getSvOrderField()) ? "service_name" : this.getSvOrderField());
        return reportFilter;
    }

    protected SalesRepReportFilter buildSalesRepAirbillFilter(SalesRepReportFilter filter) throws Exception {
        SalesRepReportFilter reportFilter = new SalesRepReportFilter();
        reportFilter.setFranchiseList(filter.getFranchiseList());
        reportFilter.setUserId(filter.getUserId());
        reportFilter.setStartDate(filter.getStartDate());
        reportFilter.setEndDate(filter.getEndDate());
        // Set page.
        Integer page = null;
        try {
            page = StringUtils.isBlank(this.getAwbPage()) ? 1 : Integer.valueOf(this.getAwbPage());
            if (page <= 0) {
                throw new CustomException("The page number cannot be less than 0.");
            }
            reportFilter.setPage(page);
        } catch (Exception e) {
            throw new CustomException("Invalid page number.");
        }
        // Set page size.
        Integer pageSize = null;
        try {
            pageSize = StringUtils.isBlank(this.getAwbPageSize()) ? Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) : Integer.valueOf(this.getAwbPageSize());
            if (pageSize <= 0) {
                throw new CustomException("The page size cannot be less than 0.");
            }
            reportFilter.setPageSize(pageSize);
        } catch (Exception e) {
            throw new CustomException("Invalid page size number.");
        }
        // Set order type.
        Integer order = null;
        try {
            order = StringUtils.isBlank(this.getAwbOrderType()) ? 0 : Integer.valueOf(this.getAwbOrderType());
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            reportFilter.setOrderType(order);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        reportFilter.setOrderField(StringUtils.isBlank(this.getAwbOrderField()) ? "airbill_number" : this.getAwbOrderField());
        return reportFilter;
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

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public SalesRepSettingModel getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(SalesRepSettingModel salesRep) {
        this.salesRep = salesRep;
    }

    public Paging<SalesRepSettingModel> getSalesRepReport() {
        return salesRepReport;
    }

    public void setSalesRepReport(Paging<SalesRepSettingModel> salesRepReport) {
        this.salesRepReport = salesRepReport;
    }

    public List<UserModel> getExistingSalesReps() {
        return existingSalesReps;
    }

    public void setExistingSalesReps(List<UserModel> existingSalesReps) {
        this.existingSalesReps = existingSalesReps;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserModel> getGenerateSalesReps() {
        return generateSalesReps;
    }

    public void setGenerateSalesReps(List<UserModel> generateSalesReps) {
        this.generateSalesReps = generateSalesReps;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public SalesRepOverviewModel getOverview() {
        return overview;
    }

    public void setOverview(SalesRepOverviewModel overview) {
        this.overview = overview;
    }

    public List<SalesRepServiceStatModel> getServiceStats() {
        return serviceStats;
    }

    public void setServiceStats(List<SalesRepServiceStatModel> serviceStats) {
        this.serviceStats = serviceStats;
    }

    public Paging<SalesRepAirbillStatModel> getAirbillStats() {
        return airbillStats;
    }

    public void setAirbillStats(Paging<SalesRepAirbillStatModel> airbillStats) {
        this.airbillStats = airbillStats;
    }

    public String getAwbPageSize() {
        return awbPageSize;
    }

    public void setAwbPageSize(String awbPageSize) {
        this.awbPageSize = awbPageSize;
    }

    public String getAwbPage() {
        return awbPage;
    }

    public void setAwbPage(String awbPage) {
        this.awbPage = awbPage;
    }

    public String getAwbOrderField() {
        return awbOrderField;
    }

    public void setAwbOrderField(String awbOrderField) {
        this.awbOrderField = awbOrderField;
    }

    public String getAwbOrderType() {
        return awbOrderType;
    }

    public void setAwbOrderType(String awbOrderType) {
        this.awbOrderType = awbOrderType;
    }

    public String getSvPageSize() {
        return svPageSize;
    }

    public void setSvPageSize(String svPageSize) {
        this.svPageSize = svPageSize;
    }

    public String getSvPage() {
        return svPage;
    }

    public void setSvPage(String svPage) {
        this.svPage = svPage;
    }

    public String getSvOrderField() {
        return svOrderField;
    }

    public void setSvOrderField(String svOrderField) {
        this.svOrderField = svOrderField;
    }

    public String getSvOrderType() {
        return svOrderType;
    }

    public void setSvOrderType(String svOrderType) {
        this.svOrderType = svOrderType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }
}
