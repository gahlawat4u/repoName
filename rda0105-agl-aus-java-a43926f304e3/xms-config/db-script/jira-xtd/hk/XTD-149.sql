/**
date: 24/03/2017
author: thanh
purpose: update lại các invoice đã thanh toán nhưng paid vẫn = 0 thành 1
**/

UPDATE xms_tbl_invoice SET paid = 1 WHERE invoiceid IN (SELECT invoiceid FROM (SELECT
	i.invoiceid,
sum(ifnull(b.customer_cost, 0.00) + ifnull(b.customer_tax_amount,0.00)) + ifnull(i.late_fee, 0.00) - ifnull(p.paid, 0.00) AS remain
FROM
	xms_tbl_invoice AS i
INNER JOIN xms_tbl_customer AS c ON c.customer_code = i.customer_code
INNER JOIN xms_tbl_shipment_invoice AS s ON s.invoiceid = i.invoiceid
INNER JOIN xms_tbl_shipment_billing AS b ON (
	b.shipmentid = s.shipmentid
	AND b.airbill_number = s.airbill_number
)
LEFT JOIN (
	SELECT
		invoiceid,
		sum(amount) AS paid
	FROM
		xms_tbl_invoice_payment
	GROUP BY
		invoiceid
) AS p ON p.invoiceid = i.invoiceid
WHERE i.paid = 0
GROUP BY i.invoiceid,p.invoiceid
HAVING remain = 0.00) AS invoice_id);
