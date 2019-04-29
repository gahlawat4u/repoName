package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.RateSheetDao;
import com.gms.xms.txndb.vo.RateSheetVo;
import org.junit.Test;

public class RateSheetDaoTest {

    @Test
    public void rateSheetTest() throws DaoException {
        RateSheetDao dao = new RateSheetDao();
        try {
            RateSheetVo RateSheetVo = dao.selectRateSheetFullTntDom(153L);
            System.out.println(RateSheetVo);

	    /*
         * RateSheetColumnModel model = new RateSheetColumnModel();
	     * model.setColumnName("4"); model.setSheetId("282");
	     * model.setColumnId("6893");
	     * System.out.println(ObjectsUtils.filterModels(listModel, model));
	     */
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}