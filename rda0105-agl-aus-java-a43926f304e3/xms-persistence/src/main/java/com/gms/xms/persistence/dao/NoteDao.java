package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.note.NoteAndFollowUpFilter;
import com.gms.xms.filter.note.NoteFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.NoteVo;
import com.gms.xms.txndb.vo.admin.note.NoteAndFollowUpVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from NoteDao
 * <p>
 * Author DatTV Date May 11, 2015 2:19:16 PM
 */
public class NoteDao extends BaseDao<Object> {
    public NoteDao() {
        super();
    }

    public NoteDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void insert(Map<String, String> context, NoteVo note) throws DaoException {
        insert(context, note, "Note.insert");
    }

    public void update(Map<String, String> context, NoteVo note) throws DaoException {
        update(context, note, "Note.update");
    }

    public void delete(Map<String, String> context, Long noteId) throws DaoException {
        delete(context, noteId, "Note.delete");
    }

    public List<NoteVo> selectByFilter(NoteFilter filter) throws DaoException {
        return this.selectList(filter, "Note.selectByFilter");
    }

    public long countByFilter(NoteFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Note.countByFilter");
    }

    public NoteVo selectById(Long noteId) throws DaoException {
        return (NoteVo) this.select(noteId, "Note.selectById");
    }

    public List<NoteAndFollowUpVo> getNotesAndFollowUp(NoteAndFollowUpFilter filter) throws DaoException {
        return this.selectList(filter, "Note.getNotesAndFollowUp");
    }

    public long countNotesAndFollowUp(NoteAndFollowUpFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Note.countNotesAndFollowUp");
    }
}