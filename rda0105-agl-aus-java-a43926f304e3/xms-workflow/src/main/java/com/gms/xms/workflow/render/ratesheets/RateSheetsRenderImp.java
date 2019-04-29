package com.gms.xms.workflow.render.ratesheets;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.admin.ratesheets.RateSheetInfoModel;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.persistence.dao.admin.CarrierCoverSheetDao;
import com.gms.xms.persistence.dao.admin.CoverSheetDao;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.admin.ratesheets.CarrierCoverSheetVo;
import com.gms.xms.txndb.vo.admin.ratesheets.CoverSheetVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RateSheetsRenderImp extends BaseRender implements IRateSheetsRender {

    public RateSheetsRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHTMLPrintRateSheets(List<Map<String, RateSheetInfoModel>> outboundRateSheets, List<Map<String, RateSheetInfoModel>> inboundboundRateSheets, String customerCode, Integer carrierId, String realPath, String outPutFilePath) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/html/customer_base_rates/print_rate_sheets/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64;

        // Get customer name
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        CustomerAddressVo customerAddressVo = customerAddressDao.getByCode(customerCode);
        String customerName = customerAddressVo != null ? customerAddressVo.getCustomerName() : "";

        // Get coversheet
        CarrierCoverSheetDao carrierCoverSheetDao = new CarrierCoverSheetDao();
        CoverSheetDao coverSheetDao = new CoverSheetDao();
        String ibCover = "";
        String obCover = "";
        CarrierCoverSheetVo carrierCoverSheetVo = carrierCoverSheetDao.selectByCarrierId(carrierId);
        if (carrierCoverSheetVo != null) {
            CoverSheetVo obCoverSheet = coverSheetDao.selectByCoverSheetId(carrierCoverSheetVo.getCoverSheetId());
            CoverSheetVo ibCoverSheet = coverSheetDao.selectByCoverSheetId(carrierCoverSheetVo.getInboundCoverSheetId());
            if (obCoverSheet != null) {
                obCover = obCoverSheet.getFileName();
            }
            if (ibCoverSheet != null) {
                ibCover = ibCoverSheet.getFileName();
            }
        }
        String currenyCode = SystemSettingCache.getInstance().getValueByKey("CurrencyCode");
        Map<String, Object> data = new HashMap<>();
        // Generate header
        data.put("realPath", realPath);
        AppUtils.template2File(outPutFilePath, false, "templates/html/customer_base_rates/print_rate_sheets/print_rate_sheets_head.ftl", data);

        String bodyTemplate = "print_rate_sheets_body_datas.ftl";
        if (carrierId == 3) {
            bodyTemplate = "print_rate_sheets_body_tnt_datas.ftl";
        }
        // Generate body
        // Outbound first
        if (outboundRateSheets.size() > 0) {
            data = new HashMap<>();
            data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
            data.put("logo", logo);
            data.put("realPath", realPath);
            data.put("coverImgName", obCover);
            data.put("currenyCode", currenyCode);
            data.put("name", customerName);
            AppUtils.template2File(outPutFilePath, true, "templates/html/customer_base_rates/print_rate_sheets/print_rate_sheets_body.ftl", data);

            for (Map<String, RateSheetInfoModel> rs : outboundRateSheets) {
                RateSheetInfoModel rateSheet = rs.get("rateSheet");
                if (carrierId == 72) {
                    if (rateSheet.getShipmentTypeId().equalsIgnoreCase("228") || rateSheet.getShipmentTypeId().equalsIgnoreCase("229")) {
                        bodyTemplate = "print_rate_sheets_body_tnt_datas.ftl";
                    } else {
                        bodyTemplate = "print_rate_sheets_body_datas.ftl";
                    }
                }
                data.put("rs", rs);
                AppUtils.template2File(outPutFilePath, true, "templates/html/customer_base_rates/print_rate_sheets/" + bodyTemplate, data);
            }
        }

        if (inboundboundRateSheets.size() > 0) {
            // Inbound
            data = new HashMap<>();
            data.put("logo", logo);
            data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
            data.put("realPath", realPath);
            data.put("coverImgName", ibCover);
            data.put("currenyCode", currenyCode);
            data.put("name", customerName);
            AppUtils.template2File(outPutFilePath, true, "templates/html/customer_base_rates/print_rate_sheets/print_rate_sheets_body.ftl", data);
            for (Map<String, RateSheetInfoModel> rs : inboundboundRateSheets) {
                RateSheetInfoModel rateSheet = rs.get("rateSheet");
                if (carrierId == 72) {
                    if (rateSheet.getShipmentTypeId().equalsIgnoreCase("228") || rateSheet.getShipmentTypeId().equalsIgnoreCase("229")) {
                        bodyTemplate = "print_rate_sheets_body_tnt_datas.ftl";
                    } else {
                        bodyTemplate = "print_rate_sheets_body_datas.ftl";
                    }
                }
                data.put("rs", rs);
                AppUtils.template2File(outPutFilePath, true, "templates/html/customer_base_rates/print_rate_sheets/" + bodyTemplate, data);
            }
        }

        // Generate foot
        AppUtils.template2File(outPutFilePath, true, "templates/html/customer_base_rates/print_rate_sheets/print_rate_sheets_foot.ftl", data);
    }

    @Override
    public void generatePdfFileRateSheets(List<Map<String, RateSheetInfoModel>> outboundRateSheets, List<Map<String, RateSheetInfoModel>> inboundboundRateSheets, String customerCode, Integer carrierId, String realPath, String outPutFilePath) throws Exception {
        byte[] imgBytes = IOUtils.toByteArray(AppConstants.class.getClassLoader().getResourceAsStream("templates/pdf/customer_base_rates/print_pdf_rate_sheets/logo.png"));
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String logo = "data:image/png;base64," + imgDataAsBase64 ;
        String htmlFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/pdf_rate_sheets_" + AppUtils.createMessageReference() + ".html";
        // Get customer name
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        CustomerAddressVo customerAddressVo = customerAddressDao.getByCode(customerCode);
        String customerName = customerAddressVo != null ? customerAddressVo.getCustomerName() : "";

        // Get coversheet
        CarrierCoverSheetDao carrierCoverSheetDao = new CarrierCoverSheetDao();
        CoverSheetDao coverSheetDao = new CoverSheetDao();
        String ibCover = "";
        String obCover = "";
        CarrierCoverSheetVo carrierCoverSheetVo = carrierCoverSheetDao.selectByCarrierId(carrierId);
        if (carrierCoverSheetVo != null) {
            CoverSheetVo obCoverSheet = coverSheetDao.selectByCoverSheetId(carrierCoverSheetVo.getCoverSheetId());
            CoverSheetVo ibCoverSheet = coverSheetDao.selectByCoverSheetId(carrierCoverSheetVo.getInboundCoverSheetId());
            if (obCoverSheet != null) {
                String obCoverName = obCoverSheet.getFileName();
                obCover = AppUtils.image2String(realPath + obCoverName);
            }
            if (ibCoverSheet != null) {
                String ibCoverName = ibCoverSheet.getFileName();
                ibCover = AppUtils.image2String(realPath + ibCoverName);
            }
        }
        String currenyCode = SystemSettingCache.getInstance().getValueByKey("CurrencyCode");
        Map<String, Object> data = new HashMap<>();
        // Generate header
        data.put("realPath", realPath);
        AppUtils.template2File(htmlFilePath, false, "templates/pdf/customer_base_rates/print_pdf_rate_sheets/pdf_rate_sheets_head.ftl", data);

        String bodyTemplate = "pdf_rate_sheets_body_datas.ftl";
        if (carrierId == 3) {
            bodyTemplate = "pdf_rate_sheets_body_datas_tnt.ftl";
        }
        // Generate body
        // Outbound first
        if (outboundRateSheets.size() > 0) {
            data = new HashMap<>();
            data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
            data.put("logo", logo);
            data.put("realPath", realPath);
            data.put("coverImg", obCover);
            data.put("currenyCode", currenyCode);
            data.put("name", customerName);
            AppUtils.template2File(htmlFilePath, true, "templates/pdf/customer_base_rates/print_pdf_rate_sheets/pdf_rate_sheets_body.ftl", data);

            for (Map<String, RateSheetInfoModel> rs : outboundRateSheets) {
                RateSheetInfoModel rateSheet = rs.get("rateSheet");
                if (carrierId == 72) {
                    if (rateSheet.getShipmentTypeId().equalsIgnoreCase("228") || rateSheet.getShipmentTypeId().equalsIgnoreCase("229")) {
                        bodyTemplate = "pdf_rate_sheets_body_datas_tnt.ftl";
                    } else {
                        bodyTemplate = "pdf_rate_sheets_body_datas.ftl";
                    }
                }
                data.put("rs", rs);
                AppUtils.template2File(htmlFilePath, true, "templates/pdf/customer_base_rates/print_pdf_rate_sheets/" + bodyTemplate, data);
            }
        }

        if (inboundboundRateSheets.size() > 0) {
            // Inbound
            data = new HashMap<>();
            data.put("logo", logo);
            data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
            data.put("realPath", realPath);
            data.put("coverImg", ibCover);
            data.put("currenyCode", currenyCode);
            data.put("name", customerName);
            AppUtils.template2File(htmlFilePath, true, "templates/pdf/customer_base_rates/print_pdf_rate_sheets/pdf_rate_sheets_body.ftl", data);
            for (Map<String, RateSheetInfoModel> rs : inboundboundRateSheets) {
                RateSheetInfoModel rateSheet = rs.get("rateSheet");
                if (carrierId == 72) {
                    if (rateSheet.getShipmentTypeId().equalsIgnoreCase("228") || rateSheet.getShipmentTypeId().equalsIgnoreCase("229")) {
                        bodyTemplate = "pdf_rate_sheets_head_body_datas_tnt.ftl";
                    } else {
                        bodyTemplate = "pdf_rate_sheets_body_datas.ftl";
                    }
                }
                data.put("rs", rs);
                AppUtils.template2File(htmlFilePath, true, "templates/pdf/customer_base_rates/print_pdf_rate_sheets/" + bodyTemplate, data);
            }
        }

        // Generate foot
        AppUtils.template2File(htmlFilePath, true, "templates/pdf/customer_base_rates/print_pdf_rate_sheets/pdf_rate_sheets_foot.ftl", data);
        AppUtils.createPDFFromHTMLWithFont(htmlFilePath, outPutFilePath, "arial", true);
    }

    @Override
    public void generateHtmlXlsFileRateSheets(List<Map<String, RateSheetInfoModel>> outboundRateSheets, List<Map<String, RateSheetInfoModel>> inboundboundRateSheets, String customerCode, Integer carrierId, String outPutFilePath) throws Exception {
        // Get customer name
        CustomerAddressDao customerAddressDao = new CustomerAddressDao();
        CustomerAddressVo customerAddressVo = customerAddressDao.getByCode(customerCode);
        String customerName = customerAddressVo != null ? customerAddressVo.getCustomerName() : "";

        String currenyCode = SystemSettingCache.getInstance().getValueByKey("CurrencyCode");
        Map<String, Object> data = new HashMap<>();
        // Generate header
        AppUtils.template2File(outPutFilePath, false, "templates/excel/customer_base_rates/print_rate_sheets/xls_rate_sheets_head.ftl", data);

        String bodyTemplate = "xls_rate_sheets_body_datas.ftl";
        if (carrierId == 3) {
            bodyTemplate = "xls_rate_sheets_body_datas_tnt.ftl";
        }
        // Generate body
        // Outbound first
        if (outboundRateSheets.size() > 0) {
            data = new HashMap<>();
            data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
            data.put("currenyCode", currenyCode);
            data.put("name", customerName);
            AppUtils.template2File(outPutFilePath, true, "templates/excel/customer_base_rates/print_rate_sheets/xls_rate_sheets_body.ftl", data);

            for (Map<String, RateSheetInfoModel> rs : outboundRateSheets) {
                RateSheetInfoModel rateSheet = rs.get("rateSheet");
                if (carrierId == 72) {
                    if (rateSheet.getShipmentTypeId().equalsIgnoreCase("228") || rateSheet.getShipmentTypeId().equalsIgnoreCase("229")) {
                        bodyTemplate = "xls_rate_sheets_body_datas_tnt.ftl";
                    } else {
                        bodyTemplate = "xls_rate_sheets_body_datas.ftl";
                    }
                }
                data.put("rs", rs);
                AppUtils.template2File(outPutFilePath, true, "templates/excel/customer_base_rates/print_rate_sheets/" + bodyTemplate, data);
            }
        }

        if (inboundboundRateSheets.size() > 0) {
            // Inbound
            data = new HashMap<>();
            data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
            data.put("currenyCode", currenyCode);
            data.put("name", customerName);
            AppUtils.template2File(outPutFilePath, true, "templates/excel/customer_base_rates/print_rate_sheets/xls_rate_sheets_body.ftl", data);
            for (Map<String, RateSheetInfoModel> rs : inboundboundRateSheets) {
                RateSheetInfoModel rateSheet = rs.get("rateSheet");
                if (carrierId == 72) {
                    if (rateSheet.getShipmentTypeId().equalsIgnoreCase("228") || rateSheet.getShipmentTypeId().equalsIgnoreCase("229")) {
                        bodyTemplate = "xls_rate_sheets_head_body_datas_tnt.ftl";
                    } else {
                        bodyTemplate = "xls_rate_sheets_body_datas.ftl";
                    }
                }
                data.put("rs", rs);
                AppUtils.template2File(outPutFilePath, true, "templates/excel/customer_base_rates/print_rate_sheets/" + bodyTemplate, data);
            }
        }

        // Generate foot
        AppUtils.template2File(outPutFilePath, true, "templates/excel/customer_base_rates/print_rate_sheets/xls_rate_sheets_foot.ftl", data);
    }

}