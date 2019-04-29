package test.com.gms.xms.service;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.note.NoteFilter;
import com.gms.xms.persistence.service.note.INoteService;
import com.gms.xms.persistence.service.note.NoteServiceImp;
import org.junit.Test;

/**
 * Posted from NoteServiceTest
 * <p>
 * Author DatTV Oct 1, 2015
 */
public class NoteServiceTest {
    @Test
    public void selectByFilterTest() throws Exception {
        NoteFilter filter = new NoteFilter();
        filter.setCustomerCode("10000002");
        INoteService noteService = new NoteServiceImp();
        System.out.println(noteService.selectByFilter(filter));
    }

    @Test
    public void countByFilterTest() throws DaoException {
        NoteFilter filter = new NoteFilter();
        filter.setCustomerCode("10000002");
        INoteService noteService = new NoteServiceImp();
        System.out.println("Count: " + noteService.countByFilter(filter));
    }
}