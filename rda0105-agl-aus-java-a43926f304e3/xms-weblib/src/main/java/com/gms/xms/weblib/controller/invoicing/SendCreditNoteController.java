package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.invoicing.SendCreditNoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.CreditNoteFilter;
import com.gms.xms.txndb.vo.CreditNoteInfoVo;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.CreditNoteClient;
import com.gms.xms.workflow.client.SendCreditNotesClient;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import com.gms.xms.workflow.client.integration.request.CreditNoteRequest;
import com.gms.xms.workflow.client.integration.request.SendCreditNotesRequest;
import com.gms.xms.workflow.client.integration.response.CreditNoteResponse;
import com.gms.xms.workflow.client.integration.response.SendCreditNotesResponse;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Posted from SendCreditNoteController
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class SendCreditNoteController extends JsonBaseController {
    private static final long serialVersionUID = -7365382576588240222L;

    private List<FranchiseInfoModel> franchiseList;
    private List<String> dateList;
    private SendCreditNoteModel sendCreditNoteModel;

    public String showPage() {
        try {
            prepareFranchiseAndDateList();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String doSendCreditNotesByFranchiseCode() {
        SendCreditNotesClient sendCreditNotesClient = new SendCreditNotesClient(this.getAddInfoMap());
        SendCreditNotesRequest sendCreditNotesRequest = new SendCreditNotesRequest();
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        try {
            if (sendCreditNoteModel != null) {
                sendCreditNotesRequest.setTmpPath(this.getServerPath("tmp"));
                if (!StringUtils.isBlank(sendCreditNoteModel.getFranchiseCode()) && !StringUtils.isBlank(sendCreditNoteModel.getCreateDate())) {
                    List<String> franchiseCodeList = new LinkedList<>();
                    if (sendCreditNoteModel.getFranchiseCode().equalsIgnoreCase("All")) {
                        franchiseCodeList = this.buildFranchiseCodeList();
                    } else {
                        franchiseCodeList.add(sendCreditNoteModel.getFranchiseCode());
                    }
                    creditNoteFilter.setFranchiseCodeList(franchiseCodeList);
                    creditNoteFilter.setStatus(1);
                    creditNoteFilter.setCreateDate(DateUtils.convertStringToDate(sendCreditNoteModel.getCreateDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                    creditNoteRequest.setCreditNoteFilter(creditNoteFilter);
                    CreditNoteResponse creditNoteResponse = creditNoteClient.getCreditNotesList(creditNoteRequest);
                    List<CreditNoteVo> creditNoteVos = creditNoteResponse.getCreditNoteVos();
                    sendCreditNotesRequest.setCreditNoteVos(creditNoteVos);
                    sendCreditNotesRequest.setAddInfo(this.getAddInfoMap());
                    sendCreditNotesRequest.setGstSummary(creditNoteResponse.getCreditNotesGSTSummaryVos());
                    SendCreditNotesResponse sendCreditNotesResponse = sendCreditNotesClient.sendCreditNotes(sendCreditNotesRequest);
                    this.setErrorCode(sendCreditNotesResponse.getErrorCode());
                    this.setErrorMessage(sendCreditNotesResponse.getErrorMessage());
                    this.prepareFranchiseAndDateList();
                }
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String doSendCreditNotesByCreditCode() {
        SendCreditNotesClient sendCreditNotesClient = new SendCreditNotesClient(this.getAddInfoMap());
        SendCreditNotesRequest sendCreditNotesRequest = new SendCreditNotesRequest();
        try {
            if (sendCreditNoteModel != null) {

                if (StringUtils.isBlank(sendCreditNoteModel.getCreditNoteCode())) {
                    this.setErrorMessage(this.getLocalizationText("Please enter credit note code."));
                    this.setErrorCode(ErrorCode.ACTION_ERROR);
                    prepareFranchiseAndDateList();
                    return "success";
                }

                sendCreditNotesRequest.setTmpPath(this.getServerPath("tmp"));
                CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
                CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
                String creditCode = sendCreditNoteModel.getCreditNoteCode();
                creditNoteRequest.setCreditNoteCode(creditCode);
                CreditNoteResponse creditNoteResponse = creditNoteClient.getCreditNotesDetail(creditNoteRequest);

                CreditNoteInfoVo creditNoteInfoVo = creditNoteResponse.getCreditNoteInfo();

                if (creditNoteInfoVo == null || creditNoteInfoVo.getStatus() != 1) {
                    this.setErrorMessage("Invalid credit note code.");
                    this.setErrorCode(ErrorCode.ACTION_ERROR);
                    prepareFranchiseAndDateList();
                    return "success";
                } else if (creditNoteInfoVo != null && creditNoteInfoVo.getTotalCredited() == 0) {
                    this.setErrorMessage(this.getLocalizationText("This credit note's customer amount is zero."));
                    this.setErrorCode(ErrorCode.ACTION_ERROR);
                    prepareFranchiseAndDateList();
                    return "success";
                }

                CreditNoteVo creditNoteVo = creditNoteClient.getCreditNoteByCreditCode(creditCode);
                List<CreditNoteVo> creditNoteVos = new ArrayList<>();
                creditNoteVos.add(creditNoteVo);
                sendCreditNotesRequest.setCreditNoteVos(creditNoteVos);
                sendCreditNotesRequest.setAddInfo(this.getAddInfoMap());
                SendCreditNotesResponse sendCreditNotesResponse = sendCreditNotesClient.sendCreditNotes(sendCreditNotesRequest);
                prepareFranchiseAndDateList();
                this.setErrorCode(sendCreditNotesResponse.getErrorCode());
                this.setErrorMessage(sendCreditNotesResponse.getErrorMessage());
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String changeFranchiseCode() {
        try {
            if (sendCreditNoteModel.getFranchiseCode().equalsIgnoreCase("All")) {
                this.prepareFranchiseAndDateList();
            } else {
                this.buildDateListByFranchiseCode();
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    protected void prepareFranchiseAndDateList() throws Exception {
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
        creditNoteFilter.setStatus(1);
        SendCreditNotesClient client = new SendCreditNotesClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        creditNoteRequest.setCreditNoteFilter(creditNoteFilter);

        try {
            CreditNoteResponse creditNoteResponse = client.getDateList(creditNoteRequest);
            this.setDateList(creditNoteResponse.getDateList());
        } catch (Exception e) {
            this.setDateList(null);
            throw new Exception(e);
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

    protected void buildDateListByFranchiseCode() throws Exception {
        List<String> franchiseCodeList = new LinkedList<>();
        franchiseCodeList.add(this.getSendCreditNoteModel().getFranchiseCode());
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        creditNoteFilter.setFranchiseCodeList(franchiseCodeList);
        creditNoteFilter.setStatus(1);
        SendCreditNotesClient client = new SendCreditNotesClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        creditNoteRequest.setCreditNoteFilter(creditNoteFilter);

        CreditNoteResponse creditNoteResponse = client.getDateList(creditNoteRequest);
        this.setDateList(creditNoteResponse.getDateList());
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
}
