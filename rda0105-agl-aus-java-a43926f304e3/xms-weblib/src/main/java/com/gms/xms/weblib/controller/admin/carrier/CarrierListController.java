package com.gms.xms.weblib.controller.admin.carrier;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.Paging;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ServiceFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from CarrierListController
 * <p>
 * Author HungNT Date Sep 21, 2015
 */
public class CarrierListController extends JsonBaseController {
    private static final long serialVersionUID = -154372669651127092L;

    private Paging<ServiceModel> carrierList;
    private List<String> listPageSize;
    private ServiceModel carrier;

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
            setOrderField("service_id");
            setOrderType("0");
            this.listPageSize = this.buildPageSizeList();
            buildCarrierList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getData() {
        try {
            buildCarrierList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String edit() {
        try {
            IServiceService service = new ServiceServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (this.carrier != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                    if (StringUtils.isBlank(this.carrier.getInactive())) {
                        this.carrier.setInactive("1");
                    }
                    if (StringUtils.isBlank(this.carrier.getNonCentralized())) {
                        this.carrier.setNonCentralized("0");
                    }
                    ServiceVo serviceVo = ModelUtils.createVoFromModel(this.carrier, ServiceVo.class);
                    service.updateService(this.getAddInfoMap(), serviceVo);
                    this.buildCarrierList();
                    return "saved";
                } else {
                    if (!StringUtils.isBlank(this.carrierId)) {
                        ServiceVo serviceVo = service.selectById(Integer.parseInt(this.carrierId));
                        ServiceModel serviceModel = ModelUtils.createModelFromVo(serviceVo, ServiceModel.class);
                        this.carrier = serviceModel;
                    } else {
                        throw new CustomException("Please select a carrier to edit.");
                    }
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String add() {
        try {
            IServiceService service = new ServiceServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (this.carrier != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                    ServiceVo serviceVo = ModelUtils.createVoFromModel(this.carrier, ServiceVo.class);

                    // Check if the accessorial detail has created on that date.
                    ServiceVo serviceVoCheck = service.getServiceByName(this.carrier.getServiceName());
                    if (serviceVoCheck != null) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        this.addActionError("The service with the name given is existed.");
                        return "success";
                    }
                    if (StringUtils.isBlank(this.carrier.getInactive())) {
                        serviceVo.setInactive((byte) 1);
                    }
                    if (StringUtils.isBlank(this.carrier.getNonCentralized())) {
                        serviceVo.setNonCentralized((byte) 0);
                    }
                    serviceVo.setFtpServer("");
                    serviceVo.setFtpUserName("");
                    serviceVo.setFtpUserPassword("");
                    serviceVo.setServerFolder("");
                    serviceVo.setFileNamePrefix("");
                    serviceVo.setFileCountryCode("");
                    serviceVo.setAllowChangeName((byte) 1);
                    serviceVo.setIntegrated((byte) 0);
                    service.insertService(this.getAddInfoMap(), serviceVo);
                    this.buildCarrierList();
                    return "saved";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String delete() {
        try {
            IServiceService service = new ServiceServiceImp();
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeFilter filter = new ShipmentTypeFilter();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (!StringUtils.isBlank(this.carrierId)) {
                    Integer serviceId = Integer.parseInt(this.carrierId);
                    filter.setServiceId(serviceId);
                    List<ShipmentTypeVo> shipmentTypeVos = shipmentTypeService.getShipmentTypeListByServiceId(filter);
                    if (shipmentTypeVos != null && shipmentTypeVos.size() > 0) {
                        throw new CustomException("This carrier cannot be deleted because it has been used.");
                    }
                    service.deleteService(this.getAddInfoMap(), serviceId);
                    this.buildCarrierList();
                    return "deleted";
                } else {
                    throw new CustomException("Please select a carrier to delete.");
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void buildCarrierList() throws Exception {
        IServiceService service = new ServiceServiceImp();
        ServiceFilter filter = this.buildFilter();
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        long recordCount = service.getAllServiceCount();
        Paging<ServiceModel> paging = new Paging<ServiceModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        List<ServiceVo> serviceVos = service.getAllService(filter);
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        paging.setRecords(serviceModels);
        this.setCarrierList(paging);
    }

    protected ServiceFilter buildFilter() throws Exception {
        ServiceFilter filter = new ServiceFilter();
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "service_id" : this.getOrderField());
        return filter;
    }

    protected Boolean validateFields() {
        if (this.carrier != null) {
            if (StringUtils.isBlank(this.carrier.getServiceName())) {
                this.addFieldError("carrier.serviceName", "Service name cannot be empty.");
            }
            if (StringUtils.isBlank(this.carrier.getCarrierType())) {
                this.addFieldError("carrier.carrierType", "Carrier Type must be selected.");
            }
        }
        if (this.hasFieldErrors()) {
            return false;
        }
        return true;
    }

    public Paging<ServiceModel> getCarrierList() {
        return carrierList;
    }

    public void setCarrierList(Paging<ServiceModel> carrierList) {
        this.carrierList = carrierList;
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

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public ServiceModel getCarrier() {
        return carrier;
    }

    public void setCarrier(ServiceModel carrier) {
        this.carrier = carrier;
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