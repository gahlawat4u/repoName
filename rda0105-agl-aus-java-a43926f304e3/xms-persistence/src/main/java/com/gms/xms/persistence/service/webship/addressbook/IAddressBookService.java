package com.gms.xms.persistence.service.webship.addressbook;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookFilter;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IAddressBookService
 * <p>
 * Author HungNT Date Jul 8, 2015
 */
public interface IAddressBookService {
    public List<AddressBookVo> getCustomerAddressBook(AddressBookFilter filter) throws Exception;

    public Long getAddressBookCount(AddressBookFilter addressBookPageFilter) throws DaoException;

    public List<AddressBookVo> getCustomerAddressBookByFilter(AddressBookFilter filter) throws DaoException;

    public Long getCustomerAddressBookCountByFilter(AddressBookFilter filter) throws DaoException;

    public CustomerAddressBookVo getCustomerAddressBookById(long addressId) throws DaoException;

    public void renderXLSFile(AddressBookFilter addressBookFilter, String outPutFilePath) throws Exception;

    public void updateCustomerAddressBook(Map<String, String> context, CustomerAddressBookVo addressBookVo) throws DaoException;

    public void insertCustomerAddressBook(Map<String, String> context, CustomerAddressBookVo addressBookVo) throws Exception;

    public void deleteCustomerAddressBookByIdTask(Map<String, String> context, long addressId) throws Exception;
}
