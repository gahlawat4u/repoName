<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<s:select list="overpaymentList" listKey="cusPaymentId" listValue="overAmount" id="overpayment-select"
          onchange="changeOverpaymentList()"/>
<script type="text/javascript">
    $(document).ready(function () {
        $("#lblAmountToApply").html($("#overpayment-select option:first").text());
        $("#overpayment_amountToApply").val($("#overpayment-select option:first").text());
    });


</script>