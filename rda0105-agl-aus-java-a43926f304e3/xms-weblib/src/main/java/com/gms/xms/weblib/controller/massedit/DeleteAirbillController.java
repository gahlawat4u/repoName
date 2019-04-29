package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.admin.invoicing.searchairbill.SearchAirbillFilterModel;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.SearchAirbillDao;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;
import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillFilter;
import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillVo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Jun 29, 2016 11:49:41 AM
 * <p>
 * Author huynd
 */
public class DeleteAirbillController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private String searchAirbillFilterStr;
    private Long count;
    private Long countFail;

    public String show() {
        try {
            String listAirbillStr = "";
            if (this.getListAirbillMassEdit() != null) {
                listAirbillStr = GsonUtils.toGson(this.getListAirbillMassEdit());
            }
            this.setListAirbillStr(listAirbillStr);
            this.prepareSearchAirbilFilter();
            this.setSearchAirbillFilterStr(GsonUtils.toGson(this.getSearchAirbillFilter()));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String delete() {
        try {
            IMassEditService service = new MassEditServiceImp();
            Long count = 0L, countFail = 0L;
            if (!StringUtils.isBlank(this.getListAirbillStr())) {
                List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
                for (ListAirbillForMassEditModel list : listAirbill) {
                    if (list.getInvoiceStatus().equalsIgnoreCase("0")) {
                        try {
                            // Delete only airbill in unfrozen invoice
                            service.deleteAirbill(this.getAddInfoMap(), Long.valueOf(list.getShipmentId()), list.getAirbillNumber(), list.getInvoiceId());
                            count++;
                        } catch (Exception e) {
                            log.error(e);
                        }
                    } else {
                        countFail++;
                    }
                }
            } else {
                SearchAirbillDao dao = new SearchAirbillDao();
                SearchAirbillFilterModel filterModel = GsonUtils.fromGson(this.getSearchAirbillFilterStr(), SearchAirbillFilterModel.class);
                SearchAirbillFilter filter = ModelUtils.createVoFromModel(filterModel, SearchAirbillFilter.class);
                filter.setInvoiceStatus(0);
                int recordSize = AppConstants.APP_SETTINGS.getDefaultProcessRecordSize();
                List<SearchAirbillVo> airbillVos = new ArrayList<>();
                int byPassCount = 0;
                do {
                    recordSize = recordSize + byPassCount;
                    byPassCount = 0;
                    filter.setPage(1);
                    filter.setPageSize(recordSize);
                    filter.setInvoiceStatus(5);
                    airbillVos = dao.selectAirbillList(filter);
                    for (SearchAirbillVo searchAirbillVo : airbillVos) {
                        try {
                            if (searchAirbillVo.getInvoiceStatus() == 0) {
                                // Delete only airbill in unfrozen invoice
                                service.deleteAirbill(this.getAddInfoMap(), Long.valueOf(searchAirbillVo.getShipmentId()), searchAirbillVo.getAirbillNumber(), String.valueOf(searchAirbillVo.getInvoiceId()));
                                count++;
                            }
                        } catch (Exception e) {
                            log.error(e);
                            byPassCount = 1;
                        }
                    }
                } while (airbillVos != null && airbillVos.size() == recordSize);
            }
            this.setCount(count);
            this.setCountFail(countFail);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCountFail() {
        return countFail;
    }

    public void setCountFail(Long countFail) {
        this.countFail = countFail;
    }

    public String getSearchAirbillFilterStr() {
        return searchAirbillFilterStr;
    }

    public void setSearchAirbillFilterStr(String searchAirbillFilterStr) {
        this.searchAirbillFilterStr = searchAirbillFilterStr;
    }
}
