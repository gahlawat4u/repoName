package com.gms.xms.workflow.render.ratesheets;

import com.gms.xms.model.admin.ratesheets.RateSheetInfoModel;

import java.util.List;
import java.util.Map;

public interface IRateSheetsRender {
    public void generateHTMLPrintRateSheets(List<Map<String, RateSheetInfoModel>> outboundRateSheets, List<Map<String, RateSheetInfoModel>> inboundboundRateSheets, String customerCode, Integer carrierId, String realPath, String outPutFilePath) throws Exception;

    public void generatePdfFileRateSheets(List<Map<String, RateSheetInfoModel>> outboundRateSheets, List<Map<String, RateSheetInfoModel>> inboundboundRateSheets, String customerCode, Integer carrierId, String realPath, String outPutFilePath) throws Exception;

    public void generateHtmlXlsFileRateSheets(List<Map<String, RateSheetInfoModel>> outboundRateSheets, List<Map<String, RateSheetInfoModel>> inboundboundRateSheets, String customerCode, Integer carrierId, String outPutFilePath) throws Exception;
}
