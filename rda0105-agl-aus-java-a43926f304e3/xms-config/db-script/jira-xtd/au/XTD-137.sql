/**
date: 08/03/2017
author: huynhlbq
purpose: Fix redundant record of invoice
target: xms_au
**/

-- Remove redundant record
delete from xms_tbl_shipment_invoice where airbill_number = '234091730X' and invoiceid=208171;

update xms_tbl_shipment_billing set senderaddressid = 3048076 where shipmentid = 653414 and shipper_reference = '';
update xms_tbl_shipment_billing set receiveraddressid = 3048077 where shipmentid = 653414 and shipper_reference = '';

-- ====================================================
-- FIX for Shipment 4INX0332 on 11800256RA30
-- delete
delete from xms_tbl_shipment_invoice where airbill_number = '4INX0332' and invoiceid = 239372;

update xms_tbl_shipment_billing set senderaddressid = 3429117 where shipmentid = 733839 and shipper_reference = 'EXPRESS SERVICE';
update xms_tbl_shipment_billing set receiveraddressid = 3429118 where shipmentid = 733839 and shipper_reference = 'EXPRESS SERVICE';

update xms_tbl_shipment_billing set pal = 0 where shipmentid = 733839 and shipper_reference = 'EXPRESS SERVICE';
update xms_tbl_shipment_billing set `zone` = '' where shipmentid = 733839 and shipper_reference = 'EXPRESS SERVICE';