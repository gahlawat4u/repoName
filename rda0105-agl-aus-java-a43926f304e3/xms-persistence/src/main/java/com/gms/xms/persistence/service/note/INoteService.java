package com.gms.xms.persistence.service.note;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.note.NoteFilter;
import com.gms.xms.txndb.vo.NoteVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from INoteService
 * <p>
 * Author DatTV Oct 1, 2015
 */
public interface INoteService {
    public List<NoteVo> selectByFilter(NoteFilter filter) throws DaoException;

    public long countByFilter(NoteFilter filter) throws DaoException;

    public NoteVo selectById(Long noteId) throws DaoException;

    public void delete(Map<String, String> context, Long noteId) throws DaoException;

    public void insert(Map<String, String> context, NoteVo note) throws DaoException;

    public void update(Map<String, String> context, NoteVo note) throws DaoException;
}
