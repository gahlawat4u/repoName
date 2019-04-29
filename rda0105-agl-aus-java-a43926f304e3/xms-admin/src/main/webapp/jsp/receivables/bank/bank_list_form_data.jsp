<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="bank-form">
    <div id="md-4" title="New Bank ">
        <div class="form-group">
            <p class="">
                <xms:localization text="Bank Name"/>
                :<span class="s30">*</span>
            </p>
            <s:textfield name="bank.bankName" class="form-control alloptions"/>
            <span class="text-danger"><s:fielderror fieldName="bank.bankName"/></span>

            <p>
                <xms:localization text="<i>(Setting Admin Level to 1 allows only Admin Level 1 to use this bank)</i>"/>
            </p>
        </div>
        <div class="form-group">
            <p class="">
                <xms:localization text="Admin Level: "/>
            </p>
            <s:select name="bank.userLevelId" list="userLevels" listValue="userLevelCode + ' - ' + userLevelName"
                      listKey="userLevelId" cssClass="form-control"/>
        </div>
    </div>
    <s:hidden name="page"/>
    <s:hidden name="pageSize"/>
</s:form>
<script type="text/javascript">
    $('#bank-form').on('keyup keypress', function (e) {
        var code = e.keyCode || e.which;
        if (code == 13) {
            e.preventDefault();

        }
    });


</script>