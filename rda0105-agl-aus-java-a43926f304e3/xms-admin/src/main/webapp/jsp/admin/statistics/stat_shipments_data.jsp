<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_shipments">
    <s:if test="shipments==null || shipments.size()==0">
        <xms:localization text="No data to display."/>
    </s:if>
    <s:else>
        <script type="text/javascript">
            $(document).ready(function () {
                var arrayData = [['Time', 'Shipment Count']];
                var element;
                <s:iterator value="shipments">
                element = ['<s:property value="columnName" />', <s:property value="shipmentCount" />];
                arrayData.push(element);
                </s:iterator>
                var chartId = 'shipments_chart_' + '<s:property value="uniqueId" />';
                var periodType = parseInt(<s:property value="period" />);
                var chartTitle;
                switch (periodType) {
                    case 1:
                        chartTitle = '<xms:localization text="Shipments Today" />';
                        break;
                    case 2:
                        chartTitle = '<xms:localization text="Shipments Week" />';
                        break;
                    case 3:
                        chartTitle = '<xms:localization text="Shipments Month" />';
                        break;
                }
                google.charts.setOnLoadCallback(function () {
                    var type = periodType;
                    var data = google.visualization.arrayToDataTable(arrayData);
                    var options = {
                        title: chartTitle,
                        animation: {
                            duration: 500,
                            startup: true
                        },
                        legend: 'none'
                    };
                    var chart = new google.visualization.ColumnChart(document.getElementById(chartId));
                    chart.draw(data, options);
                    google.visualization.events.addListener(chart, 'select', function (e) {
                        var selectedItem = chart.getSelection()[0];
                        if (selectedItem) {
                            var period = data.getValue(selectedItem.row, 0);
                            loadAirbillLabels(type, period);
                        }
                    });
                });
            });
        </script>
        <div id="shipments_chart_<s:property value="uniqueId" />"
             style='width: <s:property value="width"/>px; height: <s:property value="%{height-50}"/>px'></div>
        <!--
        <table class="table table-hover table-bordered mg0">
        <tr>
        <th><xms:localization text="Column Name"/></th>
        <th class="text-right"><xms:localization text="Shipment Count"/></th>
        </tr>
        <s:iterator value="shipments">
            <tr>
            <td><s:property value="columnName"/></td>
            <td class="text-right"><s:property value="shipmentCount"/></td>
            </tr>
        </s:iterator>
        </table>
        -->
    </s:else>
</div>