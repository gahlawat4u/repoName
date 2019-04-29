package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.note.NoteFilter;
import com.gms.xms.model.NoteModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.note.INoteService;
import com.gms.xms.persistence.service.note.NoteServiceImp;
import com.gms.xms.txndb.vo.NoteVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.List;

/**
 * Posted from ManageCustomerNotesController
 * <p>
 * Author DatTV Oct 1, 2015
 */
public class ManageCustomerNotesController extends JsonBaseController {

    private static final long serialVersionUID = -7242057538335952856L;

    // Filter properties.
    private String customerCode;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private String noteId;

    // Models.
    private List<String> pageSizes;
    private Paging<NoteModel> notes;
    private NoteModel note;

    public String show() {
        try {
            if (StringUtils.isBlank(customerCode)) {
                throw new CustomException("No customer code");
            }
            // Set default filter properties.
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            this.setOrderField("modify_date");
            this.setOrderType("1");
            this.setPage("1");
            this.setPageSizes(this.buildPageSizeList());
            loadNotes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            if (StringUtils.isBlank(customerCode)) {
                throw new CustomException("No customer code");
            }
            loadNotes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String load() {
        try {
            if (StringUtils.isBlank(customerCode)) {
                throw new CustomException("No customer code");
            }
            // New note
            if (StringUtils.isBlank(noteId)) {
                NoteModel noteModel = new NoteModel();
                noteModel.setAccountNo(customerCode);
                String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
                noteModel.setUserId(userId);
                String userType = customerCode.substring(customerCode.length() - 5);
                if ("00001".equalsIgnoreCase(userType)) {
                    noteModel.setNoteType("2"); // Franchise Note
                } else {
                    noteModel.setNoteType("1"); // Customer Note
                }
                noteModel.setCheck("0");
                noteModel.setCurrent("0");
                noteModel.setInvoiceCode("");
                noteModel.setPaymentId("0");
                this.setNote(noteModel);
            } else {
                // Load note info
                INoteService noteService = new NoteServiceImp();
                NoteVo noteVo = noteService.selectById(Long.valueOf(noteId));
                NoteModel noteModel = ModelUtils.createModelFromVo(noteVo, NoteModel.class);
                this.setNote(noteModel);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            if (!validNote()) {
                setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                INoteService noteService = new NoteServiceImp();
                NoteVo noteVo = ModelUtils.createVoFromModel(note, NoteVo.class);
                noteVo.setModifyDate(Calendar.getInstance().getTime());
                // Check if this is adding or updating note
                if (StringUtils.isBlank(note.getNoteId())) {
                    noteService.insert(this.getAddInfoMap(), noteVo);
                    log.info("Insert note with id = " + noteVo.getNoteId() + "from Customer -> Manage Customers -> Notes at " + Calendar.getInstance().getTime());
                } else {
                    noteService.update(this.getAddInfoMap(), noteVo);
                    log.info("Update note with id = " + noteVo.getNoteId() + "from Customer -> Manage Customers -> Notes at " + Calendar.getInstance().getTime());
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void delete() {
        try {
            if (StringUtils.isBlank(noteId)) {
                throw new CustomException("No note id");
            }
            INoteService noteService = new NoteServiceImp();
            noteService.delete(this.getAddInfoMap(), Long.valueOf(noteId));
            log.info("Delete note with id = " + noteId + " from Customer -> Manage Customers -> Notes at " + Calendar.getInstance().getTime());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    private boolean validNote() {
        if (note == null) {
            return false;
        }
        if (StringUtils.isBlank(note.getNote())) {
            addFieldError("note.note", "Please enter a note");
        }
        // If check was checked (1 mean true)
        if ("true".equalsIgnoreCase(note.getCheck())) {
            if (StringUtils.isBlank(note.getFollowUpDate())) {
                addFieldError("note.note", "Please enter Follow Up Date");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void loadNotes() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        INoteService noteService = new NoteServiceImp();
        NoteFilter filter = this.buildFilter();
        long recordCount = noteService.countByFilter(filter);
        // Set paging info
        Paging<NoteModel> paging = new Paging<NoteModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get data of page
        List<NoteVo> noteVos = noteService.selectByFilter(filter);
        List<NoteModel> noteModels = ModelUtils.createListModelFromVo(noteVos, NoteModel.class);
        paging.setRecords(noteModels);
        this.setNotes(paging);
    }

    private NoteFilter buildFilter() throws Exception {
        NoteFilter filter = new NoteFilter();
        filter.setCustomerCode(customerCode);
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "modify_date" : this.getOrderField());
        return filter;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Paging<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(Paging<NoteModel> notes) {
        this.notes = notes;
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

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public NoteModel getNote() {
        return note;
    }

    public void setNote(NoteModel note) {
        this.note = note;
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
