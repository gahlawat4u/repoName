package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.AirbillCreditNoteVo;

import java.util.List;

/**
 * Posted from ShipmentDaoService
 * <p>
 * Author HoangPH Date May 22, 2015
 */
public class AirbillCreditNoteDao extends BaseDao<AirbillCreditNoteVo> {

    /**
     * @return
     * @throws DaoException
     */
    /*public AirbillCreditNoteDao() {
		super();
	}

	public AirbillCreditNoteDao(SqlSessionClient sqlSessionClient) {
		super(sqlSessionClient);
	}*/
    public List<AirbillCreditNoteVo> getAdjustmentAirbillInfo(Long creditNoteId) throws DaoException {
        return selectList(creditNoteId, "AirbillCreditNote.getListAdjustmentAirbill");
    }

}
