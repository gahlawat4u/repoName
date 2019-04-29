package com.gms.xms.persistence.daoservice.webship.quotejob;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.QuotePieceDao;
import com.gms.xms.persistence.dao.WebshipQuoteLogDao;
import com.gms.xms.persistence.dao.WebshipQuoteLogDetailDao;
import com.gms.xms.txndb.vo.QuotePieceVo;
import com.gms.xms.txndb.vo.WebshipQuoteLogDetailVo;
import com.gms.xms.txndb.vo.webship.quotejob.QuoteJobVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from QuoteLogDaoService
 * <p>
 * Author DatTV Date Jul 25, 2015 10:17:57 AM
 */
public class QuoteLogDaoService {
    private static final Log log = LogFactory.getLog(QuoteLogDaoService.class);

    private AddressDao addressDao;
    private WebshipQuoteLogDao webshipQuoteLogDao;
    private WebshipQuoteLogDetailDao webshipQuoteLogDetailDao;
    private QuotePieceDao quotePieceDao;

    public void saveQuoteLog(Map<String, String> context, QuoteJobVo quoteJobVo) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        addressDao = new AddressDao(sessionClient);
        webshipQuoteLogDao = new WebshipQuoteLogDao(sessionClient);
        webshipQuoteLogDetailDao = new WebshipQuoteLogDetailDao(sessionClient);
        quotePieceDao = new QuotePieceDao(sessionClient);
        try {
            // Save sender and receiver address
            // Put signal to the context to log insert address as sender address.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Sender Address");
            addressDao.insert(context, quoteJobVo.getSenderAddress());
            quoteJobVo.setSenderAddressId(quoteJobVo.getSenderAddress().getAddressId());
            // Put signal to the context to log insert address as receiver address.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Receiver Address");
            addressDao.insert(context, quoteJobVo.getReceiverAddress());
            quoteJobVo.setReceiverAddressId(quoteJobVo.getReceiverAddress().getAddressId());
            // Save webship quote log
            webshipQuoteLogDao.insert(context, quoteJobVo);
            // Save quote pieces
            for (QuotePieceVo quotePieceVo : quoteJobVo.getQuotePieces()) {
                quotePieceVo.setQuoteId(quoteJobVo.getQuoteId());
                quotePieceDao.insert(context, quotePieceVo);
            }
            // Save webship quote log detail
            for (WebshipQuoteLogDetailVo quoteLogDetailVo : quoteJobVo.getQuoteLogDetails()) {
                quoteLogDetailVo.setQuoteId(quoteJobVo.getQuoteId());
                if (quoteLogDetailVo.getAccessorialId() != null) {
                    webshipQuoteLogDetailDao.insert(context, quoteLogDetailVo);
                }
            }
            sessionClient.endTransaction();
        } catch (DaoException e) {
            log.error(e);
            sessionClient.rollback();
            throw e;
        }
    }
}
