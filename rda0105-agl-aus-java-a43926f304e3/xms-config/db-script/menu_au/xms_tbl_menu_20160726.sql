update xms_tbl_menu set url = 'XMS2:/surcharge_list.ix' where menuname = 'Surcharge List' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/import_rate_sheet.ix' where menuname = 'Import Rates & Costs' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/rate_sheets.ix' where menuname = 'Rate Sheets' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/manage_cover_sheets.ix' where menuname = 'Manage Cover Sheets' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/set_cost_basis.ix' where menuname = 'Set Cost Basis Tables' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/set_list_rates.ix' where menuname = 'Set List Rates' AND url != '#';

update xms_tbl_menu set url = 'XMS2:/admin_menu.ix' where menuname = 'Menu Editor' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/admin_email_setting.ix' where menuname = 'Admin Email Settings' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/admin_system_setting.ix' where menuname = 'System Settings' AND url != '#';

update xms_tbl_menu set url = 'XMS2:/carrier_list.ix' where menuname = 'Carrier List' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/country_list.ix' where menuname = 'Country List' AND url != '#';

update xms_tbl_menu set url = 'XMS2:/franchise_setting.ix' where menuname = 'Franchise Settings' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/admin_permission.ix' where menuname = 'Permissions' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/admin_supply.ix' where menuname = 'Supplies List' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/manager_customer_profiles.ix' where menuname = 'Manage Customer Profiles' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/email_template.ix' where menuname = 'Email Template' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/product_carrier_list.ix' where menuname = 'Product' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/industry_list.ix' where menuname = 'Industry' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/customer_group_list.ix' where menuname = 'Customer Group' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/area_list.ix' where menuname = 'Area' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/language_value.ix' where menuname = 'Localization' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/territory_list.ix' where menuname = 'Territory List' AND url != '#';

update xms_tbl_menu set url = 'XMS2:/customer_status.ix' where menuname = 'Customer Status Report' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/customer_summary.ix' where menuname = 'Customer Summary Report' AND url != '#';

update xms_tbl_menu set url = 'XMS2:/bank_list.ix' where menuname = 'Banks' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/reminder_letter.ix' where menuname = 'Reminder Letters' AND url != '#';

----------------- new menu 20160901 -------------------
update xms_tbl_menu set url = 'XMS2:/manage_customers.ix' where menuname = 'Manage Customers' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/manage_franchise.ix' where menuname = 'Manage Franchise Accounts' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/import_total_report.ix' where menuname = 'Import Totals Report' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/airbill_import_error_logs.ix' where menuname = 'Airbill Import Error Logs' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/repair_airbill_errors.ix' where menuname = 'Repair Airbill Errors' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/move_invoices_dates.ix' where menuname = 'Move Invoices Dates' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/cost_deviations.ix' where menuname = 'Cost Deviations' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/airbill_margins.ix' where menuname = 'Airbill Margins' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/search_airbill.ix' where menuname = 'Search Airbills' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/view_edit_invoice.ix' where menuname = 'View/Edit Invoice' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/statements.ix' where menuname = 'Statements' AND url != '#';	
update xms_tbl_menu set url = 'XMS2:/freeze_inv.ix' where menuname = 'Freeze Invoices' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/send_email_invoices.ix' where menuname = 'Send E-mail Invoices' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/unfreeze_inv.ix' where menuname = 'Unfreeze Customer Invoices' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/fpb_ms.ix' where menuname = 'Franchise Payables' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/fpb_freeze_inv_ms.ix' where menuname = 'Freeze Franchise Payables' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/fpb_send_inv_ms.ix' where menuname = 'Send Franchise Payables E-mails' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/manage_sales_reps_and_commissions.ix' where menuname = 'Manage Sales Reps & Commissions' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/territory_list.ix' where menuname = 'Territory' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/csv_invoices.ix' where menuname = 'CSV Invoices' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/system_log.ix' where menuname = 'System Logs' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/system_stats.ix' where menuname = 'Stats' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/reconcile_airbill.ix' where menuname = 'Reconcile Airbills' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/import_billing_file.ix' where menuname = 'Import Billing Files' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/manage_contact.ix' where menuname = 'Search Contacts' AND url != '#';
update xms_tbl_menu set url = 'XMS2:/fpb_inv_ms.ix' where menuname = 'Franchise Payables Invoice' AND url != '#';
