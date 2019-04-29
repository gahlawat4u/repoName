<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!-- Base rate sheet -->
<s:if test="rateSheet!=null">
    <div class="caption b17 text-center">
        <xms:localization text="Rate sheet for: "/>
        <s:property value="rateSheet.title"/>
    </div>
    <div class="form-group">
        <b><xms:localization text="Value In AUD"/></b><br/> <input type="checkbox" id="showMarginAnalysis"
                                                                   onclick="showAnalysis()"/>
        <xms:localization text="Show Margin Analysis"/>
    </div>
    <div class="form-group" style="max-height: 500px; overflow: auto">
        <table class="table table-bordered mg0 table-hover">
            <thead>
            <tr>
                <th><xms:localization text="Weight"/></th>
                <s:iterator value="rateSheet.columns">
                    <th class="text-right"><s:property value="columnName"/></th>
                </s:iterator>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="rateSheet.rows" var="row">
                <tr>
                    <td><s:if test="#row.isChar==1">
                        <s:property value="%{#row.charRowName}"/>
                    </s:if> <s:else>
                        <s:property value="%{#row.rowName}"/>
                    </s:else></td>
                    <s:iterator value="rateSheet.columns" var="column">
                        <s:set var="key" value='%{#row.rowName + #row.charRowName + #column.columnName}'/>
                        <td class="text-right">
                            <div class="origin_values">
                                <s:property value="rateSheet.data[#key].rate.value"/>
                            </div>
                            <div class="analysis_values" style="display: none;">
                                R:
                                <s:property value="rateSheet.data[#key].customerCost"/>
                                <br/> A:
                                <s:property value="rateSheet.data[#key].franchiseCost"/>
                                <br/> M:
                                <s:property value="rateSheet.data[#key].margin"/>
                                <br/>
                                <s:property value="rateSheet.data[#key].percent"/>
                                %
                            </div>
                        </td>
                    </s:iterator>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</s:if>
<!-- Per weight rate sheet -->
<s:if test="perWeightRateSheet!=null">
    <div class="caption b17">
        <xms:localization text="Non-Document above "/>
        <s:property value="rateSheet.maxWeight"/>
        <xms:localization text="kg (Multiply shipment weight by zone rate)"/>
    </div>
    <div class="form-group">
        <table class="table table-bordered mg0 table-hover">
            <thead>
            <tr>
                <th><xms:localization text="Weight"/></th>
                <s:iterator value="perWeightRateSheet.columns">
                    <th class="text-right"><s:property value="columnName"/></th>
                </s:iterator>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="perWeightRateSheet.rows" var="row">
                <tr>
                    <td><xms:localization text="Per Kg"/> <s:if test="#row.isChar==1">
                        <s:property value="%{#row.charRowName}"/>
                    </s:if> <s:else>
                        <s:property value="%{#row.rowName}"/>
                    </s:else> <xms:localization text="Kgs"/></td>
                    <s:iterator value="perWeightRateSheet.columns" var="column">
                        <s:set var="key" value='%{#row.rowName + #row.charRowName + #column.columnName}'/>
                        <td class="text-right">
                            <div class="origin_values">
                                <s:property value="perWeightRateSheet.data[#key].rate.value"/>
                            </div>
                            <div class="analysis_values" style="display: none;">
                                R:
                                <s:property value="perWeightRateSheet.data[#key].customerCost"/>
                                <br/> A:
                                <s:property value="perWeightRateSheet.data[#key].franchiseCost"/>
                                <br/> M:
                                <s:property value="perWeightRateSheet.data[#key].margin"/>
                                <br/>
                                <s:property value="perWeightRateSheet.data[#key].percent"/>
                                %
                            </div>
                        </td>
                    </s:iterator>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</s:if>
<script type="text/javascript">
    $(document).ready(function () {
        showAnalysis();
    });

    function showAnalysis() {
        var analysisChecked = $("#showMarginAnalysis").is(":checked");
        if (analysisChecked) {
            $("div.origin_values").hide();
            $("div.analysis_values").show();
        } else {
            $("div.origin_values").show();
            $("div.analysis_values").hide();
        }
    }


</script>