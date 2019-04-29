/**
 *
 */
package com.gms.xms.weblib.controller.admin.ratesheets;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.RateSheetDetailModel;
import com.gms.xms.model.RateSheetModel;
import com.gms.xms.model.RateSheetRowModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.ratesheet.IRateSheetService;
import com.gms.xms.persistence.service.ratesheet.RateSheetServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.weblib.controller.JsonBaseController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from RateSheetsDetailController
 *
 * @author HungNT - @since Oct 2, 2015
 */
public class RateSheetsDetailController extends JsonBaseController {
    private static final long serialVersionUID = -6668183177410410409L;

    private List<RateSheetColumnModel> columns;
    private List<RateSheetRowModel> rows;
    private Map<String, String> details;
    private Map<String, RateSheetDetailModel> detailTnTDom;
    private RateSheetModel rateSheet;

    private List<RateSheetDetailModel> detailsList;
    private String sheetId;
    

    public String show() {
        IRateSheetService service = new RateSheetServiceImp();
        Map<String, String> details = new HashMap<String, String>();
        Map<String, RateSheetDetailModel> detailsTnTDom = new HashMap<String, RateSheetDetailModel>();
        List<RateSheetColumnVo> rateSheetColumnVos = null;
        try {
            Long sheetId = 0l;
            try {
                sheetId = Long.parseLong(this.sheetId);
            } catch (Exception e) {
            }
            RateSheetVo rateSheetVo = service.getRateSheetBySheetId(sheetId);
            RateSheetModel rateSheet = ModelUtils.createModelFromVo(rateSheetVo, RateSheetModel.class);
            this.setRateSheet(rateSheet);

            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(rateSheetVo.getShipmentTypeId());
            Integer carrierId = shipmentTypeVo.getServiceId();
            
            if(carrierId == 400){
           	 rateSheetColumnVos = service.getUpsColumnsBySheetId(sheetId);
           }else{
           	 rateSheetColumnVos = service.getColumnsBySheetId(sheetId);	
           }
           
           List<RateSheetColumnModel> columns = ModelUtils.createListModelFromVo(rateSheetColumnVos, RateSheetColumnModel.class);
           this.columns = columns;

            
            List<RateSheetRowVo> rateSheetRowVos = new ArrayList<RateSheetRowVo>();
            if (carrierId.equals(1) || carrierId.equals(15) || carrierId.equals(40) || carrierId.equals(50) || carrierId.equals(51) || carrierId.equals(54) || carrierId.equals(57) || carrierId.equals(58) || carrierId.equals(400) ) {
                details.put("0-0", "Weight");
                rateSheetRowVos = service.getRowsBySheetId(sheetId);
            } else {
                details.put("0-0", "From");
                RateSheetFilter rateSheetFilter = new RateSheetFilter();
                rateSheetFilter.setSheetId(sheetId);
                rateSheetFilter.setServiceId(carrierId);
                rateSheetRowVos = service.getRowsBySheetIdWithZone(rateSheetFilter);
            }
            List<RateSheetRowModel> rows = ModelUtils.createListModelFromVo(rateSheetRowVos, RateSheetRowModel.class);
            this.rows = rows;

            List<RateSheetDetailVo> rateSheetDetailVos = service.getRateSheetDetailsBySheetId(sheetId);
            List<RateSheetDetailModel> rateSheetDetailModels = ModelUtils.createListModelFromVo(rateSheetDetailVos, RateSheetDetailModel.class);

            String key;
            for (RateSheetDetailModel rateSheetDetailModel : rateSheetDetailModels) {
                key = rateSheetDetailModel.getRowId() + "-" + rateSheetDetailModel.getColumnId();
                if (carrierId == 3 || carrierId == 72) {
                    detailsTnTDom.put(key, rateSheetDetailModel);
                }
                details.put(key, rateSheetDetailModel.getValue());
            }
            if (carrierId == 3 || carrierId == 72) {
                this.setDetailTnTDom(detailsTnTDom);
            }
            this.setDetails(details);
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }
        return "success";
    }

    public String save() {
        try {
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (this.detailsList != null && !this.detailsList.isEmpty()) {
                    IRateSheetService service = new RateSheetServiceImp();
                    List<RateSheetDetailVo> rateSheetDetailVos = ModelUtils.createListVoFromModel(this.detailsList, RateSheetDetailVo.class);
                    for (RateSheetDetailVo rateSheetDetailVo : rateSheetDetailVos) {
                        service.updateRateSheetDetail(this.getAddInfoMap(), rateSheetDetailVo);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }
        return "success";
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public List<RateSheetColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<RateSheetColumnModel> columns) {
        this.columns = columns;
    }

    public List<RateSheetRowModel> getRows() {
        return rows;
    }

    public void setRows(List<RateSheetRowModel> rows) {
        this.rows = rows;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public RateSheetModel getRateSheet() {
        return rateSheet;
    }

    public void setRateSheet(RateSheetModel rateSheet) {
        this.rateSheet = rateSheet;
    }

    public List<RateSheetDetailModel> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<RateSheetDetailModel> detailsList) {
        this.detailsList = detailsList;
    }

    public Map<String, RateSheetDetailModel> getDetailTnTDom() {
        return detailTnTDom;
    }

    public void setDetailTnTDom(Map<String, RateSheetDetailModel> detailTnTDom) {
        this.detailTnTDom = detailTnTDom;
    }

	

    
}
