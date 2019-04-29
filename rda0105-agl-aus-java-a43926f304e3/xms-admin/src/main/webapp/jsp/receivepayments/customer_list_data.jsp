<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="%{fieldErrors.isEmpty()}">
    <s:select class="form-control" cssStyle="height: 25px;" list="customerList" listValue="displayName"
              listKey="customerCode" id="listOfCustomers" value="receivePayment.customerOrInvoiceCode"/>
    <script>
        $(document).ready(function () {
            $("#customerOrInvoiceCode").val($('#listOfCustomers').val());
            $("#listOfCustomers").on("change", function () {
                $("#customerOrInvoiceCode").val(this.value);
            });
        });
    </script>
</s:if>
<s:else>
    <s:fielderror/>
</s:else>