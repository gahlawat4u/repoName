package com.gms.xms.weblib.controller.adjustment;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from AdjustmentNoteController
 * <p>
 * Author DatTV Date May 22, 2015 10:25:01 AM
 */
public class AdjustmentNoteController extends JsonBaseController {
    private static final long serialVersionUID = -5584617730887223223L;
    private String adjustmentId;
    private String note;
    private String noteType;

    public String show() {
        Long id = null;
        Integer type = null;
        try {
            id = Long.valueOf(adjustmentId);
            type = Integer.valueOf(noteType);
            ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
            AirbillAdjustmentVo adjustmentVo = client.getAdjustmentById(id);
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
            id = Long.valueOf(adjustmentId);
            type = Integer.valueOf(noteType);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("Invalid number."));
            log.error(e);
        }
        try {
            ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
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
        if (StringUtils.isBlank(adjustmentId)) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("No adjustment id."));
            return;
        }
        Long id = null;
        try {
            id = Long.valueOf(adjustmentId);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
            return;
        }
        try {
            ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
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
        if (StringUtils.isBlank(adjustmentId)) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("No adjustment id."));
            return;
        }
        Long id = null;
        try {
            id = Long.valueOf(adjustmentId);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
            return;
        }
        try {
            ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
            client.resubmit(id);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
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