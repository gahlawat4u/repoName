<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden name="rptTxnId"/>
<div class="col-lg-12" id="weekly-report-result">
    <s:hidden name="weeklyField"/>
    <s:hidden name="weeklyType"/>
    <table class="table table-bordered mg0" id="weekly_report_table">
        <thead>
        <tr>
            <th><xms:localization text="Period"/></th>
            <th><xms:localization text="Setups"/></th>
            <th><xms:localization text="Activations"/></th>
            <s:iterator value="columns" var="col">
                <s:if test="%{#col.group==0}">
                    <th service-group='<s:property value="%{#col.serviceId}" />'><s:property
                            value="%{#col.columnName}"/></th>
                </s:if>
                <s:elseif test="%{#col.group==1}">
                    <th service-group='<s:property value="%{#col.serviceId}" />' group="inc-gst"><s:property
                            value="%{#col.columnName}"/></th>
                </s:elseif>
                <s:elseif test="%{#col.group==2}">
                    <th service-group='<s:property value="%{#col.serviceId}" />' group="exc-gst"><s:property
                            value="%{#col.columnName}"/></th>
                </s:elseif>
            </s:iterator>
            <th><xms:localization text="Total Ship"/></th>
            <th group="inc-gst"><xms:localization text="Total Rev"/></th>
            <th group="exc-gst"><xms:localization text="Total Rev"/></th>
            <th group="inc-gst"><xms:localization text="Total Mrg"/></th>
            <th group="exc-gst"><xms:localization text="Total Mrg"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="weeklyReport==null || weeklyReport.size()==0">
            <tr>
                <td colspan='<s:property value="%{columns.size()}" />'><xms:localization
                        text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="weeklyReport" var="weekly">
                <tr>
                    <td><s:property value='%{#weekly["start_date"]}'/> - <s:property
                            value='%{#weekly["end_date"]}'/></td>
                    <td class="text-right"><s:property value='%{#weekly["setups"]}'/></td>
                    <td class="text-right"><s:property value='%{#weekly["activations"]}'/></td>
                    <s:iterator value="columns" var="col">
                        <s:if test="%{#col.group==0}">
                            <td class="text-right" service-group='<s:property value="%{#col.serviceId}" />'><s:property
                                    value="%{#weekly[#col.fieldName]}"/></td>
                        </s:if>
                        <s:elseif test="%{#col.group==1}">
                            <td class="text-right" service-group='<s:property value="%{#col.serviceId}" />'
                                group="inc-gst"><s:property value="%{#weekly[#col.fieldName]}"/></td>
                        </s:elseif>
                        <s:elseif test="%{#col.group==2}">
                            <td class="text-right" service-group='<s:property value="%{#col.serviceId}" />'
                                group="exc-gst"><s:property value="%{#weekly[#col.fieldName]}"/></td>
                        </s:elseif>
                    </s:iterator>
                    <td class="text-right"><s:property value='%{#weekly["total_shipment_count"]}'/></td>
                    <td class="text-right" group="inc-gst"><s:property value='%{#weekly["total_rev_inc_gst"]}'/></td>
                    <td class="text-right" group="exc-gst"><s:property value='%{#weekly["total_rev_exc_gst"]}'/></td>
                    <td class="text-right" group="inc-gst"><s:property value='%{#weekly["total_margin_inc_gst"]}'/></td>
                    <td class="text-right" group="exc-gst"><s:property value='%{#weekly["total_margin_exc_gst"]}'/></td>
                </tr>
            </s:iterator>
            <s:if test="weeklyTotal!=null">
                <tr>
                    <th><xms:localization text="Total"/></th>
                    <th class="text-right"><s:property value='%{weeklyTotal["setups"]}'/></th>
                    <th class="text-right"><s:property value='%{weeklyTotal["activations"]}'/></th>
                    <s:iterator value="columns" var="col">
                        <s:if test="%{#col.group==0}">
                            <th class="text-right" service-group='<s:property value="%{#col.serviceId}" />'><s:property
                                    value="%{weeklyTotal[#col.fieldName]}"/></th>
                        </s:if>
                        <s:elseif test="%{#col.group==1}">
                            <th class="text-right" service-group='<s:property value="%{#col.serviceId}" />'
                                group="inc-gst"><s:property value="%{weeklyTotal[#col.fieldName]}"/></th>
                        </s:elseif>
                        <s:elseif test="%{#col.group==2}">
                            <th class="text-right" service-group='<s:property value="%{#col.serviceId}" />'
                                group="exc-gst"><s:property value="%{weeklyTotal[#col.fieldName]}"/></th>
                        </s:elseif>
                    </s:iterator>
                    <th class="text-right"><s:property value='%{weeklyTotal["total_shipment_count"]}'/></th>
                    <th class="text-right" group="inc-gst"><s:property
                            value='%{weeklyTotal["total_rev_inc_gst"]}'/></th>
                    <th class="text-right" group="exc-gst"><s:property
                            value='%{weeklyTotal["total_rev_exc_gst"]}'/></th>
                    <th class="text-right" group="inc-gst"><s:property
                            value='%{weeklyTotal["total_margin_inc_gst"]}'/></th>
                    <th class="text-right" group="exc-gst"><s:property
                            value='%{weeklyTotal["total_margin_exc_gst"]}'/></th>
                </tr>
            </s:if>
        </s:else>
        </tbody>
    </table>
</div>
<div style="clear: both; width: 1px; height: 30px;"></div>
<div class="col-lg-12" id="monthly-report-result">
    <s:hidden name="monthlyField"/>
    <s:hidden name="monthlyType"/>
    <table class="table table-bordered mg0" id="monthly_report_table">
        <thead>
        <tr>
            <th><xms:localization text="Month"/></th>
            <th><xms:localization text="Setups"/></th>
            <th><xms:localization text="Activations"/></th>
            <s:iterator value="columns" var="col">
                <s:if test="%{#col.group==0}">
                    <th service-group='<s:property value="%{#col.serviceId}" />'><s:property
                            value="%{#col.columnName}"/></th>
                </s:if>
                <s:elseif test="%{#col.group==1}">
                    <th service-group='<s:property value="%{#col.serviceId}" />' group="inc-gst"><s:property
                            value="%{#col.columnName}"/></th>
                </s:elseif>
                <s:elseif test="%{#col.group==2}">
                    <th service-group='<s:property value="%{#col.serviceId}" />' group="exc-gst"><s:property
                            value="%{#col.columnName}"/></th>
                </s:elseif>
            </s:iterator>
            <th><xms:localization text="Total Ship"/></th>
            <th group="inc-gst"><xms:localization text="Total Rev"/></th>
            <th group="exc-gst"><xms:localization text="Total Rev"/></th>
            <th group="inc-gst"><xms:localization text="Total Mrg"/></th>
            <th group="exc-gst"><xms:localization text="Total Mrg"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="monthlyReport==null || monthlyReport.size()==0">
            <tr>
                <td colspan='<s:property value="%{columns.size()}" />'><xms:localization
                        text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="monthlyReport" var="monthly">
                <tr>
                    <td><s:property value='%{#monthly["start_date"]}'/> - <s:property
                            value='%{#monthly["end_date"]}'/></td>
                    <td class="text-right"><s:property value='%{#monthly["setups"]}'/></td>
                    <td class="text-right"><s:property value='%{#monthly["activations"]}'/></td>
                    <s:iterator value="columns" var="col">
                        <s:if test="%{#col.group==0}">
                            <td class="text-right" service-group='<s:property value="%{#col.serviceId}" />'><s:property
                                    value="%{#monthly[#col.fieldName]}"/></td>
                        </s:if>
                        <s:elseif test="%{#col.group==1}">
                            <td class="text-right" service-group='<s:property value="%{#col.serviceId}" />'
                                group="inc-gst"><s:property value="%{#monthly[#col.fieldName]}"/></td>
                        </s:elseif>
                        <s:elseif test="%{#col.group==2}">
                            <td class="text-right" service-group='<s:property value="%{#col.serviceId}" />'
                                group="exc-gst"><s:property value="%{#monthly[#col.fieldName]}"/></td>
                        </s:elseif>
                    </s:iterator>
                    <td class="text-right"><s:property value='%{#monthly["total_shipment_count"]}'/></td>
                    <td class="text-right" group="inc-gst"><s:property value='%{#monthly["total_rev_inc_gst"]}'/></td>
                    <td class="text-right" group="exc-gst"><s:property value='%{#monthly["total_rev_exc_gst"]}'/></td>
                    <td class="text-right" group="inc-gst"><s:property
                            value='%{#monthly["total_margin_inc_gst"]}'/></td>
                    <td class="text-right" group="exc-gst"><s:property
                            value='%{#monthly["total_margin_exc_gst"]}'/></td>
                </tr>
            </s:iterator>
            <s:if test="monthlyTotal!=null">
                <tr>
                    <th><xms:localization text="Total"/></th>
                    <th class="text-right"><s:property value='%{monthlyTotal["setups"]}'/></th>
                    <th class="text-right"><s:property value='%{monthlyTotal["activations"]}'/></th>
                    <s:iterator value="columns" var="col">
                        <s:if test="%{#col.group==0}">
                            <th class="text-right" service-group='<s:property value="%{#col.serviceId}" />'><s:property
                                    value="%{monthlyTotal[#col.fieldName]}"/></th>
                        </s:if>
                        <s:elseif test="%{#col.group==1}">
                            <th class="text-right" service-group='<s:property value="%{#col.serviceId}" />'
                                group="inc-gst"><s:property value="%{monthlyTotal[#col.fieldName]}"/></th>
                        </s:elseif>
                        <s:elseif test="%{#col.group==2}">
                            <th class="text-right" service-group='<s:property value="%{#col.serviceId}" />'
                                group="exc-gst"><s:property value="%{monthlyTotal[#col.fieldName]}"/></th>
                        </s:elseif>
                    </s:iterator>
                    <th class="text-right"><s:property value='%{monthlyTotal["total_shipment_count"]}'/></th>
                    <th class="text-right" group="inc-gst"><s:property
                            value='%{monthlyTotal["total_rev_inc_gst"]}'/></th>
                    <th class="text-right" group="exc-gst"><s:property
                            value='%{monthlyTotal["total_rev_exc_gst"]}'/></th>
                    <th class="text-right" group="inc-gst"><s:property
                            value='%{monthlyTotal["total_margin_inc_gst"]}'/></th>
                    <th class="text-right" group="exc-gst"><s:property
                            value='%{monthlyTotal["total_margin_exc_gst"]}'/></th>
                </tr>
            </s:if>
        </s:else>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var fieldList = ["sort_start_date", "setups", "activations"];
    <s:iterator value="columns" var="col">
    fieldList.push('<s:property value="#col.fieldName" />');
    </s:iterator>
    fieldList.push("total_shipment_count");
    fieldList.push("total_rev_inc_gst");
    fieldList.push("total_rev_exc_gst");
    fieldList.push("total_margin_inc_gst");
    fieldList.push("total_margin_exc_gst");
    $(document).ready(function () {
        $("#weekly_report_table").tablesorter({
            sortFieldId: "weeklyField",
            sortTypeId: "weeklyType",
            fieldList: fieldList,
            callback: getWeekly
        });
        $("#monthly_report_table").tablesorter({
            sortFieldId: "monthlyField",
            sortTypeId: "monthlyType",
            fieldList: fieldList,
            callback: getMonthly
        });
        showHideColumns();
    });


</script>