package com.gms.xms.weblib.controller.admin.note;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.note.NoteAndFollowUpFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.note.NoteAndFollowUpModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.NoteDao;
import com.gms.xms.txndb.vo.admin.note.NoteAndFollowUpVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from Apr 26, 2016 1:49:40 PM
 * <p>
 * Author dattrinh
 */
public class NoteAndFollowUpController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;
    private String catType;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private String invoiceCode;

    private Paging<NoteAndFollowUpModel> notes;

    public String show() {
        try {
            // Set default filter properties.
            this.setCatType("0");
            this.setPage("1");
            this.setOrderField("modify_date");
            this.setOrderType("1");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            preparePageSizes();
            doSearch();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            doSearch();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void doSearch() throws Exception {
        NoteAndFollowUpFilter filter = this.buildFilter();
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        NoteDao noteDao = new NoteDao();
        long recordCount = noteDao.countNotesAndFollowUp(filter);
        // Build paging object.
        Paging<NoteAndFollowUpModel> paging = new Paging<NoteAndFollowUpModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of notes.
        List<NoteAndFollowUpVo> noteAndFollowUpVos = noteDao.getNotesAndFollowUp(filter);
        List<NoteAndFollowUpModel> noteAndFollowUpModels = ModelUtils.createListModelFromVo(noteAndFollowUpVos, NoteAndFollowUpModel.class);
        paging.setRecords(noteAndFollowUpModels);
        // Set notes list.
        this.setNotes(paging);
    }

    protected NoteAndFollowUpFilter buildFilter() throws Exception {
        NoteAndFollowUpFilter filter = new NoteAndFollowUpFilter();
        // Set invoice code.
        if (StringUtils.isBlank(this.getInvoiceCode())) {
            throw new CustomException("Please input invoice code.");
        }
        filter.setInvoiceCode(this.getInvoiceCode());
        // Set category.
        if (StringUtils.isBlank(this.getCatType())) {
            throw new CustomException("Please choose a type of search.");
        } else {
            try {
                Integer cateType = Integer.valueOf(this.getCatType());
                if (cateType != 0 && cateType != 1) {
                    throw new CustomException("Unknown type of search.");
                }
            } catch (Exception e) {
                throw new CustomException("Invalid type of search.");
            }
        }
        filter.setCatType(this.getCatType());
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "invoice_code" : this.getOrderField());
        return filter;
    }

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
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

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Paging<NoteAndFollowUpModel> getNotes() {
        return notes;
    }

    public void setNotes(Paging<NoteAndFollowUpModel> notes) {
        this.notes = notes;
    }
}
