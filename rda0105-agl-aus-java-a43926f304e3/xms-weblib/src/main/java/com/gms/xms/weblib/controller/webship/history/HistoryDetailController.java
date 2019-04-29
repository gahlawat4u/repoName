package com.gms.xms.weblib.controller.webship.history;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.PdfScaleUtils;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.SendAirlbillHistoryFillterModel;
import com.gms.xms.model.TntRouteModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.PieceModel;
import com.gms.xms.model.webship.history.*;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileRequest;
import com.gms.xms.txndb.vo.webship.history.HistoryViewFileResultVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.client.WebshipHistory2Client;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.render.dhl.DhlRenderImp;
import com.gms.xms.workflow.render.dhl.IDhlRender;
import com.gms.xms.workflow.render.othercarrier.IOtherCarrierRender;
import com.gms.xms.workflow.render.othercarrier.OtherCarrierRenderImp;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.HistoryServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.service.webship.history.IHistoryService;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Posted from HistoryDetailController
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailController extends JsonBaseController {
	private static final long serialVersionUID = -7901889480717968757L;
	private List<HistoryDetailPieceModel> detailPieceModels;
	private List<PieceModel> pieceModels;
	private List<HistoryViewAirbillModel> listPieceViewAirbill;
	private List<HistoryDetailAccessorialModel> detailAccessorialModels;
	private HistoryDetailInfoModel detailInfoModel;
	private HistoryDetailFilterModel detailFilterModel;
	private List<HistoryProductAirbillModel> productAirbillModels;
	private String totalAmount;
	private InputStream stream;
	private String shipmentId;
	private String awbBarcode;
	private String pieceBarcode;
	private String tntBarcode;
	private String fileName;
	private String small;
	private String pathFileView;
	private SendAirlbillHistoryFillterModel sendAirlbillHistoryFillterModel;

	public String show() {
		try {
			prepareHistoryDetailFilter();
			prepareHistoryDetail();
		} catch (Exception e) {
			addActionError(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return "success";
	}

	public String viewAirbill() {
		this.setPageTitle("View Airbill");
		try {
			// New follow (added by dattv).
			// Check shipment id.
			if (StringUtils.isBlank(this.getShipmentId())) {
				throw new CustomException("Shipment not found.");
			}
			// Get service id.
			IServiceService service = new ServiceServiceImp();
			ServiceVo serviceVo = service.selectAllByShipmentId(Long.parseLong(this.getShipmentId()));
			// Old follow (old code).
			String fileFtlViewAirbill = "";
			ContextBase2 context = new ContextBase2(this.getAddInfoMap());
			HistoryViewFileRequest fileRequest = new HistoryViewFileRequest();
			IShipmentService shipmentService = new ShipmentServiceImp();
			ShipmentVo shipmentVo = shipmentService.selectById(Long.parseLong(shipmentId));
			String wflName = "";
			String miniTime = String.valueOf(System.currentTimeMillis());
			Float cropLeftPercent = 0f;
			Float cropWidthPercent = 1f;
			String cropFilePath = "";
			switch (serviceVo.getServiceId()) {
			case 54: // TNT INTER
				fileRequest = prepareViewFile("tntIntViewAirbill");
				wflName = "Wfl-TntViewAirbill";
				break;
			case 1:
			case 15:
				IDhlRender dhlRender = new DhlRenderImp();
				String outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/dhl_airbill_"
						+ shipmentVo.getAirbillNumber() + ".pdf";
				dhlRender.genAirbillFile(outPutFilePath, Long.parseLong(shipmentId));
				this.setFileName("dhl_airbill_" + shipmentVo.getAirbillNumber() + ".pdf");
				this.setStream(new FileInputStream(new File(outPutFilePath)));
				break;
			case 400:
				IDhlRender upsRender = new DhlRenderImp();
				outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/ups_airbill_"
						+ shipmentVo.getAirbillNumber() + ".pdf";
				String thermalLabelFileName = "ups_thermal_label_" + shipmentVo.getAirbillNumber() + "_" + miniTime
						+ ".pdf";
				String thermalLabelPath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + thermalLabelFileName;
				upsRender.genAirbillUpsFile(outPutFilePath, Long.parseLong(shipmentId));
				cropFilePath = outPutFilePath + "_crop";
				AppUtils.cropPdf(outPutFilePath, cropFilePath, cropLeftPercent, cropWidthPercent);
				new PdfScaleUtils().manipulatePdf(cropFilePath, thermalLabelPath, 0.3f);
				this.setFileName(thermalLabelFileName);
				this.setStream(new FileInputStream(new File(thermalLabelPath)));

				break;
			case 72:
				context.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
				context.put(Attributes.WFP_NAME, "Wfl-StarTrackViewAirbill");
				context = WorkFlowManager2.getInstance().process(context);
				if (context.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
					this.setFileName(context.getString(Attributes.FILE_NAME));
					this.setStream(new FileInputStream(context.getString(Attributes.FILE_PATH)));
				} else {
					throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
				}
				break;
			default:
				prepareHistoryDetailFilter();
				prepareHistoryDetail();
				putDataToViewAirbill(Integer.parseInt(detailInfoModel.getServiceId()));
				fileFtlViewAirbill = ShipmentUtils.checkViewAirbill(Integer.parseInt(detailInfoModel.getServiceId()));
				if (fileFtlViewAirbill != null && !StringUtils.isBlank(fileFtlViewAirbill)) {
					if (!hasActionErrors() && !hasFieldErrors()) {
						if ("".equals(fileFtlViewAirbill)) {
							validAirbillBarcode();
							return "export";
						} else {
							genFileViewAirbill(fileFtlViewAirbill);
							if (small != null) {
								if (small.equals("1")) {
									return "export";
								}
							}
							return "export";
						}
					} else {
						return "error";
					}
				} else {
					throw new CustomException(
							"This service is not supported for this function. Please contact administrator for more details.");
				}
			}
			if (!StringUtils.isBlank(wflName)) {
				context.put(Attributes.WFP_NAME, "Wfl-TntViewAirbill");
				context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, fileRequest);
				HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
				context = WorkFlowManager2.getInstance().process(context);
				if (ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
					addActionError(context.getString(Attributes.ERROR_MESSAGE));
					log.error(context.getString(Attributes.ERROR_MESSAGE));
					return "error";
				} else {
					fileResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
				}
				this.setFileName(fileResultVo.getFileName());
				this.setStream(fileResultVo.getStream());
			}
			return "export";
		} catch (Exception e) {
			handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
			return "error";
		}
	}

	public String viewShipmentReceipt() {
		try {
			if (StringUtils.isNotBlank(this.getShipmentId())) {
				String customerCode = String.valueOf(this.getWebshipLoginInfo().getCustomerCode());
				String franchiseCode = customerCode.substring(0, 3) + "00001";
				ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
				CustomerAddressVo franchiseAddressVo = customerAddressService
						.getCustomerAddressByCustomerCode(Long.parseLong(franchiseCode));
				CustomerAddressModel franchiseAddress = ModelUtils.createModelFromVo(franchiseAddressVo,
						CustomerAddressModel.class);
				prepareHistoryDetailFilter();
				prepareHistoryDetail();
				IOtherCarrierRender render = new OtherCarrierRenderImp();
				IShipmentService shipmentService = new ShipmentServiceImp();
				ShipmentVo shipmentVo = shipmentService.selectById(Long.parseLong(this.getShipmentId()));
				String outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + shipmentVo.getAirbillNumber()
						+ ".html";
				String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + shipmentVo.getAirbillNumber()
						+ ".pdf";
				render.genShipmentReceiptHtml(this.getDetailInfoModel(), this.getDetailPieceModels(), franchiseAddress,
						outPutFilePath, this.getDetailAccessorialModels());
				AppUtils.createPDFFromHTMLWithFont(outPutFilePath, pdfFilePath, "arial", true);
				this.setStream(new FileInputStream(pdfFilePath));
				this.setFileName(shipmentVo.getAirbillNumber() + ".pdf");
				return "export";
			}
		} catch (Exception e) {
		}
		return "success";
	}

	public String sendAirbill() {
		try {
			if (sendAirlbillHistoryFillterModel.getEmails() != null) {
				if (!validEmail()) {
					return "error";
				}
				sentEmail();
			} else {
				ShipmentDao shipmentDao = new ShipmentDao();
				ShipmentVo shipmentVo = shipmentDao.selectById(Long.valueOf(this.getShipmentId()));
				AddressDao addressDao = new AddressDao();
				AddressVo senderAddress = addressDao.selectById(shipmentVo.getSenderAddressId());
				if (senderAddress != null) {
					SendAirlbillHistoryFillterModel airlbillHistoryFillterModel = new SendAirlbillHistoryFillterModel();
					airlbillHistoryFillterModel.setEmails(senderAddress.getEmail());
					airlbillHistoryFillterModel
							.setTemplateEmail(this.getSendAirlbillHistoryFillterModel().getTemplateEmail());
					this.setSendAirlbillHistoryFillterModel(airlbillHistoryFillterModel);
				}
			}
		} catch (Exception e) {
			this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
			log.error(e.getMessage(), e);
		}
		return "success";
	}

	public String viewManifest() {
		this.setPageTitle("View Manifest");
		try {
			// New follow (added by dattv).
			// Check shipment id.
			if (StringUtils.isBlank(this.getShipmentId())) {
				throw new CustomException("Shipment not found.");
			}
			// Get service id.
			IServiceService service = new ServiceServiceImp();
			ServiceVo serviceVo = service.selectAllByShipmentId(Long.parseLong(this.getShipmentId()));
			// Old follow (old code).
			ContextBase2 context = new ContextBase2(this.getAddInfoMap());
			HistoryViewFileRequest fileRequest = new HistoryViewFileRequest();
			switch (serviceVo.getServiceId()) {
			case 59: // Toll Ipec
				fileRequest = prepareViewFile("tollIpecNewStyleViewManifest");
				context.put(Attributes.WFP_NAME, "Wfl-TollIpecViewManifestNew");
				break;
			case 54: // TNT INTER
				fileRequest = prepareViewFile("tntIntViewManifest");
				context.put(Attributes.WFP_NAME, "Wfl-TntViewManifest");
				break;
			case 3: // TNT DOM
				fileRequest = prepareViewFile("tntDomViewManifest");
				context.put(Attributes.WFP_NAME, "Wfl-TntDomViewManifest");
				break;
			case 52: // Toll Priority
				fileRequest = prepareViewFile("tollPriorityNewStyleViewManifest");
				context.put(Attributes.WFP_NAME, "Wfl-TollPriorityViewManifestNew");
				break;
			case 72:
				context.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
				context.put(Attributes.WFP_NAME, "Wfl-StarTrackViewManifest");
				context = WorkFlowManager2.getInstance().process(context);
				if (context.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
					this.setFileName(context.getString(Attributes.FILE_NAME));
					this.setStream(new FileInputStream(new File(context.getString(Attributes.FILE_PATH))));
				} else {
					throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
				}
				return "export";
			default:
				break;
			}
			context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, fileRequest);
			HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
			context = WorkFlowManager2.getInstance().process(context);
			if (ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
				addActionError(context.getString(Attributes.ERROR_MESSAGE));
				log.error(context.getString(Attributes.ERROR_MESSAGE));
				return "error";
			} else {
				fileResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
			}
			this.setFileName(fileResultVo.getFileName());
			this.setStream(fileResultVo.getStream());
			return "export";
		} catch (Exception e) {
			handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
		}
		return "error";
	}

	public String viewCommercialInvoice() {
		this.setPageTitle("View Commercial Invoice");
		try {
			if (validViewAirbill()) {
				prepareHistoryDetailFilter();
				prepareHistoryDetail();
				HistoryViewFileRequest fileRequest = prepareViewFile("viewCommercialInvoice");
				WebshipHistory2Client client = new WebshipHistory2Client(this.getAddInfoMap());
				HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
				fileResultVo = client.viewCommercialInvoice(fileRequest);
				this.setFileName(fileResultVo.getFileName());
				this.setStream(fileResultVo.getStream());
				return "export";
			} else {
				return "error";
			}
		} catch (Exception e) {
			addActionError("This shipment is error !");
			log.error(e.getMessage(), e);
		}
		return "success";
	}

	public String viewPackingList() {
		this.setPageTitle("View Packing List");
		try {
			if (validViewAirbill()) {
				prepareHistoryDetailFilter();
				prepareHistoryDetail();
				HistoryViewFileRequest fileRequest = prepareViewFile("viewPackingList");
				WebshipHistory2Client client = new WebshipHistory2Client(this.getAddInfoMap());
				HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
				fileResultVo = client.viewPackingList(fileRequest);
				this.setFileName(fileResultVo.getFileName());
				this.setStream(fileResultVo.getStream());
				return "export";
			} else {
				return "error";
			}
		} catch (Exception e) {
			addActionError("This shipment is error !");
			log.error(e.getMessage(), e);
		}
		return "success";
	}

	public String viewThermalLable() {
		this.setPageTitle(this.getLocalizationText("View Thermal Label"));
		try {
			// New follow (added by dattv).
			// Check shipment id.
			if (StringUtils.isBlank(this.getShipmentId())) {
				throw new CustomException("Shipment not found.");
			}
			// Get service id.
			IServiceService service = new ServiceServiceImp();
			ServiceVo serviceVo = service.selectAllByShipmentId(Long.parseLong(this.getShipmentId()));
			// Old follow (old code).
			String fileFtlViewAirbill = "";
			ContextBase2 context = new ContextBase2(this.getAddInfoMap());
			HistoryViewFileRequest fileRequest = new HistoryViewFileRequest();
			IShipmentService shipmentService = new ShipmentServiceImp();
			ShipmentVo shipmentVo = shipmentService.selectById(Long.parseLong(shipmentId));
			String wflName = "";
			String thermalLabelFileName = "";
			String thermalLabelPath = "";
			String miniTime = String.valueOf(System.currentTimeMillis());
			Float cropLeftPercent = 0f;
			Float cropWidthPercent = 1f;
			String cropFilePath = "";
			String outPutFilePath = "";
			String[] cropsPercent = this.getCropsPercent(serviceVo.getServiceId());
			try {
				cropLeftPercent = Float.valueOf(cropsPercent[0]);
				cropWidthPercent = Float.valueOf(cropsPercent[1]);
			} catch (Exception e) {
			}

			switch (serviceVo.getServiceId()) {
			case 54: // TNT INTER
				fileRequest = prepareViewFile("tntIntViewAirbill");
				wflName = "Wfl-TntViewAirbill";
				break;
			case 1:
			case 15:
				IDhlRender dhlRender = new DhlRenderImp();
				outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/dhl_airbill_"
						+ shipmentVo.getAirbillNumber() + ".pdf";
				thermalLabelFileName = "dhl_thermal_label_" + shipmentVo.getAirbillNumber() + "_" + miniTime + ".pdf";
				thermalLabelPath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + thermalLabelFileName;
				dhlRender.genAirbillFile(outPutFilePath, Long.parseLong(shipmentId));
				cropFilePath = outPutFilePath + "_crop";
				AppUtils.cropPdf(outPutFilePath, cropFilePath, cropLeftPercent, cropWidthPercent);
				new PdfScaleUtils().manipulatePdf(cropFilePath, thermalLabelPath, 0.3f);
				this.setFileName(thermalLabelFileName);
				this.setStream(new FileInputStream(new File(thermalLabelPath)));
				break;
			case 400:
				IDhlRender upsRender = new DhlRenderImp();
				outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/ups_airbill_"
						+ shipmentVo.getAirbillNumber() + ".pdf";
				thermalLabelFileName = "ups_thermal_label_" + shipmentVo.getAirbillNumber() + "_" + miniTime + ".pdf";
				thermalLabelPath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + thermalLabelFileName;
				upsRender.genAirbillUpsFile(outPutFilePath, Long.parseLong(shipmentId));
				cropFilePath = outPutFilePath + "_crop";
				AppUtils.cropPdf(outPutFilePath, cropFilePath, cropLeftPercent, cropWidthPercent);
				new PdfScaleUtils().manipulatePdf(cropFilePath, thermalLabelPath, 0.3f);
				this.setFileName(thermalLabelFileName);
				this.setStream(new FileInputStream(new File(thermalLabelPath)));
				
				break;
			case 72:
				thermalLabelFileName = "startrack_thermal_label_" + shipmentVo.getAirbillNumber() + "_" + miniTime
						+ ".pdf";
				thermalLabelPath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + thermalLabelFileName;
				context.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
				context.put(Attributes.WFP_NAME, "Wfl-StarTrackViewAirbill");
				context = WorkFlowManager2.getInstance().process(context);
				if (context.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
					outPutFilePath = context.getString(Attributes.FILE_PATH);
					cropFilePath = outPutFilePath + "_crop";
					AppUtils.cropPdf(outPutFilePath, cropFilePath, cropLeftPercent, cropWidthPercent);
					new PdfScaleUtils().manipulatePdf(cropFilePath, thermalLabelPath, 0.3f);
					this.setFileName(thermalLabelFileName);
					this.setStream(new FileInputStream(thermalLabelPath));
				} else {
					throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
				}
				break;
			default:
				prepareHistoryDetailFilter();
				prepareHistoryDetail();
				putDataToViewAirbill(Integer.parseInt(detailInfoModel.getServiceId()));
				fileFtlViewAirbill = ShipmentUtils.checkViewAirbill(Integer.parseInt(detailInfoModel.getServiceId()));
				if (fileFtlViewAirbill != null && !StringUtils.isBlank(fileFtlViewAirbill)) {
					if (!hasActionErrors() && !hasFieldErrors()) {
						if ("".equals(fileFtlViewAirbill)) {
							validAirbillBarcode();
							return "export";
						} else {
							genFileViewThermalLabel(fileFtlViewAirbill, serviceVo.getServiceId());
							if (small != null) {
								if (small.equals("1")) {
									return "export";
								}
							}
							return "export";
						}
					} else {
						return "error";
					}
				} else {
					throw new CustomException(
							"This service is not supported for this function. Please contact administrator for more details.");
				}
			}

			if (!StringUtils.isBlank(wflName)) {
				context.put(Attributes.WFP_NAME, "Wfl-TntViewAirbill");
				context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, fileRequest);
				HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
				context = WorkFlowManager2.getInstance().process(context);
				if (ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
					addActionError(context.getString(Attributes.ERROR_MESSAGE));
					log.error(context.getString(Attributes.ERROR_MESSAGE));
					return "error";
				} else {
					fileResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
				}
				String outputPdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileResultVo.getFileName();
				thermalLabelFileName = getThermalFileName(serviceVo.getServiceId()) + shipmentId + miniTime + ".pdf";
				thermalLabelPath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + thermalLabelFileName;
				cropFilePath = outputPdfFilePath + "_crop";
				AppUtils.cropPdf(outputPdfFilePath, cropFilePath, cropLeftPercent, cropWidthPercent);
				new PdfScaleUtils().manipulatePdf(cropFilePath, thermalLabelPath, 0.3f);
				this.setFileName(thermalLabelFileName);
				this.setStream(new FileInputStream(new File(thermalLabelPath)));
			}
			return "export";
		} catch (Exception e) {
			handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
			return "error";
		}
	}

	public String viewConnote() {
		try {
			this.setPageTitle(this.getLocalizationText("View Consignment Note"));
			Long shipmentId = Long.valueOf(this.getShipmentId());
			ContextBase2 context = new ContextBase2(this.getAddInfoMap());
			context.put(Attributes.SHIPMENT_ID, shipmentId);
			context.put(Attributes.WFP_NAME, "Wfl-ViewTntConnote");
			context = WorkFlowManager2.getInstance().process(context);
			if (!context.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
				this.addActionError(context.getString(Attributes.ERROR_MESSAGE));
				log.error(context.getString(Attributes.ERROR_MESSAGE));
				return "error";
			} else {
				String fileName = context.getString(Attributes.FILE_NAME);
				String filePath = context.getString(Attributes.FILE_PATH);
				this.setFileName(fileName);
				this.setStream(new FileInputStream(new File(filePath)));
			}
			return "export";
		} catch (Exception e) {
			this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
		}
		return "success";
	}

	public String exportDetail() {
		try {
			this.setPageTitle("History Export Shipment Detail");
			prepareHistoryDetailFilter();
			prepareHistoryDetail();
			Map<String, Object> data = new HashMap<String, Object>();
			String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/export_history_detail_"
					+ AppUtils.createMessageReference() + ".html";
			data.put("detailPieceModels", detailPieceModels);

     		// code by shahabuddin
	       detailInfoModel.setReferenceNo(detailInfoModel.getReferenceNo().replaceAll("&", "&amp;")
	    		   .replaceAll(">","&lt;"));

			data.put("detailInfoModel", detailInfoModel);
			data.put("detailAccessorialModels", detailAccessorialModels);

			AppUtils.template2File(outputFilePath, false, "templates/pdf/history/history_detail.ftl", data);
			String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/export_history_detail_"
					+ AppUtils.createMessageReference() + ".pdf";
			AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
			this.setFileName("export_history_detail.pdf");
			this.setStream(new FileInputStream(new File(pdfFilePath)));
			return "export";
		} catch (Exception e) {
			addActionError(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return "success";
	}

	public static String decode(String url) {
		try {
			String prevURL = "";
			String decodeURL = url;
			while (!prevURL.equals(decodeURL)) {
				prevURL = decodeURL;
				decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
			}
			return decodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while decoding" + e.getMessage();
		}
	}

	public static String encode(String url) {
		try {
			String encodeURL = URLEncoder.encode(url, "UTF-8");
			return encodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while encoding" + e.getMessage();
		}
	}

	protected String[] getCropsPercent(Integer serviceId) {
		String[] cropsPercent = null;
		switch (serviceId) {
		case 54:
			cropsPercent = SystemSettingCache.getInstance().getValueByKey("TNT Intl PDF Crops Percentage").split(",");
			break;
		case 3:
			cropsPercent = SystemSettingCache.getInstance().getValueByKey("TNT Dom PDF Crops Percentage").split(",");
			break;
		case 1:
		case 15:
			cropsPercent = SystemSettingCache.getInstance().getValueByKey("DHL PDF Crops Percentage").split(",");
			break;
		case 72:
			cropsPercent = SystemSettingCache.getInstance().getValueByKey("StarTrack PDF Crops Percentage").split(",");
			break;
		case 52:
		case 59:
			cropsPercent = SystemSettingCache.getInstance().getValueByKey("Toll PDF Crops Percentage").split(",");
			break;
		}
		return cropsPercent;
	}

	protected void genFileViewAirbill(String fileViewName) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String miniTime = "";
		miniTime = String.valueOf(System.currentTimeMillis());
		String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + fileViewName
				+ shipmentId + miniTime + ".html";
		detailInfoModel.setNoOfPieces(String.valueOf(pieceModels.size()));
		detailInfoModel.setrCompanyName(StringEscapeUtils.escapeXml(detailInfoModel.getrCompanyName()));
		detailInfoModel.setsCompanyName(StringEscapeUtils.escapeXml(detailInfoModel.getsCompanyName()));
		detailInfoModel.setrContactName(StringEscapeUtils.escapeXml(detailInfoModel.getrContactName()));
		detailInfoModel.setsContactName(StringEscapeUtils.escapeXml(detailInfoModel.getsContactName()));
		detailInfoModel.setContentDescription(StringEscapeUtils.escapeXml(detailInfoModel.getContentDescription()));
		detailInfoModel.setsAddress(StringEscapeUtils.escapeHtml(detailInfoModel.getsAddress()));
		detailInfoModel.setrAddress(StringEscapeUtils.escapeHtml(detailInfoModel.getrAddress()));
		detailInfoModel.setScDescription(StringEscapeUtils.escapeHtml(detailInfoModel.getScDescription()));
		detailInfoModel.setReferenceNo(StringEscapeUtils.escapeHtml(detailInfoModel.getReferenceNo()));
		data.put("detailPieceModels", detailPieceModels);
		data.put("detailInfoModel", detailInfoModel);
		data.put("detailAccessorialModels", detailAccessorialModels);
		data.put("isThermalLable", "0");
		data.put("listPieceViewAirbill", listPieceViewAirbill);
		data.put("pieceBarcode", pieceBarcode);
		data.put("tntBarcode", tntBarcode);
		data.put("productAirbillModels", productAirbillModels);
		data.put("ttAmount", totalAmount);
		if (small != null) {
			if (small.equals("1")) {
				data.put("isThermalLable", "1");
			}
		}
		AppUtils.template2File(outputFilePath, false, "templates/pdf/history/" + fileViewName + ".ftl", data);
		String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileViewName + shipmentId + miniTime
				+ ".pdf";
		AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
		this.setFileName(fileViewName + shipmentId + miniTime + ".pdf");
		this.setStream(new FileInputStream(new File(pdfFilePath)));
		this.setPathFileView("tmp/" + fileViewName + shipmentId + miniTime + ".pdf");
	}

	protected void genFileViewThermalLabel(String fileViewName, Integer serviceId) throws Exception {
		Float cropLeftPercent = 0f;
		Float cropWidthPercent = 1f;
		String[] cropsPercent = this.getCropsPercent(serviceId);
		try {
			cropLeftPercent = Float.valueOf(cropsPercent[0]);
			cropWidthPercent = Float.valueOf(cropsPercent[1]);
		} catch (Exception e) {
		}
		Map<String, Object> data = new HashMap<String, Object>();
		String miniTime = "";
		miniTime = String.valueOf(System.currentTimeMillis());
		String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + fileViewName
				+ shipmentId + miniTime + ".html";
		detailInfoModel.setNoOfPieces(String.valueOf(pieceModels.size()));
		detailInfoModel.setrCompanyName(StringEscapeUtils.escapeXml(detailInfoModel.getrCompanyName()));
		detailInfoModel.setsCompanyName(StringEscapeUtils.escapeXml(detailInfoModel.getsCompanyName()));
		detailInfoModel.setrContactName(StringEscapeUtils.escapeXml(detailInfoModel.getrContactName()));
		detailInfoModel.setsContactName(StringEscapeUtils.escapeXml(detailInfoModel.getsContactName()));
		detailInfoModel.setContentDescription(StringEscapeUtils.escapeXml(detailInfoModel.getContentDescription()));
		detailInfoModel.setsAddress(StringEscapeUtils.escapeHtml(detailInfoModel.getsAddress()));
		detailInfoModel.setrAddress(StringEscapeUtils.escapeHtml(detailInfoModel.getrAddress()));
		detailInfoModel.setScDescription(StringEscapeUtils.escapeHtml(detailInfoModel.getScDescription()));
		detailInfoModel.setReferenceNo(StringEscapeUtils.escapeHtml(detailInfoModel.getReferenceNo()));
		data.put("detailPieceModels", detailPieceModels);
		data.put("detailInfoModel", detailInfoModel);
		data.put("detailAccessorialModels", detailAccessorialModels);
		data.put("isThermalLable", "0");
		data.put("listPieceViewAirbill", listPieceViewAirbill);
		data.put("pieceBarcode", pieceBarcode);
		data.put("tntBarcode", tntBarcode);
		data.put("productAirbillModels", productAirbillModels);
		data.put("ttAmount", totalAmount);
		if (small != null) {
			if (small.equals("1")) {
				data.put("isThermalLable", "1");
			}
		}
		
		
		
		
		AppUtils.template2File(outputFilePath, false, "templates/pdf/history/" + fileViewName + ".ftl", data);
		String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileViewName + shipmentId + miniTime
				+ ".pdf";
		String thermalLabelName = getThermalFileName(serviceId) + shipmentId + miniTime + ".pdf";
		String thermalLabelPath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + thermalLabelName;
		String cropFilePath = outputFilePath + "_crop";
		AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
		AppUtils.cropPdf(pdfFilePath, cropFilePath, cropLeftPercent, cropWidthPercent); 
		new PdfScaleUtils().manipulatePdf(cropFilePath, thermalLabelPath, 0.3f);
		this.setFileName(thermalLabelName);
		this.setStream(new FileInputStream(new File(thermalLabelPath)));
		this.setPathFileView(thermalLabelPath);
	}

	protected String getThermalFileName(Integer serviceId) {
		String fileName = "";
		switch (serviceId) {
		case 2:
			fileName = "aae_thermal_label";
			break;
		case 3:
			fileName = "tnt_dom_thermal_label";
			break;
		case 40:
			fileName = "fed_ex_thermal_label";
			break;
		case 52:
			fileName = "toll_priority_thermal_label";
			break;
		case 54:
			fileName = "tnt_intl_thermal_label";
			break;
		case 59:
			fileName = "toll_ipec_thermal_label";
			break;
		}
		return fileName;
	}

	protected void genFileViewAirbill2SendEmail(String fileViewName) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String miniTime = "";
		miniTime = String.valueOf(System.currentTimeMillis());
		String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + fileViewName
				+ shipmentId + miniTime + ".html";
		detailInfoModel.setNoOfPieces(String.valueOf(pieceModels.size()));
		detailInfoModel.setrCompanyName(StringEscapeUtils.escapeXml(detailInfoModel.getrCompanyName()));
		detailInfoModel.setsCompanyName(StringEscapeUtils.escapeXml(detailInfoModel.getsCompanyName()));
		detailInfoModel.setrContactName(StringEscapeUtils.escapeXml(detailInfoModel.getrContactName()));
		detailInfoModel.setsContactName(StringEscapeUtils.escapeXml(detailInfoModel.getsContactName()));
		detailInfoModel.setContentDescription(StringEscapeUtils.escapeXml(detailInfoModel.getContentDescription()));
		detailInfoModel.setsAddress(StringEscapeUtils.escapeHtml(detailInfoModel.getsAddress()));
		detailInfoModel.setrAddress(StringEscapeUtils.escapeHtml(detailInfoModel.getrAddress()));
		detailInfoModel.setScDescription(StringEscapeUtils.escapeHtml(detailInfoModel.getScDescription()));
		detailInfoModel.setReferenceNo(StringEscapeUtils.escapeHtml(detailInfoModel.getReferenceNo()));
		data.put("detailPieceModels", detailPieceModels);
		data.put("detailInfoModel", detailInfoModel);
		data.put("detailAccessorialModels", detailAccessorialModels);
		data.put("isThermalLable", "0");
		data.put("listPieceViewAirbill", listPieceViewAirbill);
		data.put("pieceBarcode", pieceBarcode);
		data.put("tntBarcode", tntBarcode);
		data.put("productAirbillModels", productAirbillModels);
		data.put("ttAmount", totalAmount);
		if (small != null) {
			if (small.equals("1")) {
				data.put("isThermalLable", "1");
			}
		}
		AppUtils.template2File(outputFilePath, false, "templates/pdf/history/" + fileViewName + ".ftl", data);
		String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileViewName + shipmentId + miniTime
				+ ".pdf";
		AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
		this.setFileName(fileViewName + shipmentId + miniTime + ".pdf");
		this.setPathFileView("tmp/" + fileViewName + shipmentId + miniTime + ".pdf");
	}

	protected HistoryViewFileRequest prepareViewFile(String fileName) throws Exception {
		HistoryViewFileRequest requestViewFile = new HistoryViewFileRequest();
		requestViewFile.setServerPathImage(this.getServerPath("/images"));
		requestViewFile.setShipmentId(Long.parseLong(shipmentId));
		requestViewFile.setTmpFileOutputServer(request.getSession().getServletContext().getRealPath("") + "/tmp/");
		requestViewFile.setFileName(fileName);
		requestViewFile.setServerPathBarcode(this.getServerPath("/barcode"));
		return requestViewFile;
	}

	protected void putDataToViewAirbill(Integer serviceId) throws Exception {
		Integer count = 0;
		if (listPieceViewAirbill == null) {
			listPieceViewAirbill = new ArrayList<HistoryViewAirbillModel>();
		}

		File imgDefault = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		byte[] imgBytesDefault = AppUtils.readContentIntoByteArray(imgDefault);
		byte[] imgBytesAsBase64Default = Base64.encodeBase64(imgBytesDefault);
		String imgDataAsBase64Default = new String(imgBytesAsBase64Default);
		String imgAsBase64Default = "data:image/png;base64," + imgDataAsBase64Default;
		pieceBarcode = imgAsBase64Default;

		File imgTntDefault = new File(this.getServerPath("/images") + "/tnt.png");
		byte[] imgBytesTntDefault = AppUtils.readContentIntoByteArray(imgTntDefault);
		byte[] imgBytesAsBase64TntDefault = Base64.encodeBase64(imgBytesTntDefault);
		String imgDataAsBase64TntDefault = new String(imgBytesAsBase64TntDefault);
		String imgAsBase64TntDefault = "data:image/png;base64," + imgDataAsBase64TntDefault;
		tntBarcode = imgAsBase64TntDefault;

		String articlePrefix = SystemSettingCache.getInstance().getValueByKey("AAE Article Number Prefix").trim();
		if (pieceModels.size() > 0) {
			for (PieceModel piece : pieceModels) {
				count += 1; 
				HistoryViewAirbillModel historyViewAirbillModel = new HistoryViewAirbillModel("", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "");
				if (serviceId == 3) {
					prepareDataTntDom(count, historyViewAirbillModel, piece);
				}
				if (serviceId == 54) {
					prepareDataTntInt(count, historyViewAirbillModel, piece);
				}
				if (serviceId == 59) {
					prepareDataTollIpec(count, historyViewAirbillModel, piece);
				}

				if (serviceId == 52) {
					prepareDataTollPriority(count, historyViewAirbillModel, piece);
				}

				if (serviceId == 2) {
					prepareDataAaeViewAirbill(count, historyViewAirbillModel, piece, articlePrefix);
				}

				detailInfoModel.setNoOfPieces(count.toString());
				listPieceViewAirbill.add(historyViewAirbillModel);
			}
		} else {
			count = 1;
			PieceModel piece = new PieceModel();
			piece.setCustomValue("");
			piece.setDeadWeight("0");
			piece.setDimension("");
			piece.setQuantity("0");
			piece.setWeight("0");
			HistoryViewAirbillModel historyViewAirbillModel = new HistoryViewAirbillModel("", "", "", "", "", "", "",
					"", "", "", "", "", "", "", "", "", "", "");
			if (serviceId == 3) {
				prepareDataTntDom(count, historyViewAirbillModel, piece);
			}
			if (serviceId == 54) {
				prepareDataTntInt(count, historyViewAirbillModel, piece);
			}
			if (serviceId == 59) {
				prepareDataTollIpec(count, historyViewAirbillModel, piece);
			}

			if (serviceId == 52) {
				prepareDataTollPriority(count, historyViewAirbillModel, piece);
			}

			if (serviceId == 2) {
				prepareDataAaeViewAirbill(count, historyViewAirbillModel, piece, articlePrefix);
			}

			listPieceViewAirbill.add(historyViewAirbillModel);
			detailInfoModel.setNoOfPieces(count.toString());
		}
	}

	protected boolean validAirbillBarcode() throws Exception {
		IHistoryService iHistoryService = new HistoryServiceImp();
		if (StringUtils.isEmpty(iHistoryService.getAwbBarcode(Long.parseLong(shipmentId)))) {
			addFieldError("historyViewAirbillModel.barcode", "Can not Find Barcode!");
		} else {
			this.setAwbBarcode(iHistoryService.getAwbBarcode(Long.parseLong(shipmentId)));
			this.setShipmentId(shipmentId);
			String filePath = this.getServerPath("/barcode") + "/" + shipmentId + ".pdf";
			AppUtils.createPDFFromBarCode(filePath, awbBarcode);
			this.setFileName(shipmentId + ".pdf");
			this.setStream(new FileInputStream(new File(filePath)));
		}
		return !hasActionErrors() && !hasFieldErrors();
	}

	protected void prepareDataAaeViewAirbill(Integer count, HistoryViewAirbillModel historyViewAirbillModel,
			PieceModel piece, String articlePrefix) throws Exception {
		String articleId = "";
		if (piece.getLicensePlate().length() > 5) {
			articlePrefix = "";
		}
		if (piece.getLicensePlate().equals("")) {
			articleId = AppUtils.leftPad(count.toString(), 5, "0".charAt(0));
		} else {
			articleId = AppUtils.leftPad(piece.getLicensePlate(), 5, "0".charAt(0));
		}
		articleId = articlePrefix.concat(articleId);
		String atlCode = "";
		atlCode = detailInfoModel.getDhlRoutingDataId();
		String atlPrefix = SystemSettingCache.getInstance().getValueByKey("AAE ATL Number Prefix");
		String atlEnd = SystemSettingCache.getInstance().getValueByKey("AAE ATL Number End");
		String atlNumber = AppUtils.leftPad(atlCode, atlEnd.length(), "0".charAt(0));
		if (StringUtils.isEmpty(atlCode)) {
			atlCode = "1";
		}
		atlCode = atlPrefix.concat(atlNumber);

		AppUtils.createBarCode(atlCode, "png",
				this.getServerPath("/barcode") + "/ROUTING_" + shipmentId + count + ".png", 130, 15);
		File imgAtlCode = new File(this.getServerPath("/barcode") + "/ROUTING_" + shipmentId + count + ".png");
		if (detailInfoModel.getStatus().equals("1")) {
			imgAtlCode = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		}

		byte[] imgBytesAtlCode = AppUtils.readContentIntoByteArray(imgAtlCode);
		byte[] imgBytesAsBase64AtlCode = Base64.encodeBase64(imgBytesAtlCode);
		String imgDataAsBase64AtlCode = new String(imgBytesAsBase64AtlCode);
		String imgAsBase64AtlCode = "data:image/png;base64," + imgDataAsBase64AtlCode;
		historyViewAirbillModel.setImageBarcode3(imgAsBase64AtlCode);
		historyViewAirbillModel.setPieceBarcode3(atlCode);

		if (detailInfoModel.getReferenceNo().equals("")) {
			detailInfoModel.setReferenceNo(detailInfoModel.getCustomerCode());
		}

		String routingCode = detailInfoModel.getProductContentCode().concat(detailInfoModel.getrPostalCode())
				.concat(detailInfoModel.getPrimaryPort());
		AppUtils.createBarCode(routingCode, "png",
				this.getServerPath("/barcode") + "/ROUTING_" + shipmentId + count + ".png", 200, 60);
		File imgRoutingCode = new File(this.getServerPath("/barcode") + "/ROUTING_" + shipmentId + count + ".png");
		if (detailInfoModel.getStatus().equals("1")) {
			imgRoutingCode = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		}

		byte[] imgBytesRoutingCode = AppUtils.readContentIntoByteArray(imgRoutingCode);
		byte[] imgBytesAsBase64RoutingCode = Base64.encodeBase64(imgBytesRoutingCode);
		String imgDataAsBase64RoutingCode = new String(imgBytesAsBase64RoutingCode);
		String imgAsBase64RoutingCode = "data:image/png;base64," + imgDataAsBase64RoutingCode;
		historyViewAirbillModel.setImageBarcode(imgAsBase64RoutingCode);
		historyViewAirbillModel.setPieceBarcode(routingCode);

		if (StringUtils.isEmpty(detailInfoModel.getPackageType())) {
			detailInfoModel.setPackageType("CARTON");
		}

		String freightId = "A".concat(detailInfoModel.getTracking()).concat(articleId);
		AppUtils.createBarCode(freightId, "png", this.getServerPath("/barcode") + "/AEE_" + shipmentId + count + ".png",
				250, 75);
		File img = new File(this.getServerPath("/barcode") + "/AEE_" + shipmentId + count + ".png");
		if (detailInfoModel.getStatus().equals("1")) {
			img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		}

		historyViewAirbillModel.setArticleId(articleId);
		historyViewAirbillModel.setPicesCount(count.toString());
		byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
		byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
		String imgDataAsBase64 = new String(imgBytesAsBase64);
		String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
		historyViewAirbillModel.setImageBarcode2(imgAsBase64);
		historyViewAirbillModel.setPieceBarcode2(freightId);
		//historyViewAirbillModel.setWeight(piece.getDeadWeight());  //previous code
		historyViewAirbillModel.setDimWeight(piece.getDeadWeight());
		historyViewAirbillModel.setWeight(piece.getWeight());
	}

	protected void prepareDataTollIpec(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece)
			throws Exception {
		String reasonForExport = "";
		if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
			reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
		}
		if (detailInfoModel.getShipmentTypeId().equals("215")) {
			detailInfoModel.setServiceCode("3");
		}
		if (detailInfoModel.getShipmentTypeId().equals("216")) {
			detailInfoModel.setServiceCode("4");
		}
		if (detailInfoModel.getShipmentTypeId().equals("217")) {
			detailInfoModel.setServiceCode("8");
		}
		String productCode = "";
		if (detailInfoModel.getServiceGroup() != null) {
			productCode = detailInfoModel.getServiceGroup().replaceAll("0", "");
		}
		String pieceBarcode = "6".concat(detailInfoModel.getServiceCode()).concat(productCode)
				.concat(detailInfoModel.getrPostalCode().concat(detailInfoModel.getTracking()))
				.concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0)));
		historyViewAirbillModel.setPieceBarcode(pieceBarcode);
		AppUtils.createBarCode(pieceBarcode, "png",
				this.getServerPath("/barcode") + "/TOLL_IPEC_" + shipmentId + count + ".png", 300, 90);

		historyViewAirbillModel.setItemCode(
				detailInfoModel.getTracking().concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0))));
		File img = new File(this.getServerPath("/barcode") + "/TOLL_IPEC_" + shipmentId + count + ".png");
		if (detailInfoModel.getStatus().equals("1")) {
			img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		}

		historyViewAirbillModel.setPicesCount(count.toString());
		byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
		byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
		String imgDataAsBase64 = new String(imgBytesAsBase64);
		String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

		historyViewAirbillModel.setImageBarcode(imgAsBase64);
		if (piece.getDeadWeight() != null) {
			historyViewAirbillModel.setDimWeight(piece.getDeadWeight());//setWeight(piece.getDeadWeight());
		}
		if (piece.getWeight() != null) {
			historyViewAirbillModel.setWeight(piece.getWeight());
		}
		if (piece.getDimension() != null) {
			historyViewAirbillModel.setDimession(piece.getDimension());
		}
		historyViewAirbillModel.setReasonForExport(reasonForExport);
	}

	protected void prepareDataTollPriority(Integer count, HistoryViewAirbillModel historyViewAirbillModel,
			PieceModel piece) throws Exception {
		String airbillNo = detailInfoModel.getrPostalCode().concat(detailInfoModel.getServiceCode())
				.concat(detailInfoModel.getConnNumber()).concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0)));
		String reasonForExport = "";
		if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
			reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
		}
		String pieceBarcode = ShipmentUtils.getTollCheckDigit("T".concat(airbillNo));
		String pieceBarcode2 = ShipmentUtils.getTollCheckDigit(detailInfoModel.getConnNumber().concat(airbillNo));
		historyViewAirbillModel.setPieceBarcode(pieceBarcode);
		AppUtils.createBarCode(pieceBarcode, "png",
				this.getServerPath("/barcode") + "/TOLL_PRIORITY_" + shipmentId + count + ".png", 388, 42);
		AppUtils.createBarCode(pieceBarcode2, "png",
				this.getServerPath("/barcode") + "/TOLL_PRIORITY_1_" + shipmentId + count + ".png", 388, 42);

		historyViewAirbillModel.setItemCode(
				detailInfoModel.getTracking().concat(AppUtils.leftPad(count.toString(), 3, "0".charAt(0))));
		File img = new File(this.getServerPath("/barcode") + "/TOLL_PRIORITY_" + shipmentId + count + ".png");
		if (detailInfoModel.getStatus().equals("1")) {
			img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		}

		historyViewAirbillModel.setPicesCount(count.toString());
		byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
		byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
		String imgDataAsBase64 = new String(imgBytesAsBase64);
		String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

		File img2 = new File(this.getServerPath("/barcode") + "/TOLL_PRIORITY_1_" + shipmentId + count + ".png");
		if (detailInfoModel.getStatus().equals("1")) {
			img2 = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		}
		byte[] imgBytes2 = AppUtils.readContentIntoByteArray(img2);
		byte[] imgBytesAsBase642 = Base64.encodeBase64(imgBytes2);
		String imgDataAsBase642 = new String(imgBytesAsBase642);
		String imgAsBase642 = "data:image/png;base64," + imgDataAsBase642;
		historyViewAirbillModel.setDimWeight(piece.getWeight());
		historyViewAirbillModel.setImageBarcode(imgAsBase64);
		historyViewAirbillModel.setImageBarcode2(imgAsBase642);
		if (piece.getDeadWeight() != null) {
			historyViewAirbillModel.setWeight(piece.getDeadWeight());
		}
		if (piece.getDimension() != null) {
			historyViewAirbillModel.setDimession(piece.getDimension());
		}
		historyViewAirbillModel.setReasonForExport(reasonForExport);
	}

	protected void prepareDataTntInt(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece)
			throws Exception {
		historyViewAirbillModel.setPieceBarcode(detailInfoModel.getTracking());
		String reasonForExport = "";
		if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
			reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
		}
		AppUtils.createBarCode(detailInfoModel.getTracking(), "png",
				this.getServerPath("/barcode") + "/TNT_INT_" + shipmentId + count + ".png", 300, 90);

		File img = new File(this.getServerPath("/barcode") + "/TNT_INT_" + shipmentId + count + ".png");
		if (detailInfoModel.getStatus().equals("1")) {
			img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		}
		historyViewAirbillModel.setPicesCount(count.toString());
		byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
		byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
		String imgDataAsBase64 = new String(imgBytesAsBase64);
		String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

		File imgTnt = new File(this.getServerPath("/images") + "/tnt.png");
		byte[] imgBytesTnt = AppUtils.readContentIntoByteArray(imgTnt);
		byte[] imgBytesAsBase64Tnt = Base64.encodeBase64(imgBytesTnt);
		String imgDataAsBase64Tnt = new String(imgBytesAsBase64Tnt);
		String imgAsBase64Tnt = "data:image/png;base64," + imgDataAsBase64Tnt;

		historyViewAirbillModel.setTntLogo(imgAsBase64Tnt);
		historyViewAirbillModel.setImageBarcode(imgAsBase64);
		if (piece.getDeadWeight() != null) {
			historyViewAirbillModel.setWeight(piece.getDeadWeight());
		}
		if (piece.getDimension() != null) {
			historyViewAirbillModel.setDimession(piece.getDimension());
		}

		historyViewAirbillModel.setReasonForExport(reasonForExport);
	}

	protected void prepareDataTntDom(Integer count, HistoryViewAirbillModel historyViewAirbillModel, PieceModel piece)
			throws Exception {
		String reasonForExport = "";
		if (!StringUtils.isEmpty(detailInfoModel.getReasonForExport().trim())) {
			reasonForExport = "Authorize to Leave : ".concat(detailInfoModel.getReasonForExport());
		}
		String destinationPortDescription = "NULL";
		TntRouteViewFilter tntRouteFilter = new TntRouteViewFilter();
		tntRouteFilter.setDestinationSuburb(detailInfoModel.getrCity());
		tntRouteFilter.setPostCode(detailInfoModel.getrPostalCode());
		tntRouteFilter.setServiceGroup(detailInfoModel.getServiceGroup());
		IHistoryDetailService serviceHistory = new HistoryDetailServiceImp();
		List<TntRouteModel> tntRouteModels = new ArrayList<TntRouteModel>();
		tntRouteModels = ModelUtils.createListModelFromVo(serviceHistory.selectByFilterView(tntRouteFilter),
				TntRouteModel.class);
		String sortBin = "0000";
		String exPort = "";
		String defaultRout = "";
		if (StringUtils.isEmpty(detailInfoModel.getServiceGroup())) {
			detailInfoModel.setServiceGroup("EXP");
		}
		if (!StringUtils.isEmpty(detailInfoModel.getOriginDepot().trim())) {
			exPort = "Ex ".concat(detailInfoModel.getOriginDepot());
		}
		for (TntRouteModel tntRouteModel : tntRouteModels) {
			if ("".equals(defaultRout) || tntRouteModel.getOriginDepot().equals(detailInfoModel.getOriginDepot())) {
				sortBin = tntRouteModel.getRouteBin();

				if (tntRouteModel.getGatewayDepot().equals(tntRouteModel.getForwardingDepot())) {
					destinationPortDescription = tntRouteModel.getGatewayDepot();
				} else {
					destinationPortDescription = "via ".concat(tntRouteModel.getGatewayDepot()).concat(" to ")
							.concat(tntRouteModel.getForwardingDepot());
				}

				if (tntRouteModel.getOriginDepot().equals(detailInfoModel.getOriginDepot())) {
					break;
				}
			}
			defaultRout = "1";
		}

		String strDanger = "Does Not Contain Dangerous Goods";
		if (detailInfoModel.getCourierMessage() != null) {
			if (detailInfoModel.getCourierMessage().trim().equals("DG")) {
				String dgary[] = detailInfoModel.getDhlRoutingCode().split("##@##");
				String UNno = "";
				if (dgary[0] != null) {
					UNno = dgary[0];
				}
				String dGPkGroup = "";
				if (dgary[1] != null) {
					dGPkGroup = dgary[1];
				}
				strDanger = "Contain Dangerous Goods, UN Number(".concat(UNno).concat("), Packaging Group(")
						.concat(dGPkGroup).concat("), ").concat(detailInfoModel.getContentDescription());
			}
		} else {
			detailInfoModel.setCourierMessage("");
		}

		historyViewAirbillModel
				.setItemCode(ShipmentUtils.genTNTItemIdentifier("", detailInfoModel.getTracking(), count.toString()));
		historyViewAirbillModel.setPieceBarcode(
				"6104".concat(ShipmentUtils.genTNTItemIdentifier("", detailInfoModel.getTracking(), count.toString()))
						.concat("0").concat(detailInfoModel.getrPostalCode()).concat("0"));
		AppUtils.createBarCode(
				"6104".concat(ShipmentUtils.genTNTItemIdentifier("", detailInfoModel.getTracking(), count.toString()))
						.concat("0").concat(detailInfoModel.getrPostalCode()).concat("0"),
				"png", this.getServerPath("/barcode") + "/TNT_DOM_" + shipmentId + count + ".png", 300, 90);
		historyViewAirbillModel.setPicesCount(count.toString());

		File img = new File(this.getServerPath("/barcode") + "/TNT_DOM_" + shipmentId + count + ".png");
		if (detailInfoModel.getStatus().equals("1")) {
			img = new File(this.getServerPath("/images") + "/AWB_VOID.jpg");
		}
		byte[] imgBytes = AppUtils.readContentIntoByteArray(img);
		byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
		String imgDataAsBase64 = new String(imgBytesAsBase64);
		String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
		historyViewAirbillModel.setImageBarcode(imgAsBase64);
		if (piece.getDeadWeight() != null) {
			historyViewAirbillModel.setWeight(piece.getDeadWeight());
		}
		historyViewAirbillModel.setDestinationPortDescription(destinationPortDescription);
		historyViewAirbillModel.setSortBin(sortBin);
		historyViewAirbillModel.setExPort(exPort);
		historyViewAirbillModel.setStrDanger(strDanger);
		historyViewAirbillModel.setReasonForExport(reasonForExport);
	}

	protected boolean validViewAirbill() {
		if (StringUtils.isEmpty(this.getShipmentId())) {
			addFieldError("shipmentId",
					"Shipment does not exist in the system or it lacks information. Please turn back.!");
		}
		return !hasActionErrors() && !hasFieldErrors();
	}

	protected void prepareHistoryDetail() throws Exception {
		IHistoryDetailService detailService = new HistoryDetailServiceImp();
		
		HistoryDetailFilter filter = new HistoryDetailFilter();
		
		filter = ModelUtils.createVoFromModel(this.getDetailFilterModel(), HistoryDetailFilter.class);
		
		HistoryDetailInfoVo historyDetailInfoVo = detailService.selectHistoryDetailInfo(filter);
		
		this.setDetailInfoModel(ModelUtils.createModelFromVo(historyDetailInfoVo, HistoryDetailInfoModel.class));
		
		this.setPieceModels(ModelUtils.createListModelFromVo(
				detailService.selectPieceByIdNonGroup(Long.parseLong(shipmentId)), PieceModel.class));
		
		this.setDetailPieceModels(ModelUtils.createListModelFromVo(detailService.selectPieceInfo(filter, true),
				HistoryDetailPieceModel.class));
		
		this.setDetailAccessorialModels(ModelUtils.createListModelFromVo(
				detailService.selectHistoryDetailAccessorial(filter, historyDetailInfoVo),
				HistoryDetailAccessorialModel.class));
	}

	protected void prepareHistoryDetailFilter() throws Exception {
		if (validHistoryDetail()) {
			HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
			detailFilterModelN.setShipmentId(shipmentId);
			detailFilterModelN.setLbToKg("0.45359237");
			detailFilterModelN.setInToCm("2.54");
			detailFilterModelN.setWeightValue("5000");
			IServiceService serviceService = new ServiceServiceImp();
			ServiceVo serviceVo = serviceService.selectAllByShipmentId(Long.parseLong(shipmentId));
			detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(serviceVo.getServiceId()).toString());
			this.setDetailFilterModel(detailFilterModelN);
		}
	}

	protected void sentEmail() throws Exception {
		IHistoryService iHistoryService = new HistoryServiceImp();
		this.setAwbBarcode(iHistoryService.getAwbBarcode(Long.parseLong(shipmentId)));

		String fileFtlViewAirbill = "";
		if (validViewAirbill()) {
			IServiceService service = new ServiceServiceImp();
			ServiceVo serviceVo = service.selectAllByShipmentId(Long.parseLong(shipmentId));

			IShipmentService shipmentService = new ShipmentServiceImp();
			ShipmentVo shipmentVo = shipmentService.selectById(Long.parseLong(this.getShipmentId()));

			ContextBase2 context = new ContextBase2(this.getAddInfoMap());
			HistoryViewFileRequest fileRequest = new HistoryViewFileRequest();
			prepareHistoryDetailFilter();
			prepareHistoryDetail();
			switch (serviceVo.getServiceId()) {
			case 1:
			case 15:
				IDhlRender dhlRender = new DhlRenderImp();
				String outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/dhl_airbill_"
						+ this.getDetailInfoModel().getAirbillNumber() + ".pdf";
				dhlRender.genAirbillFile(outPutFilePath, Long.parseLong(shipmentId));
				this.setFileName("dhl_airbill_" + this.getDetailInfoModel().getAirbillNumber() + ".pdf");
				this.setStream(new FileInputStream(new File(outPutFilePath)));
				this.setPathFileView(AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + "dhl_airbill_"
						+ this.getDetailInfoModel().getAirbillNumber() + ".pdf");
				break;
			case 400:
				dhlRender = new DhlRenderImp();
				outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/ups_airbill_"
						+ this.getDetailInfoModel().getAirbillNumber() + ".pdf";
				// dhlRender.genAirbillFile(outPutFilePath,
				// Long.parseLong(shipmentId)); //code by rakesh sir
				dhlRender.genAirbillUpsFile(outPutFilePath, Long.parseLong(shipmentId));
				this.setFileName("ups_airbill_" + this.getDetailInfoModel().getAirbillNumber() + ".pdf");
				this.setStream(new FileInputStream(new File(outPutFilePath)));
				this.setPathFileView(AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + "ups_airbill_"
						+ this.getDetailInfoModel().getAirbillNumber() + ".pdf");
				break;
			case 54: // TNT INTER
				fileRequest = prepareViewFile("tntIntViewAirbill");
				context.put(Attributes.WFP_NAME, "Wfl-TntViewAirbill");
				context.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, fileRequest);
				HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
				context = WorkFlowManager2.getInstance().process(context);
				if (ErrorCode.ERROR.equals(context.get(Attributes.ERROR_CODE))) {
					addActionError(context.getString(Attributes.ERROR_MESSAGE));
					log.error(context.getString(Attributes.ERROR_MESSAGE));
				} else {
					fileResultVo = context.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
				}
				this.setFileName(fileResultVo.getFileName());
				this.setPathFileView(this.getServerPath("tmp") + "/" + this.getFileName());
				break;
			case 72:
				if (shipmentVo == null) {
					throw new CustomException("This shipment doesn't exist.");
				} else {
					String pdfString = shipmentVo.getLabelPdf();
					String fileName = "startrack_" + shipmentVo.getAirbillNumber() + ".pdf";
					String filePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileName;
					AppUtils.createPDFFromBarCode(filePath, pdfString);
					this.setFileName(fileName);
					this.setPathFileView(filePath);
				}
				break;
			default:
				putDataToViewAirbill(Integer.parseInt(detailInfoModel.getServiceId()));
				fileFtlViewAirbill = ShipmentUtils.checkViewAirbill(Integer.parseInt(detailInfoModel.getServiceId()));
				genFileViewAirbill2SendEmail(fileFtlViewAirbill);
				this.setPathFileView(this.getServerPath("tmp") + "/" + this.getFileName());
			}

			String tntConnoteFilePath = "";
			String tntConnoteFilePathNew = AppConstants.APP_SETTINGS.getAppTmpPath() + "/tnt_connote_"
					+ this.getDetailInfoModel().getAirbillNumber() + ".pdf";
			if (serviceVo.getServiceId() == 54) {
				ContextBase2 connoteContext = new ContextBase2(this.getAddInfoMap());
				connoteContext.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
				connoteContext.put(Attributes.WFP_NAME, "Wfl-ViewTntConnote");
				connoteContext = WorkFlowManager2.getInstance().process(connoteContext);
				if (connoteContext.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
					tntConnoteFilePath = connoteContext.getString(Attributes.FILE_PATH);
					File oldConnoteFile = new File(tntConnoteFilePath);
					File newConnoteFile = new File(tntConnoteFilePathNew);
					if (oldConnoteFile.renameTo(newConnoteFile)) {
					} else {
						throw new CustomException("Can't send email. Cannot get connote file.");
					}
				}
			}

			String manifestFilePath = "";
			String manifestFilePathNew = "";

			if (serviceVo.getServiceId() == 3) {
				ContextBase2 manifestContext = new ContextBase2(this.getAddInfoMap());
				fileRequest = prepareViewFile("tntDomViewManifest");
				manifestContext.put(Attributes.WFP_NAME, "Wfl-TntDomViewManifest");
				manifestContext.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, fileRequest);
				manifestContext = WorkFlowManager2.getInstance().process(manifestContext);
				HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
				if (ErrorCode.ERROR.equals(manifestContext.get(Attributes.ERROR_CODE))) {
					addActionError(manifestContext.getString(Attributes.ERROR_MESSAGE));
					log.error(manifestContext.getString(Attributes.ERROR_MESSAGE));
				} else {
					fileResultVo = manifestContext.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
				}

				manifestFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + File.separator
						+ fileResultVo.getFileName();
				manifestFilePathNew = AppConstants.APP_SETTINGS.getAppTmpPath() + File.separator
						+ fileResultVo.getFileName();
			}

			if (serviceVo.getServiceId() == 52) {
				ContextBase2 manifestContext = new ContextBase2(this.getAddInfoMap());
				fileRequest = prepareViewFile("tollPriorityNewStyleViewManifest");
				manifestContext.put(Attributes.WFP_NAME, "Wfl-TollPriorityViewManifestNew");
				manifestContext.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, fileRequest);
				manifestContext = WorkFlowManager2.getInstance().process(manifestContext);
				HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
				if (ErrorCode.ERROR.equals(manifestContext.get(Attributes.ERROR_CODE))) {
					addActionError(manifestContext.getString(Attributes.ERROR_MESSAGE));
					log.error(manifestContext.getString(Attributes.ERROR_MESSAGE));
				} else {
					fileResultVo = manifestContext.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
				}

				manifestFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + File.separator
						+ fileResultVo.getFileName();
				manifestFilePathNew = AppConstants.APP_SETTINGS.getAppTmpPath() + File.separator
						+ fileResultVo.getFileName();
			}

			if (serviceVo.getServiceId() == 59) {
				ContextBase2 manifestContext = new ContextBase2(this.getAddInfoMap());
				fileRequest = prepareViewFile("tollIpecNewStyleViewManifest");
				manifestContext.put(Attributes.WFP_NAME, "Wfl-TollIpecViewManifestNew");
				manifestContext.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, fileRequest);
				manifestContext = WorkFlowManager2.getInstance().process(manifestContext);
				HistoryViewFileResultVo fileResultVo = new HistoryViewFileResultVo();
				if (ErrorCode.ERROR.equals(manifestContext.get(Attributes.ERROR_CODE))) {
					addActionError(manifestContext.getString(Attributes.ERROR_MESSAGE));
					log.error(manifestContext.getString(Attributes.ERROR_MESSAGE));
				} else {
					fileResultVo = manifestContext.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
				}

				manifestFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + File.separator
						+ fileResultVo.getFileName();
				manifestFilePathNew = AppConstants.APP_SETTINGS.getAppTmpPath() + File.separator
						+ fileResultVo.getFileName();
			}

			if (serviceVo.getServiceId() == 72) {
				manifestFilePathNew = AppConstants.APP_SETTINGS.getAppTmpPath() + "/startrack_manifest_"
						+ this.getDetailInfoModel().getAirbillNumber() + ".pdf";
				ContextBase2 manifestContext = new ContextBase2(this.getAddInfoMap());
				manifestContext.put(Attributes.SHIPMENT_ID, Long.valueOf(this.getShipmentId()));
				manifestContext.put(Attributes.WFP_NAME, "Wfl-StarTrackViewManifest");
				manifestContext = WorkFlowManager2.getInstance().process(manifestContext);
				if (manifestContext.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
					manifestFilePath = manifestContext.getString(Attributes.FILE_PATH);
					File oldManifestFile = new File(manifestFilePath);
					File newManifestFile = new File(manifestFilePathNew);
					if (oldManifestFile.renameTo(newManifestFile)) {
					} else {
						throw new CustomException("Can't send email. Cannot get manifest file.");
					}
				} else {
					throw new CustomException("Can't send email. Cannot get manifest file.");
				}
			}

			String commercialInvoiceFilePath = "";
			String commercialInvoiceFilePathNew = AppConstants.APP_SETTINGS.getAppTmpPath() + "/commercial_invoice_"
					+ this.getDetailInfoModel().getAirbillNumber() + ".pdf";
			if (shipmentVo != null && shipmentVo.getCommercialInvoiceId() == 3) {
				HistoryViewFileRequest commercialInvoiceViewFileReq = prepareViewFile("viewCommercialInvoice");
				ContextBase2 commercialInvoiceContext = new ContextBase2(this.getAddInfoMap());
				commercialInvoiceContext.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, commercialInvoiceViewFileReq);
				commercialInvoiceContext.put(Attributes.WFP_NAME, "Wfl-DHLViewCommercialInvoice");
				HistoryViewFileResultVo historyViewCommercialResultVo = new HistoryViewFileResultVo();
				commercialInvoiceContext = WorkFlowManager2.getInstance().process(commercialInvoiceContext);
				if (!ErrorCode.ERROR.equals(commercialInvoiceContext.get(Attributes.ERROR_CODE))) {
					historyViewCommercialResultVo = commercialInvoiceContext.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
					commercialInvoiceFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/"
							+ historyViewCommercialResultVo.getFileName();
					File oldCommercialInvoiceFile = new File(commercialInvoiceFilePath);
					File newCommercialInvoiceFile = new File(commercialInvoiceFilePathNew);
					if (oldCommercialInvoiceFile.renameTo(newCommercialInvoiceFile)) {
					} else {
						throw new CustomException("Can't send email. Cannot get commercial invoices file.");
					}
				} else {
					throw new CustomException("Can't send email. Cannot get commercial invoices file.");
				}

			}

			String packingListFilePath = "";
			String packingListFilePathNew = AppConstants.APP_SETTINGS.getAppTmpPath() + "/packing_list_"
					+ this.getDetailInfoModel().getAirbillNumber() + ".pdf";
			if (shipmentVo != null && shipmentVo.getPackingList() == 2) {
				HistoryViewFileRequest packingListViewFileReq = prepareViewFile("viewPackingList");
				ContextBase2 packingListContext = new ContextBase2(this.getAddInfoMap());
				packingListContext.put(Attributes.HISTORY_VIEW_AIRBILL_REQUEST, packingListViewFileReq);
				packingListContext.put(Attributes.WFP_NAME, "Wfl-DHLViewPakingList");
				HistoryViewFileResultVo viewPackingListResultVo = new HistoryViewFileResultVo();
				packingListContext = WorkFlowManager2.getInstance().process(packingListContext);
				if (!ErrorCode.ERROR.equals(packingListContext.get(Attributes.ERROR_CODE))) {
					viewPackingListResultVo = packingListContext.get(Attributes.VIEW_AIRBILL_RESULT_DATA);
					packingListFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/"
							+ viewPackingListResultVo.getFileName();
					File oldPackingListFile = new File(packingListFilePath);
					File newPackingListFile = new File(packingListFilePathNew);
					if (oldPackingListFile.renameTo(newPackingListFile)) {
					} else {
						throw new CustomException("Can't send email. Cannot get packing list file.");
					}
				} else {
					throw new CustomException("Can't send email. Cannot get packing list file.");
				}

			}
			// code by rakesh sir
			String airbill_pdf = this.getPathFileView();
			String newFileName = AppConstants.APP_SETTINGS.getAppTmpPath() + "/"
					+ this.getDetailInfoModel().getAirbillNumber() + ".pdf";
			// code by shahabuddin just one line below
			// String newFileName = AppConstants.APP_SETTINGS.getAppTmpPath() +
			// "/" + this.getDetailInfoModel().getAirbillNumber();
			File oldFile = new File(StringUtils.replace(airbill_pdf, "\\", "/"));
			File newFile = new File(StringUtils.replace(newFileName, "\\", "/"));

			if (oldFile.renameTo(newFile)) {
			} else {
				throw new CustomException("Can't send email.");
			}

			List<String> files = new ArrayList<>();
			files.add(newFileName);
			if (!StringUtils.isBlank(tntConnoteFilePath)) {
				files.add(tntConnoteFilePathNew);
			}
			if (!StringUtils.isBlank(manifestFilePath)) {
				files.add(manifestFilePathNew);
			}
			if (!StringUtils.isBlank(commercialInvoiceFilePath)) {
				files.add(commercialInvoiceFilePathNew);
			}
			if (!StringUtils.isBlank(packingListFilePath)) {
				files.add(packingListFilePathNew);
			}

			String[] filepath = new String[files.size()];
			filepath = files.toArray(new String[0]);

			sendAirlbillHistoryFillterModel.setFilepath(filepath);
			String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
			String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
			String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
			String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
			int smtpPortNumber = Integer
					.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
			String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
			EmailTemplateVo emailTemplateVo = iHistoryService
					.getEmailTemplateByName(sendAirlbillHistoryFillterModel.getTemplateEmail());
			String subject = emailTemplateVo.getSubject();
			subject = subject.replace("[Airbill Number]", this.getDetailInfoModel().getAirbillNumber());
			String content = emailTemplateVo.getTemplateContent();
			Map<String, String> replaceMap = new HashMap<String, String>();
			replaceMap.put("[Airbill Number]", this.getDetailInfoModel().getAirbillNumber());
			replaceMap.put("\r\n", "<br>");
			content = AppUtils.replaceStringByMap(replaceMap, content);
			sendMailExecutor(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword,
					sendAirlbillHistoryFillterModel.getEmails(), "", "", subject, content, filepath);
		} else {
			throw new Exception("No Shipment.");
		}
	}

	protected boolean validEmail() {
		if (StringUtils.contains(sendAirlbillHistoryFillterModel.getEmails(), ";")) {
			String[] emailAddresses = sendAirlbillHistoryFillterModel.getEmails().split(";");
			for (int i = 0; i < emailAddresses.length; i++) {
				String email = emailAddresses[i];
				if (!HelperUtils.isEmailAddress(email)) {
					addFieldError("sendAirlbillHistoryFillterModel", "Email number " + i + " is not valid!");
					setErrorCode(ErrorCode.FIELD_ERROR);
					return false;
				}
			}
		} else {
			if (!HelperUtils.isEmailAddress(sendAirlbillHistoryFillterModel.getEmails())) {
				addFieldError("sendAirlbillHistoryFillterModel",
						"Email is not valid! Please use \";\" if you want to send multiple email.");
				setErrorCode(ErrorCode.FIELD_ERROR);
				return false;
			}
		}
		return true;
	}

	protected boolean validHistoryDetail() {
		if (StringUtils.isEmpty(shipmentId)) {
			addFieldError("detailFilterModel.shipmentId", "Please select a shipment!");
		}
		return !hasActionErrors() && !hasFieldErrors();
	}

	private void sendMailExecutor(final String host, final int port, final String fromName, final String fromEmail,
			final String username, final String password, final String toEmail, final String cc, final String bcc,
			final String subject, final String content, final String[] attachFiles) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					AppUtils.sendEmail(host, port, fromName, fromEmail, username, password, toEmail, cc, bcc, subject,
							content, attachFiles);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		});
		executorService.shutdown();
	}

	public HistoryDetailFilterModel getDetailFilterModel() {
		return detailFilterModel;
	}

	public void setDetailFilterModel(HistoryDetailFilterModel detailFilterModel) {
		this.detailFilterModel = detailFilterModel;
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public List<HistoryDetailAccessorialModel> getDetailAccessorialModels() {
		return detailAccessorialModels;
	}

	public void setDetailAccessorialModels(List<HistoryDetailAccessorialModel> detailAccessorialModels) {
		this.detailAccessorialModels = detailAccessorialModels;
	}

	public List<HistoryDetailPieceModel> getDetailPieceModels() {
		return detailPieceModels;
	}

	public void setDetailPieceModels(List<HistoryDetailPieceModel> detailPieceModels) {
		this.detailPieceModels = detailPieceModels;
	}

	public HistoryDetailInfoModel getDetailInfoModel() {
		return detailInfoModel;
	}

	public void setDetailInfoModel(HistoryDetailInfoModel detailInfoModel) {
		this.detailInfoModel = detailInfoModel;
	}

	public String getAwbBarcode() {
		return awbBarcode;
	}

	public void setAwbBarcode(String awbBarcode) {
		this.awbBarcode = awbBarcode;
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

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public List<HistoryViewAirbillModel> getListPieceViewAirbill() {
		return listPieceViewAirbill;
	}

	public void setListPieceViewAirbill(List<HistoryViewAirbillModel> listPieceViewAirbill) {
		this.listPieceViewAirbill = listPieceViewAirbill;
	}

	public List<PieceModel> getPieceModels() {
		return pieceModels;
	}

	public void setPieceModels(List<PieceModel> pieceModels) {
		this.pieceModels = pieceModels;
	}

	public String getPieceBarcode() {
		return pieceBarcode;
	}

	public void setPieceBarcode(String pieceBarcode) {
		this.pieceBarcode = pieceBarcode;
	}

	public String getTntBarcode() {
		return tntBarcode;
	}

	public void setTntBarcode(String tntBarcode) {
		this.tntBarcode = tntBarcode;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getPathFileView() {
		return pathFileView;
	}

	public void setPathFileView(String pathFileView) {
		this.pathFileView = pathFileView;
	}

	public List<HistoryProductAirbillModel> getProductAirbillModels() {
		return productAirbillModels;
	}

	public void setProductAirbillModels(List<HistoryProductAirbillModel> productAirbillModels) {
		this.productAirbillModels = productAirbillModels;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public SendAirlbillHistoryFillterModel getSendAirlbillHistoryFillterModel() {
		return sendAirlbillHistoryFillterModel;
	}

	public void setSendAirlbillHistoryFillterModel(SendAirlbillHistoryFillterModel sendAirlbillHistoryFillterModel) {
		this.sendAirlbillHistoryFillterModel = sendAirlbillHistoryFillterModel;
	}

}