package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.model.massedit.ServiceLevelModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.massedit.MassEditDao;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.ShipmentBillingFilter;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.massedit.ServiceLevelVo;
import com.gms.xms.workflow.utils.RecalculateChargeImp;

import java.util.List;

/**
 * Posted from Jul 15, 2016 2:35:35 PM
 * <p>
 * Author huynd
 */
public class ChangeServiceTypeController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private Long count;
    private Long carrierId;
    private List<ServiceLevelModel> serviceLevelList;
    private String serviceLevel;
    private Boolean checkRecalcCustomerCost;

    public String show() {
        try {
            this.buildListAirbill();
            if (isSameCarrier()) {
                this.getServiceLevelByCarrier(this.getCarrierId());
            } else {
                throw new CustomException("Can only edit the airbill(s) that has same carrier with carrier of selected service type.");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private Boolean isSameCarrier() throws DaoException, CustomException {
        List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
        ShipmentBillingVo shipmentBillingVo;
        ShipmentBillingFilter shipmentBillingFilter;
        Long carrierId = 0L;
        Integer count = 0;
        for (ListAirbillForMassEditModel list : listAirbill) {
            shipmentBillingVo = new ShipmentBillingVo();
            shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(list.getAirbillNumber());
            shipmentBillingFilter.setShipmentId(Long.valueOf(list.getShipmentId()));
            shipmentBillingVo = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
            if (carrierId != shipmentBillingVo.getCarrier()) {
                carrierId = shipmentBillingVo.getCarrier();
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        if (carrierId == 0) {
            throw new CustomException("Please select Airbill");
        }
        this.setCarrierId(carrierId);
        return true;
    }

    private void getServiceLevelByCarrier(Long carrier) throws Exception {
        MassEditDao massEditDao = new MassEditDao();
        List<ServiceLevelVo> serviceLevelVos = massEditDao.getServiceLevelByCarrier(carrier);
        List<ServiceLevelModel> serviceLevelModels = ModelUtils.createListModelFromVo(serviceLevelVos, ServiceLevelModel.class);
        this.setServiceLevelList(serviceLevelModels);
    }

    public String save() {
        try {
            List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
            Long count = 0L;
            IMassEditService service = new MassEditServiceImp();
            Boolean checkRecalcCustomerCost = (this.getCheckRecalcCustomerCost() != null) ? true : false;
            for (ListAirbillForMassEditModel list : listAirbill) {
                IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(this.getAddInfoMap());
                service.changeServiceType(this.getAddInfoMap(), Long.parseLong(list.getShipmentId()), list.getAirbillNumber(), this.getServiceLevel(), checkRecalcCustomerCost, iRecalculateCharge);
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

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public List<ServiceLevelModel> getServiceLevelList() {
        return serviceLevelList;
    }

    public void setServiceLevelList(List<ServiceLevelModel> serviceLevelList) {
        this.serviceLevelList = serviceLevelList;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Boolean getCheckRecalcCustomerCost() {
        return checkRecalcCustomerCost;
    }

    public void setCheckRecalcCustomerCost(Boolean checkRecalcCustomerCost) {
        this.checkRecalcCustomerCost = checkRecalcCustomerCost;
    }
}
