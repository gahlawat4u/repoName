ALTER TABLE xms_tbl_shipment_billing 
ADD INDEX ship_date USING BTREE (ship_date ASC);
ALTER TABLE xms_tbl_invoice 
ADD INDEX invoice_date USING BTREE (invoice_date ASC);
ALTER TABLE xms_tbl_address
ADD INDEX addressid USING BTREE (addressid ASC);
ALTER TABLE xms_tbl_address
ADD INDEX countryid USING BTREE (countryid ASC);
ALTER TABLE xms_tbl_country
ADD INDEX countrycode USING BTREE (countrycode ASC);
ALTER TABLE xms_tbl_address
ADD INDEX companyname USING BTREE (companyname ASC);
