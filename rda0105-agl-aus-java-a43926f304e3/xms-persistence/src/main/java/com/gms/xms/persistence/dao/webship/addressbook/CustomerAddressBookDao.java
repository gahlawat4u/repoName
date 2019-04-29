package com.gms.xms.persistence.dao.webship.addressbook;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookFilter;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookFilter;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerAddressBookDaoService
 * <p>
 * Author HungNT Date Mar 25, 2015
 */
public class CustomerAddressBookDao extends BaseDao<CustomerAddressBookVo> {

    public CustomerAddressBookDao() {
        super();
    }

    public CustomerAddressBookDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<AddressBookVo> selectAddressBookList(AddressBookFilter addressBookPageFilter) throws DaoException {
        return selectList(addressBookPageFilter, "CustomerAddressBook.selectAddressBookList");
    }

    public Long selectCountCustomerAddressBook(AddressBookFilter addressBookPageFilter) throws DaoException {
        return (Long) selectObject(addressBookPageFilter, "CustomerAddressBook.selectAddressBookRecordCount");
    }

    /**
     * Get the list of the Customer Address Book by filter
     *
     * @param {@link CustomerAddressBookFilter} filter
     * @return List of {@link CustomerAddressBookVo}
     * @throws DaoException
     */
    public Long getCustomerAddressBookRecordCount(AddressBookFilter filter) throws DaoException {
        return (Long) selectObject(filter, "CustomerAddressBook.getCustomerAddressBookRecordCount");
    }

    /**
     * Get Customer address book by Address Id
     *
     * @param {@link CustomerAddressBookVo} addressId
     * @return {@link CustomerAddressBookVo}
     * @throws DaoException
     */
    public CustomerAddressBookVo getCustomerAddressBookById(long addressId) throws DaoException {
        return select(addressId, "CustomerAddressBook.getCustomerAddressBookById");
    }

    /**
     * Get customer address book list by filter
     *
     * @param {@link CustomerAddressBookFilter} filter
     * @return List of {@link CustomerAddressBookVo}
     * @throws DaoException
     */
    public List<AddressBookVo> selectCustomerAddressBookByFilter(AddressBookFilter filter) throws DaoException {
        return selectList(filter, "CustomerAddressBook.selectCustomerAddressBookByFilter");
    }

    /**
     * Get total records count by {@link CustomerAddressBookFilter}
     *
     * @param {@link CustomerAddressBookFilter} filter
     * @return {@link Long}
     * @throws DaoException
     */
    public Long selectCustomerAddressBookCountByFilter(AddressBookFilter filter) throws DaoException {
        return (Long) selectObject(filter, "CustomerAddressBook.selectCustomerAddressBookCountByFilter");
    }

    /**
     * Insert new Customer address book into DB
     *
     * @param {@link CustomerAddressBookVo} addressBookVo
     * @throws DaoException
     */
    public Long checkDuplicateCustomerAddress(CustomerAddressBookVo addressBookVo) throws DaoException {
        return (Long) selectObject(addressBookVo, "CustomerAddressBook.checkDuplicateCustomerAddress");
    }

    public void insertCustomerAddressBook(Map<String, String> context, CustomerAddressBookVo addressBookVo) throws DaoException {
        insert(context, addressBookVo, "CustomerAddressBook.insertCustomerAddressBook");
    }

    /**
     * @param addressBookVo
     * @throws DaoException
     */
    public void updateCustomerAddressBookById(Map<String, String> context, CustomerAddressBookVo addressBookVo) throws DaoException {
        update(context, addressBookVo, "CustomerAddressBook.updateCustomerAddressBookById");
    }

    /**
     * Delete Customer Address Book by Address Id
     *
     * @param {@link Long} addressId
     * @throws DaoException
     */
    public void deleteCustomerAddressBookById(Map<String, String> context, long addressId) throws DaoException {
        delete(context, addressId, "CustomerAddressBook.deleteCustomerAddressBookById");
    }

    /**
     * Gets list of customer address book by company or contact name
     *
     * @param filter
     * @return List<{@link CustomerAddressBookVo}>
     * @throws DaoException
     */
    public List<CustomerAddressBookVo> getCustomerAddressBookByCompanyOrContact(AddressBookFilter filter) throws DaoException {
        return selectList(filter, "CustomerAddressBook.getCustomerAddressBookByCompanyOrContact");
    }

}
