package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.invoicing.SendCreditNoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.persistence.dao.CreditNoteDao;
import com.gms.xms.persistence.dao.CreditNoteDetailDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.CreditNoteClient;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import com.gms.xms.workflow.client.integration.request.CreditNoteRequest;
import com.gms.xms.workflow.client.integration.response.CreditNoteResponse;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Posted from CreditNotesController
 * <p>
 * Author TanDT Date May 20, 2015
 */
public class FreezeCreditNotesController extends JsonBaseController {
    private static final long serialVersionUID = 4923595193899566440L;

    private List<String> listCreditNoteDate;
    private CreditNoteFilter creditNoteFilter;

    private List<FranchiseInfoModel> franchiseList;
    private List<String> dateList;
    private SendCreditNoteModel sendCreditNoteModel;
    private String creditNoteCode;

    public String showFreezePage() {
        try {
            prepareFranchiseAndDateList(0);
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    /**
     * Show CreditNote Detail by CreditCode
     *
     * @return success
     */
    public String doFreezeCreditNotes() {
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        try {
            if (sendCreditNoteModel != null) {
                if (!StringUtils.isBlank(sendCreditNoteModel.getFranchiseCode()) && !StringUtils.isBlank(sendCreditNoteModel.getCreateDate())) {
                    List<String> franchiseCodeList = new LinkedList<>();
                    if (sendCreditNoteModel.getFranchiseCode().equalsIgnoreCase("All")) {
                        franchiseCodeList = this.buildFranchiseCodeList();
                    } else {
                        franchiseCodeList.add(sendCreditNoteModel.getFranchiseCode());
                    }
                    creditNoteFilter.setFranchiseCodeList(franchiseCodeList);
                    creditNoteFilter.setCreateDate(DateUtils.convertStringToDate(sendCreditNoteModel.getCreateDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                    creditNoteFilter.setStatus(0);
                    creditNoteRequest.setCreditNoteFilter(creditNoteFilter);
                    CreditNoteResponse creditNoteResponse = creditNoteClient.freezeCreditNotes(creditNoteRequest);
                    this.setErrorCode(creditNoteResponse.getErrorCode());
                    this.setErrorMessage(creditNoteResponse.getErrorMessage());
                    if (sendCreditNoteModel.getFranchiseCode().equalsIgnoreCase("All")) {
                        this.prepareFranchiseAndDateList(0);
                    } else {
                        this.buildDateListByFranchiseCode(0);
                    }
                } else {
                    throw new CustomException("Franchise Code and Credit note's date cannot be empty.");
                }
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public String doFreezeCreditNotesByCode() {
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        try {
            if (creditNoteCode != null) {
                creditNoteFilter.setCreditCode(creditNoteCode);
                creditNoteFilter.setStatus(0);
                creditNoteRequest.setCreditNoteFilter(creditNoteFilter);

                CreditNoteVo creditNoteVo = creditNoteDao.selectCreditNoteByCreditCode(creditNoteCode);
                if (creditNoteVo != null) {
                    if (creditNoteVo.getStatus() == 1) {
                        this.setErrorMessage(this.getLocalizationText("This credit note has been frozen"));
                        this.setErrorCode(ErrorCode.ACTION_ERROR);
                        this.prepareFranchiseAndDateList(0);
                        return "success";
                    } else if (creditNoteVo.getStatus() != 0) {
                        this.setErrorMessage(this.getLocalizationText("This credit note is not unfrozen"));
                        this.setErrorCode(ErrorCode.ACTION_ERROR);
                        this.prepareFranchiseAndDateList(0);
                        return "success";
                    } else {
                        CreditNoteDetailDao creditNoteDetailDao = new CreditNoteDetailDao();
                        List<CreditNoteDetailVo> creditNoteDetailVos = creditNoteDetailDao.selectByCreditNoteId(creditNoteVo.getCreditNoteId());
                        for (CreditNoteDetailVo creditNoteDetailVo : creditNoteDetailVos) {
                            AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
                            AirbillAdjustmentVo adjustmentVo = adjustmentDao.selectById(creditNoteDetailVo.getAdjustmentid());
                            if (adjustmentVo.getStatus() != 4) {
                                this.setErrorCode(ErrorCode.ACTION_ERROR);
                                this.setErrorMessage(this.getLocalizationText("Some adjustments of this credit note are not approved"));
                                this.prepareFranchiseAndDateList(0);
                                return "success";
                            }
                        }
                    }
                } else {
                    this.prepareFranchiseAndDateList(0);
                    this.setErrorCode(ErrorCode.ACTION_ERROR);
                    this.setErrorMessage(this.getLocalizationText("Invalid credit note code."));
                    return "success";
                }

                CreditNoteResponse creditNoteResponse = creditNoteClient.freezeCreditNotesByCode(creditNoteRequest);
                this.setErrorCode(creditNoteResponse.getErrorCode());
                this.setErrorMessage(creditNoteResponse.getErrorMessage());
                this.prepareFranchiseAndDateList(0);
            }

        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public CreditNoteFilter getCreditNoteFilter() {
        return creditNoteFilter;
    }

    public void setCreditNoteFilter(CreditNoteFilter creditNoteFilter) {
        this.creditNoteFilter = creditNoteFilter;
    }

    public String changeFranchiseCodeForFreeze() {
        Integer statusType = 0;
        try {
            if (sendCreditNoteModel.getFranchiseCode().equalsIgnoreCase("All")) {
                this.prepareFranchiseAndDateList(statusType);
            } else {
                this.buildDateListByFranchiseCode(statusType);
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String changeFranchiseCodeForUnfreeze() {
        Integer statusType = 1;
        try {
            if (sendCreditNoteModel.getFranchiseCode().equalsIgnoreCase("All")) {
                this.prepareFranchiseAndDateList(statusType);
            } else {
                this.buildDateListByFranchiseCode(statusType);
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    protected void prepareFranchiseAndDateList(Integer statusType) throws Exception {
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        ManageAdjustmentClient manageAdjustmentClient = new ManageAdjustmentClient(this.getAddInfoMap());
        List<String> franchiseCodeList = new LinkedList<>();
        List<FranchiseInfoVo> franchiseInfoVos = manageAdjustmentClient.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        for (FranchiseInfoModel franchiseInfoModel : franchiseInfoModels) {
            franchiseCodeList.add(franchiseInfoModel.getCode());
        }
        this.setFranchiseList(franchiseInfoModels);

        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        creditNoteFilter.setFranchiseCodeList(franchiseCodeList);
        creditNoteFilter.setStatus(statusType);
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        creditNoteRequest.setCreditNoteFilter(creditNoteFilter);

        CreditNoteResponse creditNoteResponse = creditNoteClient.getFreezeDateList(creditNoteRequest);

        if (creditNoteResponse.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            this.setDateList(creditNoteResponse.getDateList());
        } else {
            this.setDateList(null);
        }
    }

    protected List<String> buildFranchiseCodeList() throws Exception {
        List<String> franchiseCodeList = new LinkedList<>();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        ManageAdjustmentClient manageAdjustmentClient = new ManageAdjustmentClient(this.getAddInfoMap());
        List<FranchiseInfoVo> franchiseInfoVos = manageAdjustmentClient.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        for (FranchiseInfoModel franchiseInfoModel : franchiseInfoModels) {
            franchiseCodeList.add(franchiseInfoModel.getCode());
        }

        return franchiseCodeList;
    }

    protected void buildDateListByFranchiseCode(Integer statusType) throws Exception {
        List<String> franchiseCodeList = new LinkedList<>();
        franchiseCodeList.add(this.getSendCreditNoteModel().getFranchiseCode());
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        creditNoteFilter.setFranchiseCodeList(franchiseCodeList);
        creditNoteFilter.setStatus(statusType);
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        creditNoteRequest.setCreditNoteFilter(creditNoteFilter);

        CreditNoteResponse creditNoteResponse = creditNoteClient.getFreezeDateList(creditNoteRequest);

        if (creditNoteResponse.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            this.setDateList(creditNoteResponse.getDateList());
        } else {
            this.setDateList(null);
        }
    }

    /**
     * prepareListCreditNoteByDate
     *
     * @param date
     * @return
     * @throws Exception
     */
    public List<CreditNoteVo> prepareListCreditNoteByDate(String date) throws Exception {
        CreditNoteClient client = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest request = new CreditNoteRequest();
        CreditNoteResponse response = new CreditNoteResponse();
        List<String> franchiseCodeList = this.prepareFranchiseCodeList();
        Date dateFomated = DateUtils.convertStringToDate(date, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        CreditNoteFilter filter = new CreditNoteFilter();
        filter.setFranchiseCodeList(franchiseCodeList);
        filter.setCreateDate(dateFomated);
        request.setCreditNoteFilter(filter);

        response = client.getCreditNotesList(request);
        List<CreditNoteVo> creditNoteVos = response.getCreditNoteVos();
        return creditNoteVos;
    }

    /**
     * Prepare ListDate
     *
     * @throws Exception
     */
    public void prepareListDateCreditNote() throws Exception {
        List<String> franchiseCodeList = this.prepareFranchiseCodeList();
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        creditNoteFilter.setFranchiseCodeList(franchiseCodeList);
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        creditNoteRequest.setCreditNoteFilter(creditNoteFilter);

        CreditNoteResponse creditNoteResponse = creditNoteClient.getFreezeDateList(creditNoteRequest);
        this.setListCreditNoteDate(creditNoteResponse.getDateList());
    }

    /**
     * prepareFranchiseCodeList
     *
     * @return
     * @throws Exception
     */
    public List<String> prepareFranchiseCodeList() throws Exception {
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        ManageAdjustmentClient manageAdjustmentClient = new ManageAdjustmentClient(this.getAddInfoMap());
        List<String> franchiseCodeList = new LinkedList<>();
        List<FranchiseInfoVo> franchiseInfoVos = manageAdjustmentClient.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        for (FranchiseInfoModel franchiseInfoModel : franchiseInfoModels) {
            franchiseCodeList.add(franchiseInfoModel.getCode());
        }
        return franchiseCodeList;
    }

    public List<String> getListCreditNoteDate() {
        return listCreditNoteDate;
    }

    public void setListCreditNoteDate(List<String> listCreditNoteDate) {
        this.listCreditNoteDate = listCreditNoteDate;
    }

    public List<FranchiseInfoModel> getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(List<FranchiseInfoModel> franchiseList) {
        this.franchiseList = franchiseList;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public SendCreditNoteModel getSendCreditNoteModel() {
        return sendCreditNoteModel;
    }

    public void setSendCreditNoteModel(SendCreditNoteModel sendCreditNoteModel) {
        this.sendCreditNoteModel = sendCreditNoteModel;
    }

    public String getCreditNoteCode() {
        return creditNoteCode;
    }

    public void setCreditNoteCode(String creditNoteCode) {
        this.creditNoteCode = creditNoteCode;
    }
}
