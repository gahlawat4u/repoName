package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.exception.DaoException;
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
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.txndb.vo.massedit.MassAccessorialInfoVo;
import com.gms.xms.txndb.vo.massedit.MassAccessorialVo;
import com.gms.xms.workflow.utils.RecalculateChargeImp;

import java.util.List;

/**
 * Posted from Aug 4, 2016 2:58:00 PM
 * <p>
 * Author huynd
 */
public class AddAccessorialChargeController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private Long carrierId;
    private Long count;
    private List<MassAccessorialModel> accessorialList;
    private AccessorialInfoModel accessorialInfoModels;
    private MassAccessorialInfoVo massAccessorialInfo;

    public String show() {
        try {
            determineAdminLevel();
            this.buildListAirbill();
            if (validateSameCarrier()) {
                this.prepareData();
            } else {
                throw new CustomException("Can only edit the airbill(s) that has same carrier with carrier of selected accessorial(s)");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean validateSameCarrier() throws DaoException {
        List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
        Long carrier = 0L;
        for (ListAirbillForMassEditModel list : listAirbill) {
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(list.getAirbillNumber());
            shipmentBillingFilter.setShipmentId(Long.valueOf(list.getShipmentId()));
            ShipmentBillingVo shipmentBillingVo = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
            if (carrier == 0) {
                carrier = shipmentBillingVo.getCarrier();
            }
            if (carrier != shipmentBillingVo.getCarrier()) {
                return false;
            }
        }
        this.setCarrierId(carrier);
        return true;
    }

    private void prepareData() throws Exception {
        this.prepareAccessorialView();
        this.prepareAccessorialOptions();
    }

    private void prepareAccessorialOptions() throws Exception {
        MassEditDao massEditDao = new MassEditDao();
        List<MassAccessorialVo> accessorialVos = massEditDao.selectAccessorialsByCarrier(this.getCarrierId());
        this.setAccessorialList(ModelUtils.createListModelFromVo(accessorialVos, MassAccessorialModel.class));
    }

    private void prepareAccessorialView() throws Exception {
        AccessorialInfoVo accessorialInfoN = new AccessorialInfoVo();
        accessorialInfoN.setIsGst(false);
        accessorialInfoN.setRequireCalculate(false);
        this.setAccessorialInfoModels(ModelUtils.createModelFromVo(accessorialInfoN, AccessorialInfoModel.class));
    }

    public String save() {
        try {
            determineAdminLevel();
            List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
            Long count = 0L;
            IMassEditService service = new MassEditServiceImp();
            AccessorialInfoVo accessorialInfoVo = ModelUtils.createVoFromModel(this.getAccessorialInfoModels(), AccessorialInfoVo.class);
            for (ListAirbillForMassEditModel list : listAirbill) {
                IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(this.getAddInfoMap());
                service.addAccessorialCharge(this.getAddInfoMap(), Long.valueOf(list.getShipmentId()), list.getAirbillNumber(), accessorialInfoVo, this.getAdminLevel(), iRecalculateCharge);
                count++;
            }
            this.setCount(count);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
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

    public AccessorialInfoModel getAccessorialInfoModels() {
        return accessorialInfoModels;
    }

    public void setAccessorialInfoModels(AccessorialInfoModel accessorialInfoModels) {
        this.accessorialInfoModels = accessorialInfoModels;
    }

    public MassAccessorialInfoVo getMassAccessorialInfo() {
        return massAccessorialInfo;
    }

    public void setMassAccessorialInfo(MassAccessorialInfoVo massAccessorialInfo) {
        this.massAccessorialInfo = massAccessorialInfo;
    }

}
