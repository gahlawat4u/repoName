package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.LanguageListVo;

import java.util.List;

public class LanguageDao extends BaseDao<Object> {
    public String getLanguageNameByLanguageId(Integer languageId) throws DaoException {
        return (String) selectObject(languageId, "Language.selectLanguageNameByLanguageId");
    }

    @SuppressWarnings("unchecked")
    public List<LanguageListVo> getLanguageListWithCode() throws DaoException {
        return (List<LanguageListVo>) (Object) selectObjectList(null, "Language.selectLanguageListWithCode");
    }
}
