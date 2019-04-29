/**
date: 04/07/2017
author: huynhlbq
purpose: select all rate and rate detail value on database to create update sql
this ticket not affect DHL so comment out
target: xms_au
**/

-- select DISTINCT rate from xms_tbl_customer_base_rate WHERE rate_type = 1 AND baserate_description IN
-- ('DHL Worldwide Express - Package','DHL Worldwide Express - Documents','DHL Worldwide Express - Documents (Inbound)','DHL Worldwide Express - Package (Inbound)')
-- INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/dhl_markup.txt';
-- --
-- select DISTINCT rate from xms_tbl_customer_base_rate WHERE rate_type = 2 AND baserate_description IN
-- ('DHL Worldwide Express - Package','DHL Worldwide Express - Documents','DHL Worldwide Express - Documents (Inbound)','DHL Worldwide Express - Package (Inbound)')
-- INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/dhl_margin.txt';

SELECT DISTINCT rate
FROM xms_tbl_customer_base_rate
WHERE rate_type = 1 AND baserate_description IN
                        ('Express - Documents', 'Express - Package', 'Express - Documents (Inbound)', 'Express - Package (Inbound)')
INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/tntexpress_markup.txt';

SELECT DISTINCT rate
FROM xms_tbl_customer_base_rate
WHERE rate_type = 2 AND baserate_description IN
                        ('Express - Documents', 'Express - Package', 'Express - Documents (Inbound)', 'Express - Package (Inbound)')
INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/tntexpress_margin.txt';

SELECT DISTINCT rate
FROM xms_tbl_customer_base_rate
WHERE rate_type = 1 AND baserate_description IN
                        ('Economy Express - Package', 'Economy Express - Package (Inbound)', 'Economy - Package', 'Economy - Package (Inbound)')
INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/tnteco_markup.txt';

SELECT DISTINCT rate
FROM xms_tbl_customer_base_rate
WHERE rate_type = 2 AND baserate_description IN
                        ('Economy Express - Package', 'Economy Express - Package (Inbound)', 'Economy - Package', 'Economy - Package (Inbound)')
INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/tnteco_margin.txt';

-- DETAIL
-- select DISTINCT brd.rate from xms_tbl_customer_base_rate_detail brd LEFT JOIN xms_tbl_customer_base_rate br ON brd.customer_base_rate_id = br.customer_base_rate_id WHERE br.rate_type = 1 AND
-- baserate_description IN ('DHL Worldwide Express - Package','DHL Worldwide Express - Documents','DHL Worldwide Express - Documents (Inbound)','DHL Worldwide Express - Package (Inbound)')
-- INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/detail_dhl_markup.txt';
--
-- select DISTINCT brd.rate from xms_tbl_customer_base_rate_detail brd LEFT JOIN xms_tbl_customer_base_rate br ON brd.customer_base_rate_id = br.customer_base_rate_id WHERE br.rate_type = 2 AND
-- baserate_description IN ('DHL Worldwide Express - Package','DHL Worldwide Express - Documents','DHL Worldwide Express - Documents (Inbound)','DHL Worldwide Express - Package (Inbound)')
-- INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/detail_dhl_margin.txt';

SELECT DISTINCT brd.rate
FROM xms_tbl_customer_base_rate_detail brd LEFT JOIN xms_tbl_customer_base_rate br
    ON brd.customer_base_rate_id = br.customer_base_rate_id
WHERE br.rate_type = 1 AND
      baserate_description IN
      ('Express - Documents', 'Express - Package', 'Express - Documents (Inbound)', 'Express - Package (Inbound)')
INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/detail_tntexpress_markup.txt';

SELECT DISTINCT brd.rate
FROM xms_tbl_customer_base_rate_detail brd LEFT JOIN xms_tbl_customer_base_rate br
    ON brd.customer_base_rate_id = br.customer_base_rate_id
WHERE br.rate_type = 2 AND
      baserate_description IN
      ('Express - Documents', 'Express - Package', 'Express - Documents (Inbound)', 'Express - Package (Inbound)')
INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/detail_tntexpress_margin.txt';

SELECT DISTINCT brd.rate
FROM xms_tbl_customer_base_rate_detail brd LEFT JOIN xms_tbl_customer_base_rate br
    ON brd.customer_base_rate_id = br.customer_base_rate_id
WHERE br.rate_type = 1 AND
      baserate_description IN
      ('Economy Express - Package', 'Economy Express - Package (Inbound)', 'Economy - Package', 'Economy - Package (Inbound)')
INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/detail_tnteco_markup.txt';

SELECT DISTINCT brd.rate
FROM xms_tbl_customer_base_rate_detail brd LEFT JOIN xms_tbl_customer_base_rate br
    ON brd.customer_base_rate_id = br.customer_base_rate_id
WHERE br.rate_type = 2 AND
      baserate_description IN
      ('Economy Express - Package', 'Economy Express - Package (Inbound)', 'Economy - Package', 'Economy - Package (Inbound)')
INTO OUTFILE 'D:/Projects/GMS/bitbucket/XI/XI-118/oldmarkup/detail_tnteco_margin.txt';