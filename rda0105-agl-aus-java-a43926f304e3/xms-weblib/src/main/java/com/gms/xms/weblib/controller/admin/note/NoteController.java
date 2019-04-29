package com.gms.xms.weblib.controller.admin.note;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.NoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.NoteDao;
import com.gms.xms.txndb.vo.NoteVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Posted from Apr 25, 2016 5:07:01 PM
 * <p>
 * Author dattrinh
 */
public class NoteController extends JsonBaseController {

    private static final long serialVersionUID = 1L;

    private String invoiceCode;
    private NoteModel note;

    public String add() {
        try {
            // Valid invoice code.
            if (StringUtils.isBlank(this.getInvoiceCode())) {
                throw new CustomException("Please select an invoice for adding a note.");
            }
            if (this.getInvoiceCode().length() < 12) {
                throw new CustomException("Invalid invoice code.");
            }
            // Prepare note model.
            prepareAddingNote2Invoice();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            // Valid note model.
            if (this.getNote() == null) {
                throw new CustomException("No note for saving.");
            }
            if (!isValidNote(this.getNote())) {
                setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                // Get and set some other info.
                NoteModel noteModel = this.getNote();
                noteModel.setUserId((String) this.getSession("SESS_XMS_ADMIN_ID"));
                Byte noteType = 0;
                if (noteModel.getAccountNo().length() > 5 && noteModel.getAccountNo().endsWith("00001")) {
                    noteType = 2;
                } else {
                    noteType = 1;
                }
                // Convert 2 vo.
                NoteVo noteVo = ModelUtils.createVoFromModel(noteModel, NoteVo.class);
                noteVo.setNoteType(noteType);
                noteVo.setModifyDate(new Date());
                noteVo.setCurrent((byte) 0);
                noteVo.setPaymentId(0L);
                // Insert into the database.
                NoteDao dao = new NoteDao();
                dao.insert(this.getAddInfoMap(), noteVo);
                log.info("Insert note: " + noteVo);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareAddingNote2Invoice() {
        NoteModel noteModel = new NoteModel();
        String customerCode = this.getInvoiceCode().substring(0, 8);
        noteModel.setAccountNo(customerCode);
        noteModel.setInvoiceCode(this.getInvoiceCode());
        noteModel.setNote("");
        this.setNote(noteModel);
    }

    protected boolean isValidNote(NoteModel note) {
        if (StringUtils.isBlank(note.getAccountNo())) {
            addFieldError("note.accountNo", "Customer # cannot be blank.");
        }
        if (StringUtils.isBlank(note.getInvoiceCode())) {
            addFieldError("note.invoiceCode", "Invoice Code cannot be blank.");
        }
        if (StringUtils.isBlank(note.getNote())) {
            addFieldError("note.note", "Note cannot be blank.");
        }
        if ("true".equalsIgnoreCase(note.getCheck())) {
            if (StringUtils.isBlank(note.getFollowUpDate())) {
                addFieldError("note.followUpDate", "Follow Up Date cannot be blank.");
            } else {
                Date followUpDate = null;
                try {
                    note.setFollowUpDate(note.getFollowUpDate() + " 00:00:00");
                    followUpDate = DateUtils.convertStringToDate(note.getFollowUpDate(), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null);
                } catch (Exception e) {
                    followUpDate = null;
                }
                if (followUpDate == null) {
                    addFieldError("note.followUpDate", "Invalid Follow Up Date.");
                }
            }
        }
        return !hasFieldErrors();
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public NoteModel getNote() {
        return note;
    }

    public void setNote(NoteModel note) {
        this.note = note;
    }
}
