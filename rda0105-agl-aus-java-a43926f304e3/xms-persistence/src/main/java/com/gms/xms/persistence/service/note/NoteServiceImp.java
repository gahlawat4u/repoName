package com.gms.xms.persistence.service.note;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.note.NoteFilter;
import com.gms.xms.persistence.dao.NoteDao;
import com.gms.xms.txndb.vo.NoteVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from NoteServiceImp
 * <p>
 * Author DatTV Oct 1, 2015
 */
public class NoteServiceImp implements INoteService {

    @Override
    public List<NoteVo> selectByFilter(NoteFilter filter) throws DaoException {
        NoteDao noteDao = new NoteDao();
        return noteDao.selectByFilter(filter);
    }

    @Override
    public long countByFilter(NoteFilter filter) throws DaoException {
        NoteDao noteDao = new NoteDao();
        return noteDao.countByFilter(filter);
    }

    @Override
    public NoteVo selectById(Long noteId) throws DaoException {
        NoteDao noteDao = new NoteDao();
        return noteDao.selectById(noteId);
    }

    @Override

    public void delete(Map<String, String> context, Long noteId) throws DaoException {
        NoteDao noteDao = new NoteDao();
        noteDao.delete(context, noteId);
    }

    @Override
    public void insert(Map<String, String> context, NoteVo note) throws DaoException {
        NoteDao noteDao = new NoteDao();
        noteDao.insert(context, note);
    }

    @Override
    public void update(Map<String, String> context, NoteVo note) throws DaoException {
        NoteDao noteDao = new NoteDao();
        noteDao.update(context, note);
    }
}
