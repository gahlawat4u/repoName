package com.gms.xms.weblib.controller.admin.ratesheets;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.ZipFileUtils;
import com.gms.xms.model.account.customers.manage.ServiceSettingModel;
import com.gms.xms.model.admin.ratesheets.RateSheetTypeModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.ratesheet.ImportRateSheetDao;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.account.customers.manage.ServiceSettingVo;
import com.gms.xms.txndb.vo.admin.ratesheets.RateSheetTypeVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.service.TntDomImportRateSheetParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

/**
 * Posted from May 5, 2016 10:44:22 AM
 * <p>
 * Author huynd
 */
public class ImportRateSheetController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private List<ServiceSettingModel> services;
    private List<RateSheetTypeModel> rateSheetType;

    private Long carrierId;

    // Upload rate sheet file
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String rateSheetFilePath;

    // Prepare to import rate sheet
    private String filePath;
    private String rateSheetName;
    private String shipmentType;
    private Boolean isSourceZone;
    private String sourceZone;
    private Boolean isCarrierCost;
    private Boolean isPerWeight;

    public String show() {
        try {
            loadCarriers();
            loadRateSheetType();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void loadRateSheetType() throws Exception {
        ImportRateSheetDao rateSheetTypeDao = new ImportRateSheetDao();
        if (this.getCarrierId() == null) {
            if (this.getServices() != null && this.getServices().size() > 0) {
                this.setCarrierId(Long.valueOf(this.getServices().get(0).getServiceId()));
            } else {
                this.setCarrierId(1L);
            }
        }
        List<RateSheetTypeModel> rateSheetTypeModels = new LinkedList<RateSheetTypeModel>();
        if (this.getCarrierId() == 3) {
            RateSheetTypeModel rateSheetTypeModel = new RateSheetTypeModel();
            rateSheetTypeModel.setId("tnt_domestic_rate");
            rateSheetTypeModel.setShipmentTypeName("TNT Domestic Rate");
            rateSheetTypeModels.add(rateSheetTypeModel);
        } else {
            List<RateSheetTypeVo> rateSheetTypeVos = rateSheetTypeDao.getRateSheetTypeByCarrier(this.getCarrierId());
            rateSheetTypeModels = ModelUtils.createListModelFromVo(rateSheetTypeVos, RateSheetTypeModel.class);
        }
        this.setRateSheetType(rateSheetTypeModels);
    }

    private void loadCarriers() throws Exception {
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> serviceVos = service.selectAll();
        // Create new Service Setting list

        List<ServiceSettingVo> serviceSettingVos = new ArrayList<ServiceSettingVo>();
        for (ServiceVo serviceVo : serviceVos) {
            ServiceSettingVo serviceSettingVo = new ServiceSettingVo();
            // Get Service Setting info
            serviceSettingVo.setServiceId(serviceVo.getServiceId());
            serviceSettingVo.setServiceName(serviceVo.getServiceName());
            // Add Service Setting to list
            serviceSettingVos.add(serviceSettingVo);
        }
        // Convert to model
        List<ServiceSettingModel> serviceSettingModels = ModelUtils.createListModelFromVo(serviceSettingVos, ServiceSettingModel.class);
        this.setServices(serviceSettingModels);
    }

    public String getRateSheetTypeData() {
        try {
            loadRateSheetType();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String upload() {
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                String rateSheetFilePath = request.getSession().getServletContext().getRealPath("") + "/rate_sheets";
                // Get extension
                String rateSheetFileName = this.getFileUploadFileName();
                String[] rateSheetFileNameArr = rateSheetFileName.split("\\.");
                String ext = rateSheetFileNameArr[rateSheetFileNameArr.length - 1];
                String outputFilePath = "/import_rate_" + AppUtils.createMessageReference() + "." + ext;
                File rateSheetFile = new File(rateSheetFilePath, outputFilePath);
                // Upload file
                FileUtils.copyFile(this.getFileUpload(), rateSheetFile);
                this.setRateSheetFilePath(rateSheetFilePath + outputFilePath);
                return "uploaded";
            } catch (Exception e) {
                handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            }
        }
        return "success";
    }

    public void saveImportRateSheet() {
        try {
            if (StringUtils.isBlank(this.getRateSheetName())) {
                throw new CustomException("You must name the custom rate sheet before importing.");
            }
            Integer carrierId = Integer.parseInt(this.getCarrierId().toString());
            // Do import TNT Domestic Rate Sheet if the carrier is 3.
            if (carrierId == 3) {
                saveTntDomRateSheet();
            } else {
                Long shipmentTypeId = null;
                Integer content = null;
                Integer bound = null;
                if (!"0".equalsIgnoreCase(this.getShipmentType())) {
                    String[] shipmentTypeArray = this.getShipmentType().split(",");
                    shipmentTypeId = Long.valueOf(shipmentTypeArray[0]);
                    content = Integer.valueOf(shipmentTypeArray[1]);
                    bound = Integer.valueOf(shipmentTypeArray[2]);
                } else {
                    throw new CustomException("Please select Rate Sheet Type.");
                }
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.SHIPMENT_TYPE_ID, shipmentTypeId);
                context.put(Attributes.SHEET_NAME, this.getRateSheetName());
                if (this.getIsCarrierCost()) {
                    context.put(Attributes.CARRIER_COST, 1);
                } else {
                    context.put(Attributes.CARRIER_COST, 0);
                }
                if (this.getIsPerWeight()) {
                    context.put(Attributes.PER_WEIGHT, 1);
                } else {
                    context.put(Attributes.PER_WEIGHT, 0);
                }
                context.put(Attributes.CONTENT, content);
                context.put(Attributes.BOUND, bound);
                context.put(Attributes.RATE_SHEET_FILE, this.getRateSheetFilePath().replace("\\", "/").trim());
                if (carrierId == 72 && (shipmentTypeId == 228 || shipmentTypeId == 229)) {
                    // Road Express & Premium Air Freight
                    context.put(Attributes.WFP_NAME, "Wfl-ImportRateSheet-Startrack");
                } else {
                    context.put(Attributes.WFP_NAME, "Wfl-ImportRateSheet");
                }
                context = WorkFlowManager2.getInstance().process(context);
                // Write log if there is error message.
                if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    throw new CustomException(context.getString(Attributes.ERROR_MESSAGE));
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    private void saveTntDomRateSheet() throws Exception {
        String sheetName = this.getRateSheetName();
        String zipFile = this.getRateSheetFilePath().replace("\\", "/").trim();
        String outputFolder = this.getServerPath("tmp");
        outputFolder += File.separator + UUID.randomUUID().toString();
        outputFolder = outputFolder.replace("\\", "/").trim();
        Map<String, String> context = this.getAddInfoMap();
        List<String> files = ZipFileUtils.unZipIt(zipFile, outputFolder);
        TntDomImportRateSheetParser parser;
        int invalidCount = 0, fileCount = (files != null) ? files.size() : 0;
        for (String file : files) {
            parser = new TntDomImportRateSheetParser(context, file, sheetName);
            if (!parser.parser()) {
                invalidCount++;
                log.info("ERROR: Import TNT Domestic Rate Sheet (The file [" + file + "] of zip file [" + zipFile + "] is invalid.");
            }
        }
        if (fileCount - invalidCount == 0) {
            throw new Exception("Please import valid text or zip file.");
        }
    }

    public List<ServiceSettingModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceSettingModel> services) {
        this.services = services;
    }

    public List<RateSheetTypeModel> getRateSheetType() {
        return rateSheetType;
    }

    public void setRateSheetType(List<RateSheetTypeModel> rateSheetType) {
        this.rateSheetType = rateSheetType;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getRateSheetFilePath() {
        return rateSheetFilePath;
    }

    public void setRateSheetFilePath(String rateSheetFilePath) {
        this.rateSheetFilePath = rateSheetFilePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRateSheetName() {
        return rateSheetName;
    }

    public void setRateSheetName(String rateSheetName) {
        this.rateSheetName = rateSheetName;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public Boolean getIsSourceZone() {
        return isSourceZone;
    }

    public void setIsSourceZone(Boolean isSourceZone) {
        this.isSourceZone = isSourceZone;
    }

    public String getSourceZone() {
        return sourceZone;
    }

    public void setSourceZone(String sourceZone) {
        this.sourceZone = sourceZone;
    }

    public Boolean getIsCarrierCost() {
        return isCarrierCost;
    }

    public void setIsCarrierCost(Boolean isCarrierCost) {
        this.isCarrierCost = isCarrierCost;
    }

    public Boolean getIsPerWeight() {
        return isPerWeight;
    }

    public void setIsPerWeight(Boolean isPerWeight) {
        this.isPerWeight = isPerWeight;
    }

}
