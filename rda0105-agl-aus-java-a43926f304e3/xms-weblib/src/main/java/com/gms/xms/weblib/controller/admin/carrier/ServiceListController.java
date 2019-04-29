package com.gms.xms.weblib.controller.admin.carrier;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Posted from CarrierDetailController
 * <p>
 * Author HungNT Date Sep 23, 2015
 */
public class ServiceListController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    private Paging<ShipmentTypeModel> serviceList;
    private String carrierName;
    private List<String> listPageSize;
    private ShipmentTypeModel service;
    private String oldServiceName;
    private String oldEdiCode;

    private String serviceId;
    private String carrierId;

    // Filter props.
    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;

    public String show() {
        try {
            // Set default sorting props.
            setPage("1");
            setOrderType("0");
            setOrderField("shipment_type_id");
            this.listPageSize = this.buildPageSizeList();
            if (!StringUtils.isBlank(this.carrierId)) {
                buildServiceList();
                detectCarrierName();
            } else {
                return "redirect";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getData() {
        try {
            buildServiceList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String edit() {
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        ShipmentTypeFilter filter = new ShipmentTypeFilter();
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                if (this.service != null) {
                    if (!this.validateField()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                    ShipmentTypeVo shipmentTypeVo = ModelUtils.createVoFromModel(this.service, ShipmentTypeVo.class);
                    shipmentTypeVo.setShowStatus(shipmentTypeVo.getShowStatus() == null ? 0 : shipmentTypeVo.getShowStatus());
                    if (!this.oldServiceName.equals(shipmentTypeVo.getShipmentTypeName()) || !this.oldEdiCode.equals(shipmentTypeVo.getEdiDescription())) {
                        filter.setShipmentTypeName(shipmentTypeVo.getShipmentTypeName());
                        filter.setEdiDescription(shipmentTypeVo.getEdiDescription());
                        filter.setServiceId(Integer.parseInt(this.carrierId));
                        List<ShipmentTypeVo> shipmentTypeVoCheck = service.checkDuplicateShipmentType(filter);
                        if (shipmentTypeVoCheck != null && shipmentTypeVoCheck.size() > 0) {
                            if (shipmentTypeVoCheck.size() == 1) {
                                ShipmentTypeVo shipmentTypeVo2 = shipmentTypeVoCheck.get(0);
                                if (!shipmentTypeVo.getShipmentTypeId().equals(shipmentTypeVo2.getShipmentTypeId())) {
                                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                                    this.addActionMessage("Input Service Name or EDI Description already exists.");
                                    ShipmentTypeModel serviceModel = this.service;
                                    this.service = serviceModel;
                                    return "success";
                                }
                            } else {
                                this.setErrorCode(ErrorCode.FIELD_ERROR);
                                this.addActionMessage("Input Service Name or EDI Description already exists.");
                                ShipmentTypeModel serviceModel = this.service;
                                this.service = serviceModel;
                                return "success";
                            }
                        }
                    }
                    shipmentTypeVo = this.setDefaultCheckBoxValue(shipmentTypeVo);
                    service.updateShipmentType(this.getAddInfoMap(), shipmentTypeVo);
                    this.buildServiceList();
                    return "saved";
                } else {
                    Integer shipmentTypeId = 0;
                    try {
                        shipmentTypeId = Integer.parseInt(this.serviceId);
                    } catch (Exception e) {
                    }
                    ShipmentTypeVo shipmentTypeVo = service.getShipmentTypeByShipmentTypeId(shipmentTypeId);
                    ShipmentTypeModel shipmentTypeModel = ModelUtils.createModelFromVo(shipmentTypeVo, ShipmentTypeModel.class);
                    this.setOldEdiCode(shipmentTypeModel.getEdiDescription());
                    this.setOldServiceName(shipmentTypeModel.getShipmentTypeName());
                    this.service = shipmentTypeModel;
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String add() {
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        ShipmentTypeFilter filter = new ShipmentTypeFilter();
        try {
            if (this.service != null) {
                if (!this.validateField()) {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return "success";
                }
                ShipmentTypeVo shipmentTypeVo = ModelUtils.createVoFromModel(this.service, ShipmentTypeVo.class);
                filter.setShipmentTypeName(shipmentTypeVo.getShipmentTypeName());
                filter.setEdiDescription(shipmentTypeVo.getEdiDescription());
                filter.setServiceId(Integer.parseInt(this.carrierId));
                List<ShipmentTypeVo> shipmentTypeVoCheck = service.checkDuplicateShipmentType(filter);
                if (shipmentTypeVoCheck != null && shipmentTypeVoCheck.size() > 0) {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    this.addActionMessage("Input Service Name or EDI Description already exists.");
                    return "success";
                }
                shipmentTypeVo = this.setDefaultAddValue(shipmentTypeVo);
                service.addShipmentType(this.getAddInfoMap(), shipmentTypeVo);
                this.buildServiceList();
                return "saved";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String delete() {
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        try {
            if (StringUtils.isBlank(this.serviceId)) {
                throw new CustomException("Please select a service to delete.");
            } else {
                Integer shipmentTypeId = 0;
                try {
                    shipmentTypeId = Integer.parseInt(this.serviceId);
                } catch (Exception e) {
                }
                Integer totalUsedRecords = service.getTotalUsedRecords(shipmentTypeId);
                if (totalUsedRecords > 0) {
                    throw new CustomException("This service cannot be deleted because it has been used.");
                }
                service.deleteShipmentType(this.getAddInfoMap(), shipmentTypeId);
                this.buildServiceList();
                return "deleted";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void detectCarrierName() throws Exception {
        ServiceDao dao = new ServiceDao();
        ServiceVo serviceVo = dao.selectById(Integer.valueOf(this.getCarrierId()));
        this.setCarrierName(serviceVo.getServiceName());
    }

    protected void buildServiceList() throws Exception {
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        ShipmentTypeFilter filter = this.buildFilter();
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        long recordCount = service.getCountShipmentTypeByServiceId(filter.getServiceId());
        Paging<ShipmentTypeModel> paging = new Paging<ShipmentTypeModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        List<ShipmentTypeVo> shipmentTypeVos = service.getShipmentTypeListByServiceId(filter);
        List<ShipmentTypeModel> shipmentTypeModels = ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class);
        paging.setRecords(shipmentTypeModels);
        this.setServiceList(paging);
    }

    protected ShipmentTypeFilter buildFilter() throws Exception {
        ShipmentTypeFilter filter = new ShipmentTypeFilter();
        filter.setServiceId(Integer.parseInt(this.getCarrierId()));
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "shipment_type_id" : this.getOrderField());
        return filter;
    }

    protected ShipmentTypeVo setDefaultCheckBoxValue(ShipmentTypeVo shipmentTypeVo) {
        if (shipmentTypeVo.getShowStatus() == null) {
            shipmentTypeVo.setShowStatus((byte) 1);
        }
        if (shipmentTypeVo.getDocument() == null) {
            shipmentTypeVo.setDocument(false);
        }
        if (shipmentTypeVo.getDocumentInbound() == null) {
            shipmentTypeVo.setDocumentInbound(false);
        }
        if (shipmentTypeVo.getPackage() == null) {
            shipmentTypeVo.setPackage(false);
        }
        if (shipmentTypeVo.getPackageInbound() == null) {
            shipmentTypeVo.setPackageInbound(false);
        }
        if (shipmentTypeVo.getAllowCarrier() == null) {
            shipmentTypeVo.setAllowCarrier(false);
        }
        if (shipmentTypeVo.getAllowNonCarrier() == null) {
            shipmentTypeVo.setAllowNonCarrier(false);
        }
        if (shipmentTypeVo.getStartWithCarrierName() == null) {
            shipmentTypeVo.setStartWithCarrierName(false);
        }
        if (shipmentTypeVo.getShowStatus() == null) {
            shipmentTypeVo.setShowStatus((byte) 0);
        }
        return shipmentTypeVo;
    }

    protected ShipmentTypeVo setDefaultEditValue(ShipmentTypeVo shipmentTypeVo) {
        shipmentTypeVo = this.setDefaultCheckBoxValue(shipmentTypeVo);

        return shipmentTypeVo;
    }

    protected ShipmentTypeVo setDefaultAddValue(ShipmentTypeVo shipmentTypeVo) {
        shipmentTypeVo = this.setDefaultCheckBoxValue(shipmentTypeVo);
        if (StringUtils.isBlank(this.service.getBasicCharge())) {
            shipmentTypeVo.setBasicCharge(0d);
        }
        if (StringUtils.isBlank(this.service.getServiceCode())) {
            shipmentTypeVo.setServiceCode(" ");
        }
        if (StringUtils.isBlank(this.service.getPerKg())) {
            shipmentTypeVo.setPerKg(" ");
        }
        if (StringUtils.isBlank(this.service.getGlobalProductCodeDoc())) {
            shipmentTypeVo.setGlobalProductCodeDoc(" ");
        }
        if (StringUtils.isBlank(this.service.getGlobalProductCodeNonDoc())) {
            shipmentTypeVo.setGlobalProductCodeNonDoc(" ");
        }
        if (StringUtils.isBlank(this.service.getLocalProductCodeDoc())) {
            shipmentTypeVo.setLocalProductCodeDoc(" ");
        }
        if (StringUtils.isBlank(this.service.getLocalProductCodeNonDoc())) {
            shipmentTypeVo.setLocalProductCodeNonDoc(" ");
        }

        shipmentTypeVo.setPak(false);
        shipmentTypeVo.setPakInbound(false);
        shipmentTypeVo.setCarrierDocumentRate(0l);
        shipmentTypeVo.setCarrierDocumentInboundRate(0l);
        shipmentTypeVo.setCarrierPackageRate(0l);
        shipmentTypeVo.setCarrierPackagePerWeightRate(0l);
        shipmentTypeVo.setCarrierPackageInboundRate(0l);
        shipmentTypeVo.setCarrierPackageInboundPerWeightRate(0l);
        shipmentTypeVo.setCarrierPakRate(0l);
        shipmentTypeVo.setCarrierPakPerWeightRate(0l);
        shipmentTypeVo.setCarrierPakInboundPerWeightRate(0l);
        shipmentTypeVo.setCarrierPakInboundRate(0l);
        shipmentTypeVo.setModifiedDate(new Date());
        shipmentTypeVo.setNonCarrierDocumentInboundRate(0l);
        shipmentTypeVo.setNonCarrierDocumentRate(0l);
        shipmentTypeVo.setNonCarrierPackageInboundPerWeightRate(0l);
        shipmentTypeVo.setNonCarrierPackageInboundRate(0l);
        shipmentTypeVo.setNonCarrierPackagePerWeightRate(0l);
        shipmentTypeVo.setNonCarrierPackageRate(0l);
        shipmentTypeVo.setNonCarrierPakInboundPerWeightRate(0l);
        shipmentTypeVo.setNonCarrierPakInboundRate(0l);
        shipmentTypeVo.setNonCarrierPakPerWeightRate(0l);
        shipmentTypeVo.setNonCarrierPakRate(0l);
        shipmentTypeVo.setServiceId(Integer.parseInt(this.carrierId));
        shipmentTypeVo.setLocalizationId(0l);

        return shipmentTypeVo;
    }

    protected Boolean validateField() {
        if (this.service != null) {
            if (StringUtils.isBlank(this.service.getShipmentTypeName())) {
                this.addFieldError("service.shipmentTypeName", "Please enter service name.");
            }
            if (StringUtils.isBlank(this.service.getEdiDescription())) {
                this.addFieldError("service.ediDescription", "Please enter Edi Description.");
            }
            if (StringUtils.isBlank(this.service.getServicePriority())) {
                this.addFieldError("service.servicePriority", "Please enter Service Priority.");
            }
        }
        if (this.hasFieldErrors()) {
            return false;
        }
        return true;
    }

    public Paging<ShipmentTypeModel> getServiceList() {
        return serviceList;
    }

    public void setServiceList(Paging<ShipmentTypeModel> serviceList) {
        this.serviceList = serviceList;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
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

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public ShipmentTypeModel getService() {
        return service;
    }

    public void setService(ShipmentTypeModel service) {
        this.service = service;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOldServiceName() {
        return oldServiceName;
    }

    public void setOldServiceName(String oldServiceName) {
        this.oldServiceName = oldServiceName;
    }

    public String getOldEdiCode() {
        return oldEdiCode;
    }

    public void setOldEdiCode(String oldEdiCode) {
        this.oldEdiCode = oldEdiCode;
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