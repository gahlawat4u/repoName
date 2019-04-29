package com.gms.xms.txndb.vo.webship;

/**
 * Posted from AddressBookPageFilter
 * <p>
 * Author HungNT Date Jul 7, 2015
 */
public class CustomerAddressBookFilter extends CustomerAddressBookVo {
    private static final long serialVersionUID = 1297545031088383219L;

    private int startRecord;
    private int recordSize;

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }
}
