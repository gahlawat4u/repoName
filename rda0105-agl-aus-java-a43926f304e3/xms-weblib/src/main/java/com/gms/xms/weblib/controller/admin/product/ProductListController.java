package com.gms.xms.weblib.controller.admin.product;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.ProductFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.ProductCarrierModel;
import com.gms.xms.model.admin.ProductModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.IProductCarrierService;
import com.gms.xms.persistence.service.admin.IProductService;
import com.gms.xms.persistence.service.admin.ProductCarrierServiceImp;
import com.gms.xms.persistence.service.admin.ProductServiceImp;
import com.gms.xms.txndb.vo.admin.ProductCarrierVo;
import com.gms.xms.txndb.vo.admin.ProductVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from ProductListController
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductListController extends AdminJsonBaseController {

    private static final long serialVersionUID = -7945740360436049333L;

    // Models.
    private Paging<ProductModel> productList;
    private ProductModel product;
    private ProductCarrierModel productCarrier;

    // Filter properties.
    private String productId;
    private String pageSize;
    private String page;
    private String orderType;
    private String orderField;
    private String id;

    public String show() {
        try {
            // Prepare page size list.
            this.preparePageSizes();
            // Check product carrier id.
            if (StringUtils.isBlank(this.getId())) {
                throw new CustomException("Please choose a product carrier id.");
            } else {
                try {
                    Long.valueOf(this.getId());
                } catch (Exception e) {
                    throw new CustomException("Invalid product carrier id.");
                }
            }
            // Set default paging properties for product list.
            this.setPage("1");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            this.setOrderField("product_id");
            this.setOrderType("0");
            // Get product carrier.
            IProductCarrierService carrierService = new ProductCarrierServiceImp();
            ProductCarrierVo productCarrierVo = carrierService.selectById(Long.valueOf(this.getId()));
            ProductCarrierModel productCarrierModel = ModelUtils.createModelFromVo(productCarrierVo, ProductCarrierModel.class);
            this.setProductCarrier(productCarrierModel);
            // Get list of product by product carrier id.
            this.prepareProducts();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            // Get list of product by product carrier id.
            this.prepareProducts();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String load() {
        try {
            // Check product carrier id.
            if (StringUtils.isBlank(this.getId())) {
                throw new CustomException("Please choose a product carrier id.");
            } else {
                try {
                    Long.valueOf(this.getId());
                } catch (Exception e) {
                    throw new CustomException("Invalid product carrier id.");
                }
            }
            // Set new product if there's not product id.
            if (StringUtils.isBlank(this.getProductId())) {
                this.setProduct(new ProductModel());
                this.getProduct().setCarrierId(this.getId());
                this.getProduct().setLocalizationId("0");
            } else {
                try {
                    Long productId = Long.valueOf(this.getProductId());
                    // Load product.
                    IProductService service = new ProductServiceImp();
                    ProductVo productVo = service.selectById(productId);
                    ProductModel productModel = ModelUtils.createModelFromVo(productVo, ProductModel.class);
                    this.setProduct(productModel);
                } catch (Exception e) {
                    throw new CustomException("Invalid product id.");
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
            if (this.getProduct() == null) {
                throw new CustomException("No product to save.");
            }
            // Validate product.
            if (!isValidProduct(this.getProduct())) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "success";
            }
            // Convert to vo.
            ProductVo productVo = ModelUtils.createVoFromModel(this.getProduct(), ProductVo.class);
            // Save product.
            IProductService service = new ProductServiceImp();
            if (StringUtils.isBlank(this.getProduct().getProductId())) {
                service.insert(this.getAddInfoMap(), productVo);
            } else {
                service.update(this.getAddInfoMap(), productVo);
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void delete() {
        try {
            // Check product id.
            if (this.getProductId() == null) {
                throw new CustomException("No product to delete.");
            }
            IProductService service = new ProductServiceImp();
            service.delete(this.getAddInfoMap(), Long.valueOf(this.getProductId()));
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    protected boolean isValidProduct(ProductModel product) {
        if (StringUtils.isBlank(product.getProductName())) {
            this.addFieldError("product.productName", "Please enter product name.");
        }
        return !this.hasFieldErrors();
    }

    protected void prepareProducts() throws Exception {
        // Get filter.
        ProductFilter filter = this.buildFilter();
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        IProductService productService = new ProductServiceImp();
        long recordCount = productService.countByFilter(filter);
        // Build paging object.
        Paging<ProductModel> paging = new Paging<ProductModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of product.
        List<ProductVo> productVos = productService.selectByFilter(filter);
        List<ProductModel> productModels = ModelUtils.createListModelFromVo(productVos, ProductModel.class);
        paging.setRecords(productModels);
        this.setProductList(paging);
    }

    protected ProductFilter buildFilter() throws Exception {
        ProductFilter filter = new ProductFilter();
        // Set product carrier id.
        Long productCarrierId = null;
        try {
            productCarrierId = Long.valueOf(this.getId());
        } catch (Exception e) {
            throw new CustomException("Invalid product carrier id.");
        }
        if (productCarrierId == null) {
            throw new CustomException("Please choose a product carrier id.");
        }
        filter.setCarrierId(productCarrierId);
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "product_id" : this.getOrderField());
        return filter;
    }

    public Paging<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(Paging<ProductModel> productList) {
        this.productList = productList;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductCarrierModel getProductCarrier() {
        return productCarrier;
    }

    public void setProductCarrier(ProductCarrierModel productCarrier) {
        this.productCarrier = productCarrier;
    }
}
