package com.gms.xms.weblib.controller.admin.product;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.ProductCarrierFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.ProductCarrierModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.IProductCarrierService;
import com.gms.xms.persistence.service.admin.ProductCarrierServiceImp;
import com.gms.xms.txndb.vo.admin.ProductCarrierVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from ProductCarrierListController
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductCarrierListController extends AdminJsonBaseController {

    private static final long serialVersionUID = -7945740360436049333L;

    // Models.
    private Paging<ProductCarrierModel> productCarrierList;
    private ProductCarrierModel productCarrier;

    // Filter properties.
    private String pageSize;
    private String page;
    private String orderField;
    private String orderType;
    private String productCarrierId;

    public String show() {
        try {
            // Load data for page size drop down list.
            this.preparePageSizes();
            // Load product carriers.
            // Set default paging properties.
            this.setPage("1");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            this.setOrderField("product_carrierid");
            this.setOrderType("0");
            // Get filter.
            ProductCarrierFilter filter = this.buildFilter();
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            IProductCarrierService productService = new ProductCarrierServiceImp();
            long recordCount = productService.countByFilter(filter);
            // Build paging object.
            Paging<ProductCarrierModel> paging = new Paging<ProductCarrierModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            // Get list of records of cost deviation report.
            List<ProductCarrierVo> productCarrierVos = productService.selectByFilter(filter);
            List<ProductCarrierModel> productCarrierModels = ModelUtils.createListModelFromVo(productCarrierVos, ProductCarrierModel.class);
            paging.setRecords(productCarrierModels);
            this.setProductCarrierList(paging);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            // Get filter.
            ProductCarrierFilter filter = this.buildFilter();
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            IProductCarrierService productService = new ProductCarrierServiceImp();
            long recordCount = productService.countByFilter(filter);
            // Build paging object.
            Paging<ProductCarrierModel> paging = new Paging<ProductCarrierModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            // Get list of records of cost deviation report.
            List<ProductCarrierVo> productCarrierVos = productService.selectByFilter(filter);
            List<ProductCarrierModel> productCarrierModels = ModelUtils.createListModelFromVo(productCarrierVos, ProductCarrierModel.class);
            paging.setRecords(productCarrierModels);
            this.setProductCarrierList(paging);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String load() {
        try {
            // Set new product carrier if there's not product carrier id.
            if (StringUtils.isBlank(this.getProductCarrierId())) {
                this.setProductCarrier(new ProductCarrierModel());
            } else {
                try {
                    Long productCarrierId = Long.valueOf(this.getProductCarrierId());
                    // Load product carrier.
                    IProductCarrierService service = new ProductCarrierServiceImp();
                    ProductCarrierVo productCarrierVo = service.selectById(productCarrierId);
                    ProductCarrierModel productCarrierModel = ModelUtils.createModelFromVo(productCarrierVo, ProductCarrierModel.class);
                    this.setProductCarrier(productCarrierModel);
                } catch (Exception e) {
                    throw new CustomException("Invalid product carrier id.");
                }
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            // Check null.
            if (this.getProductCarrier() == null) {
                throw new CustomException("No product carrier to save.");
            }
            // Validate product carrier.
            if (!isValidProductCarrier(this.getProductCarrier())) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "success";
            }
            // Convert to vo.
            ProductCarrierVo productCarrierVo = ModelUtils.createVoFromModel(this.getProductCarrier(), ProductCarrierVo.class);
            // Save product carrier.
            IProductCarrierService service = new ProductCarrierServiceImp();
            if (StringUtils.isBlank(this.getProductCarrier().getProductCarrierId())) {
                service.insert(this.getAddInfoMap(), productCarrierVo);
            } else {
                service.update(this.getAddInfoMap(), productCarrierVo);
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void delete() {
        try {
            // Check product carrier id.
            if (this.getProductCarrierId() == null) {
                throw new CustomException("No product carrier to delete.");
            }
            IProductCarrierService service = new ProductCarrierServiceImp();
            service.delete(this.getAddInfoMap(), Long.valueOf(this.getProductCarrierId()));
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    protected boolean isValidProductCarrier(ProductCarrierModel productCarrier) {
        if (StringUtils.isBlank(productCarrier.getProductCarrierName())) {
            this.addFieldError("productCarrier.productCarrierName", "Please enter product carrier name.");
        }
        return !this.hasFieldErrors();
    }

    protected List<ProductCarrierModel> getProductCarrierListModel() throws Exception {
        IProductCarrierService service = new ProductCarrierServiceImp();
        List<ProductCarrierVo> productCarrierListVos = service.selectByFilter(this.buildFilter());
        List<ProductCarrierModel> surchargeListModels = ModelUtils.createListModelFromVo(productCarrierListVos, ProductCarrierModel.class);
        return surchargeListModels;
    }

    protected ProductCarrierFilter buildFilter() throws Exception {
        ProductCarrierFilter filter = new ProductCarrierFilter();
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "product_carrierid" : this.getOrderField());
        return filter;
    }

    public Paging<ProductCarrierModel> getProductCarrierList() {
        return productCarrierList;
    }

    public void setProductCarrierList(Paging<ProductCarrierModel> productCarrierList) {
        this.productCarrierList = productCarrierList;
    }

    public String getProductCarrierId() {
        return productCarrierId;
    }

    public void setProductCarrierId(String productCarrierId) {
        this.productCarrierId = productCarrierId;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

    public ProductCarrierModel getProductCarrier() {
        return productCarrier;
    }

    public void setProductCarrier(ProductCarrierModel productCarrier) {
        this.productCarrier = productCarrier;
    }
}
