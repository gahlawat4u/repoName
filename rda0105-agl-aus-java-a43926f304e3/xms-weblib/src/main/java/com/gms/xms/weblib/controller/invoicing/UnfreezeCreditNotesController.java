package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.invoicing.SendCreditNoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CreditNoteDao;
import com.gms.xms.txndb.vo.CreditNoteFilter;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.CreditNoteClient;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import com.gms.xms.workflow.client.integration.request.CreditNoteRequest;
import com.gms.xms.workflow.client.integration.response.CreditNoteResponse;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Posted from CreditNotesController
 * <p>
 * Author TanDT Date May 20, 2015
 */
public class UnfreezeCreditNotesController extends JsonBaseController {
    private static final long serialVersionUID = 4923595193899566440L;

    private List<String> listCreditNoteDate;
    private CreditNoteFilter creditNoteFilter;

    private List<FranchiseInfoModel> franchiseList;
    private List<String> dateList;
    private SendCreditNoteModel sendCreditNoteModel;
    private String creditNoteCode;

    public String showUnfreezePage() {
        try {
            prepareFranchiseAndDateList(1);
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String doUnfreezeCreditNotes() {
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
                    creditNoteFilter.setStatus(1);
                    creditNoteRequest.setCreditNoteFilter(creditNoteFilter);
                    CreditNoteResponse creditNoteResponse = creditNoteClient.unfreezeCreditNotes(creditNoteRequest);
                    this.setErrorCode(creditNoteResponse.getErrorCode());
                    this.setErrorMessage(creditNoteResponse.getErrorMessage());
                    if (sendCreditNoteModel.getFranchiseCode().equalsIgnoreCase("All")) {
                        this.prepareFranchiseAndDateList(1);
                    } else {
                        this.buildDateListByFranchiseCode(1);
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

    public String doUnfreezeCreditNotesByCode() {
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        CreditNoteDao creditNoteDao = new CreditNoteDao();
        try {
            if (creditNoteCode != null) {
                creditNoteFilter.setCreditCode(creditNoteCode);
                creditNoteFilter.setStatus(1);
                creditNoteRequest.setCreditNoteFilter(creditNoteFilter);
                CreditNoteVo creditNoteVo = creditNoteDao.selectCreditNoteByCreditCode(creditNoteCode);
                if (creditNoteVo != null) {
                    if (creditNoteVo.getStatus() != 1) {
                        this.setErrorMessage(this.getLocalizationText("This credit note has not been frozen"));
                        this.setErrorCode(ErrorCode.ACTION_ERROR);
                        this.prepareFranchiseAndDateList(1);
                        return "success";
                    }
                } else {
                    this.setErrorMessage(this.getLocalizationText("Invalid credit note code."));
                    this.setErrorCode(ErrorCode.ACTION_ERROR);
                    this.prepareFranchiseAndDateList(1);
                    return "success";
                }
                CreditNoteResponse creditNoteResponse = creditNoteClient.unfreezeCreditNotesByCode(creditNoteRequest);
                this.setErrorCode(creditNoteResponse.getErrorCode());
                this.setErrorMessage(creditNoteResponse.getErrorMessage());
            }
            this.prepareFranchiseAndDateList(1);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public CreditNoteFilter getCreditNoteFilter() {
        return creditNoteFilter;
    }

    public void setCreditNoteFilter(CreditNoteFilter creditNoteFilter) {
        this.creditNoteFilter = creditNoteFilter;
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
