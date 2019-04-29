package com.gms.xms.weblib.controller.adjustmentrequest;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.adjustmentrequest.ManageAdjustmentRequestClient;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from AdjustmentRequestNoteController
 * </p>
 *
 * @author hungnt - Nov 12, 2015
 */
public class AdjustmentRequestNoteController extends JsonBaseController {
    private static final long serialVersionUID = 8069960178674508009L;
    private String adjustmentRequestId;
    private String note;
    private String noteType;

    public String show() {
        Long id = null;
        Integer type = null;
        try {
            id = Long.valueOf(this.getAdjustmentRequestId());
            type = Integer.valueOf(noteType);
            ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
            AirbillAdjustmentRequestVo adjustmentVo = client.getAdjustmentById(id);
            switch (type) {
                case 1: // Note to delete (reason for deleting)
                    this.setNote(adjustmentVo.getReasonForDeleting());
                    break;
                case 2: // Franchise Comments to Fsc
                    this.setNote(adjustmentVo.getFranchiseCommentsToFsc());
                    break;
                case 3: // Fsc Credit Notes
                    this.setNote(adjustmentVo.getFscCreditNote());
                    break;
            }
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        switch (type) {
            case 1: // Delete the adjustment with reason
                return "delete";
            case 2: // Franchise Comments to FSC
                return "fsc_comment";
            case 3: // Fsc Credit Notes
                return "fsc_credit";
            default:
                return "delete";
        }
    }

    public void makeNote() {
        Long id = null;
        Integer type = null;
        try {
            id = Long.valueOf(this.getAdjustmentRequestId());
            type = Integer.valueOf(noteType);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("Invalid number."));
            log.error(e);
        }
        try {
            ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
            Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
            Double userLevel = client.getUserLevel(userLevelId);
            client.updateNoteForAdjustment(id, note, type, userLevel.intValue());
        } catch (Exception e) {
            log.error(e);
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
        }
    }

    public void updateSubStatus() {
        if (StringUtils.isBlank(this.getAdjustmentRequestId())) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("No adjustment id."));
            return;
        }
        Long id = null;
        try {
            id = Long.valueOf(this.getAdjustmentRequestId());
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
            return;
        }
        try {
            ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
            Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
            Double userLevel = client.getUserLevel(userLevelId);
            client.updateSubStatus(id, userLevel.intValue());
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    public void resubmit() {
        if (StringUtils.isBlank(this.getAdjustmentRequestId())) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("No adjustment id."));
            return;
        }
        Long id = null;
        try {
            id = Long.valueOf(this.getAdjustmentRequestId());
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
            return;
        }
        try {
            ManageAdjustmentRequestClient client = new ManageAdjustmentRequestClient(this.getAddInfoMap());
            client.resubmit(id);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    public String getAdjustmentRequestId() {
        return adjustmentRequestId;
    }

    public void setAdjustmentRequestId(String adjustmentRequestId) {
        this.adjustmentRequestId = adjustmentRequestId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
}