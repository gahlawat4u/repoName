package com.gms.xms.weblib.controller.webship.quotejob;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.QuoteJobModel;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.QuoteJobFilter;
import com.gms.xms.txndb.vo.WebshipQuoteLogDetailVo;
import com.gms.xms.txndb.vo.webship.quotejob.QuoteJobVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.service.webship.quotejob.IQuoteJobService;
import com.gms.xms.workflow.service.webship.quotejob.QuoteJobServiceImp;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Posted from QuoteJobController
 * <p>
 * Author HungNT Date Jul 9, 2015
 */
public class QuoteJobController extends JsonBaseController {
    private static final long serialVersionUID = -8028730604424732517L;
    private Paging<QuoteJobModel> quoteJobList = new Paging<>();
    private QuoteJobModel quoteJob;

    private List<String> pageSizeList = this.buildPageSizeList();
    private String startDate;
    private String endDate;
    private String quoteNumber;
    private String quoteId;
    private String page;
    private String pageSize;

    public String show() {
        this.setPageTitle("Quote/Job");
        this.setBreadCrumb("Quote/Job");
        return "success";
    }

    public String getData() {
        IQuoteJobService service = new QuoteJobServiceImp();
        try {
            Date startDate = DateUtils.convertStringToDate(this.startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), "");
            Date endDate = DateUtils.convertStringToDate(this.endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), "");
            if (startDate != null && endDate != null) {
                if (startDate.after(endDate)) {
                    this.setErrorCode(ErrorCode.ERROR);
                    this.setErrorMessage("Start date must be set before end date.");
                    return "success";
                }
            }
            QuoteJobFilter quoteJobFilter = this.buildFilter();
            List<QuoteJobVo> quoteJobVos = service.getQuoteJobList(quoteJobFilter);
            List<QuoteJobModel> quoteJobModels = ModelUtils.createListModelFromVo(quoteJobVos, QuoteJobModel.class);
            this.quoteJobList.setRecords(quoteJobModels);
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            return "error";
        }
        return "success";
    }

    public String viewDetail() {
        IQuoteJobService service = new QuoteJobServiceImp();
        try {
            if (StringUtils.isBlank(this.quoteId)) {
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage("QuoteId is empty.");
                return "error";
            }
            QuoteJobFilter quoteJobFilter = new QuoteJobFilter();
            quoteJobFilter.setQuoteId(Long.parseLong(this.quoteId));
            QuoteJobVo quoteJobVo = service.getQuoteJobDetail(quoteJobFilter);
            if (quoteJobVo != null) {
                List<WebshipQuoteLogDetailVo> detailVos = quoteJobVo.getQuoteLogDetails();
                for(WebshipQuoteLogDetailVo webshipQuoteLogDetailVo : detailVos){
                    AccessorialVo accessorial = webshipQuoteLogDetailVo.getAccessorial();
                    String prefix ="";
                    if(accessorial.getCode().equals("RAS") || accessorial.getCode().equals("EAS") ){
                       if(webshipQuoteLogDetailVo.getType()!=null){
                           prefix = webshipQuoteLogDetailVo.getType() == 0 ? " - origin":" - destination";
                           accessorial.setDescription(accessorial.getDescription() + prefix);
                       }
                    }
                }
                if (quoteJobVo.getWithInsurance() != null && quoteJobVo.getWithInsurance() != 0) {
                    WebshipQuoteLogDetailVo detailVo = new WebshipQuoteLogDetailVo();
                    AccessorialVo accessorialVo = new AccessorialVo();
                    accessorialVo.setDescription("Additional protection");
                    detailVo.setAccessorial(accessorialVo);
                    detailVo.setAmount(quoteJobVo.getWithInsurance());
                    detailVos.add(detailVo);
                }
                if (quoteJobVo.getManualHandlingSurcharge() != null && quoteJobVo.getManualHandlingSurcharge() > 0) {
                    WebshipQuoteLogDetailVo detailVo = new WebshipQuoteLogDetailVo();
                    AccessorialVo manualHandingSurcharge = new AccessorialVo();
                    manualHandingSurcharge.setDescription("Manual handling surcharge");
                    manualHandingSurcharge.setValue(quoteJobVo.getManualHandlingSurcharge());
                    detailVo.setAccessorial(manualHandingSurcharge);
                    detailVo.setAmount(quoteJobVo.getManualHandlingSurcharge());
                    detailVos.add(detailVo);
                }

                quoteJobVo.setQuoteLogDetails(detailVos);
                QuoteJobModel quoteJob = ModelUtils.createModelFromVo(quoteJobVo, QuoteJobModel.class);
                this.quoteJob = quoteJob;
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            return "error";
        }
        return "success";
    }

    public QuoteJobFilter buildFilter() {
        IQuoteJobService service = new QuoteJobServiceImp();

        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pageSize = 10;
        }

        int page = 0;
        try {
            page = Integer.parseInt(this.page);
        } catch (Exception ex) {
            page = 1;
        }

        long customerCode = 0;
        try {
            customerCode = this.getWebshipLoginInfo().getCustomerCode();
        } catch (Exception ex) {
            customerCode = 0;
        }
        long webshipId = 0;
        try {
            webshipId = this.getWebshipLoginInfo().getWebshipId();
        } catch (Exception e) {
            webshipId = 0;
        }

        QuoteJobFilter filter = new QuoteJobFilter();
        filter.setCustomerCode(customerCode);
        filter.setWebshipId(webshipId);
        filter.setStartDate(DateUtils.convertStringToDate(getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        filter.setEndDate(DateUtils.convertStringToDate(getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        filter.setQuoteNumber(getQuoteNumber());
        long recordCount = 0;
        try {
            recordCount = service.getQuoteJobListCount(filter);
        } catch (Exception ex) {
            recordCount = 0;
        }

        Paging<QuoteJobModel> paging = new Paging<QuoteJobModel>(page, 10, pageSize, recordCount);

        this.setQuoteJobList(paging);

        filter.setRecordSize(paging.getPageSize());
        filter.setStartRecord((paging.getCurrentPage() - 1) * paging.getPageSize());

        return filter;
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

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
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

    public Paging<QuoteJobModel> getQuoteJobList() {
        return quoteJobList;
    }

    public void setQuoteJobList(Paging<QuoteJobModel> quoteJobList) {
        this.quoteJobList = quoteJobList;
    }

    public QuoteJobModel getQuoteJob() {
        return quoteJob;
    }

    public void setQuoteJob(QuoteJobModel quoteJob) {
        this.quoteJob = quoteJob;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public List<String> getPageSizeList() {
        return pageSizeList;
    }

    public void setPageSizeList(List<String> pageSizeList) {
        this.pageSizeList = pageSizeList;
    }
}
