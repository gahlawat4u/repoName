<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_sales_graph">
    <s:if test="salesGraphs==null || salesGraphs.size()==0">
        <xms:localization text="No data to display."/>
    </s:if>
    <s:else>
        <s:if test="salesGraphTotal!=null">
            <div style="font-weight: bold; text-align: right">
                <xms:localization text="Total Sales Forecast "/>
                <s:property value="currencySymbol"/>
                <s:property value="salesGraphTotal"/>
            </div>
        </s:if>
        <script type="text/javascript">
            $(document).ready(function () {
                var arrayData = [['Status', 'Shipment Count']];
                var element;
                <s:iterator value="salesGraphs">
                element = ['<s:property value="statusName" />', <s:property value="count" />];
                arrayData.push(element);
                </s:iterator>
                var chartId = 'sales_graph_chart_' + '<s:property value="uniqueId" />';
                google.charts.setOnLoadCallback(function () {
                    var data = google.visualization.arrayToDataTable(arrayData);
                    var options = {
                        title: '<xms:localization text="Sales Forecast" />',
                        animation: {
                            duration: 500,
                            startup: true
                        }
                    };
                    var chart = new google.visualization.ColumnChart(document.getElementById(chartId));
                    chart.draw(data, options);
                });
            });
        </script>
        <div id="sales_graph_chart_<s:property value="uniqueId" />"
             style='width: <s:property value="width"/>px; height: <s:property value="%{height-50}"/>px'></div>
        <!--
        <table class="table table-hover table-bordered mg0">
        <tr>
        <th><xms:localization text="Status Name"/></th>
        <th class="text-right"><xms:localization text="Count"/></th>
        </tr>
        <s:iterator value="salesGraphs">
            <tr>
            <td><s:property value="statusName"/></td>
            <td class="text-right"><s:property value="count"/></td>
            </tr>
        </s:iterator>
        </table>
        -->
    </s:else>
</div>