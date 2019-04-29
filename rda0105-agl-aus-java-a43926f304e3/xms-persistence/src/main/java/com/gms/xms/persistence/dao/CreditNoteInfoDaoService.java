package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CreditNoteInfoVo;
import com.gms.xms.txndb.vo.CreditNotesGSTSummaryVo;

import java.util.List;

/**
 * Posted from CreditNoteInfoDaoService
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CreditNoteInfoDaoService extends BaseDao<CreditNoteInfoVo> {
    public CreditNoteInfoDaoService() {
        super();
    }

    public CreditNoteInfoDaoService(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * selectCreditNoteInfo
     *
     * @param creditNoteId
     * @return CreditNoteVo
     * @throws DaoException
     */
    public CreditNoteInfoVo selectCreditNoteInfo(String creditNoteCode) throws DaoException {
        return select(creditNoteCode, "CreditNote.CreditNote_SelectCreditInfo");
    }

    @SuppressWarnings("unchecked")
    public List<CreditNotesGSTSummaryVo> selectCreditNoteGSTSummary(String creditNoteCode) throws DaoException {
        return (List<CreditNotesGSTSummaryVo>) (Object) selectObjectList(creditNoteCode, "CreditNote.selectCreditNoteGSTSummary");
    }

}
