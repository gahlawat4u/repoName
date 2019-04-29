package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CreditNoteDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CreditNoteDetailDao
 * <p>
 * Author DatTV Date May 20, 2015 10:33:03 PM
 */
public class CreditNoteDetailDao extends BaseDao<CreditNoteDetailVo> {
    public CreditNoteDetailDao() {
        super();
    }

    public CreditNoteDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<CreditNoteDetailVo> selectByCreditNoteId(Long creditNoteId) throws DaoException {
        return selectList(creditNoteId, "CreditNoteDetail.selectByCreditNoteId");
    }

    public CreditNoteDetailVo selectByAdjustmentId(Long adjustmentId) throws DaoException {
        return select(adjustmentId, "CreditNoteDetail.selectByAdjustmentId");
    }

    public CreditNoteDetailVo selectByVo(CreditNoteDetailVo creditNoteDetailVo) throws DaoException {
        return select(creditNoteDetailVo, "CreditNoteDetail.selectByVo");
    }

    public void insert(Map<String, String> context, CreditNoteDetailVo creditNoteDetail) throws DaoException {
        this.insert(context, creditNoteDetail, "CreditNoteDetail.insert");
    }

    public void resetCustomerPaymentId(Map<String, String> context, Long creditNoteId) throws DaoException {
        this.update(context, creditNoteId, "CreditNoteDetail.resetCustomerPaymentId");
    }

    public void updateCustomerPaymentId(Map<String, String> context, CreditNoteDetailVo creditNoteDetailVo) throws DaoException {
        this.update(context, creditNoteDetailVo, "CreditNoteDetail.updateCustomerPaymentId");
    }

    public void deleteCreditDetailByAdjustmentId(Map<String, String> context, Long adjustmentId) throws DaoException {
        this.delete(context, adjustmentId, "CreditNoteDetail.deleteCreditDetailByAdjustmentId");
    }

    public int countByCreditNoteId(Long creditNoteId) throws DaoException {
        return (int) this.selectObject(creditNoteId, "CreditNoteDetail.countByCreditNoteId");
    }

    public void update(Map<String, String> context, CreditNoteDetailVo creditNoteDetailVo) throws DaoException {
        this.update(context, creditNoteDetailVo, "CreditNoteDetail.update");
    }
}
