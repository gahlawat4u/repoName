package com.gms.xms.workflow.task2.generateetfile.tollipec;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.constants.GenerateETFileConstants;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentAddressVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.generateetfile.GenerateTollETDataVo;
import com.gms.xms.txndb.vo.toll.TollShipmentVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Posted from Sep 26, 2016 10:59:53 AM
 * <p>
 * Author huynd
 */
public class PrepareDataForETFileTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareDataForETFileTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            List<TollShipmentVo> tollShipmentVos = context.get(GenerateETFileConstants.TOLL_SHIPMENT_GENERATE_LIST);
            ShipmentDao shipmentDao = new ShipmentDao();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            AddressDao addressDao = new AddressDao();
            ShipmentVo shipmentInfo = null;
            ShipmentAddressVo senderAddressVo = null, receiverAddressVo = null;
            List<GenerateTollETDataVo> tollETData = new LinkedList<GenerateTollETDataVo>();
            GenerateTollETDataVo tollETDataRow = null;
            Map<String, String> aMap = null;
            String airbillNumber = "", shipmentIdStr = "";
            Double weight = 0D, cubicLength = 0D, cubicWidth = 0D, cubicHeight = 0D, cubicVolume = 0D;
            Integer tg = 0, tg1 = 0, tg2 = 0, dec = 0, tenth = 0, unit = 0;
            String cubicVolumeStr = "";
            Integer cubicQty = 0;
            Integer numberOfPieces = 0;
            String serviceGroup = "", itemNumber = "", connNoItemNumber = "";
            Integer lineCount = 0;
            for (TollShipmentVo tollShipment : tollShipmentVos) {
                shipmentInfo = new ShipmentVo();
                shipmentInfo = shipmentDao.getShipmentInfoForETFile(tollShipment.getShipmentId());
                if (shipmentInfo != null) {
                    shipmentIdStr += shipmentInfo.getShipmentId().toString() + ",";
                    tollETDataRow = new GenerateTollETDataVo();
                    numberOfPieces = shipmentInfo.getNoOfPieces();
                    aMap = new LinkedHashMap<String, String>();
                    aMap.put("number_of_items", getRecord("1", "NUM", 5, "BF", "0"));
                    aMap.put("item_number_count", getRecord("1", "NUM", 3, "BF", "0"));
                    airbillNumber = shipmentInfo.getAirbillNumber();
                    aMap.put("airbill_number", getRecord(airbillNumber, "CHAR", 20, "AF", " "));
                    aMap.put("line_item_no", getRecord("00", "NUM", 2, "BF", "0"));
                    aMap.put("connote_entered_code", getRecord("Y", "CHAR", 1, "AF", " "));
                    aMap.put("activity_code", getRecord("RD", "CHAR", 2, "AF", " "));
                    aMap.put("segment_code", getRecord("R", "CHAR", 2, "AF", " "));
                    ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentInfo.getShipmentTypeId());
                    switch (shipmentInfo.getShipmentTypeId()) {
                        case 215:
                            serviceGroup = "3";
                            break;
                        case 216:
                            serviceGroup = "4";
                            break;
                        case 217:
                            serviceGroup = "8";
                            break;
                        default:
                            serviceGroup = shipmentTypeVo.getServiceGroup();
                            break;
                    }
                    aMap.put("service_code", getRecord(shipmentTypeVo.getServiceCode(), "CHAR", 2, "AF", " "));
                    aMap.put("system_identifer", getRecord("ia31", "CHAR", 4, "AF", " "));
                    DateFormat dateFormat = new SimpleDateFormat("yMd");
                    aMap.put("despatch_date", getRecord(dateFormat.format(new Date()), "NUM", 6, "AF", "0"));
                    aMap.put("special_handling", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("status_flag", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("sender_id", getRecord("", "CHAR", 3, "AF", " "));
                    senderAddressVo = new ShipmentAddressVo();
                    senderAddressVo = addressDao.getShipmentAddressById(shipmentInfo.getSenderAddressId());
                    aMap.put("sender_company_name", getRecord(senderAddressVo == null ? "" : senderAddressVo.getCompanyName().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("sender_city_name", getRecord(senderAddressVo == null ? "" : senderAddressVo.getCity().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("sender_postal_code", getRecord(senderAddressVo == null ? "" : senderAddressVo.getPostalCode().toUpperCase(), "NUM", 6, "AF", " "));
                    aMap.put("sender_contact_name", getRecord(senderAddressVo == null ? "" : senderAddressVo.getContactName().toUpperCase(), "CHAR", 20, "AF", " "));
                    aMap.put("sender_phone", getRecord(senderAddressVo == null ? "" : senderAddressVo.getPhone().toUpperCase(), "CHAR", 11, "AF", " "));
                    aMap.put("sender_fax", getRecord("", "CHAR", 11, "AF", " "));
                    aMap.put("sender_location", getRecord("", "CHAR", 6, "AF", " "));
                    aMap.put("sender_location_ext", getRecord("", "CHAR", 3, "AF", " "));
                    aMap.put("sender_address1", getRecord(senderAddressVo == null ? "" : senderAddressVo.getAddress().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("sender_address2", getRecord(senderAddressVo == null ? "" : senderAddressVo.getAddress2().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("sender_state", getRecord(senderAddressVo == null ? "" : senderAddressVo.getState().toUpperCase(), "CHAR", 3, "AF", " "));
                    aMap.put("sender_reference", getRecord(shipmentInfo.getCustomerCode().toString(), "CHAR", 15, "AF", " "));
                    aMap.put("sender_country_name", getRecord(senderAddressVo == null ? "" : senderAddressVo.getCountryName().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("service_description", getRecord(shipmentInfo.getShipmentTypeId() == 217 ? "Direct" : shipmentTypeVo.getShipmentTypeName(), "CHAR", 25, "AF", " "));
                    receiverAddressVo = new ShipmentAddressVo();
                    receiverAddressVo = addressDao.getShipmentAddressById(shipmentInfo.getReceiverAddressId());
                    aMap.put("receiver_country_name", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getCountryName().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("receiver_state", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getState().toUpperCase(), "CHAR", 3, "AF", " "));
                    aMap.put("receiver_company_name", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getCompanyName().toUpperCase(), "CHAR", 40, "AF", " "));
                    aMap.put("receiver_address1", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getAddress().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("receiver_address2", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getAddress2().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("receiver_city_name", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getCity().toUpperCase(), "CHAR", 30, "AF", " "));
                    aMap.put("receiver_postal_code", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getPostalCode().toUpperCase(), "NUM", 6, "AF", " "));
                    aMap.put("delv_early_date", getRecord("", "NUM", 6, "AF", " "));
                    aMap.put("delv_early_time", getRecord("", "NUM", 4, "AF", " "));
                    aMap.put("delv_late_date", getRecord("", "NUM", 6, "AF", " "));
                    aMap.put("delv_late_time", getRecord("", "NUM", 4, "AF", " "));
                    aMap.put("delv_special_instruction", getRecord(shipmentInfo.getReasonForExport(), "CHAR", 90, "AF", " "));
                    aMap.put("delv_special_code", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("receiver_contact_name", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getContactName().toUpperCase(), "CHAR", 20, "AF", " "));
                    aMap.put("receiver_phone", getRecord(receiverAddressVo == null ? "" : receiverAddressVo.getPhone().toUpperCase(), "CHAR", 11, "AF", " "));
                    aMap.put("receiver_fax", getRecord("", "CHAR", 11, "AF", " "));
                    aMap.put("receiver_location", getRecord("", "CHAR", 6, "AF", " "));
                    aMap.put("receiver_location_ext", getRecord("", "CHAR", 3, "AF", " "));
                    aMap.put("connote_reference", getRecord("", "CHAR", 15, "AF", " "));
                    aMap.put("receiver_key", getRecord("", "CHAR", 15, "AF", " "));
                    aMap.put("pallet_chep", getRecord("", "NUM", 3, "AF", " "));
                    aMap.put("pallet_loscam", getRecord("", "NUM", 3, "AF", " "));
                    aMap.put("pallet_other", getRecord("", "NUM", 3, "AF", " "));
                    aMap.put("insur_class", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("insur_cover", getRecord(MathUtils.round(shipmentInfo.getWithInsurance().doubleValue(), 2).toString(), "NUM", 7, "BF", "0"));
                    aMap.put("charge_account", getRecord(shipmentInfo.getBillingAccount(), "CHAR", 10, "AF", " "));
                    aMap.put("charge_code", getRecord("S", "CHAR", 1, "AF", " "));
                    aMap.put("apply_basic_charge", getRecord("Y", "CHAR", 1, "AF", " "));
                    aMap.put("charge_basic", getRecord("", "NUM", 5, "AF", " "));
                    aMap.put("charge_freight", getRecord("", "NUM", 7, "AF", " "));
                    aMap.put("charge_extra", getRecord("", "NUM", 7, "AF", " "));
                    aMap.put("charge_other", getRecord("", "NUM", 7, "AF", " "));
                    aMap.put("charge_tax", getRecord("", "NUM", 6, "AF", " "));
                    aMap.put("charge_surcharge", getRecord("", "NUM", 5, "AF", " "));
                    aMap.put("charge_total", getRecord("", "NUM", 9, "AF", " "));
                    aMap.put("charge_insur_per_con", getRecord("", "NUM", 9, "AF", " "));
                    aMap.put("charge_insur_additional", getRecord("", "NUM", 9, "AF", " "));
                    aMap.put("item_identifier", getRecord("", "CHAR", 20, "AF", " "));
                    aMap.put("line_unit_commod_items_count", getRecord("", "NUM", 5, "AF", " "));
                    aMap.put("line_unit_commod_items_code", getRecord("", "CHAR", 2, "AF", " "));
                    PieceDao pieceDao = new PieceDao();
                    List<PieceVo> pieceVos = pieceDao.selectByShipmentId(shipmentInfo.getShipmentId());
                    cubicLength = pieceVos.get(Integer.valueOf(GenerateETFileConstants.FIRST_PIECE_RESULT)).getDimensionL() / 100;
                    cubicWidth = pieceVos.get(Integer.valueOf(GenerateETFileConstants.FIRST_PIECE_RESULT)).getDimensionW() / 100;
                    cubicHeight = pieceVos.get(Integer.valueOf(GenerateETFileConstants.FIRST_PIECE_RESULT)).getDimensionH() / 100;
                    weight = MathUtils.round(pieceVos.get(Integer.valueOf(GenerateETFileConstants.FIRST_PIECE_RESULT)).getWeight(), 1);
                    aMap.put("line_weight", getRecord(weight.toString(), "NUM", 6, "BF", "0"));
                    aMap.put("line_commod", getRecord("", "CHAR", 2, "AF", " "));
                    aMap.put("package_type", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("desc_of_goods", getRecord("parcel", "CHAR", 20, "AF", " "));
                    aMap.put("cubic_length", getRecord(cubicLength.toString().replace(".", "0"), "NUM", 5, "BF", "0"));
                    aMap.put("cubic_width", getRecord(cubicWidth.toString().replace(".", "0"), "NUM", 5, "BF", "0"));
                    aMap.put("cubic_height", getRecord(cubicHeight.toString().replace(".", "0"), "NUM", 5, "BF", "0"));
                    cubicQty = cubicLength != 0 ? 1 : cubicQty;
                    cubicVolume = MathUtils.round(Math.round(cubicLength * cubicWidth * cubicHeight * 1000D) / 1000D, 3);
                    tg = Integer.valueOf(cubicVolume.toString().split("\\.")[0]);
                    dec = Integer.valueOf(cubicVolume.toString().split("\\.")[1]);
                    tg1 = tg / 10;
                    unit = tg - tg1.intValue() * 10;
                    tg2 = tg1 / 10;
                    tenth = tg1 - tg2.intValue() * 10;
                    cubicVolumeStr = tenth.toString() + unit.toString() + dec.toString();
                    aMap.put("cubic_qty", getRecord(cubicLength != 0 ? "00001" : cubicQty.toString(), "NUM", 5, "BF", "0"));
                    aMap.put("cubic_volume", getRecord(cubicVolumeStr, "NUM", 5, "BF", "0"));
                    aMap.put("rating_code", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("consolidation_flag", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("security_flag", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("remote_printing_flag", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("handpriced_insur", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("direct_delivery", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("returns_collections", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("extra_chrg_codes", getRecord("", "CHAR", 10, "AF", " "));
                    aMap.put("extended_description", getRecord("", "CHAR", 1, "AF", " "));
                    aMap.put("connote_narration", getRecord("", "CHAR", 78, "AF", " "));
                    aMap.put("danger_class", getRecord("", "CHAR", 10, "AF", " "));
                    aMap.put("dg_pack_group", getRecord("", "CHAR", 10, "AF", " "));
                    aMap.put("dg_un_code", getRecord("", "CHAR", 6, "AF", " "));
                    aMap.put("extended_connote_reference", getRecord(dateFormat.format(new Date()) + "  ", "CHAR", 40, "AF", " "));
                    aMap.put("declared_value", getRecord("0", "NUM", 20, "BF", "0"));
                    aMap.put("declared_currency_code", getRecord("AUD", "CHAR", 10, "AF", " "));
                    aMap.put("other_refs", getRecord("Agl Australia", "CHAR", 40, "AF", " "));
                    aMap.put("spare", getRecord("", "CHAR", 90, "AF", " "));
                    itemNumber = "6" + serviceGroup + receiverAddressVo == null ? "" : receiverAddressVo.getPostalCode() + airbillNumber + "1";
                    connNoItemNumber = "6" + serviceGroup + receiverAddressVo == null ? "" : receiverAddressVo.getPostalCode() + airbillNumber;
                    aMap.put("item_number", getRecord(itemNumber, "CHAR", 32, "AF", " "));
                    aMap.put("hud_batch_number", getRecord("", "CHAR", 14, "AF", " "));
                    aMap.put("manifest_number", getRecord(shipmentInfo.getShipmentId().toString(), "CHAR", 10, "BF", "0"));
                    aMap.put("record_id", getRecord("", "NUM", 5, "AF", " "));
                    aMap.put("system_reserved", getRecord("", "CHAR", 28, "AF", " "));
                    aMap.put("checksum", getRecord("", "CHAR", 3, "AF", " "));
                    aMap.put("terminator", getRecord("", "CHAR", 2, "AF", " "));
                    if (numberOfPieces == 1) {
                        tollETDataRow.setaRow(aMap);
                        tollETData.add(tollETDataRow);
                    } else if (numberOfPieces > 1) {
                        tollETData = loopForPieces(tollETData, aMap, connNoItemNumber, pieceVos, numberOfPieces, 1, 1, lineCount);
                    }
                    lineCount++;
                }
            }
            context.put(GenerateETFileConstants.GENERATE_TOLL_ET_DATA, tollETData);
            context.put(GenerateETFileConstants.SHIPMENT_ID_STRING, shipmentIdStr.substring(0, shipmentIdStr.length() - 1));
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private List<GenerateTollETDataVo> loopForPieces(List<GenerateTollETDataVo> tollETData, Map<String, String> aMap, String connNoItemNumber, List<PieceVo> pieceVos, Integer numberOfPieces, Integer numberOfItemsCount, Integer itemNumberCount, Integer lineCount) {
        GenerateTollETDataVo tollETDataRow = null;
        Map<String, String> aMapTmp = null;
        Double weight = 0D, cubicLength = 0D, cubicWidth = 0D, cubicHeight = 0D, cubicVolume = 0D;
        Integer cubicQty = 0;
        Integer j = 0;
        for (PieceVo piece : pieceVos) {
            j++;
            tollETDataRow = new GenerateTollETDataVo();
            aMapTmp = aMap;
            cubicLength = piece.getDimensionL() / 100;
            cubicWidth = piece.getDimensionW() / 100;
            cubicHeight = piece.getDimensionH() / 100;
            weight = MathUtils.round(piece.getWeight(), 1);
            cubicQty = cubicLength != 0 ? 1 : cubicQty;
            if (cubicQty > 0) {
                cubicVolume = Math.ceil(cubicLength * cubicWidth * cubicHeight * 250);
            }
            cubicVolume = cubicVolume * 1000;
            aMapTmp.put("number_of_items", getRecord("00001", "NUM", 5, "BF", "0"));
            if (numberOfItemsCount < numberOfPieces) {
                numberOfItemsCount++;
            } else {
                numberOfItemsCount = 1;
            }
            aMapTmp.put("item_number", getRecord(connNoItemNumber + getRecord(j.toString(), "NUM", 3, "BF", "0"), "CHAR", 32, "AF", " "));
            if (itemNumberCount < numberOfPieces) {
                itemNumberCount++;
            } else {
                itemNumberCount = 1;
            }
            aMapTmp.put("line_weight", getRecord(weight.toString(), "NUM", 6, "BF", "0"));
            aMapTmp.put("cubic_length", getRecord(cubicLength.toString().replace(".", "0"), "NUM", 5, "BF", "0"));
            aMapTmp.put("cubic_width", getRecord(cubicWidth.toString().replace(".", "0"), "NUM", 5, "BF", "0"));
            aMapTmp.put("cubic_height", getRecord(cubicHeight.toString().replace(".", "0"), "NUM", 5, "BF", "0"));
            aMapTmp.put("cubic_qty", getRecord(cubicLength != 0 ? "00001" : cubicQty.toString(), "NUM", 5, "BF", "0"));
            aMapTmp.put("cubic_volume", getRecord(String.valueOf(cubicVolume.intValue()), "NUM", 5, "BF", "0"));
            tollETDataRow.setaRow(aMapTmp);
            tollETData.add(tollETDataRow);
        }
        return tollETData;
    }

    private String getRecord(String text, String type, int length, String format, String fill) {
        if (text.length() > length) {
            text = text.substring(0, length);
        }
        if (StringUtils.isBlank(format)) {
            return text;
        } else {
            if ("CHAR".equalsIgnoreCase(type)) {
                if ("BF".equalsIgnoreCase(format)) {
                    return StringUtils.leftPad(text, length, fill);
                } else if ("AF".equalsIgnoreCase(format)) {
                    return StringUtils.rightPad(text, length, fill);
                }
            } else if ("NUM".equalsIgnoreCase(type)) {
                if ("BF".equalsIgnoreCase(format)) {
                    return StringUtils.leftPad(text, length, fill);
                } else if ("AF".equalsIgnoreCase(format)) {
                    return StringUtils.rightPad(text, length, fill);
                }
            }
        }
        return text;
    }

}
