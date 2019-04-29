package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.LanguageListVo;

import java.util.List;

public class LocalizationDao extends BaseDao<Object> {
    public String selectLocalizationValueByLocalizationName(String localizationName) throws DaoException {
        return (String) selectObject(localizationName, "Localization.selectLocalizationValueByLocalizationName");
    }


}
