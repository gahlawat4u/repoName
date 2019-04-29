package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.EmailTemplateVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IEmailTemplateService
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public interface IEmailTemplateService {
    public List<EmailTemplateVo> selectAll() throws DaoException;

    public EmailTemplateVo selectById(Integer templateId) throws DaoException;

    public void insert(Map<String, String> context, EmailTemplateVo emailTemplateVo) throws DaoException;

    public void update(Map<String, String> context, EmailTemplateVo emailTemplateVo) throws DaoException;
}
