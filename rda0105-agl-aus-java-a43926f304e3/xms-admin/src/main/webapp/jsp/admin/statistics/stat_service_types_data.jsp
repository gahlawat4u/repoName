<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_service_types">
    <div>
        <xms:localization text="Period: "/>
        <s:select name="period" list="serviceTypePeriods" listValue="value" listKey="key"
                  onchange="onServiceTypesPeriodChange($(this))"/>
    </div>
    <s:if test="serviceTypes==null || serviceTypes.size()==0">
        <xms:localization text="No data to display."/>
    </s:if>
    <s:else>
        <script type="text/javascript">
            $(document).ready(function () {
                var arrayData = [['Service Type', 'Shipment Count']];
                var element;
                <s:iterator value="serviceTypes">
                element = ['<s:property value="serviceTypeName" />', <s:property value="shipmentCount" />];
                arrayData.push(element);
                </s:iterator>
                var chartId = 'service_types_chart_' + '<s:property value="uniqueId" />';
                google.charts.setOnLoadCallback(function () {
                    var data = google.visualization.arrayToDataTable(arrayData);
                    var options = {
                        title: 'Service Types',
                        is3D: true
                    };
                    var chart = new google.visualization.PieChart(document.getElementById(chartId));
                    chart.draw(data, options);
                });
            });
        </script>
        <div id="service_types_chart_<s:property value="uniqueId" />"
             style='width: <s:property value="width"/>px; height: <s:property value="%{height-50}"/>px'></div>
        <!--
        <table class="table table-hover table-bordered mg0">
        <tr>
        <th><xms:localization text="Service Type Name"/></th>
        <th class="text-right"><xms:localization text="Shipment Count"/></th>
        </tr>
        <s:iterator value="serviceTypes">
            <tr>
            <td><s:property value="serviceTypeName"/></td>
            <td class="text-right"><s:property value="shipmentCount"/></td>
            </tr>
        </s:iterator>
        </table>
        -->
    </s:else>
</div>
<script type="text/javascript">
    function onServiceTypesPeriodChange(obj) {
        var period = $(obj).val();
        var replaceDiv = $(obj).closest("div#stat_service_types");
        $.post("statistics_service_types.ix?reqType=json", "period=" + period, function (res) {
            if (res.errorCode == "SUCCESS") {
                replaceDiv.replaceWith(res.content);
            } else {
                replaceDiv.html(res.errorMsg);
            }
        }).fail(function () {
            replaceDiv.html('<xms:localization text="System internal error, please contact administrator." />');
        });
    }


</script>