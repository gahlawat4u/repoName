package com.gms.xms.workflow.service;

import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.admin.CarrierZoneDao;
import com.gms.xms.txndb.parameter.RateSheetParam;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.helper.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

/**
 * Posted from Nov 22, 2016 2:58:29 PM
 * <p>
 * Author dattrinh
 */
public class TntDomImportRateSheetParser {
    protected static Log log = LogFactory.getLog(TntDomImportRateSheetParser.class);

    // Input.
    private String importFile;
    private String sheetName;

    // Output.
    private Integer shipmentTypeId = 0;
    private Long sheetId = 0L;
    private Long rowId = 0L;
    private Long columnId = 0L;
    private String serCode = "";
    private String oldSerCode = "";
    private String rowName = "";
    private String oldRowName = "";
    private String colName = "";
    private String oldColName = "";
    private String minConCharge = "0";
    private String basicCharge = "0";
    private String kiloR1 = "0";
    private int colCount = 0;
    private int rowCount = 0;
    private String zoneNo = "";
    @SuppressWarnings("unused")
    private String pZoneNo = "";
    private String zoneName = "";
    private String zoneCode = "";
    private String postCode = "";
    private String suburbName = "";
    private Map<Long, Integer> updRateSheetArr = new LinkedHashMap<Long, Integer>();
    private int rateSheetMsg = 1;
    private int carrierZoneMsg = 1;
    private int carrierPostCodeMsg = 0;
    private int carrierSuburbMsg = 0;
    private List<Integer> shipmentTypeArr = new LinkedList<Integer>();
    private Map<Integer, Long> sheetIdArr = new LinkedHashMap<Integer, Long>();
    private Map<String, Map<String, String>> serviceDiscArr = new LinkedHashMap<String, Map<String, String>>();

    // Object to private use.
    private ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
    private RateSheetDao rateSheetDao = new RateSheetDao();
    private RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
    private RateSheetColumnDao rateSheetColumnDao = new RateSheetColumnDao();
    private RateSheetRowDao rateSheetRowDao = new RateSheetRowDao();
    private CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
    private CarrierPostCodeDao carrierPostCodeDao = new CarrierPostCodeDao();
    private CarrierSuburbDao carrierSuburbDao = new CarrierSuburbDao();
    private Map<String, String> context;

    public TntDomImportRateSheetParser(Map<String, String> context, String importFile, String sheetName) {
        this.context = context;
        this.importFile = importFile;
        this.sheetName = sheetName;
    }

    public boolean parser() throws Exception {
        // Get line count.
        int lineCount = getLineCount(importFile);
        // Read file.
        BufferedReader br = new BufferedReader(new FileReader(importFile));
        String lineContent;
        int line = 0;
        while ((lineContent = br.readLine()) != null) {
            line++;
            // Get line content.
            // lineContent = lineContent.trim();
            // Get type.
            String type = lineContent.substring(0, 1);
            // Validate line content.
            if (!isValidLineContent(line, type, lineContent, lineCount)) {
                br.close();
                return false;
            }
            // Parse line if it's Record C.
            if ("C".equalsIgnoreCase(type)) {
                parseRecordC(lineContent);
            }
            // Parse line if it's Record V, X, Y.
            if ("V".equalsIgnoreCase(type) || "X".equalsIgnoreCase(type) || "Y".equalsIgnoreCase(type)) {
                // Get some rate sheet info.
                getSomeInfo(type, lineContent);
                // Parse Record V.
                if ("V".equalsIgnoreCase(type)) {
                    parseRecordV();
                }
                // Parse Record X.
                if ("X".equalsIgnoreCase(type)) {
                    parseRecordX();
                }
                // Parse Record Y.
                if ("Y".equalsIgnoreCase(type)) {
                    parseRecordY();
                }
            }
        }
        // Update cell in rate sheet.
        updateCells();
        log.info("Import TNT Domestic rate sheet: " + String.valueOf(rateSheetMsg) + "#@#" + String.valueOf(carrierZoneMsg) + "#@#" + String.valueOf(carrierPostCodeMsg) + "#@#" + String.valueOf(carrierSuburbMsg));
        br.close();
        return true;
    }

    private void updateCells() throws Exception {
        if (updRateSheetArr != null && updRateSheetArr.size() > 0) {
            for (Long key : updRateSheetArr.keySet()) {
                RateSheetVo rateSheetVo = new RateSheetVo();
                rateSheetVo.setCells(updRateSheetArr.get(key));
                rateSheetVo.setSheetId(key);
                rateSheetDao.updateRateSheet(context, rateSheetVo);
            }
        }
    }

    private void parseRecordV() throws Exception {
        if (!oldSerCode.equalsIgnoreCase(serCode)) {
            // Check existing of shipment type.
            ShipmentTypeVo shipmentTypeFilter = new ShipmentTypeVo();
            shipmentTypeFilter.setServiceCode(serCode);
            shipmentTypeFilter.setServiceId(3);
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.getShipmentTypeByCode(shipmentTypeFilter);
            if (shipmentTypeVo != null) {
                shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
            } else {
                shipmentTypeId = createShipmentType(serCode, 3);
            }
            rowCount = 0;
            colCount = 0;
            // Check existing of rate sheet.
            RateSheetVo rateSheetFilter = new RateSheetVo();
            rateSheetFilter.setSheetName(sheetName);
            rateSheetFilter.setShipmentTypeId(shipmentTypeId);
            rateSheetFilter.setCarrierCost((byte) 1);
            rateSheetFilter.setIsPerWeight((byte) 0);
            rateSheetFilter.setContent(0);
            rateSheetFilter.setBound(0);
            RateSheetVo rateSheetVo = rateSheetDao.checkRateSheet(rateSheetFilter);
            if (rateSheetVo == null) {
                sheetId = createRateSheet();
                rateSheetMsg++;
            } else {
                sheetId = rateSheetVo.getSheetId();
            }
            // Put sheet id array.
            sheetIdArr.put(shipmentTypeId, sheetId);
            // Delete rate sheet info.
            if (!shipmentTypeArr.contains(shipmentTypeId)) {
                // Delete rate sheet detail.
                rateSheetDetailDao.deleteRateSheetDetailBySheetId(context, sheetId);
                // Delete rate sheet column.
                rateSheetColumnDao.deleteColumnsBySheetId(context, sheetId);
                // Delete rate sheet row.
                rateSheetRowDao.deleteRateSheetRowsBySheetId(context, sheetId);
            }
            shipmentTypeArr.add(shipmentTypeId);
        }
        // Rate sheet column -> Destination.
        if (!colName.equalsIgnoreCase(oldColName)) {
            RateSheetColumnVo columnFilter = new RateSheetColumnVo();
            columnFilter.setSheetId(sheetId);
            columnFilter.setColumnName(colName);
            RateSheetColumnVo rateSheetColumnVo = rateSheetColumnDao.getRateSheetColumn(columnFilter);
            if (rateSheetColumnVo == null) {
                columnId = createRateSheetColumn();
                colCount++;
            } else {
                columnId = rateSheetColumnVo.getColumnId();
            }
        }
        // Rate sheet row -> Source.
        if (!rowName.equalsIgnoreCase(oldRowName)) {
            RateSheetRowVo rowFilter = new RateSheetRowVo();
            rowFilter.setSheetId(sheetId);
            rowFilter.setRowName(Double.valueOf(rowName));
            RateSheetRowVo rateSheetRowVo = rateSheetRowDao.getRateSheetRow(rowFilter);
            if (rateSheetRowVo == null) {
                rowId = createRateSheetRow();
                rowCount++;
            } else {
                rowId = rateSheetRowVo.getRowId();
            }
        }
        // Create rate sheet detail.
        if (sheetId != 0 && rowId != 0 && columnId != 0) {
            createRateSheetDetail();
        }
        // Update rate sheet.
        if (rowCount != 0 && colCount != 0) {
            updRateSheetArr.put(sheetId, rowCount * colCount);
        }
        oldColName = colName;
        oldRowName = rowName;
        oldSerCode = serCode;
    }

    private void parseRecordX() throws Exception {
        if (sheetIdArr != null && sheetIdArr.size() > 0) {
            updateRateSheetRowColumn();
        }
        createCarrierZone();
        carrierZoneMsg++;
    }

    private void parseRecordY() throws Exception {
        CarrierZoneVo filter = new CarrierZoneVo();
        filter.setCarrier(3L);
        filter.setZoneNo(zoneNo);
        CarrierZoneVo carrierZoneVo = carrierZoneDao.getZoneByCarrierAndZoneNo(filter);
        if (carrierZoneVo != null) {
            createCarrierPostalCode(carrierZoneVo.getZoneCode());
            carrierPostCodeMsg++;
        }
        createCarrierSuburb();
        carrierSuburbMsg++;
    }

    private void createCarrierSuburb() throws Exception {
        // Create carrier suburb object.
        CarrierSuburbVo carrierSuburbVo = new CarrierSuburbVo();
        carrierSuburbVo.setSuburbName(suburbName);
        carrierSuburbVo.setStateCode("");
        carrierSuburbVo.setPostCode(postCode);
        carrierSuburbVo.setCarrier(3L);
        carrierSuburbVo.setPrimaryPort("");
        carrierSuburbVo.setSecondaryPort("");
        carrierSuburbVo.setCountryFlag("");
        // Save it into the database.
        carrierSuburbDao.insertCarrierSuburb(context, carrierSuburbVo);
    }

    private void createCarrierPostalCode(String pZoneCode) throws Exception {
        // Create new carrier post code object.
        CarrierPostCodeVo carrierPostCodeVo = new CarrierPostCodeVo();
        carrierPostCodeVo.setZoneCode(pZoneCode);
        carrierPostCodeVo.setPostCode(postCode);
        carrierPostCodeVo.setCarrier(3L);
        // Save it into the database.
        carrierPostCodeDao.insertCarrierPostCode(context, carrierPostCodeVo);
    }

    private void createCarrierZone() throws Exception {
        // Create carrier zone object.
        CarrierZoneVo carrierZoneVo = new CarrierZoneVo();
        carrierZoneVo.setZoneCode(zoneCode);
        carrierZoneVo.setZoneName(zoneName);
        carrierZoneVo.setCarrier(3L);
        carrierZoneVo.setStateCode(0);
        carrierZoneVo.setZoneNo(zoneNo);
        carrierZoneVo.setIsDisplay(false);
        // Insert it into the database.
        carrierZoneDao.saveCarrierZone(context, carrierZoneVo);
    }

    private void updateRateSheetRowColumn() throws Exception {
        RateSheetParam param = new RateSheetParam();
        param.setZoneCode(zoneCode);
        param.setZoneNo(zoneNo);
        param.setSheetIdList(getSheetIdList());
        // Update rate sheet column.
        rateSheetColumnDao.updateRateSheetColumns(context, param);
        // Update rate sheet row.
        rateSheetRowDao.updateRateSheetRows(context, param);
    }

    private String getSheetIdList() {
        String result = "";
        for (Integer key : sheetIdArr.keySet()) {
            result += String.valueOf(sheetIdArr.get(key)) + ",";
        }
        return result.substring(0, result.length() - 1);
    }

    private void createRateSheetDetail() throws Exception {
        // Create rate sheet detail object.
        RateSheetDetailVo rateSheetDetailVo = new RateSheetDetailVo();
        rateSheetDetailVo.setSheetId(sheetId);
        rateSheetDetailVo.setRowId(rowId);
        rateSheetDetailVo.setColumnId(columnId);
        rateSheetDetailVo.setValue(0.0);
        rateSheetDetailVo.setMinCharge(getDiscountRate(serviceDiscArr, minConCharge, serCode, "min"));
        rateSheetDetailVo.setBaseCharge(getDiscountRate(serviceDiscArr, basicCharge, serCode, "basic"));
        rateSheetDetailVo.setPerKg(getDiscountRate(serviceDiscArr, kiloR1, serCode, "perkg"));
        // Insert it into the database.
        rateSheetDetailDao.insertRateSheetDetail(context, rateSheetDetailVo);
    }

    private double getDiscountRate(Map<String, Map<String, String>> serviceDiscArr, String amount, String serviceCode, String type) {
        if (serviceDiscArr.get(serviceCode) == null) {
            return Float.valueOf(amount);
        }
        Map<String, String> arr = serviceDiscArr.get(serviceCode);
        double amt = Float.valueOf(amount);
        double discAmount = amt;
        String baseDiscText = "", baseAmountText = "", useText = "";
        if ("basic".equalsIgnoreCase(type)) {
            useText = arr.get("BasicUse");
            baseDiscText = arr.get("Basicdis");
            baseAmountText = arr.get("Basicamt");
        } else if ("perkg".equalsIgnoreCase(type)) {
            useText = arr.get("RateUse");
            baseDiscText = arr.get("Ratedis");
            baseAmountText = arr.get("RateAmt");
        } else {
            useText = arr.get("MinUse");
            baseDiscText = arr.get("MinDis");
            baseAmountText = arr.get("MinConAmt");
        }
        @SuppressWarnings("unused")
        float baseDisc, baseAmount;
        if (StringUtil.isBlank(baseDiscText)) {
            baseDisc = 0;
        } else {
            baseDisc = Float.valueOf(baseDiscText);
        }
        if (StringUtils.isBlank(baseAmountText)) {
            baseAmount = 0;
        } else {
            baseAmount = Float.valueOf(baseAmountText);
        }
        if ("D".equalsIgnoreCase(useText)) {
            double disc = amt * (baseDisc / 100);
            discAmount = amt - disc;
        }
        return MathUtils.round(discAmount, 2);
    }

    private Long createRateSheetRow() throws Exception {
        // Create new rate sheet row object.
        RateSheetRowVo rowVo = new RateSheetRowVo();
        rowVo.setSheetId(sheetId);
        boolean isDouble = false;
        double rowValue = 0.0;
        try {
            rowValue = Double.valueOf(rowName);
            isDouble = true;
        } catch (Exception e) {
            isDouble = false;
        }
        if (isDouble) {
            rowVo.setRowName(rowValue);
            rowVo.setIsChar(false);
            rowVo.setCharRowName("");
        } else {
            rowVo.setRowName(0.0);
            rowVo.setCharRowName(rowName);
            rowVo.setIsChar(true);
        }
        // Insert it into the database.
        rateSheetRowDao.insertRateSheetRow(context, rowVo);
        return rowVo.getRowId();
    }

    private Long createRateSheetColumn() throws Exception {
        RateSheetColumnVo columnVo = new RateSheetColumnVo();
        columnVo.setSheetId(sheetId);
        columnVo.setColumnName(colName);
        rateSheetColumnDao.insertRateSheetColumn(context, columnVo);
        return columnVo.getColumnId();
    }

    private Long createRateSheet() throws Exception {
        // Create new rate sheet object.
        RateSheetVo rateSheetVo = new RateSheetVo();
        rateSheetVo.setSheetName(sheetName);
        rateSheetVo.setCreateDate(new Date());
        rateSheetVo.setShipmentTypeId(shipmentTypeId);
        rateSheetVo.setCells(0);
        rateSheetVo.setSourceZone("");
        rateSheetVo.setCarrierCost((byte) 1);
        rateSheetVo.setIsPerWeight((byte) 0);
        rateSheetVo.setContent(0);
        rateSheetVo.setBound(0);
        // Insert it into the database.
        rateSheetDao.insertRateSheet(context, rateSheetVo);
        return rateSheetVo.getSheetId();
    }

    private Integer createShipmentType(String serCode, Integer carrier) throws Exception {
        // Create new shipment type object.
        ShipmentTypeVo shipmentTypeVo = new ShipmentTypeVo();
        shipmentTypeVo.setServicePriority(0);
        shipmentTypeVo.setShipmentTypeName(serCode);
        shipmentTypeVo.setEdiDescription("");
        shipmentTypeVo.setServiceCode(serCode);
        shipmentTypeVo.setBasicCharge(0.0);
        shipmentTypeVo.setDocument(false);
        shipmentTypeVo.setDocumentInbound(false);
        shipmentTypeVo.setPackage(false);
        shipmentTypeVo.setPackageInbound(false);
        shipmentTypeVo.setPak(false);
        shipmentTypeVo.setPakInbound(false);
        shipmentTypeVo.setAllowCarrier(false);
        shipmentTypeVo.setAllowNonCarrier(false);
        shipmentTypeVo.setCarrierDocumentRate(0L);
        shipmentTypeVo.setCarrierDocumentInboundRate(0L);
        shipmentTypeVo.setCarrierPackageRate(0L);
        shipmentTypeVo.setCarrierPackageInboundRate(0L);
        shipmentTypeVo.setCarrierPackagePerWeightRate(0L);
        shipmentTypeVo.setCarrierPackageInboundPerWeightRate(0L);
        shipmentTypeVo.setCarrierPakRate(0L);
        shipmentTypeVo.setCarrierPakInboundRate(0L);
        shipmentTypeVo.setCarrierPakPerWeightRate(0L);
        shipmentTypeVo.setCarrierPakInboundPerWeightRate(0L);
        shipmentTypeVo.setModifiedDate(new Date());
        shipmentTypeVo.setNonCarrierDocumentRate(0L);
        shipmentTypeVo.setNonCarrierDocumentInboundRate(0L);
        shipmentTypeVo.setNonCarrierPackageRate(0L);
        shipmentTypeVo.setNonCarrierPackageInboundRate(0L);
        shipmentTypeVo.setNonCarrierPackagePerWeightRate(0L);
        shipmentTypeVo.setNonCarrierPackageInboundPerWeightRate(0L);
        shipmentTypeVo.setNonCarrierPakRate(0L);
        shipmentTypeVo.setNonCarrierPakInboundRate(0L);
        shipmentTypeVo.setNonCarrierPakPerWeightRate(0L);
        shipmentTypeVo.setNonCarrierPakInboundPerWeightRate(0L);
        shipmentTypeVo.setServiceId(carrier);
        // shipmentTypeVo.setNoOfRate(0);
        shipmentTypeVo.setLocalizationId(0L);
        shipmentTypeVo.setPerWeightStatus((byte) 0);
        shipmentTypeVo.setShowStatus((byte) 1);
        shipmentTypeVo.setShowDomestic((byte) 0);
        shipmentTypeVo.setGlobalProductCodeDoc("");
        shipmentTypeVo.setGlobalProductCodeNonDoc("");
        shipmentTypeVo.setLocalProductCodeDoc("");
        shipmentTypeVo.setLocalProductCodeNonDoc("");
        // shipmentTypeVo.setDocOutboundTax(0.0);
        // shipmentTypeVo.setNonDocOutboundTax(0.0);
        // shipmentTypeVo.setDocInboundTax(0.0);
        // shipmentTypeVo.setNonDocInboundTax(0.0);
        shipmentTypeVo.setAllowChangeName(true);
        shipmentTypeVo.setStartWithCarrierName(false);
        // shipmentTypeVo.setPerKg("");
        // shipmentTypeVo.setServiceGroup("");
        // Insert into the database.
        shipmentTypeDao.insertShipmentType(context, shipmentTypeVo);
        return shipmentTypeVo.getShipmentTypeId();
    }

    private String getText(String lineContent, int pos, int length) {
        return lineContent.substring(pos, pos + length).trim();
    }

    private void getSomeInfo(String type, String lineContent) {
        int pos = 0, length;
        Map<String, Integer> definition = this.getDefinition().get(type);
        String text;
        for (String key : definition.keySet()) {
            length = definition.get(key);
            text = getText(lineContent, pos, length);
            // Get column name.
            if ("V".equalsIgnoreCase(type) && "DestArea".equalsIgnoreCase(key)) {
                colName = text;
            }
            // Get row name.
            if ("V".equalsIgnoreCase(type) && "SourceArea".equalsIgnoreCase(key)) {
                rowName = text;
            }
            // Get ser code.
            if ("V".equalsIgnoreCase(type) && "ServType".equalsIgnoreCase(key)) {
                serCode = text;
            }
            // Get min con charge.
            if ("V".equalsIgnoreCase(type) && "MinConCharge".equalsIgnoreCase(key)) {
                minConCharge = text;
            }
            // Get basic charge.
            if ("V".equalsIgnoreCase(type) && "KiloBasicBP1".equalsIgnoreCase(key)) {
                basicCharge = text;
            }
            // Get kilor1.
            if ("V".equalsIgnoreCase(type) && "KiloR1".equalsIgnoreCase(key)) {
                kiloR1 = text;
            }
            // Get zone no.
            if ("X".equalsIgnoreCase(type) && "Zone".equalsIgnoreCase(key)) {
                zoneNo = text;
            }
            // Get zone name.
            if ("X".equalsIgnoreCase(type) && "Name".equalsIgnoreCase(key)) {
                zoneName = text;
            }
            // Get zone code.
            if ("X".equalsIgnoreCase(type) && "Short".equalsIgnoreCase(key)) {
                zoneCode = text;
            }
            // Get postal code.
            if ("Y".equalsIgnoreCase(type) && "Postcode".equalsIgnoreCase(key)) {
                postCode = text;
            }
            // Get p zone no.
            if ("Y".equalsIgnoreCase(type) && "Zone".equalsIgnoreCase(key)) {
                pZoneNo = text;
            }
            // Get suburb name.
            if ("Y".equalsIgnoreCase(type) && "Suburb".equalsIgnoreCase(key)) {
                suburbName = text;
            }
            pos += length;
        }
    }

    private void parseRecordC(String lineContent) {
        int pos = 0, length;
        Map<String, Integer> definitionC = this.getDefinition().get("C");
        Map<String, String> serviceDiscMapTmp = new LinkedHashMap<String, String>();
        String discSerCode = "", text;
        for (String key : definitionC.keySet()) {
            length = definitionC.get(key);
            text = getText(lineContent, pos, length);
            if ("ServType".equalsIgnoreCase(key)) {
                discSerCode = text;
            }
            serviceDiscMapTmp.put(key, text);
            pos += length;
        }
        if (!StringUtils.isBlank(discSerCode)) {
            serviceDiscArr.put(discSerCode, serviceDiscMapTmp);
        }
    }

    private boolean isValidLineContent(int line, String type, String lineContent, int lineCount) {
        // Header length is 36.
        if (line == 1 && "H".equalsIgnoreCase(type) && lineContent.length() != 36) {
            return false;
        }
        // Footer length is 28.
        if (line == lineCount && "Z".equalsIgnoreCase(type) && lineContent.length() != 28) {
            return false;
        }
        // Record V length is 506.
        if ("V".equalsIgnoreCase(type) && lineContent.length() != 506) {
            return false;
        }
        // Record X length is 61.
        if ("X".equalsIgnoreCase(type) && lineContent.length() != 61) {
            return false;
        }
        // Record Y length is 43.
        if ("Y".equalsIgnoreCase(type) && lineContent.length() != 43) {
            return false;
        }
        return true;
    }

    private int getLineCount(String fileName) throws Exception {
        int lineNumber = 0;
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        LineNumberReader lnr = new LineNumberReader(fr);
        while (lnr.readLine() != null) {
            lineNumber++;
        }
        lnr.close();
        return lineNumber;
    }

    private Map<String, Map<String, Integer>> getDefinition() {
        Map<String, Map<String, Integer>> definition = new LinkedHashMap<String, Map<String, Integer>>();
        // Definition for Record C.
        Map<String, Integer> definitionC = new LinkedHashMap<String, Integer>();
        definitionC.put("RType", 1);
        definitionC.put("RNumber", 8);
        definitionC.put("AccountCode", 9);
        definitionC.put("ServiceGroup", 4);
        definitionC.put("ServType", 4);
        definitionC.put("BasicUse", 1);
        definitionC.put("Basicdis", 7);
        definitionC.put("Basicamt", 7);
        definitionC.put("MTLBasicdis", 7);
        definitionC.put("MTLBasicUse", 1);
        definitionC.put("MTLRatedis", 7);
        definitionC.put("MTLRateUse", 1);
        definitionC.put("MTLmincharge", 7);
        definitionC.put("MTLbreak1", 5);
        definitionC.put("MTLbasic1", 7);
        definitionC.put("MTLbreak2", 5);
        definitionC.put("MTLbasic2", 7);
        definitionC.put("MTLbreak3", 5);
        definitionC.put("MTLbasic3", 7);
        definitionC.put("MTLbreak4", 5);
        definitionC.put("MTLbasic4", 7);
        definitionC.put("MTLbreak5", 5);
        definitionC.put("MTLbasic5", 7);
        definitionC.put("RateUse", 1);
        definitionC.put("Ratedis", 7);
        definitionC.put("RateCalType", 4);
        definitionC.put("Rateusage", 1);
        definitionC.put("RateUseMaxWeight", 1);
        definitionC.put("RateMaxWeight", 5);
        definitionC.put("RateBreakType", 1);
        definitionC.put("RateCalMethod", 1);
        definitionC.put("RateAmt", 7);
        definitionC.put("MinUse", 1);
        definitionC.put("MinDis", 7);
        definitionC.put("MinConAmt", 7);
        definitionC.put("MinItemAmt", 7);
        definitionC.put("OverrideCubic", 1);
        definitionC.put("CubicFactor", 4);
        definitionC.put("Artiles", 1);
        definitionC.put("whichBasic", 1);
        definitionC.put("AllowRate", 1);
        definitionC.put("AllowDisc", 1);
        definitionC.put("Onforward", 1);
        definitionC.put("OnforwardCharge", 7);
        definitionC.put("OnforwardDisc", 7);
        definition.put("C", definitionC);
        // Definition for Record V.
        Map<String, Integer> definitionV = new LinkedHashMap<String, Integer>();
        definitionV.put("RType", 1);
        definitionV.put("RNumber", 8);
        definitionV.put("SchNum", 1);
        definitionV.put("ServGrp", 4);
        definitionV.put("ServType", 4);
        definitionV.put("SourceArea", 4);
        definitionV.put("DestArea", 4);
        definitionV.put("RateType", 1);
        definitionV.put("RateCalType", 4);
        definitionV.put("Reciprocal", 1);
        definitionV.put("AOOTL", 1);
        definitionV.put("UMCC", 1);
        definitionV.put("MinConCharge", 7);
        definitionV.put("UseMinItemC", 1);
        definitionV.put("MinItemCharge", 7);
        definitionV.put("UseBasicC", 1);
        definitionV.put("BasicCharge", 7);
        definitionV.put("UseKiloR", 1);
        definitionV.put("KiloBreakT", 1);
        definitionV.put("KiloRateT1", 1);
        definitionV.put("KiloBreak1", 5);
        definitionV.put("KiloInc1", 5);
        definitionV.put("KiloBasicBP1", 7);
        definitionV.put("KiloR1", 9);
        definition.put("V", definitionV);
        // Definition for Record X.
        Map<String, Integer> definitionX = new LinkedHashMap<String, Integer>();
        definitionX.put("RType", 1);
        definitionX.put("RNumber", 8);
        definitionX.put("ServGrp", 4);
        definitionX.put("Zone", 4);
        definitionX.put("Name", 30);
        definitionX.put("Medium", 10);
        definitionX.put("Short", 3);
        definitionX.put("Typeflg", 1);
        definition.put("X", definitionX);
        // Definition for Record Y.
        Map<String, Integer> definitionY = new LinkedHashMap<String, Integer>();
        definitionY.put("RType", 1);
        definitionY.put("RNumber", 8);
        definitionY.put("ServGrp", 4);
        definitionY.put("Postcode", 4);
        definitionY.put("Suburb", 20);
        definitionY.put("Zone", 4);
        definitionY.put("Primary", 1);
        definition.put("Y", definitionY);
        return definition;
    }
}
