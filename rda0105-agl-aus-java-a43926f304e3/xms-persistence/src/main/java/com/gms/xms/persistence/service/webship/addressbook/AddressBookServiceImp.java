package com.gms.xms.persistence.service.webship.addressbook;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.addressbook.AddressBookModel;
import com.gms.xms.persistence.dao.webship.addressbook.CustomerAddressBookDao;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookFilter;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookVo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from AddressBookServiceImp
 * <p>
 * Author HungNT Date Jul 8, 2015
 */
public class AddressBookServiceImp implements IAddressBookService {
    private CustomerAddressBookDao bookPageDao = new CustomerAddressBookDao();

    @Override
    public List<AddressBookVo> getCustomerAddressBook(AddressBookFilter filter) throws Exception {
        List<AddressBookVo> addressBookVos = bookPageDao.selectAddressBookList(filter);
        return addressBookVos;
    }

    @Override
    public Long getAddressBookCount(AddressBookFilter addressBookPageFilter) throws DaoException {
        Long recordCount = bookPageDao.selectCountCustomerAddressBook(addressBookPageFilter);
        return recordCount;
    }

    @Override
    public List<AddressBookVo> getCustomerAddressBookByFilter(AddressBookFilter filter) throws DaoException {
        List<AddressBookVo> addressBookVos = bookPageDao.selectCustomerAddressBookByFilter(filter);
        return addressBookVos;
    }

    @Override
    public Long getCustomerAddressBookCountByFilter(AddressBookFilter filter) throws DaoException {
        Long recordCount = bookPageDao.selectCustomerAddressBookCountByFilter(filter);
        return recordCount;
    }

    @Override
    public CustomerAddressBookVo getCustomerAddressBookById(long addressId) throws DaoException {
        CustomerAddressBookVo addressBookVo = bookPageDao.getCustomerAddressBookById(addressId);
        return addressBookVo;
    }

    @Override
    public void renderXLSFile(AddressBookFilter addressBookFilter, String outPutFilePath) throws Exception {
        List<AddressBookVo> addressBookVos = getCustomerAddressBook(addressBookFilter);
        List<AddressBookModel> addressBookModel = ModelUtils.createListModelFromVo(addressBookVos, AddressBookModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("addressBookList", addressBookModel);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/customer_address_book/address_book.xls", data);
    }

    @Override
    public void updateCustomerAddressBook(Map<String, String> context, CustomerAddressBookVo addressBookVo) throws DaoException {
        bookPageDao.updateCustomerAddressBookById(context, addressBookVo);
    }

    @Override
    public void insertCustomerAddressBook(Map<String, String> context, CustomerAddressBookVo addressBookVo) throws Exception {
        bookPageDao.insertCustomerAddressBook(context, addressBookVo);
    }

    @Override
    public void deleteCustomerAddressBookByIdTask(Map<String, String> context, long addressId) throws Exception {
        bookPageDao.deleteCustomerAddressBookById(context, addressId);
    }
}
