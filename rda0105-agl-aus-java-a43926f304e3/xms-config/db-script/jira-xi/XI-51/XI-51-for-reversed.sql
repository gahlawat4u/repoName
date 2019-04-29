INSERT INTO `xms_tbl_accessorial` (`accessorialid`, `code`, `description`, `modified_date`, `typeid`, `isquotable`, `carrier`) VALUES ('662', 'Fuel Surcharge', 'Fuel Surcharge', '2017-05-08 12:42:39', '2', '0', '72');

INSERT INTO `xms_tbl_accessorial_detail` (`accessorialid`, `default_charge`, `apply_start_date`, `default_charge_carrier`) VALUES ('662', '12.10', '2017-05-02', '12.10');

INSERT INTO `xms_tbl_customer_accessorial` (`customer_code`, `accessorialid`, `amount`) VALUES ('13101413', '662', '25.00');
INSERT INTO `xms_tbl_customer_accessorial` (`customer_code`, `accessorialid`, `amount`) VALUES ('11601452', '662', '15.00');



