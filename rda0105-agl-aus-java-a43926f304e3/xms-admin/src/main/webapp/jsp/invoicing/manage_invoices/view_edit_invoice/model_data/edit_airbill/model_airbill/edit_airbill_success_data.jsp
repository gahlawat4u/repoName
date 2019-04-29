<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<xms:localization text="Edit Airbill Success"/>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // Load invoice if invoice code is not null.
        var invoiceId = $("#sel_invoice_data").val();
        var action = "view_edit_invoice_get_invoice_detail.ix?reqType=json";
        if (invoiceId > 0) {
            var data = {
                'invoiceId': invoiceId
            };
            doPostDataByParameters(action, data, "", "div_invocie_detail", true, false);
            $("#selected-inv").val(invoiceId);
        }
    });
</script>