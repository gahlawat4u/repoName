package com.gms.xms.workflow.service.webship.addressbook;

import com.gms.xms.model.webship.addressbook.AddressRowDataModel;

import java.util.Map;

/**
 * Posted from Aug 27, 2016 12:42:36 PM
 * <p>
 * Author huynd
 */
public interface IWebshipAddressBookImportService {

    public Integer importAddressBook(Map<String, String> context, AddressRowDataModel rowData, Long customerCode, Long webshipId) throws Exception;
}
