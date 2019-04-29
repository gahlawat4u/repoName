package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.CreditNoteShowListFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CreditNoteFilter;
import com.gms.xms.txndb.vo.CreditNoteInfoVo;
import com.gms.xms.txndb.vo.CreditNoteVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CreditNoteDao
 * <p>
 * Author DatTV Date May 20, 2015 10:29:41 PM
 */
public class CreditNoteDao extends BaseDao<CreditNoteVo> {
    public CreditNoteDao() {
        super();
    }

    public CreditNoteDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public int insert(Map<String, String> context, CreditNoteVo creditNote) throws DaoException {
        return this.insert(context, creditNote, "CreditNote.insert");
    }

    public List<CreditNoteVo> selectCreateDateList(CreditNoteFilter creditNoteFilter) throws DaoException {
        return selectList(creditNoteFilter, "CreditNote.selectCreateDateList");
    }

    public List<CreditNoteVo> selectCreateDateListForSendCreditNotes(CreditNoteFilter creditNoteFilter) throws DaoException {
        return selectList(creditNoteFilter, "CreditNote.selectCreateDateListForSendCreditNotes");
    }

    public List<CreditNoteVo> selectFreezeCreditNoteDateList(CreditNoteFilter creditNoteFilter) throws DaoException {
        return selectList(creditNoteFilter, "CreditNote.selectFreezeCreditNoteDateList");
    }

    public List<CreditNoteVo> selectCreateDateListByCustomerCode(CreditNoteFilter creditNoteFilter) throws DaoException {
        return selectList(creditNoteFilter, "CreditNote_SelectCreateDateListCustomerCode");
    }

    public List<CreditNoteVo> selectListCreditNotesByFilter(CreditNoteFilter creditNoteFilter) throws DaoException {
        return selectList(creditNoteFilter, "CreditNote.selectListCreditNotesByFilter");
    }

    /**
     * selectCreditNoteTo
     *
     * @param creditNoteId
     * @return List<CreditNoteVo>
     * @throws DaoException
     */
    public List<CreditNoteVo> selectCreditNoteTo(Long creditNoteId) throws DaoException {
        return selectList(creditNoteId, "CreditNote.CreditNote_SelectCreditNoteTo");
    }

    /**
     * selectCreditNoteInfo
     *
     * @param creditNoteId
     * @return CreditNoteVo
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
    public List<CreditNoteInfoVo> selectCreditNoteInfo(Long creditNoteId) throws DaoException {
        return (List<CreditNoteInfoVo>) selectObject(creditNoteId, "CreditNote.CreditNote_SelectCreditInfo");
    }

    /**
     * selectCreditNoteShowList
     *
     * @param creditNoteId
     * @return CreditNoteVo
     * @throws DaoException
     */
    public List<CreditNoteVo> selectCreditNoteShowList(CreditNoteShowListFilter filter) throws DaoException {
        return selectList(filter, "CreditNote.CreditNote_SelectCreditShowList");
    }

    /**
     * Get credit note by credit code
     *
     * @param creditCode
     * @return
     * @throws DaoException
     */
    public CreditNoteVo selectCreditNoteByCreditCode(String creditCode) throws DaoException {
        return select(creditCode, "CreditNote.selectCreditNoteByCreditCode");
    }

    public CreditNoteVo selectByCreditCode(String creditCode) throws DaoException {
        return select(creditCode, "CreditNote.selectByCreditCode");
    }

    public Integer updateCreditNote(Map<String, String> context, CreditNoteVo creditNoteVo) throws DaoException {
        return update(context, creditNoteVo, "CreditNote.UpdateByPrimaryKeySelective");
    }

    public CreditNoteVo selectCreditNotesByCode(CreditNoteFilter creditNoteFilter) throws DaoException {
        return select(creditNoteFilter, "CreditNote.selectCreditNotesByCode");
    }

    public CreditNoteVo selectById(Long creditNoteId) throws DaoException {
        return this.select(creditNoteId, "CreditNote.selectById");
    }

    public void update(Map<String, String> context, CreditNoteVo creditNote) throws DaoException {
        update(context, creditNote, "CreditNote.update");
    }

    public void deleteById(Map<String, String> context, Long creditNoteId) throws DaoException {
        delete(context, creditNoteId, "CreditNote.deleteById");
    }
}
