/**
 *
 */
package com.gms.xms.weblib.controller.admin.ratesheets;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.admin.ratesheets.CoverSheetFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.ratesheets.CoverSheetModel;
import com.gms.xms.model.admin.ratesheets.ServiceCoverSheetModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.carriercoversheet.CarrierCoverSheetServiceImp;
import com.gms.xms.persistence.service.admin.carriercoversheet.ICarrierCoverSheetService;
import com.gms.xms.persistence.service.admin.coversheet.CoverSheetServiceImp;
import com.gms.xms.persistence.service.admin.coversheet.ICoverSheetService;
import com.gms.xms.txndb.vo.admin.ratesheets.CarrierCoverSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.CoverSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.ServiceCoverSheetVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Posted from ManageCoverSheetsController
 *
 * @author HungNT - @since Oct 8, 2015
 */
public class ManageCoverSheetsController extends JsonBaseController {
    private static final long serialVersionUID = 8577179794671631194L;

    private Paging<CoverSheetModel> cvSheets;
    private List<ServiceCoverSheetModel> scvSheets;
    private List<String> listPageSize;

    private List<String> serviceId;
    private String coverSheetId;
    private String coverType;
    private String cvPage;
    private String cvPageSize;

    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;

    public String show() {
        try {
            this.setListPageSize(this.buildPageSizeList());
            this.buildCoverSheets();
            this.buildServiceCoverSheets();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String getCSheetData() {
        try {
            this.buildCoverSheets();
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }
        return "success";
    }

    public String getScSheetData() {
        try {
            this.buildServiceCoverSheets();
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }
        return "success";
    }

    public String deleteCover() {
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                if (!StringUtils.isBlank(this.coverSheetId)) {
                    Long coverSheetId = Long.parseLong(this.coverSheetId);
                    // Check delete
                    ICarrierCoverSheetService carrierCoverSheetService = new CarrierCoverSheetServiceImp();
                    Integer totalCount = carrierCoverSheetService.getCountByCoverSheetId(coverSheetId);
                    if (totalCount > 0) {
                        this.setErrorCode(ErrorCode.ERROR);
                        this.setErrorMessage("This cover sheet cannot be deleted because it has been used.");
                        return "success";
                    } else {
                        ICoverSheetService service = new CoverSheetServiceImp();
                        CoverSheetVo coverSheetVo = service.getCoverSheetById(coverSheetId);
                        service.deleteCoverSheet(this.getAddInfoMap(), coverSheetId);
                        String coverFilePath = request.getSession().getServletContext().getRealPath("") + "/cover_images";
                        File coverImg = new File(coverFilePath, coverSheetVo.getFileName());
                        if (coverImg.exists()) {
                            coverImg.delete();
                        }
                        this.buildCoverSheets();
                        return "deleted";
                    }
                } else {
                    this.setErrorCode(ErrorCode.ERROR);
                    this.setErrorMessage("Please select a cover to delete.");
                }
            } catch (Exception e) {
                log.error(e);
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage(e.getMessage());
            }
        }
        return "success";
    }

    public String attach() {
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                if (this.serviceId != null && !this.serviceId.isEmpty()) {
                    if (!StringUtils.isBlank(this.coverSheetId) && !this.coverSheetId.equals("0")) {
                        ICarrierCoverSheetService service = new CarrierCoverSheetServiceImp();
                        for (String serviceId : this.serviceId) {
                            CarrierCoverSheetVo carrierCoverSheetVo = new CarrierCoverSheetVo();
                            Integer id = Integer.parseInt(serviceId);
                            Long coverId = Long.parseLong(this.coverSheetId);
                            carrierCoverSheetVo.setCarrierId(id);
                            if (this.coverType != null) {
                                switch (this.coverType) {
                                    case "cover":
                                        carrierCoverSheetVo.setCoverSheetId(coverId);
                                        carrierCoverSheetVo.setInboundCoverSheetId(0l);
                                        break;
                                    case "inbound_cover":
                                        carrierCoverSheetVo.setCoverSheetId(0l);
                                        carrierCoverSheetVo.setInboundCoverSheetId(coverId);
                                        break;
                                }
                                service.doAttach(this.getAddInfoMap(), carrierCoverSheetVo);
                            }
                        }
                        this.buildServiceCoverSheets();
                        return "saved";
                    } else {
                        this.setErrorCode(ErrorCode.ERROR);
                        this.setErrorMessage("Please select a cover to attach.");
                    }
                } else {
                    this.setErrorCode(ErrorCode.ERROR);
                    this.setErrorMessage("Please select a service to attach.");
                }
            } catch (Exception e) {
                log.error(e);
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage(e.getMessage());
            }
        }
        return "success";
    }

    public String removeAttach() {
        if (request.getMethod().equalsIgnoreCase("post")) {
            if (this.serviceId != null && !this.serviceId.isEmpty()) {
                ICarrierCoverSheetService service = new CarrierCoverSheetServiceImp();
                try {
                    for (String serviceId : this.serviceId) {
                        Integer id = Integer.parseInt(serviceId);
                        service.removeAttach(this.getAddInfoMap(), id);
                    }
                    this.buildServiceCoverSheets();
                    return "deleted";
                } catch (Exception e) {
                    log.error(e);
                    this.setErrorCode(ErrorCode.ACTION_ERROR);
                    this.setErrorMessage(e.getMessage());
                }
            } else {
                this.setErrorCode(ErrorCode.ERROR);
                this.setErrorMessage("Please select a service to remove attachments.");
            }
        }
        return "success";
    }

    public String upload() {
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                ICoverSheetService service = new CoverSheetServiceImp();
                String coverFilePath = request.getSession().getServletContext().getRealPath("") + "/cover_images";
                File coverImg = new File(coverFilePath, this.getFileUploadFileName());
                if (coverImg.exists() && !coverImg.isDirectory()) {
                    this.setErrorCode(ErrorCode.ERROR);
                    this.setErrorMessage("This cover is already exist. Please upload another cover image or change the cover file's name.");
                    return "success";
                }
                FileUtils.copyFile(this.getFileUpload(), coverImg);
                service.addCoverSheet(this.getAddInfoMap(), this.getFileUploadFileName());
                this.buildCoverSheets();
                return "saved";
            } catch (Exception e) {
                log.error(e);
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage(e.getMessage());
            }
        }
        return "success";
    }

    protected void buildCoverSheets() throws Exception {
        ICoverSheetService service = new CoverSheetServiceImp();
        List<CoverSheetVo> coverSheetVos = service.getAllCoverSheets(this.buildCoverSheetsFilter());
        List<CoverSheetModel> coverSheetModels = ModelUtils.createListModelFromVo(coverSheetVos, CoverSheetModel.class);
        this.cvSheets.setRecords(coverSheetModels);
    }

    protected void buildServiceCoverSheets() throws Exception {
        ICoverSheetService service = new CoverSheetServiceImp();
        List<ServiceCoverSheetVo> scoverSheetVos = service.getAllServiceCoverSheets();
        List<ServiceCoverSheetModel> scoverSheetModels = ModelUtils.createListModelFromVo(scoverSheetVos, ServiceCoverSheetModel.class);
        this.scvSheets = scoverSheetModels;
    }

    protected CoverSheetFilter buildCoverSheetsFilter() {
        ICoverSheetService service = new CoverSheetServiceImp();
        CoverSheetFilter filter = new CoverSheetFilter();
        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(this.cvPageSize);
        } catch (Exception ex) {
            pageSize = 5;
        }

        int page = 0;
        try {
            page = Integer.parseInt(this.cvPage);
        } catch (Exception ex) {
            page = 1;
        }
        long recordCount = 0;
        try {
            recordCount = service.getCountAllCoverSheets();
        } catch (Exception ex) {
            recordCount = 0;
        }
        Paging<CoverSheetModel> paging = new Paging<CoverSheetModel>(page, 10, pageSize, recordCount);
        this.cvSheets = paging;
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        return filter;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public String getCoverSheetId() {
        return coverSheetId;
    }

    public void setCoverSheetId(String coverSheetId) {
        this.coverSheetId = coverSheetId;
    }

    public Paging<CoverSheetModel> getCvSheets() {
        return cvSheets;
    }

    public void setCvSheets(Paging<CoverSheetModel> cvSheets) {
        this.cvSheets = cvSheets;
    }

    public List<ServiceCoverSheetModel> getScvSheets() {
        return scvSheets;
    }

    public void setScvSheets(List<ServiceCoverSheetModel> scvSheets) {
        this.scvSheets = scvSheets;
    }

    public String getCvPage() {
        return cvPage;
    }

    public void setCvPage(String cvPage) {
        this.cvPage = cvPage;
    }

    public String getCvPageSize() {
        return cvPageSize;
    }

    public void setCvPageSize(String cvPageSize) {
        this.cvPageSize = cvPageSize;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public List<String> getServiceId() {
        return serviceId;
    }

    public void setServiceId(List<String> serviceId) {
        this.serviceId = serviceId;
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
}
