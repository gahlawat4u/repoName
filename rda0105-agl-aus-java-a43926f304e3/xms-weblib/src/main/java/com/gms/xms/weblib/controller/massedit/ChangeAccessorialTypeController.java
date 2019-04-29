package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.admin.invoicing.manageinvoice.editairbill.AccessorialInfoModel;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.model.massedit.MassAccessorialModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.massedit.MassEditDao;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.ShipmentBillingFilter;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.invoicing.ListAirbillForMassEditVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.txndb.vo.massedit.MassAccessorialInfoVo;
import com.gms.xms.txndb.vo.massedit.MassAccessorialVo;
import com.gms.xms.workflow.utils.RecalculateChargeImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Jul 18, 2016 2:40:42 PM
 * <p>
 * Author huynd
 */
public class ChangeAccessorialTypeController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private Long count;
    private List<MassAccessorialModel> accessorialList;
    private List<AccessorialInfoModel> accessorialInfoModels;
    private MassAccessorialInfoVo massAccessorialInfo;

    public String show() {
        try {
            determineAdminLevel();
            this.buildListAirbill();
            this.prepareData();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void prepareData() throws Exception {
        this.prepareAccessorialView();
        this.prepareAccessorialOptions();
        this.prepareAccessorialInfo();
    }

    private void prepareAccessorialInfo() throws Exception {
        List<ListAirbillForMassEditVo> listAirbill = ModelUtils.createListVoFromModel(this.getListAirbill(), ListAirbillForMassEditVo.class);
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
        ShipmentBillingFilter shipmentBillingFilter;
        MassAccessorialInfoVo massAccessorialInfo = new MassAccessorialInfoVo();
        List<AccessorialInfoVo> accessorialInfoVos = new ArrayList<AccessorialInfoVo>();
        for (ListAirbillForMassEditVo list : listAirbill) {
            shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(list.getAirbillNumber());
            shipmentBillingFilter.setShipmentId(list.getShipmentId());
            List<ShipmentBillingVo> shipmentBillingVos = shipmentBillingDao.selectSurchargesByFilter(shipmentBillingFilter);
            for (ShipmentBillingVo shipmentBillingVo : shipmentBillingVos) {
                AccessorialInfoVo accessorialInfoVo = new AccessorialInfoVo();
                accessorialInfoVo.setServiceId(Integer.valueOf(shipmentBillingVo.getCarrier().toString()));
                accessorialInfoVo.setDescriptionExt(shipmentBillingVo.getCarrier().toString() + "#" + shipmentBillingVo.getDescription());
                accessorialInfoVo.setDescription(shipmentBillingVo.getDescription());
                accessorialInfoVo.setCustomerCost(shipmentBillingVo.getCustomerCost());
                accessorialInfoVo.setFranchiseCost(shipmentBillingVo.getFranchiseCost());
                accessorialInfoVo.setCarrierCost(shipmentBillingVo.getCarrierCost());
                accessorialInfoVo.setRequireCalculate(shipmentBillingVo.getRequireCalculate());
                accessorialInfoVo.setCustomerTaxPercent(shipmentBillingVo.getCustomerTaxPercent());
                accessorialInfoVo.setCustomerTaxAmount(shipmentBillingVo.getCustomerTaxAmount());
                if (shipmentBillingVo.getCustomerTaxPercent() != 0) {
                    accessorialInfoVo.setIsGst(true);
                } else {
                    accessorialInfoVo.setIsGst(false);
                }
                accessorialInfoVo.setAccessorialCount(shipmentBillingVo.getAccessorialCount());
                accessorialInfoVo.setIsNew(0);
                accessorialInfoVos.add(accessorialInfoVo);
            }
        }
        massAccessorialInfo.setAccessorialInfo(accessorialInfoVos);
        this.setMassAccessorialInfo(massAccessorialInfo);
    }

    private void prepareAccessorialOptions() throws Exception {
        MassEditDao massEditDao = new MassEditDao();
        List<MassAccessorialVo> accessorialVos = massEditDao.selectAllAccessorials();
        this.setAccessorialList(ModelUtils.createListModelFromVo(accessorialVos, MassAccessorialModel.class));
    }

    private void prepareAccessorialView() throws Exception {
        List<AccessorialInfoVo> accessorialInfoVos = new ArrayList<AccessorialInfoVo>();
        for (int i = 0; i < 8; i++) {
            AccessorialInfoVo accessorialInfoN = new AccessorialInfoVo();
            accessorialInfoN.setIsGst(false);
            accessorialInfoN.setRequireCalculate(false);
            accessorialInfoN.setIsNew(1);
            accessorialInfoVos.add(accessorialInfoN);
        }
        this.setAccessorialInfoModels(ModelUtils.createListModelFromVo(accessorialInfoVos, AccessorialInfoModel.class));
    }

    public String save() {
        try {
            List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
            Long count = 0L;
            IMassEditService service = new MassEditServiceImp();
            List<AccessorialInfoVo> accessorialInfoVos = ModelUtils.createListVoFromModel(this.getAccessorialInfoModels(), AccessorialInfoVo.class);
            for (ListAirbillForMassEditModel list : listAirbill) {
                IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(this.getAddInfoMap());
                service.changeAccessorialType(this.getAddInfoMap(), Long.valueOf(list.getShipmentId()), list.getAirbillNumber(), accessorialInfoVos, iRecalculateCharge);
                count++;
            }
            this.setCount(count);
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

    public List<MassAccessorialModel> getAccessorialList() {
        return accessorialList;
    }

    public void setAccessorialList(List<MassAccessorialModel> accessorialList) {
        this.accessorialList = accessorialList;
    }

    public List<AccessorialInfoModel> getAccessorialInfoModels() {
        return accessorialInfoModels;
    }

    public void setAccessorialInfoModels(List<AccessorialInfoModel> accessorialInfoModels) {
        this.accessorialInfoModels = accessorialInfoModels;
    }

    public MassAccessorialInfoVo getMassAccessorialInfo() {
        return massAccessorialInfo;
    }

    public void setMassAccessorialInfo(MassAccessorialInfoVo massAccessorialInfo) {
        this.massAccessorialInfo = massAccessorialInfo;
    }

}
