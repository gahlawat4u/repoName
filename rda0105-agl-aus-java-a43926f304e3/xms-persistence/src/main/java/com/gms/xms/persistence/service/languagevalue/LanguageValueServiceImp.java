package com.gms.xms.persistence.service.languagevalue;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.languagevalue.LanguageValueFilter;
import com.gms.xms.persistence.dao.LanguageValueDao;
import com.gms.xms.txndb.vo.LanguageValueVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from LanguageValueServiceImp
 * </p>
 *
 * @author hungnt - Nov 30, 2015
 */
public class LanguageValueServiceImp implements ILanguageValueService {
    private LanguageValueDao dao = new LanguageValueDao();

    @Override
    public List<LanguageValueVo> getLanguageValueList(LanguageValueFilter filter) throws DaoException {
        return dao.getLanguageValueList(filter);
    }

    @Override
    public Long getLanguageValueCount(LanguageValueFilter filter) throws DaoException {
        return dao.getLanguageValueCount(filter);
    }

    @Override
    public LanguageValueVo getLanguageValueByKey(LanguageValueVo languageValueVo) throws DaoException {
        String orgKey = languageValueVo.getOriginal();
        List<LanguageValueVo> languageValueVos = dao.getLanguageValueByKey(languageValueVo);
        if (languageValueVos != null) {
            for (LanguageValueVo vo : languageValueVos) {
                if (vo != null && vo.getOriginal() != null && vo.getOriginal().equals(orgKey)) {
                    return vo;
                }
            }
        }
        return null;

    }

    @Override
    public LanguageValueVo getLanguageValueById(Integer id) throws DaoException {
        return dao.getLanguageValueById(id);
    }

    @Override
    public void insertLanguageValue(Map<String, String> context, LanguageValueVo languageValueVo) throws DaoException {
        dao.insertLanguageValue(context, languageValueVo);
    }

    @Override
    public void updateLanguageValue(Map<String, String> context, LanguageValueVo languageValueVo) throws DaoException {
        dao.updateLanguageValue(context, languageValueVo);
    }
}
