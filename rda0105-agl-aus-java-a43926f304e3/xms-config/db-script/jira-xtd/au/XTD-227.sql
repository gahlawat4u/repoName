/**
date: 13/04/2017
author: thanhbc
purpose:  restore losing credit note data
**/

-- kiểm tra dữ liệu trước
SELECT * FROM xms_tbl_shipment WHERE airbill_number = '00003752';
SELECT * FROM xms_tbl_shipment_invoice WHERE airbill_number = '00003752';
SELECT * FROM xms_tbl_invoice WHERE invoice_code = '13900001RB27';
SELECT * FROM `xms_tbl_adjustment_history` WHERE adjustmentid = '18974';
SELECT * FROM `xms_tbl_airbill_adjustment` WHERE airbill_number = '00003752';
SELECT * FROM `xms_tbl_credit_note` WHERE credit_note_id = '8642';
SELECT * FROM `xms_tbl_credit_note_detail` WHERE adjustmentid = '18974';

-- add dữ liệu bị thiếu
INSERT INTO `xms_tbl_credit_note` (`credit_code`, `create_date`, `invoice_code`, `status`) VALUES ('C13900001RB27-RB28', '2017-02-28 09:50:31', '13900001RB27', '1');

SELECT @credit_note_id := credit_note_id FROM xms_tbl_credit_note WHERE credit_code = 'C13900001RB27-RB28';

UPDATE xms_tbl_credit_note_detail SET credit_note_id = @credit_note_id WHERE adjustmentid = '18974';

-- khi đã có dữ liệu bị thiếu,yêu cầu GMS mở freeze của credit note C13900001RB27-RB28 sau kho freeze lại,dữ liệu sẽ được update chuẩn cho payment,kiểm tra:
SELECT @paymentid := invoice_paymentid FROM xms_tbl_invoice_payment WHERE invoiceid = '243816';
SELECT * FROM xms_tbl_invoice_payment_detail WHERE airbill_number = '00003752';
SELECT * FROM `xms_tbl_customer_payment` WHERE cus_paymentid = '139935';

-- update lại ngày thanh toán
UPDATE `xms_tbl_invoice_payment` SET `apply_date`='2017-02-28' WHERE (`invoice_paymentid`= @paymentid);
UPDATE `xms_tbl_customer_payment` SET `payment_date`='2017-02-28 06:55:24' WHERE (`cus_paymentid`='139935');

-- Sau đó mở freeze các date range sau để update lại dữ liệu trong payable
UPDATE `xms_tbl_period` SET `franchise_payable_status`='0',`rpt_txn_id`='' WHERE (`start_date`='2017-02-27') AND (`end_date`='2017-03-05') LIMIT 1;
UPDATE `xms_tbl_period` SET `franchise_payable_status`='0',`rpt_txn_id`='' WHERE (`start_date`='2017-04-24') AND (`end_date`='2017-04-30') LIMIT 1;