package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.CreditNoteShowListFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CreditNoteShowListVo;

import java.util.List;

/**
 * Posted from CreditNoteInfoDaoService
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CreditNoteShowListDaoService extends BaseDao<CreditNoteShowListVo> {
    public CreditNoteShowListDaoService() {
        super();
    }

    public CreditNoteShowListDaoService(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * selectCreditNoteInfo
     *
     * @param creditNoteId
     * @return CreditNoteVo
     * @throws DaoException
     */
    public List<CreditNoteShowListVo> selectCreditNoteShowList(CreditNoteShowListFilter filter) throws DaoException {
        return selectList(filter, "CreditNote.CreditNote_SelectCreditShowList");
    }

}
