package com.gms.xms.persistence.utils;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.persistence.dao.PackageDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.admin.payables.salesrep.SalesRepDao;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Jul 14, 2016 3:39:06 PM
 * <p>
 * Author dattrinh
 */
public class ShipmentHelper {
    public String getRealAirbillNumber(String airbillNumber) {
        String realAirbillNumber = airbillNumber;
        while (realAirbillNumber.endsWith("X")) {
            realAirbillNumber = realAirbillNumber.substring(0, realAirbillNumber.length() - 1);
        }
        return realAirbillNumber;
    }

    public ShipmentTypeVo getShipmentType(String description, String displayDescription, Integer carrier) throws DaoException {
        String desc = removeDocAndNon(description.trim());
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentTypeVo;
        // Try get shipment type id from description.
        ShipmentTypeFilter filter = new ShipmentTypeFilter();
        filter.setShipmentTypeName(desc);
        filter.setServiceId(carrier);
        shipmentTypeVo = shipmentTypeDao.selectByShipmentTypeNameAndCarrier(filter);
        if (shipmentTypeVo != null) {
            return shipmentTypeVo;
        }
        // Try get shipment type id from display description.
        desc = removeDocAndNon(displayDescription.trim());
        filter = new ShipmentTypeFilter();
        filter.setShipmentTypeName(desc);
        filter.setServiceId(carrier);
        shipmentTypeVo = shipmentTypeDao.selectByEdiDescription(filter);
        if (shipmentTypeVo != null) {
            return shipmentTypeVo;
        }
        return null;
    }

    public Integer getContentType(String displayDescription) {
        String desc = displayDescription.trim().toLowerCase();
        if (desc.contains("Doc".toLowerCase())) {
            return 0; // Document
        }
        if (desc.contains("Env".toLowerCase())) {
            return 0; // Envelope.
        }
        if (desc.contains("Pak".toLowerCase())) {
            return 1; // Package
        }
        return 2; // Pak
    }

    public Integer getContentType4ViewRateSheet(String displayDescription) {
        String desc = displayDescription.trim().toLowerCase();
        if (desc.contains("Doc".toLowerCase())) {
            return 0; // Document
        }
        return 1; // Package
    }

    public String getContentTypeName(Integer contentType, Integer carrier, Integer packageFlag) {
        if (packageFlag == -1) {
            return "";
        }
        if (contentType == 0) {
            if (carrier == 40 || carrier == 51) {
                return "Env";
            } else {
                return "Doc";
            }
        } else if (contentType == 2) {
            return "Pak";
        } else {
            return "";
        }
    }

    public Integer getBound(String displayDescription) {
        String desc = displayDescription.trim().toLowerCase();
        if (desc.contains("(Inbound)".toLowerCase())) {
            return 1;
        }
        return 0;
    }

    public String getBoundName(Integer bound) {
        return bound == 0 ? "" : "(Inbound)";
    }

    public Integer getPackageFlag(Integer carrier, Integer content, Integer shipmentTypeId) {
        Integer packageFlag;
        if (carrier == 1 || carrier == 15 || carrier == 40 || carrier == 50 || carrier == 51) {
            packageFlag = content;
        } else {
            packageFlag = -1;
        }
        if (shipmentTypeId == 0) {
            packageFlag = -1;
        }
        return packageFlag;
    }

    public Integer getPackageIdByContentType(Integer contentType, Integer carrier) throws DaoException {
        PackageDao packageDao = new PackageDao();
        PackageVo packageVo = new PackageVo();
        packageVo.setContentType(contentType);
        packageVo.setCarrier(carrier);
        return packageDao.getPackageIdByContent(packageVo);
    }

    public String removeDocAndNon(String description) {
        Map<String, String> replaceMap = new HashMap<String, String>();
        String result = description;
        // First remove.
        replaceMap.put(" non doc", "");
        replaceMap.put(" nondoc", "");
        replaceMap.put(" (inbound)", "");
        result = AppUtils.replaceStringByMap(replaceMap, description);
        // Second remove.
        replaceMap.clear();
        replaceMap.put(" nondo", "");
        replaceMap.put(" non do", "");
        replaceMap.put(" doc", "");
        replaceMap.put(" non", "");
        result = AppUtils.replaceStringByMap(replaceMap, description);
        return result;
    }

    public String removeDocAndNon(String description, Integer carrier) {
        String result = removeDocAndNon(description);
        if (carrier == 3) {
            result = result.trim();
            result = result.substring(2, result.length());
        }
        return result.trim();
    }

    public String getWeightUnit(String weightUnit) {
        return (StringUtils.isBlank(weightUnit) || "k".equalsIgnoreCase(weightUnit) || "kg".equalsIgnoreCase(weightUnit)) ? "kg" : "lb";
    }

    public String getDimensionUnit(String weightUnit) {
        return "kg".equalsIgnoreCase(weightUnit) ? "cm" : "in";
    }

    public Long getSaleRepIdByCustomerCode(String customerCode) throws DaoException {
        SalesRepDao salesRepDao = new SalesRepDao();
        Long saleRepId = salesRepDao.getSaleRepIdByCustomerCode(customerCode);
        return saleRepId;
    }
}
