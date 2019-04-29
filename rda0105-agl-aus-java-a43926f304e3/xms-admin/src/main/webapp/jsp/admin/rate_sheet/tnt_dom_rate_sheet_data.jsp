<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!-- Base rate sheet -->
<s:if test="rateSheet!=null">
    <div class="caption b17 text-center">
        <xms:localization text="Rate sheet for: "/>
        <s:property value="rateSheet.title"/>
        <br/>
        <s:property value="rateSheet.senderSuburb"/>
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
                <th><xms:localization text="Destination"/></th>
                <th class="text-right"><xms:localization text="Min"/></th>
                <th class="text-right"><xms:localization text="Base"/></th>
                <th class="text-right"><xms:localization text="Per Kg"/></th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="rateSheet.columns" var="column">
                <s:iterator value="rateSheet.rows" var="row">
                    <s:set var="key" value='%{#row.rowName + #row.charRowName + #column.columnName}'/>
                    <s:if test="rateSheet.data[#key].rate!=null">
                        <tr>
                            <td><s:property value="%{#column.columnName}"/></td>
                            <td class="text-right">
                                <div class="origin_values">
                                    <s:property value="rateSheet.data[#key].rate.minCharge"/>
                                </div>
                                <div class="analysis_values" style="display: none;">
                                    <s:if test="rateSheet.data[#key].minCustomerCost!=null">
                                        R:
                                        <s:property value="rateSheet.data[#key].minCustomerCost"/>
                                        <br/> A:
                                        <s:property value="rateSheet.data[#key].minFranchiseCost"/>
                                        <br/> M:
                                        <s:property value="rateSheet.data[#key].minMargin"/>
                                        <br/>
                                        <s:property value="rateSheet.data[#key].minPercent"/>
                                        %
                                    </s:if>
                                </div>
                            </td>
                            <td class="text-right">
                                <div class="origin_values">
                                    <s:property value="rateSheet.data[#key].rate.baseCharge"/>
                                </div>
                                <div class="analysis_values" style="display: none;">
                                    <s:if test="rateSheet.data[#key].baseCustomerCost!=null">
                                        R:
                                        <s:property value="rateSheet.data[#key].baseCustomerCost"/>
                                        <br/> A:
                                        <s:property value="rateSheet.data[#key].baseFranchiseCost"/>
                                        <br/> M:
                                        <s:property value="rateSheet.data[#key].baseMargin"/>
                                        <br/>
                                        <s:property value="rateSheet.data[#key].basePercent"/>
                                        %
                                    </s:if>
                                </div>
                            </td>
                            <td class="text-right">
                                <div class="origin_values">
                                    <s:property value="rateSheet.data[#key].rate.perKg"/>
                                </div>
                                <div class="analysis_values" style="display: none;">
                                    <s:if test="rateSheet.data[#key].kgCustomerCost!=null">
                                        R:
                                        <s:property value="rateSheet.data[#key].kgCustomerCost"/>
                                        <br/> A:
                                        <s:property value="rateSheet.data[#key].kgFranchiseCost"/>
                                        <br/> M:
                                        <s:property value="rateSheet.data[#key].kgMargin"/>
                                        <br/>
                                        <s:property value="rateSheet.data[#key].kgPercent"/>
                                        %
                                    </s:if>
                                </div>
                            </td>
                        </tr>
                    </s:if>
                </s:iterator>
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
                <th><xms:localization text="Destination"/></th>
                <th class="text-right"><xms:localization text="Min"/></th>
                <th class="text-right"><xms:localization text="Base"/></th>
                <th class="text-right"><xms:localization text="Per Kg"/></th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="perWeightRateSheet.columns" var="column">
                <s:iterator value="perWeightRateSheet.rows" var="row">
                    <s:set var="key" value='%{#row.rowName + #row.charRowName + #column.columnName}'/>
                    <s:if test="perWeightRateSheet.data[#key].rate!=null">
                        <tr>
                            <td><s:property value="%{#column.columnName}"/></td>
                            <td class="text-right">
                                <div class="origin_values">
                                    <s:property value="perWeightRateSheet.data[#key].rate.minCharge"/>
                                </div>
                                <div class="analysis_values" style="display: none;">
                                    <s:if test="perWeightRateSheet.data[#key].minCustomerCost!=null">
                                        R:
                                        <s:property value="perWeightRateSheet.data[#key].minCustomerCost"/>
                                        <br/> A:
                                        <s:property value="perWeightRateSheet.data[#key].minFranchiseCost"/>
                                        <br/> M:
                                        <s:property value="perWeightRateSheet.data[#key].minMargin"/>
                                        <br/>
                                        <s:property value="perWeightRateSheet.data[#key].minPercent"/>
                                        %
                                    </s:if>
                                </div>
                            </td>
                            <td class="text-right">
                                <div class="origin_values">
                                    <s:property value="perWeightRateSheet.data[#key].rate.baseCharge"/>
                                </div>
                                <div class="analysis_values" style="display: none;">
                                    <s:if test="perWeightRateSheet.data[#key].baseCustomerCost!=null">
                                       R:
                                        <s:property value="perWeightRateSheet.data[#key].baseCustomerCost"/>
                                        <br/> A:
                                        <s:property value="perWeightRateSheet.data[#key].baseFranchiseCost"/>
                                        <br/> M:
                                        <s:property value="perWeightRateSheet.data[#key].baseMargin"/>
                                        <br/>
                                        <s:property value="perWeightRateSheet.data[#key].basePercent"/>
                                        %
                                    </s:if>
                                </div>
                            </td>
                            <td class="text-right">
                                <div class="origin_values">
                                    <s:property value="perWeightRateSheet.data[#key].rate.perKg"/>
                                </div>
                                <div class="analysis_values" style="display: none;">
                                    <s:if test="perWeightRateSheet.data[#key].kgCustomerCost!=null">
                                        R:
                                        <s:property value="perWeightRateSheet.data[#key].kgCustomerCost"/>
                                        <br/> A:
                                        <s:property value="perWeightRateSheet.data[#key].kgFranchiseCost"/>
                                        <br/> M:
                                        <s:property value="perWeightRateSheet.data[#key].kgMargin"/>
                                        <br/>
                                        <s:property value="perWeightRateSheet.data[#key].kgPercent"/>
                                        %
                                    </s:if>
                                </div>
                            </td>
                        </tr>
                    </s:if>
                </s:iterator>
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