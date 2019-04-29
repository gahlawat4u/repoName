/**
 *
 */
package com.gms.xms.weblib.controller.admin.ratesheets;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.admin.ratesheets.SetListRatesSearchFilter;
import com.gms.xms.model.admin.ratesheets.SetListRatesModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.setlistrates.ISetListRatesService;
import com.gms.xms.persistence.service.admin.setlistrates.SetListRatesServiceImp;
import com.gms.xms.persistence.service.ratesheet.IRateSheetService;
import com.gms.xms.persistence.service.ratesheet.RateSheetServiceImp;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.RateSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.SetListRatesVo;
import com.gms.xms.weblib.controller.JsonBaseController;

import java.util.List;

/**
 * Posted from SetListRatesController
 *
 * @author HungNT - @since Oct 8, 2015
 */
public class SetListRatesController extends JsonBaseController {
    private static final long serialVersionUID = -1738894422740392493L;

    private List<SetListRatesModel> records;
    private String sheetName;

    private String sheetId;
    private String shipmentTypeId;
    private String content;
    private String bound;
    private String isPerWeight;

    private SetListRatesSearchFilter searchFilter;

    public String show() {
        try {
            ISetListRatesService service = new SetListRatesServiceImp();

            List<SetListRatesVo> costBasisTableVos = service.getAllTableRecords();
            RateSheetFilter filter = new RateSheetFilter();
            for (SetListRatesVo setCostBasisTableVo : costBasisTableVos) {
                Integer content = 0;
                if (setCostBasisTableVo.getContent() != null) {
                    content = setCostBasisTableVo.getContent();
                }

                Integer bound = 0;
                if (setCostBasisTableVo.getBound() != null) {
                    bound = setCostBasisTableVo.getBound();
                }

                Byte isPerWeight = 0;
                try {
                    isPerWeight = Byte.parseByte(String.valueOf(setCostBasisTableVo.getIsPerWeight()));
                } catch (Exception e) {
                }
                filter.setBound(bound);
                filter.setContent(content);
                filter.setShipmentTypeId(setCostBasisTableVo.getShipmentTypeId());
                filter.setIsPerWeight(isPerWeight);
                List<RateSheetVo> rateSheets = service.getRateSheets(filter);
                setCostBasisTableVo.setRateSheets(rateSheets);
            }
            List<SetListRatesModel> costBasisTableModels = ModelUtils.createListModelFromVo(costBasisTableVos, SetListRatesModel.class);
            this.records = costBasisTableModels;
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String search() {
        try {
            ISetListRatesService service = new SetListRatesServiceImp();
            SetListRatesSearchFilter searchFilter = this.getSearchFilter() != null ? this.getSearchFilter() : new SetListRatesSearchFilter();
            List<SetListRatesVo> basisTableVos = service.getTableRecordsByFilter(searchFilter);
            RateSheetFilter filter = new RateSheetFilter();
            for (SetListRatesVo setCostBasisTableVo : basisTableVos) {
                Integer content = 0;
                if (setCostBasisTableVo.getContent() != null) {
                    content = setCostBasisTableVo.getContent();
                }

                Integer bound = 0;
                if (setCostBasisTableVo.getBound() != null) {
                    bound = setCostBasisTableVo.getBound();
                }

                Byte isPerWeight = 0;
                try {
                    isPerWeight = Byte.parseByte(String.valueOf(setCostBasisTableVo.getIsPerWeight()));
                } catch (Exception e) {
                }
                filter.setBound(bound);
                filter.setContent(content);
                filter.setShipmentTypeId(setCostBasisTableVo.getShipmentTypeId());
                filter.setIsPerWeight(isPerWeight);
                List<RateSheetVo> rateSheets = service.getRateSheets(filter);
                setCostBasisTableVo.setRateSheets(rateSheets);
            }
            List<SetListRatesModel> costBasisTableModels = ModelUtils.createListModelFromVo(basisTableVos, SetListRatesModel.class);
            this.records = costBasisTableModels;
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String set() {
        if (request.getMethod().equalsIgnoreCase("post")) {
            ISetListRatesService service = new SetListRatesServiceImp();
            try {
                Integer shipmentTypeId = 0;
                Integer content = 0;
                Integer bound = 0;
                Byte isPerWeight = 0;
                Long sheetId = 0l;
                try {
                    shipmentTypeId = Integer.parseInt(this.shipmentTypeId);
                    content = Integer.parseInt(this.content);
                    bound = Integer.parseInt(this.bound);
                    isPerWeight = Byte.parseByte(this.isPerWeight);
                    sheetId = Long.parseLong(this.sheetId);
                } catch (Exception e) {
                }
                RateSheetVo rateSheetVo = new RateSheetVo();
                rateSheetVo.setSheetId(sheetId);
                rateSheetVo.setShipmentTypeId(shipmentTypeId);
                rateSheetVo.setBound(bound);
                rateSheetVo.setContent(content);
                rateSheetVo.setIsPerWeight(isPerWeight);
                service.setCostBasis(this.getAddInfoMap(), rateSheetVo);

                IRateSheetService rateSheetService = new RateSheetServiceImp();
                rateSheetVo = rateSheetService.getRateSheetBySheetId(sheetId);
                if (rateSheetVo != null) {
                    this.sheetName = rateSheetVo.getSheetName();
                }
                return "saved";
            } catch (Exception e) {
                log.error(e);
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage(e.getMessage());
            }
        }
        return "success";
    }

    public List<SetListRatesModel> getRecords() {
        return records;
    }

    public void setRecords(List<SetListRatesModel> records) {
        this.records = records;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getIsPerWeight() {
        return isPerWeight;
    }

    public void setIsPerWeight(String isPerWeight) {
        this.isPerWeight = isPerWeight;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public SetListRatesSearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SetListRatesSearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }
}