<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <s:hidden id="svOrderField" name="svOrderField"/>
    <s:hidden id="svOrderType" name="svOrderType"/>
    <table class="table table-bordered mg0" id="sales_rep_service_stats_table">
        <thead>
        <tr bgcolor="#F0F2F5">
            <th><xms:localization text="Service Level"/></th>
            <th class="text-right"><xms:localization text="Shipment Goals"/></th>
            <th class="text-right"><xms:localization text="Actual Shipments"/></th>
            <th class="text-right"><xms:localization text="% Goal"/></th>
            <th class="text-right"><xms:localization text="Actual Margin"/></th>
            <th class="text-right"><xms:localization text="% Payout"/></th>
            <th class="text-right"><xms:localization text="Payout"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="serviceStats==null || serviceStats.size()==0">
            <tr>
                <td colspan="7"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="serviceStats">
                <tr>
                    <td><s:property value="serviceName"/></td>
                    <td class="text-right"><s:property value="goal"/></td>
                    <td class="text-right"><s:property value="actualShipments"/></td>
                    <td class="text-right"><s:property value="goalPct"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="actualMargin"/></td>
                    <td class="text-right"><s:property value="payout"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="payoutAmount"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    var serviceColumns = ["service_name", "goal", "actual_shipments", "goal_pct", "actual_margin", "payout", "payout_amt"];
    $(document).ready(function () {
        $("#sales_rep_service_stats_table").tablesorter({
            sortFieldId: "svOrderField",
            sortTypeId: "svOrderType",
            fieldList: serviceColumns,
            callback: getSalesRepServiceStats
        });
    });


</script>