package com.gms.xms.workflow.helper;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.DhlRemoteSurchargeDataFilter;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.dao.webship.CheckRemoteSurchargeDao;
import com.gms.xms.persistence.dao.webship.DhlRemoteSurchargeDataDao;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.webship.DhlRemoteSurchargeDataVo;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class DhlHelper {
    private static DhlHelper instance = new DhlHelper();

    private DhlHelper() {
    }

    public static DhlHelper getInstance() {
        return instance;
    }

    public Boolean hasRemoteSurcharge(Long countryId, String stateName, String cityName, String postalCode) throws DaoException {
        String countryCode = "";
        CountryDao countryDao = new CountryDao();
        CountryVo countryVo = countryDao.getCountryById(countryId);
        if (countryVo != null) {
            countryCode = countryVo.getCountryCode();
        } else {
            return false;
        }

        // Get type of remote area surcharge
        CheckRemoteSurchargeDao checkRemoteSurchargeDao = new CheckRemoteSurchargeDao();
        String checkType = checkRemoteSurchargeDao.getCheckRemoteData(countryId);
        if (StringUtils.isBlank(checkType)) {
            return false;
        }
        DhlRemoteSurchargeDataDao dhlRemoteSurchargeDataDao = new DhlRemoteSurchargeDataDao();
        DhlRemoteSurchargeDataFilter filter = new DhlRemoteSurchargeDataFilter();
        filter.setCountryCode(countryCode);
        filter.setCityName(cityName);
        filter.setCheckType(checkType);
        filter.setPostalCode(postalCode);
        if (!StringUtils.isBlank(postalCode) && StringUtils.isNumeric(postalCode)) {
            Integer postalCodeCheck = Integer.valueOf(postalCode);
            if (postalCodeCheck >= 20000 && postalCodeCheck <= 20999) {
                return false;
            }
        }
        List<DhlRemoteSurchargeDataVo> dhlRemoteSurchargeDataVos = dhlRemoteSurchargeDataDao.getDhlRemoteSurchargeData(filter);
        if (dhlRemoteSurchargeDataVos != null && !dhlRemoteSurchargeDataVos.isEmpty()) {
            for (DhlRemoteSurchargeDataVo dhlRemoteSurchargeDataVo : dhlRemoteSurchargeDataVos) {
                String fromPostalCode = dhlRemoteSurchargeDataVo.getFromPostal();
                String toPostalCode = dhlRemoteSurchargeDataVo.getToPostal();
                String state = dhlRemoteSurchargeDataVo.getStateName();
                String city = dhlRemoteSurchargeDataVo.getCityName();
                if (StringUtils.isNotBlank(state) && StringUtils.contains(stateName.toUpperCase(), state)) {
                    return true;
                } else if (countryId == 98) {
                    if ((cityName.equals(city) && StringUtils.isBlank(fromPostalCode) && StringUtils.isBlank(toPostalCode)) || (postalCode.equals(toPostalCode) && postalCode.equals(fromPostalCode))) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
