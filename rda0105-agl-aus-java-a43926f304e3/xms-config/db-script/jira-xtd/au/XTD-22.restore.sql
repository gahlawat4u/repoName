/**
date: 17/07/2017
author: thanh
purpose: restore deleted duplicate adjustment
**/

INSERT INTO `xms_tbl_airbill_adjustment` (`adjustmentid`, `adjustment_type`, `shipmentid`, `airbill_number`, `create_date`, `carrier_amount`, `gst_carrier_amount`, `customer_amount`, `gst_customer_amount`, `carrier_credit`, `gst_carrier_credit`, `request_carrier`, `note`, `credit_type`, `apply_gst_type`, `status`, `actual_delivery_date`, `franchise_amount`, `gst_franchise_amount`, `reason_for_deleting`, `franchise_comments_to_fsc`, `fsc_credit_note`, `sub_status`, `start_pausing_date`) VALUES ('19050', 'Full Refund', '733839', '4INX0332', '2017-03-08 15:30:40', '1868.14', '97.49', '1869.80', '186.98', '0.00', '0.00', '0', 'Credit and re-issue back to the customer ', '0', '0', '6', NULL, '1868.14', '190.69', 'duplication', NULL, NULL, '0', '2017-03-08');