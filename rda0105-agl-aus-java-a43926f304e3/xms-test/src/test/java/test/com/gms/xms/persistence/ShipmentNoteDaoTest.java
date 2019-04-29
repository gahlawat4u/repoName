package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ShipmentNoteDao;
import com.gms.xms.txndb.vo.ShipmentNoteFilter;
import org.junit.Test;

public class ShipmentNoteDaoTest {

    @Test
    public void test() throws DaoException {
        ShipmentNoteFilter filter = new ShipmentNoteFilter();
        ShipmentNoteDao dao = new ShipmentNoteDao();
        filter.setShipmentId(324300L);
        filter.setSizeRecord(25);
        System.out.print(dao.selectNoteList(filter));

    }

}