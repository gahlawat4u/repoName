ALTER TABLE `xms_tbl_product_carrier` 
DROP FOREIGN KEY `xms_tbl_product_carrier_ibfk_2`;

ALTER TABLE `xms_tbl_product` 
ADD CONSTRAINT `fk_product_carrierid`
  FOREIGN KEY (`carrierid`)
  REFERENCES `xms_tbl_product_carrier` (`product_carrierid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;