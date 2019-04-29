package com.gms.xms.persistence.service.languagevalue;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.languagevalue.LanguageValueFilter;
import com.gms.xms.txndb.vo.LanguageValueVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ILanguageValueService
 * </p>
 *
 * @author hungnt - Nov 28, 2015
 */
public interface ILanguageValueService {

    public List<LanguageValueVo> getLanguageValueList(LanguageValueFilter filter) throws DaoException;

    public Long getLanguageValueCount(LanguageValueFilter filter) throws DaoException;

    public LanguageValueVo getLanguageValueByKey(LanguageValueVo languageValueVo) throws DaoException;

    public LanguageValueVo getLanguageValueById(Integer id) throws DaoException;

    public void insertLanguageValue(Map<String, String> context, LanguageValueVo languageValueVo) throws DaoException;

    public void updateLanguageValue(Map<String, String> context, LanguageValueVo languageValueVo) throws DaoException;
}
