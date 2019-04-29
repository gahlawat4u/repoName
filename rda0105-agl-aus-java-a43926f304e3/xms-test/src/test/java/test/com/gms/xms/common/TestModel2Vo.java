package test.com.gms.xms.common;

import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.CustomerAddressBookModel;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;

public class TestModel2Vo {
    public CustomerAddressBookVo vo2Model() {
        CustomerAddressBookModel bookModel = new CustomerAddressBookModel();
        bookModel.setCustomerCode("4343");
        bookModel.setContactName("Hung thoi");
        try {
            CustomerAddressBookVo addressBookVo = ModelUtils.createVoFromModel(bookModel, CustomerAddressBookVo.class);
            return addressBookVo;
        } catch (Exception e) {
            System.out.println("Faill to convert from Model to Vo");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        TestModel2Vo model2Vo = new TestModel2Vo();
        System.out.println(model2Vo.vo2Model());
        System.out.println(ModelUtils.createModelFromVo(model2Vo.vo2Model(), CustomerAddressBookModel.class));
    }
}
