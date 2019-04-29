/**
date: 08/03/2017
author: huynhlbq
purpose: Restore for XTD-137
target: xms_au
**/

INSERT INTO xms_tbl_shipment_invoice (`invoiceid`, `shipmentid`, `airbill_number`, `cus_paymentid`, `paid_customer_cost`, `paid_carrier_cost`, `invoice_paymentid`) VALUES ('208171', '653414', '234091730X', NULL, '0.00', '0.00', NULL);

update xms_tbl_shipment_billing set senderaddressid = 3046297 where shipmentid = 653414 and shipper_reference = '';
update xms_tbl_shipment_billing set receiveraddressid = 3046298 where shipmentid = 653414 and shipper_reference = '';

-- ====================================================

INSERT INTO xms_tbl_shipment_invoice (`invoiceid`, `shipmentid`, `airbill_number`, `cus_paymentid`, `paid_customer_cost`, `paid_carrier_cost`, `invoice_paymentid`) VALUES ('239372', '733839', '4INX0332', NULL, '0.00', '0.00', NULL);

update xms_tbl_shipment_billing set senderaddressid = 3429169 where shipmentid = 733839 and shipper_reference = 'EXPRESS SERVICE';
update xms_tbl_shipment_billing set receiveraddressid = 3429170 where shipmentid = 733839 and shipper_reference = 'EXPRESS SERVICE';

update xms_tbl_shipment_billing set pal = 10 where shipmentid = 733839 and shipper_reference = 'EXPRESS SERVICE';
update xms_tbl_shipment_billing set `zone` = '1' where shipmentid = 733839 and shipper_reference = 'EXPRESS SERVICE';