<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_cost_vs_margins">
    <s:if test="costVsMargins==null || costVsMargins.size()==0">
        <xms:localization text="No data to display."/>
    </s:if>
    <s:else>
        <script type="text/javascript">
            $(document).ready(function () {
                var arrayData = [['<xms:localization text="Period" />', '<xms:localization text="Margin" />', '<xms:localization text="Cost" />', '<xms:localization text="Revenue" />']];
                var element;
                <s:iterator value="costVsMargins">
                element = ['<s:property value="columnName" />', <s:property value="margin" />, <s:property value="cost" />, <s:property value="revenue" />];
                arrayData.push(element);
                </s:iterator>
                var chartId = 'cost_vs_margin_chart_' + '<s:property value="uniqueId" />';
                google.charts.setOnLoadCallback(function () {
                    var data = google.visualization.arrayToDataTable(arrayData);
                    var options = {
                        title: '<xms:localization text="Cost Vs Margin" />',
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
        <div id="cost_vs_margin_chart_<s:property value="uniqueId" />"
             style='width: <s:property value="width"/>px; height: <s:property value="%{height-50}"/>px'></div>
        <!--
        <table class="table table-hover table-bordered mg0">
        <tr>
        <th><xms:localization text="Column Name"/></th>
        <th class="text-right"><xms:localization text="Cost"/></th>
        <th class="text-right"><xms:localization text="Margin"/></th>
        <th class="text-right"><xms:localization text="Revenue"/></th>
        </tr>
        <s:iterator value="costVsMargins">
            <tr>
            <td><s:property value="columnName"/></td>
            <td class="text-right"><s:property value="cost"/></td>
            <td class="text-right"><s:property value="margin"/></td>
            <td class="text-right"><s:property value="revenue"/></td>
            </tr>
        </s:iterator>
        </table>
        -->
    </s:else>
</div>