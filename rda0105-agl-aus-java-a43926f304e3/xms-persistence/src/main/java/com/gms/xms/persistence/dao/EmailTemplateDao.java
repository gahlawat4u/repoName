package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.EmailTemplateVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from EmailTemplateDaoService
 * <p>
 * Author DatTV Date Mar 24, 2015
 */
public class EmailTemplateDao extends BaseDao<Object> {

    public List<EmailTemplateVo> getEmailTemplateList() throws DaoException {
        return selectList(null, "EmailTemplate.getEmailTemplateList");
    }

    public List<EmailTemplateVo> getNormalEmailTemplateList() throws DaoException {
        return selectList(null, "EmailTemplate.getNormalEmailTemplateList");
    }

    public EmailTemplateVo getEmailTemplateByName(String templateName) throws DaoException {
        return (EmailTemplateVo) select(templateName, "EmailTemplate.getEmailTemplateByName");
    }

    public EmailTemplateVo getEmailTemplateById(Integer templateId) throws DaoException {
        return (EmailTemplateVo) select(templateId, "EmailTemplate.getEmailTemplateById");
    }

    public List<String> selectAdminEmaillistByName(String name) throws DaoException {
        return selectList(name, "EmailTemplate.selectAdminEmaillistByName");
    }

    public void insert(Map<String, String> context, EmailTemplateVo emailTemplateVo) throws DaoException {
        insert(context, emailTemplateVo, "EmailTemplate.insert");
    }

    public void updateEmailTemplate(Map<String, String> context, EmailTemplateVo emailTemplateVo) throws DaoException {
        update(context, emailTemplateVo, "EmailTemplate.update");
    }
}
