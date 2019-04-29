package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.ShipmentNoteFilterModel;
import com.gms.xms.model.ShipmentNoteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.ShipmentNoteFilter;
import com.gms.xms.txndb.vo.ShipmentNoteVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.service.webship.history.HistoryServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from ManageCustomerWebshipHistoryShipmentNoteController
 * <p>
 * Author TANDT 06-11-2015
 */
public class ManageCustomerWebshipHistoryShipmentNoteController extends JsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = 7008055707900010413L;
    private List<ShipmentNoteModel> shipmentNoteModels;
    private ShipmentNoteModel shipmentNoteModel;
    private ShipmentNoteFilterModel shipmentNoteFilterModel;
    private List<Integer> listPageSize;

    public String index() {
        prepareListPageSize();
        try {
            preareIndexData();
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String add() {
        try {
            if (!validNoteFilter()) {
                return "success";
            } else {
                if (!validShipmentModel()) {
                    return "success";
                } else {
                    IHistoryService iHistoryService = new HistoryServiceImp();
                    ShipmentNoteVo shipmentNoteVo = new ShipmentNoteVo();
                    shipmentNoteVo = ModelUtils.createVoFromModel(this.getShipmentNoteModel(), ShipmentNoteVo.class);
                    iHistoryService.insertShipmentNote(this.getAddInfoMap(), shipmentNoteVo);
                }
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    protected void preareIndexData() throws Exception {
        IHistoryService iHistoryService = new HistoryServiceImp();
        ShipmentNoteFilter shipmentNoteFilter = ModelUtils.createVoFromModel(shipmentNoteFilterModel, ShipmentNoteFilter.class);
        List<ShipmentNoteVo> shipmentNoteVos = new ArrayList<ShipmentNoteVo>();
        shipmentNoteVos = iHistoryService.selectNoteList(shipmentNoteFilter);
        this.setShipmentNoteModels(ModelUtils.createListModelFromVo(shipmentNoteVos, ShipmentNoteModel.class));

    }

    /**
     * prepareListPageSize
     */
    protected void prepareListPageSize() {
        List<Integer> listPageS = new ArrayList<Integer>();
        listPageS.add(5);
        listPageS.add(10);
        listPageS.add(25);
        listPageS.add(50);
        listPageS.add(100);
        this.setListPageSize(listPageS);
    }

    protected boolean validShipmentModel() {
        if (shipmentNoteModel != null) {
            if (StringUtils.isEmpty(shipmentNoteModel.getNote().trim())) {
                setErrorMessage("Please enter content for shipment note!");
                setErrorCode(ErrorCode.ACTION_ERROR);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    // Valid ShipmentNote
    protected boolean validNoteFilter() {
        if (shipmentNoteFilterModel != null) {
            if (StringUtils.isEmpty(shipmentNoteFilterModel.getShipmentId())) {
                addFieldError("shipmentNoteFilterModel.shipmentId", "Please choice a shipment!");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public List<ShipmentNoteModel> getShipmentNoteModels() {
        return shipmentNoteModels;
    }

    public void setShipmentNoteModels(List<ShipmentNoteModel> shipmentNoteModels) {
        this.shipmentNoteModels = shipmentNoteModels;
    }

    public ShipmentNoteFilterModel getShipmentNoteFilterModel() {
        return shipmentNoteFilterModel;
    }

    public void setShipmentNoteFilterModel(ShipmentNoteFilterModel shipmentNoteFilterModel) {
        this.shipmentNoteFilterModel = shipmentNoteFilterModel;
    }

    public List<Integer> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<Integer> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public ShipmentNoteModel getShipmentNoteModel() {
        return shipmentNoteModel;
    }

    public void setShipmentNoteModel(ShipmentNoteModel shipmentNoteModel) {
        this.shipmentNoteModel = shipmentNoteModel;
    }

}