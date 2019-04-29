package com.gms.xms.workflow.render.systemstats;

import com.gms.xms.filter.admin.systemstats.SysStatsAdjustmentLogFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsWebshipLogFilter;
import com.gms.xms.model.CarrierPostCodeModel;
import com.gms.xms.model.CarrierSuburbModel;
import com.gms.xms.model.CarrierZoneModel;
import com.gms.xms.model.MultiZoneModel;
import com.gms.xms.model.admin.LoginLogModel;
import com.gms.xms.model.admin.systemstats.SysStatAdjustmentLogModel;
import com.gms.xms.model.webship.DhlCountryModel;
import com.gms.xms.model.webship.TntInternationalAuZoneModel;
import com.gms.xms.model.webship.TntRemoteAreaModel;
import com.gms.xms.model.webship.TollIpecZoneModel;
import com.gms.xms.model.webship.toll.TollPriorityRateSheetModel;
import com.gms.xms.model.webship.toll.TollPrioritySuburbModel;
import com.gms.xms.model.webship.toll.TollRemoteAreaModel;

import java.util.List;

public interface ISystemStatsRender {
    public void generateHtmlDhlCountryZone(List<DhlCountryModel> dhlCountryModels, String realPath, String outputFilePath);

    public void generateXLSDhlCountryZone(List<DhlCountryModel> dhlCountryModels, String outputFilePath);

    public void generateHtmlLoginLogs(List<LoginLogModel> loginLogModels, String realPath, String outputFilePath);

    public void generateXLSLoginLog(List<LoginLogModel> loginLogModels, String outputFilePath);

    public void generateHtmlWebshipLogs(SysStatsWebshipLogFilter filter, String realPath, String outputFilePath) throws Exception;

    public void generateHtmlAdjustmentLogs(SysStatsAdjustmentLogFilter filter, String realPath, String outputFilePath) throws Exception;

    public void generateXLSAdjustmentLog(List<SysStatAdjustmentLogModel> logModels, String outputFilePath);

    public void generateHtmlTNTZone(List<CarrierZoneModel> carrierZoneModels, String realPath, String outputFilePath);

    public void generateXLSTNTZone(List<CarrierZoneModel> carrierZoneModels, String outputFilePath);

    public void generateHtmlTNTPostCode(List<CarrierPostCodeModel> carrierPostCodeModels, String realPath, String outputFilePath);

    public void generateXLSTNTPostCode(List<CarrierPostCodeModel> carrierPostCodeModels, String outputFilePath);

    public void generateHtmlTNTSuburb(List<CarrierSuburbModel> suburbModels, String realPath, String outputFilePath);

    public void generateXLSTNTSuburb(List<CarrierSuburbModel> suburbModels, String outputFilePath);

    public void generateHtmlTNTDomRemoteAreas(List<TntRemoteAreaModel> remoteAreaModels, String realPath, String outputFilePath);

    public void generateXLSTNTDomRemoteAreas(List<TntRemoteAreaModel> remoteAreaModels, String outputFilePath);

    public void generateXLSTollPriorityRemoteArea(List<TollRemoteAreaModel> tollRemoteAreaModels, String outputFilePath);

    public void generateHtmlTollPriorityRemoteArea(List<TollRemoteAreaModel> tollRemoteAreaModels, String realPath, String outputFilePath);

    public void generateHtmlTNTInternationalZone(List<TntInternationalAuZoneModel> tntInternationalModels, String realPath, String outputFilePath);

    public void generateXLSTNTInternationalZone(List<TntInternationalAuZoneModel> tntInternationalModels, String outputFilePath);

    public void generateHtmlTollIPECTownPostcode(List<TollIpecZoneModel> ipecZoneModels, String realPath, String outputFilePath);

    public void generateXLSTollIPECTownPostcode(List<TollIpecZoneModel> ipecZoneModels, String outputFilePath);

    public void generateHtmlTollPriorityRateSheet(List<TollPriorityRateSheetModel> tollRateSheetModels, String realPath, String outputFilePath);

    public void generateXLSTollPriorityRateSheet(List<TollPriorityRateSheetModel> tollRateSheetModels, String outputFilePath);

    public void generateHtmlTollPrioritySuburb(List<TollPrioritySuburbModel> tollSuburbModels, String realPath, String outputFilePath);

    public void generateXLSTollPrioritySuburb(List<TollPrioritySuburbModel> tollSuburbModels, String outputFilePath);

    public void generateHtmlDhlDomesticZone(List<MultiZoneModel> multiZoneModels, String realPath, String outputFilePath);

    public void generateXLSDhlDomesticZone(List<MultiZoneModel> multiZoneModels, String outputFilePath);
}
