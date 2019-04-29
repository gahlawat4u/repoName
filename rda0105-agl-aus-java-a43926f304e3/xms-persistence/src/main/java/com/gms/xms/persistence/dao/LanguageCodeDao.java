package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;

public class LanguageCodeDao extends BaseDao<Object> {
    public String getLanguageCodeByLanguageName(String languageName) throws DaoException {
        return (String) selectObject(languageName, "LanguageCode.selectLanguageCodeByLanguageName");
    }
}