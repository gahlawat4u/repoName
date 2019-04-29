package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.languagevalue.LanguageValueFilter;
import com.gms.xms.txndb.vo.LanguageValueVo;

import java.util.List;
import java.util.Map;

public class LanguageValueDao extends BaseDao<LanguageValueVo> {

    /**
     * @return list {@link LanguageValueVo}
     * @throws DaoException
     */
    public List<LanguageValueVo> getAll() throws DaoException {
        return selectList(new LanguageValueVo(), "LanguageValue.getAll");
    }

    /**
     * @param filter
     * @return list {@link LanguageValueVo}
     * @throws DaoException
     */
    public List<LanguageValueVo> getLanguageValueList(LanguageValueFilter filter) throws DaoException {
        return selectList(filter, "LanguageValue.getLanguageValueList");
    }

    /**
     * @param languageValueVo
     * @return {@link LanguageValueVo}
     * @throws DaoException
     */
    public List<LanguageValueVo> getLanguageValueByKey(LanguageValueVo languageValueVo) throws DaoException {
        return selectList(languageValueVo, "LanguageValue.selectLanguageValueByKey");
    }

    public LanguageValueVo getLanguageValueById(Integer id) throws DaoException {
        return select(id, "LanguageValue.selectLanguageValueById");
    }

    public Long getLanguageValueCount(LanguageValueFilter filter) throws DaoException {
        return (Long) selectObject(filter, "LanguageValue.getLanguageValueCount");
    }

    public void insertLanguageValue(Map<String, String> context, LanguageValueVo languageValueVo) throws DaoException {
        insert(context, languageValueVo, "LanguageValue.insertLanguageValue");
    }

    public void updateLanguageValue(Map<String, String> context, LanguageValueVo languageValueVo) throws DaoException {
        update(context, languageValueVo, "LanguageValue.updateLanguageValue");
    }
}