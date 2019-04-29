package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.txndb.vo.EmailTemplateVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from EmailTemplateServiceImp
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class EmailTemplateServiceImp implements IEmailTemplateService {
    public List<EmailTemplateVo> selectAll() throws DaoException {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        // List<ProductVo> productVos = productDao.selectByFilter(filter);
        return emailTemplateDao.getEmailTemplateList();
    }

    public EmailTemplateVo selectById(Integer templateId) throws DaoException {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        return emailTemplateDao.getEmailTemplateById(templateId);
    }

    public void insert(Map<String, String> context, EmailTemplateVo emailTemplateVo) throws DaoException {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        emailTemplateDao.insert(context, emailTemplateVo);
    }

    public void update(Map<String, String> context, EmailTemplateVo emailTemplateVo) throws DaoException {
        EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
        emailTemplateDao.updateEmailTemplate(context, emailTemplateVo);
    }
}
