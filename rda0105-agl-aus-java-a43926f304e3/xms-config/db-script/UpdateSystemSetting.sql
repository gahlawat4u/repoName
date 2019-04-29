ALTER TABLE xms_tbl_system_setting ADD value_type VARCHAR(200);
ALTER TABLE xms_tbl_system_setting ADD list_value_default  TEXT;

update xms_tbl_system_setting set value_type="string",list_value_default = '[{"key":"0","value":"DHL","textDisplay":"Value", "inputDisplay":"select"},{"key":"1","value":"% Markup","textDisplay":"Value", "inputDisplay":"select"},{"key":"2","value":"% Margin","textDisplay":"Value", "inputDisplay":"select"},{"key":"3","value":"% Topup","textDisplay":"Value", "inputDisplay":"select"}]'
where setting_id=77;
update xms_tbl_system_setting set value_type="string",list_value_default = '[{"key":"dhl_countryid","value":"dhl_countryname","textDisplay":"Country Name", "inputDisplay":"select", "dataSource":"xms_tbl_dhl_country"},{"key":"sheet_id","value":"sheet_name","textDisplay":"Inbound Carrier Document", "inputDisplay":"select","dataSource":"xms_tbl_rate_sheet", "filter":"carrier_cost=1 and is_per_weight=0 and content=0 and bound=1"},{"key":"sheet_id","value":"sheet_name","textDisplay":"Inbound Carrier Non-Document", "inputDisplay":"select","dataSource":"xms_tbl_rate_sheet", "filter":"carrier_cost=1 and is_per_weight=0 and content=1 and bound=1"},{"key":"sheet_id","value":"sheet_name","textDisplay":"Inbound Carrier Per Weight", "inputDisplay":"select","dataSource":"xms_tbl_rate_sheet", "filter":"carrier_cost=1 and is_per_weight=1 and content=1 and bound=1"}]'
where setting_name='Default Carrier Inbound Rate Sheet by Zone';
update xms_tbl_system_setting set value_type="string", list_value_default = '[{"key":"0","value":"NO","inputDisplay":"radio"},{"key":"1","value":"YES", "inputDisplay":"radio"}]' 
WHERE setting_name='Compulsory for Webship Phone No';
