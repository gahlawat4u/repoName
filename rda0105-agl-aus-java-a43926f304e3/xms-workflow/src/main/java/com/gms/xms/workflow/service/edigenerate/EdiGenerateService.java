package com.gms.xms.workflow.service.edigenerate;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.dto.edigenerate.TntShipmentDetailForEtVo;
import com.gms.xms.dto.edigenerate.TntShipmentForEtVo;
import com.gms.xms.dto.edigenerate.TntTransmissionParamVo;
import com.gms.xms.dto.edigenerate.TntTransmissionVo;
import com.gms.xms.dto.edigenerate.tntrecord.*;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.admin.edigenerate.EdiGenerateDao;
import com.gms.xms.persistence.dao.admin.edigenerate.TntTransmissionDao;
import com.gms.xms.persistence.dao.webship.TntConnoteDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.SystemSettingVo;
import com.gms.xms.txndb.vo.tnt.TntConnoteVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from Sep 22, 2016 10:53:49 AM
 * <p>
 * Author dattrinh
 */
public class EdiGenerateService extends BaseService {

    private EdiGenerateDao ediGenerateDao = new EdiGenerateDao();

    public EdiGenerateService(Map<String, String> context) {
        super(context);
    }

    public String generateETFileCombine(final String dir) throws Exception {
        String path = dir;
        if (StringUtils.isBlank(path)) {
            path = AppConstants.APP_SETTINGS.getEdiPath() + "/tnt_et_file_dispose";
        }
        // Get some system settings.
        SystemSettingDao settingDao = new SystemSettingDao();
        // Get sender id code.
        SystemSettingVo settingVo = settingDao.getSystemSettingByName("TNT Domestic Sender Identifier Code");
        String senderIdCode = (settingVo == null) ? "" : settingVo.getSettingValue();
        // Get item prefix.
        settingVo = settingDao.getSystemSettingByName("TNT Domestic Item Identifier Prefix");
        String itemPrefix = (settingVo == null) ? "" : settingVo.getSettingValue();
        // Get current date.
        Date now = new Date();
        // Get file name.
        String fileName = getFileName(now);
        List<TntShipmentForEtVo> tntShipmentForEtVos = getTntShipmentForEt();
        // Do not any thing if there isn't TNT shipments.
        if (tntShipmentForEtVos == null || tntShipmentForEtVos.size() == 0) {
            return "";
        }
        // Save TNT transmission.
        long total = tntShipmentForEtVos.size();
        TntTransmissionVo transmissionVo = saveTntTransmission(total, fileName);
        // Generate ET file.
        return createETFile(tntShipmentForEtVos, transmissionVo, path, fileName, now, senderIdCode, itemPrefix);
    }

    private String createETFile(final List<TntShipmentForEtVo> tntShipmentForEtVos, final TntTransmissionVo transmissionVo, final String path, final String fileName, final Date now, String senderIdCode, String itemPrefix) throws Exception {
        String fullFileName = path + "/" + fileName;
        File file = new File(fullFileName);
        if (!file.exists()) {
            // Create a new file.
            file.createNewFile();
            // Using file writer to write data.
            FileWriter writer = new FileWriter(file, false);
            // Begin write.
            int etLineNo = 0;
            // Transmission Header (Type A) Record.
            TntRecordA recordA = new TntRecordA();
            recordA.setRecordType("A");
            recordA.setTransmissionIdentifier(String.valueOf(transmissionVo.getTransIdentifier().intValue()));
            recordA.setSenderInterChangeAddress("               ");
            recordA.setReceiverInterChangeAddress("               ");
            recordA.setTradingPartnerIdentifier("");
            recordA.setCarrier("TNT");
            recordA.setFileGenerationDate(DateUtils.convertDateToString(now, "yyyyMMdd", null));
            recordA.setFileGenerationTime(DateUtils.convertDateToString(now, "HHmm", null));
            recordA.setFileVersionNumber("12");
            recordA.setRoutingEffectiveDate("20110731");
            recordA.setRoutingVersionNumber("123");
            if (recordA.isValidRow()) {
                writer.write(recordA.getRow());
                etLineNo++;
            }
            // Loop over TNT shipment for ET.
            long manifestIndex = 0;
            long manifestId = transmissionVo.getManifestIdentifier();
            for (TntShipmentForEtVo tntShipmentForEtVo : tntShipmentForEtVos) {
                manifestId += manifestIndex;
                manifestIndex = 1;
                String billingType = "S";
                if (tntShipmentForEtVo.getBillingType() == 2) {
                    billingType = "R";
                }
                if (tntShipmentForEtVo.getBillingType() == 3) {
                    billingType = "T";
                }
                // Manifest For Record B.
                TntRecordB recordB = new TntRecordB();
                recordB.setRecordType("B");
                recordB.setManifestIdentifier(String.valueOf(manifestId));
                recordB.setSenderIdentifierCode(senderIdCode);
                recordB.setSenderAccountNumber(!"S".equalsIgnoreCase(billingType) ? "          " : tntShipmentForEtVo.getBillingAccount());
                recordB.setSenderName(tntShipmentForEtVo.getCompanyName());
                recordB.setSenderAddressLine1(tntShipmentForEtVo.getAddress());
                recordB.setSenderAddressLine2(tntShipmentForEtVo.getAddress2());
                recordB.setSenderSuburb(tntShipmentForEtVo.getCity());
                recordB.setSenderState(tntShipmentForEtVo.getState());
                recordB.setSenderPostCode(tntShipmentForEtVo.getPostalCode());
                recordB.setSenderContactName(tntShipmentForEtVo.getContactName());
                recordB.setSenderContactPhone(tntShipmentForEtVo.getPhone());
                if (recordB.isValidRow()) {
                    writer.write(recordB.getRow());
                    etLineNo++;
                }
                // Get TNT shipment detail for ET.
                List<TntShipmentDetailForEtVo> tntShipmentDetailForEtVos = ediGenerateDao.getTntShipmentDetailForEt(tntShipmentForEtVo);
                for (TntShipmentDetailForEtVo tntShipmentDetailForEtVo : tntShipmentDetailForEtVos) {
                    if (transmissionVo.getTransId() != 0 && tntShipmentDetailForEtVo.getShipmentId() != null) {
                        TntConnoteDao tntConnoteDao = new TntConnoteDao();
                        TntConnoteVo tntConnoteVo = new TntConnoteVo();
                        tntConnoteVo.setTransId(transmissionVo.getTransId());
                        tntConnoteVo.setManifestIdentifier(manifestId);
                        tntConnoteVo.setShipmentId(tntShipmentDetailForEtVo.getShipmentId());
                        // Update TNT transmission connote.
                        tntConnoteDao.updateTntTransmissionConnote(this.getContext(), tntConnoteVo);
                    }
                    // Get shipment info by shipment id for ET file.
                    ShipmentVo shipmentVo = ediGenerateDao.getShipmentInfoByShipmentIdForEtFile(tntShipmentDetailForEtVo.getShipmentId());
                    boolean isDangerousGood = "DG".equalsIgnoreCase(shipmentVo.getCourierMessage().trim()) ? true : false;
                    // Get receiver address.
                    AddressDao addressDao = new AddressDao();
                    AddressVo receiverAddress = addressDao.selectById(shipmentVo.getReceiverAddressId());
                    // Get shipment pieces.
                    PieceDao pieceDao = new PieceDao();
                    List<PieceVo> pieceVos = pieceDao.selectByShipmentId(shipmentVo.getShipmentId());
                    int pageIndex = 0;
                    Map<Integer, Object> fItemArray = new HashMap<Integer, Object>();
                    Map<Integer, String> itemIdentifierArray = new HashMap<Integer, String>();
                    for (PieceVo pieceVo : pieceVos) {
                        pageIndex++;
                        Map<String, Object> ary = new HashMap<String, Object>();
                        ary.put("count", 1);
                        ary.put("l", pieceVo.getDimensionL());
                        ary.put("w", pieceVo.getDimensionW());
                        ary.put("h", pieceVo.getDimensionH());
                        ary.put("weight", pieceVo.getDeadWeight());
                        setItemGroup(ary, fItemArray);
                        String itemTempt = generateTntItemIdentifier(itemPrefix, shipmentVo.getAirbillNumber(), String.valueOf(pageIndex));
                        itemIdentifierArray.put(pageIndex, itemTempt);
                    }
                    Map<Integer, Integer> itemIdentifierGroupArray = new HashMap<Integer, Integer>();
                    int i = 0, j = 0;
                    for (@SuppressWarnings("unused")
                    Integer item : itemIdentifierArray.keySet()) {
                        i++;
                        if (i > 8) {
                            j++;
                            itemIdentifierGroupArray.put(j, i - 1);
                            i = 0;
                        }
                    }
                    // Consignment for record C.
                    TntRecordC recordC = new TntRecordC();
                    recordC.setRecordType("C");
                    recordC.setManifestIdentifier(String.valueOf(manifestId));
                    recordC.setConsignmentNoteNumber(shipmentVo.getAirbillNumber());
                    recordC.setLinesThisConsignment(String.valueOf(fItemArray.size()));
                    recordC.setReceiverIdentifierCode("");
                    recordC.setReceiverAccountNumber(!"R".equalsIgnoreCase(billingType) ? "          " : shipmentVo.getBillingAccount());
                    recordC.setReceiverName(receiverAddress.getCompanyName());
                    recordC.setReceiverAddressLine1(receiverAddress.getAddress());
                    recordC.setReceiverAddressLine2(receiverAddress.getAddress2());
                    recordC.setReceiverSuburb(receiverAddress.getCity());
                    recordC.setReceiverState(receiverAddress.getState());
                    recordC.setReceiverPostCode(receiverAddress.getPostalCode());
                    recordC.setReceiverContactName(receiverAddress.getContactName());
                    recordC.setReceiverContactPhone(receiverAddress.getPhone());
                    recordC.setConNoteDate(DateUtils.convertDateToString(shipmentVo.getShipmentDate(), "ddMMyyyy", null));
                    String serviceCode = "76";
                    Map<Integer, String> serviceMap = getServiceCodeMap();
                    if (serviceMap.get(shipmentVo.getShipmentTypeId()) != null) {
                        serviceCode = serviceMap.get(shipmentVo.getShipmentTypeId());
                        String[] arr = serviceCode.split(",");
                        serviceCode = arr[0];
                    }
                    recordC.setService(serviceCode);
                    recordC.setDangerousGoodsFlag(isDangerousGood ? "1" : "0");
                    recordC.setPayerFlag(billingType);
                    recordC.setCancelledFlag("0");
                    recordC.setFoodStuffsFlag("0");
                    recordC.setExtendedWarrantyValue(shipmentVo.getWithInsurance().doubleValue() > 0 ? String.valueOf(shipmentVo.getTotalCustomValue()) : "0");
                    recordC.setExtendedWarrantyClass(shipmentVo.getWithInsurance().doubleValue() > 0 ? "B" : " ");
                    recordC.setAdditionalService1("   ");
                    recordC.setAdditionalService2("   ");
                    recordC.setAdditionalService3("   ");
                    recordC.setAdditionalService4("   ");
                    recordC.setAdditionalService5("   ");
                    recordC.setHandRateAmount("0.00");
                    recordC.setOtherCharges("0.00");
                    recordC.setCustomerConsignmentReference("                              ");
                    writer.write(recordC.getRow());
                    etLineNo++;
                    // Third Party (Type D) Record.
                    TntRecordD recordD = new TntRecordD();
                    recordD.setRecordType("D");
                    recordD.setManifestIdentifier(String.valueOf(manifestId));
                    recordD.setConNoteNumber(shipmentVo.getAirbillNumber());
                    recordD.setThirdPartyIdentifierCode("");
                    recordD.setThirdPartyAccountNumber(shipmentVo.getBillingAccount());
                    recordD.setThirdPartyName("Third Party Name");
                    recordD.setThirdPartyAddressLine1("Third Party Address");
                    recordD.setThirdPartyAddressLine2("");
                    recordD.setThirdPartySuburb("Suburb");
                    recordD.setThirdPartyState("DSW");
                    recordD.setThirdPartyPostCode("1234");
                    if (recordD.isValidRow() && "T".equalsIgnoreCase(billingType)) {
                        writer.write(recordD.getRow());
                        etLineNo++;
                    }
                    // Special Instructions and Customer Text (Type E) Record.
                    TntRecordE recordE = new TntRecordE();
                    recordE.setRecordType("E");
                    recordE.setManifestIdentifier(String.valueOf(manifestId));
                    recordE.setConNoteNumber(shipmentVo.getAirbillNumber());
                    recordE.setTextType("0");
                    recordE.setTextLine(shipmentVo.getReasonForExport());
                    boolean isSpecialInstructions = StringUtils.isBlank(shipmentVo.getReasonForExport().trim()) ? false : true;
                    if (recordE.isValidRow() && isSpecialInstructions) {
                        writer.write(recordE.getRow());
                        etLineNo++;
                    }
                    int lineIndex = 0;
                    String fWeight = "";
                    for (Integer key : fItemArray.keySet()) {
                        @SuppressWarnings("unchecked")
                        Map<Integer, Object> item = (Map<Integer, Object>) fItemArray.get(key);
                        Double l = (Double) item.get("l");
                        Double w = (Double) item.get("w");
                        Double h = (Double) item.get("h");
                        Integer count = (Integer) item.get("count");
                        Double weight = (Double) item.get("weight");
                        lineIndex++;
                        if ("IN".equalsIgnoreCase(shipmentVo.getDimensionUnit())) {
                            shipmentVo.setDimensionUnit("CM");
                            l *= 2.54;
                            w *= 2.54;
                            h *= 2.54;
                        }
                        if ("LB".equalsIgnoreCase(shipmentVo.getWeightUnit())) {
                            shipmentVo.setWeightUnit("KG");
                            weight *= 0.45359237;
                        }
                        // Consignment Line (Type F) Record.
                        TntRecordF recordF = new TntRecordF();
                        recordF.setRecordType("F");
                        recordF.setManifestIdentifier(String.valueOf(manifestId));
                        recordF.setConNoteNumber(shipmentVo.getAirbillNumber());
                        recordF.setLineSequence(String.valueOf(lineIndex));
                        recordF.setCustomerReference(lineIndex > 1 ? "" : shipmentVo.getReference());
                        String packageName = "CARTON";
                        Map<Integer, String> packageMap = getPackageMap();
                        if (packageMap.get(shipmentVo.getPackageId()) != null) {
                            packageName = packageMap.get(shipmentVo.getPackageId()).toUpperCase();
                        }
                        recordF.setDescriptionOfGood(packageName);
                        recordF.setCommodityCode("      ");
                        recordF.setNumberOfItems(String.valueOf(count));
                        fWeight = AppUtils.formatNumber(String.valueOf(weight), 3);
                        recordF.setWeight(fWeight);
                        recordF.setUnitOfMeasureWeight(shipmentVo.getWeightUnit());
                        recordF.setItemDimensionLength(AppUtils.formatNumber(String.valueOf(l), 2));
                        recordF.setItemDimensionWidth(AppUtils.formatNumber(String.valueOf(w), 2));
                        recordF.setItemDimensionHeight(AppUtils.formatNumber(String.valueOf(h), 2));
                        recordF.setUnitOfMeasureDimension(shipmentVo.getDimensionUnit());
                        recordF.setCubicVolume(AppUtils.formatNumber(String.valueOf(0.000001 * l * w * h * count), 3));
                        recordF.setUnitOfMeasureCubicVolume("CO");
                        recordF.setArticleQty("0");
                        recordF.setGarmentQty("0");
                        if (recordF.isValidRow()) {
                            writer.write(recordF.getRow());
                            etLineNo++;
                        }
                    }
                    // Dangerous Goods For G Record.
                    TntRecordG recordG = new TntRecordG();
                    recordG.setRecordType("G");
                    recordG.setManifestIdentifier("Manifest");
                    recordG.setConNoteNumber(shipmentVo.getAirbillNumber());
                    recordG.setLineSequence("001");
                    recordG.setProductSequence("01");
                    recordG.setProductWeight(fWeight);
                    recordG.setUnitOfMeasureWeight("KG");
                    recordG.setHazMaterialType("c");
                    recordG.setHazMaterialCode("Haz");
                    recordG.setHazMaterialTechnicalName("e80");
                    recordG.setPrincipleRiskClass("Principle");
                    recordG.setPackagingGroupCode("Pack");
                    if (recordG.isValidRow() && isDangerousGood) {
                        writer.write(recordG.getRow());
                        etLineNo++;
                    }
                    int index = 0;
                    String itemId;
                    for (Integer key : itemIdentifierGroupArray.keySet()) {
                        index++;
                        // Item Identifiers (Type H) Record.
                        TntRecordH recordH = new TntRecordH();
                        recordH.setRecordType("H");
                        recordH.setManifestIdentifier(String.valueOf(manifestId));
                        recordH.setConNoteNumber(shipmentVo.getAirbillNumber());
                        recordH.setSequence(String.valueOf(index));
                        itemId = itemIdentifierGroupArray.get(key + 1) != null ? String.valueOf(itemIdentifierGroupArray.get(key + 1)) : "";
                        recordH.setItemIdentifier_1_9(itemId);
                        itemId = itemIdentifierGroupArray.get(key + 2) != null ? String.valueOf(itemIdentifierGroupArray.get(key + 2)) : "";
                        recordH.setItemIdentifier_2_10(itemId);
                        itemId = itemIdentifierGroupArray.get(key + 3) != null ? String.valueOf(itemIdentifierGroupArray.get(key + 3)) : "";
                        recordH.setItemIdentifier_3_11(itemId);
                        itemId = itemIdentifierGroupArray.get(key + 4) != null ? String.valueOf(itemIdentifierGroupArray.get(key + 4)) : "";
                        recordH.setItemIdentifier_4_12(itemId);
                        itemId = itemIdentifierGroupArray.get(key + 5) != null ? String.valueOf(itemIdentifierGroupArray.get(key + 5)) : "";
                        recordH.setItemIdentifier_5_13(itemId);
                        itemId = itemIdentifierGroupArray.get(key + 6) != null ? String.valueOf(itemIdentifierGroupArray.get(key + 6)) : "";
                        recordH.setItemIdentifier_6_14(itemId);
                        itemId = itemIdentifierGroupArray.get(key + 7) != null ? String.valueOf(itemIdentifierGroupArray.get(key + 7)) : "";
                        recordH.setItemIdentifier_7_15(itemId);
                        itemId = itemIdentifierGroupArray.get(key + 8) != null ? String.valueOf(itemIdentifierGroupArray.get(key + 8)) : "";
                        recordH.setItemIdentifier_8_16(itemId);
                        if (recordH.isValidRow()) {
                            writer.write(recordH.getRow());
                            etLineNo++;
                        }
                    }
                    // Alert Address (Type J) Record.
                    TntRecordJ recordJ = new TntRecordJ();
                    recordJ.setRecordType("J");
                    recordJ.setManifestIdentifier(String.valueOf(manifestId));
                    recordJ.setConNoteNumber(shipmentVo.getAirbillNumber());
                    recordJ.setAlertType("OP");
                    recordJ.setAddressType("E");
                    recordJ.setAddress("tui.adams1@jcu.edu.au                                                           ");
                    if (recordJ.isValidRow()) {
                        writer.write(recordJ.getRow());
                        etLineNo++;
                    }
                    // Special Reference (Type S) Record.
                    String newText = AppUtils.wordWrap(shipmentVo.getReference(), 8, "\n", true);
                    String[] refArr = newText.split("\n");
                    for (String text : refArr) {
                        text = text.trim();
                        if (StringUtils.isBlank(text))
                            continue;
                        TntRecordS recordS = new TntRecordS();
                        recordS.setRecordType("S");
                        recordS.setManifestIdentifier(String.valueOf(manifestId));
                        recordS.setConNoteNumber(shipmentVo.getAirbillNumber());
                        recordS.setSpecialReferenceText(text);
                        if (recordS.isValidRow()) {
                            writer.write(recordS.getRow());
                            etLineNo++;
                        }
                    }
                }
            }
            // Transmission Trailer For Z Record.
            etLineNo++;
            TntRecordZ recordZ = new TntRecordZ();
            recordZ.setRecordType("Z");
            recordZ.setTransmissionIdentifier(String.valueOf(transmissionVo.getTransIdentifier().intValue()));
            recordZ.setRecordCheckCount(String.valueOf(etLineNo));
            if (recordZ.isValidRow()) {
                writer.write(recordZ.getRow());
            }
            // End of writing file.
            writer.flush();
            writer.close();
            return fileName;
        } else {
            return "";
        }
    }

    private String generateTntItemIdentifier(String itemPrefix, String connoteNumber, String count) throws Exception {
        String connotePrefix = connoteNumber.substring(0, 3);
        connoteNumber = connoteNumber.substring(3);
        if (StringUtils.isBlank(itemPrefix.trim())) {
            SystemSettingDao settingDao = new SystemSettingDao();
            SystemSettingVo settingVo = settingDao.getSystemSettingByName("TNT Domestic Item Identifier Prefix");
            itemPrefix = settingVo.getSettingValue().trim();
        }
        int ch1 = connotePrefix.charAt(0) - 55;
        int ch2 = connotePrefix.charAt(1) - 55;
        int ch3 = connotePrefix.charAt(2) - 55;
        connotePrefix = String.valueOf(ch1) + String.valueOf(ch2) + String.valueOf(ch3);
        connotePrefix = connotePrefix.substring(0, 6);
        count = StringUtils.leftPad(count, 3, '0');
        return itemPrefix + connotePrefix + connoteNumber + count;
    }

    private void setItemGroup(Map<String, Object> ary, Map<Integer, Object> fItemArray) {
        int i = 0;
        for (Integer key : fItemArray.keySet()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> item = (Map<String, Object>) fItemArray.get(key);
            if (item.get("l") == ary.get("l") && item.get("w") == ary.get("w") && item.get("h") == ary.get("h")) {
                int count = (int) item.get("count") + 1;
                Double weight = (Double) item.get("weight") + (Double) ary.get("weight");
                Map<String, Object> gItem = new HashMap<String, Object>();
                gItem.put("count", count);
                gItem.put("l", item.get("l"));
                gItem.put("w", item.get("w"));
                gItem.put("h", item.get("h"));
                gItem.put("weight", weight);
                fItemArray.put(i, gItem);
                break;
            }
            i++;
        }
        fItemArray.put(i, ary);
    }

    private TntTransmissionVo saveTntTransmission(final long total, final String fileName) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            SystemSettingDao settingDao = new SystemSettingDao(sessionClient);
            SystemSettingVo settingVo;
            // Get transmission start.
            settingVo = settingDao.getSystemSettingByName("TNT Domestic Transmission Identifier Start");
            Double tranStart = (settingVo == null) ? 10000000000000000001D : Double.valueOf(settingVo.getSettingValue());
            // Get transmission end.
            settingVo = settingDao.getSystemSettingByName("TNT Domestic Transmission Identifier End");
            Double tranEnd = (settingVo == null) ? 19999999999999999999D : Double.valueOf(settingVo.getSettingValue());
            // Get manifest start.
            settingVo = settingDao.getSystemSettingByName("TNT Domestic Manifest Identifier Start");
            Long maniStart = (settingVo == null) ? 1000000001L : Long.valueOf(settingVo.getSettingValue());
            // Get manifest end.
            settingVo = settingDao.getSystemSettingByName("TNT Domestic Manifest Identifier End");
            Long maniEnd = (settingVo == null) ? 1999999999L : Long.valueOf(settingVo.getSettingValue());
            // Get reset.
            settingVo = settingDao.getSystemSettingByName("TNT Domestic Transmission and Manifest Reset");
            Integer reset = (settingVo == null) ? 0 : Integer.valueOf(settingVo.getSettingValue());
            TntTransmissionDao transmissionDao = new TntTransmissionDao(sessionClient);
            if (reset == 1) {
                // Set upload file name.
                String uploadFile = "Reset at " + DateUtils.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss", null);
                // Create new TNT Transmission vo.
                TntTransmissionVo tntTransmissionVo = new TntTransmissionVo();
                tntTransmissionVo.setTransIdentifier(tranEnd);
                tntTransmissionVo.setManifestIdentifier(maniEnd);
                tntTransmissionVo.setUploadFile(uploadFile);
                tntTransmissionVo.setStatus(1);
                tntTransmissionVo.setManifestCount(1);
                // Insert TNT transmission record into the database.
                transmissionDao.insert(this.getContext(), tntTransmissionVo);
                // Update reset status.
                settingVo.setSettingValue("0");
                settingDao.update(this.getContext(), settingVo);
            }
            // Get new TNT transmission info.
            TntTransmissionParamVo paramVo = new TntTransmissionParamVo();
            paramVo.setTransStart(tranStart);
            paramVo.setTransEnd(tranEnd);
            paramVo.setManifestStart(maniStart);
            paramVo.setManifestEnd(maniEnd);
            paramVo.setManifestCount(total);
            TntTransmissionVo tntTransmissionVo = transmissionDao.getTntTransmissionByParams(paramVo);
            tntTransmissionVo.setUploadFile(fileName);
            // Insert new TNT transmission record into the database.
            transmissionDao.insert(this.getContext(), tntTransmissionVo);
            // Commit transaction.
            sessionClient.endTransaction();
            return tntTransmissionVo;
        } catch (Exception e) {
            sessionClient.rollback();
            throw e;
        }
    }

    private Map<Integer, String> getServiceCodeMap() throws Exception {
        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        List<ShipmentTypeVo> shipmentTypeVos = shipmentTypeDao.selectAll();
        for (ShipmentTypeVo shipmentTypeVo : shipmentTypeVos) {
            resultMap.put(shipmentTypeVo.getShipmentTypeId(), shipmentTypeVo.getServiceCode());
        }
        return resultMap;
    }

    private Map<Integer, String> getPackageMap() throws Exception {
        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        PackageDao packageDao = new PackageDao();
        List<PackageVo> packageVos = packageDao.getPackageListByCarrier(2);
        for (PackageVo packageVo : packageVos) {
            resultMap.put(packageVo.getPackageId(), packageVo.getPackageName());
        }
        return resultMap;
    }

    private String getFileName(final Date now) throws Exception {
        SystemSettingDao settingDao = new SystemSettingDao();
        SystemSettingVo settingVo = settingDao.getSystemSettingByName("TNT Domestic ET File Name");
        String tradingPartner = (settingVo == null) ? "" : settingVo.getSettingValue();
        if (StringUtils.isBlank(tradingPartner)) {
            tradingPartner = "INXPAU";
        }
        String fileName = tradingPartner.substring(0, 6);
        fileName += DateUtils.convertDateToString(now, "dd-MM-hh:mm", null) + ".dat";
        fileName = fileName.replace("-", "");
        fileName = fileName.replace(":", "");
        return fileName;
    }

    private List<TntShipmentForEtVo> getTntShipmentForEt() throws Exception {
        return ediGenerateDao.getTntShipmentForEt();
    }

    public int tntEtFileDispose(String files, String localFolder) throws Exception {
        SystemSettingDao settingDao = new SystemSettingDao();
        // Get FTP server.
        SystemSettingVo settingVo = settingDao.getSystemSettingByName("TNT Domestic FTP URL");
        String ftpServer = settingVo.getSettingValue().trim();
        // Get FTP username.
        settingVo = settingDao.getSystemSettingByName("TNT Domestic FTP User Name");
        String ftpUsername = settingVo.getSettingValue().trim();
        // Get FTP password.
        settingVo = settingDao.getSystemSettingByName("TNT Domestic FTP Password");
        String ftpPassword = settingVo.getSettingValue().trim();
        // Get server folder.
        settingVo = settingDao.getSystemSettingByName("TNT Domestic FTP ET File Path");
        String serverFolder = settingVo.getSettingValue().trim();
        // Set return value.
        int ret = 0;
        FTPClient client = new FTPClient();
        client.connect(ftpServer);
        if (client.login(ftpUsername, ftpPassword)) {
            client.enterLocalPassiveMode();
            client.setFileType(FTP.ASCII_FILE_TYPE);
            String[] fileArray = files.split(",");
            for (String file : fileArray) {
                File localFile = new File(localFolder + "/" + file);
                if (localFile.exists()) {
                    String remoteFile = serverFolder + "/" + file;
                    InputStream inputStream = new FileInputStream(localFile);
                    boolean done = client.storeFile(remoteFile, inputStream);
                    inputStream.close();
                    ret = done ? 1 : 0;
                }
            }
        } else {
            ret = -1;
        }
        client.disconnect();
        return ret;
    }

    public void updateTntTransmissionUpload(String fileName) throws Exception {
        TntTransmissionDao tntTransmissionDao = new TntTransmissionDao();
        TntTransmissionVo tntTransmissionVo = new TntTransmissionVo();
        tntTransmissionVo.setUploadFile(fileName);
        tntTransmissionDao.updateTntTransmissionUpload(this.getContext(), tntTransmissionVo);
    }

    public int tntEtFileDelete(String files, String localFolder) throws Exception {
        SystemSettingDao settingDao = new SystemSettingDao();
        // Get FTP server.
        SystemSettingVo settingVo = settingDao.getSystemSettingByName("TNT Domestic FTP URL");
        String ftpServer = settingVo.getSettingValue().trim();
        // Get FTP username.
        settingVo = settingDao.getSystemSettingByName("TNT Domestic FTP User Name");
        String ftpUsername = settingVo.getSettingValue().trim();
        // Get FTP password.
        settingVo = settingDao.getSystemSettingByName("TNT Domestic FTP Password");
        String ftpPassword = settingVo.getSettingValue().trim();
        // Get server folder.
        settingVo = settingDao.getSystemSettingByName("TNT Domestic FTP ET File Path");
        String serverFolder = settingVo.getSettingValue().trim();
        // Set return value.
        int ret = 0;
        FTPClient client = new FTPClient();
        client.connect(ftpServer);
        if (client.login(ftpUsername, ftpPassword)) {
            client.enterLocalPassiveMode();
            String[] fileArray = files.split(",");
            for (String file : fileArray) {
                String remoteFile = serverFolder + "/" + file;
                boolean done = client.deleteFile(remoteFile);
                ret = done ? 1 : 0;
            }
        } else {
            ret = -1;
        }
        client.disconnect();
        return ret;
    }

    public void sendEmailTntEtFile(String fromEmail, String subject, String body, String toEmail, boolean isHtml) throws Exception {
        String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
        int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
        String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
        if (StringUtils.isBlank(fromEmail)) {
            fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
        }
        String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
        String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
        AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, toEmail, "", "", subject, body, null);
    }
}
