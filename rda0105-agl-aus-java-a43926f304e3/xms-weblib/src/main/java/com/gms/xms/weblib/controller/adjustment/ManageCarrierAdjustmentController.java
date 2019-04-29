package com.gms.xms.weblib.controller.adjustment;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.Paging;
import com.gms.xms.model.adjustment.CarrierAdjustmentCountStaticModel;
import com.gms.xms.model.adjustment.CarrierAdjustmentModel;
import com.gms.xms.model.adjustment.ManageAdjustmentPageModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentCountStaticVo;
import com.gms.xms.txndb.vo.adjustment.CarrierAdjustmentFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import com.gms.xms.workflow.client.integration.request.CarrierAdjustmentRequest;
import com.gms.xms.workflow.client.integration.response.CarrierAdjustmentResponse;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from ManageCarrierAdjustmentController
 * <p>
 * Author TanDT Date May 26, 2015
 */
public class ManageCarrierAdjustmentController extends JsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = -3236127011215220184L;
    ManageAdjustmentPageModel manageAdjustmentPageModel;
    private Paging<CarrierAdjustmentModel> listCarrierAdjustment;
    private List<String> listRecordSize;
    private CarrierAdjustmentCountStaticModel carrierAdjustmentCountStatic;
    private String fileName;
    private InputStream stream;
    private String listAdjId;
    private String tableId;
    private String orderType;
    private String orderField;

    /**
     * Show Index page Carrier Adjustment
     *
     * @return manage_carrier_adjustment.jsp
     */
    public String index() {
        this.setPageTitle(this.getLocalizationText("Carrier Adjustment"));
        try {
            this.pepareListRecordSize();
            // Static Count Carrier Adjustment by Status
            this.prepareStaticCarrierAdj();
            // Show list carrier Adj
            this.prepareShowPage();
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        return "success";

    }

    public String doExportCarrierAdjustment() {
        CarrierAdjustmentFilter filter = this.prepareDataFilter();
        ManageAdjustmentClient manageAdjustmentClient = new ManageAdjustmentClient(this.getAddInfoMap());
        String fileName = "";
        try {
            if (manageAdjustmentPageModel != null) {
                switch (manageAdjustmentPageModel.getStatus()) {
                    case "1":
                        fileName = "All_Submitted_Requests.xls";
                        break;
                    case "2":
                        fileName = "All_Pending_Requests.xls";
                        break;
                    case "3":
                        fileName = "All_Disputed_Requests.xls";
                        break;
                    case "4":
                        fileName = "All_Approved_Requests.xls";
                        break;
                    case "5":
                        fileName = "All_Disputed_Denied_Requests.xls";
                        break;
                    default:
                        fileName = "Carrier_Adjustment.xls";
                        break;
                }
                this.setFileName(fileName);
                filter.setPage(null);
                filter.setRecordSize(null);
                filter.setStatus(Integer.parseInt(manageAdjustmentPageModel.getStatus()));
                CarrierAdjustmentRequest carrierAdjustmentRequest = new CarrierAdjustmentRequest();
                carrierAdjustmentRequest.setFilter(filter);
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.getFileName();
                manageAdjustmentClient.renderCarrierAdjustmentXLSFile(carrierAdjustmentRequest, outPutFilePath);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
            } else {
                return "redirect";
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        return "export";
    }

    /**
     * Do accept Submitted request
     *
     * @throws Exception
     */
    public void doCarrierAdjustmentsSubmitted() {
        Long userId = Long.parseLong(request.getSession().getAttribute("SESS_XMS_ADMIN_ID").toString());
        CarrierAdjustmentRequest AdjRequest = new CarrierAdjustmentRequest();
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        try {
            if (this.getListAdjId() != null) {
                String[] listAdjustmentId = this.getListAdjId().split("\\|");
                AdjRequest.setListAdjustmentId(listAdjustmentId);
                AdjRequest.setUserId(userId);
                CarrierAdjustmentResponse response = client.acceptSubmittedRequests(AdjRequest);
                if (!response.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
                    if (!StringUtils.isBlank(response.getErrorType()) && response.getErrorType().equalsIgnoreCase(AppConstants.CUSTOM_ERROR_MESSAGE)) {
                        throw new CustomException(response.getErrorMessage());
                    } else {
                        throw new Exception(response.getErrorMessage());
                    }
                }
            } else {
                this.setErrorCode(ErrorCode.ERROR);
                this.setErrorMessage(this.getLocalizationText("Please select an adjustment!"));
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    /**
     * Do Reject Submitted request
     *
     * @throws Exception
     */
    public void doCarrierAdjustmentsSubmittedRe() {
        Long userId = Long.parseLong(request.getSession().getAttribute("SESS_XMS_ADMIN_ID").toString());
        CarrierAdjustmentRequest AdjRequest = new CarrierAdjustmentRequest();
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        try {
            if (this.getListAdjId() != null) {
                String[] listAdjustmentId = this.getListAdjId().split("\\|");
                AdjRequest.setListAdjustmentId(listAdjustmentId);
                AdjRequest.setUserId(userId);
                client.rejectSubmittedRequests(AdjRequest);
            } else {
                this.setErrorCode(ErrorCode.ERROR);
                this.setErrorMessage(this.getLocalizationText("Please select an adjustment!"));
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    /**
     * Do accept Pending request
     *
     * @throws Exception
     */
    public void doCarrierAdjustmentsPending() {
        Long userId = Long.parseLong(request.getSession().getAttribute("SESS_XMS_ADMIN_ID").toString());
        CarrierAdjustmentRequest AdjRequest = new CarrierAdjustmentRequest();
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        try {
            if (this.getListAdjId() != null) {
                String[] listAdjustmentId = this.getListAdjId().split("\\|");
                AdjRequest.setListAdjustmentId(listAdjustmentId);
                AdjRequest.setUserId(userId);
                client.acceptSubmittedRequests(AdjRequest);
            } else {
                this.setErrorCode(ErrorCode.ERROR);
                this.setErrorMessage(this.getLocalizationText("Please select an adjustment!"));
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    /**
     * Do Reject Pending request
     *
     * @throws Exception
     */
    public void doCarrierAdjustmentsPendingRe() {
        Long userId = Long.parseLong(request.getSession().getAttribute("SESS_XMS_ADMIN_ID").toString());
        CarrierAdjustmentRequest AdjRequest = new CarrierAdjustmentRequest();
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        try {
            if (this.getListAdjId() != null) {
                String[] listAdjustmentId = this.getListAdjId().split("\\|");
                AdjRequest.setListAdjustmentId(listAdjustmentId);
                AdjRequest.setUserId(userId);
                client.rejectPendingRequests(AdjRequest);
            } else {
                this.setErrorCode(ErrorCode.ERROR);
                this.setErrorMessage(this.getLocalizationText("Please select an adjustment!"));
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    /**
     * @param filter
     * @return
     */
    private CarrierAdjustmentResponse prepareListCarrierAdjustment(CarrierAdjustmentFilter filter) {
        CarrierAdjustmentRequest request = new CarrierAdjustmentRequest();
        CarrierAdjustmentResponse response = new CarrierAdjustmentResponse();
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        request.setFilter(filter);
        try {
            response = client.manageCarrierAdjustment(request);
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        return response;
    }

    /**
     * Build Filter for Index action
     */
    private CarrierAdjustmentFilter prepareDataFilter() {
        Integer status = 1;
        Integer page = 1;
        Integer recordSize = 25;
        CarrierAdjustmentFilter filter = new CarrierAdjustmentFilter();
        try {
            if (manageAdjustmentPageModel != null) {
                if (!StringUtils.isBlank(this.manageAdjustmentPageModel.getStatus())) {
                    status = Integer.parseInt(this.manageAdjustmentPageModel.getStatus());
                }
                if (!StringUtils.isBlank(this.manageAdjustmentPageModel.getPage())) {
                    page = Integer.parseInt(this.manageAdjustmentPageModel.getPage());
                    if (page <= 0) {
                        page = 1;
                    }
                }
                if (!StringUtils.isBlank(this.manageAdjustmentPageModel.getRecordSize())) {
                    if (this.manageAdjustmentPageModel.getRecordSize().equals("All")) {
                        recordSize = 0;
                    } else {
                        recordSize = Integer.parseInt(this.manageAdjustmentPageModel.getRecordSize());
                    }
                }
            }
            filter.setStatus(status);
            filter.setPage(page);
            filter.setRecordSize(recordSize);
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
            filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "adjustmentId" : this.getOrderField());
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        return filter;
    }

    /**
     * Set value for CarrierAdjustmentCountStaticByStatus
     *
     * @throws Exception
     */
    private void prepareStaticCarrierAdj() {
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        CarrierAdjustmentCountStaticVo carrierAdjustmentCountStaticVo = new CarrierAdjustmentCountStaticVo();
        try {
            carrierAdjustmentCountStaticVo = client.getCarrierAdjustmentCountStaticByStatus();
            this.setCarrierAdjustmentCountStatic(ModelUtils.createModelFromVo(carrierAdjustmentCountStaticVo, CarrierAdjustmentCountStaticModel.class));
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

    }

    /**
     * prepare data for index page
     */
    private void prepareShowPage() {
        CarrierAdjustmentFilter filter = this.prepareDataFilter();
        CarrierAdjustmentResponse response;
        try {
            response = this.prepareListCarrierAdjustment(filter);
            Paging<CarrierAdjustmentModel> paging = new Paging<>(filter.getPage(), 5, filter.getRecordSize(), response.getTotalCarrierAdjustment());
            this.setListCarrierAdjustment(paging);
            // Format Currency for Customer Amount
            List<CarrierAdjustmentModel> listAdjustmentModel = new ArrayList<CarrierAdjustmentModel>();
            for (CarrierAdjustmentModel adjustmentModel : ModelUtils.createListModelFromVo(response.getListCarrierAdjustment(), CarrierAdjustmentModel.class)) {
                adjustmentModel.setCustomerAmountFormatted(HelperUtils.formatCurrency(adjustmentModel.getCustomerAmount()));
                listAdjustmentModel.add(adjustmentModel);
            }
            listCarrierAdjustment.setRecords(listAdjustmentModel);
            if (this.manageAdjustmentPageModel == null) {
                ManageAdjustmentPageModel adjustmentPageModel = new ManageAdjustmentPageModel();
                adjustmentPageModel.setPage(String.valueOf(filter.getPage()));
                adjustmentPageModel.setRecordSize(String.valueOf(filter.getRecordSize()));
                adjustmentPageModel.setStatus(String.valueOf(filter.getStatus()));
                this.setManageAdjustmentPageModel(adjustmentPageModel);
            }
            if (StringUtils.isBlank(this.getTableId())) {
                this.setTableId("Airbill-tab");
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

    }

    /**
     * Build Dropdown list recordSize
     */
    private void pepareListRecordSize() {
        this.setListRecordSize(this.buildPageSizeList());
    }

    public Paging<CarrierAdjustmentModel> getListCarrierAdjustment() {
        return listCarrierAdjustment;
    }

    public void setListCarrierAdjustment(Paging<CarrierAdjustmentModel> listCarrierAdjustment) {
        this.listCarrierAdjustment = listCarrierAdjustment;
    }

    public List<String> getListRecordSize() {
        return listRecordSize;
    }

    public void setListRecordSize(List<String> listRecordSize) {
        this.listRecordSize = listRecordSize;
    }

    public ManageAdjustmentPageModel getManageAdjustmentPageModel() {
        return manageAdjustmentPageModel;
    }

    public void setManageAdjustmentPageModel(ManageAdjustmentPageModel manageAdjustmentPageModel) {
        this.manageAdjustmentPageModel = manageAdjustmentPageModel;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public String getListAdjId() {
        return listAdjId;
    }

    public void setListAdjId(String listAdjId) {
        this.listAdjId = listAdjId;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public CarrierAdjustmentCountStaticModel getCarrierAdjustmentCountStatic() {
        return carrierAdjustmentCountStatic;
    }

    public void setCarrierAdjustmentCountStatic(CarrierAdjustmentCountStaticModel carrierAdjustmentCountStatic) {
        this.carrierAdjustmentCountStatic = carrierAdjustmentCountStatic;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
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
