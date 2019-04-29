<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="caption b17">
    <s:property value="rateSheet.sheetName"/>
    (
    <s:if test="rateSheet.carrierCost == 1">
        <xms:localization text="Carrier Cost Rate Sheet"/>
    </s:if>
    <s:else>
        <xms:localization text="Non Carrier Cost Rate Sheet"/>
    </s:else>
    )
</div>
<div class="alert alert-danger" role="alert" id="alert-div" style="display: none;"></div>
<s:form id="rate-sheet-detail-form">
    <div class="form-group">
        <table style="width:<s:property value="%{columns.size() * 100}"/>px;max-width: none;"
               class="table table-bordered mg0  table-hover" id="rate-sheet-details-table">
            <thead>
            <tr>
                <th width="90px">
                    <s:property value="%{details['0-0']}"/>
                </th>
                <s:iterator value="columns">
                    <th width="90px"><s:property value="columnName"/></th>
                </s:iterator>
            </tr>
            </thead>
            <tbody>
            <s:set var="count" value="0"/>
            <s:iterator value="rows" status="rowCount">
                <tr>
                    <td><s:if test="'false'.equalsIgnoreCase(isChar)">
                        <s:property value="rowName"/>
                    </s:if> <s:else>
                        <s:property value="charRowName"/>
                    </s:else></td>
                    <s:iterator value="columns" status="columnCount">
                        <s:set name="key" value="rowId+'-'+columnId"></s:set>
                        <td data-row-id='<s:property value="rowId"/>' data-column-id='<s:property value="columnId" />'
                            data-old-value='<s:property value="%{details[#key]}" />'
                            onfocusout="changeValue(<s:property value="rowId"/>,<s:property value="columnId"/>)"
                            onclick="showRateSheetInput(<s:property value="rowId"/>,<s:property
                                    value="columnId"/>,false)">
                            <s:if test="%{detailTnTDom[#key].minCharge != null && detailTnTDom[#key].minCharge != ''}">
                                <label><xms:localization text="Min Charge:"/> </label>
                                <s:textfield name="detailsList[%{count}].value" value="%{detailTnTDom[#key].minCharge}"
                                             cssClass="form-control s50" disabled="true" cssStyle=" width:55px;"
                                             onkeypress="return formartNumber(event, this, true)"/>
                                <br/>
                                <label><xms:localization text="Base Charge:"/> </label>
                                <s:textfield name="detailsList[%{count}].value" value="%{detailTnTDom[#key].baseCharge}"
                                             cssClass="form-control s50" disabled="true" cssStyle="width:55px;"
                                             onkeypress="return formartNumber(event, this, true)"/>
                                <br/>
                                <label><xms:localization text="Per Kg:"/> </label>
                                <s:textfield name="detailsList[%{count}].value" value="%{detailTnTDom[#key].perKg}"
                                             cssClass="form-control s50" disabled="true" cssStyle="width:55px;"
                                             onkeypress="return formartNumber(event, this, true)"/>
                            </s:if>
                            <s:else>
                                <label><s:property value="%{details[#key]}"/></label>
                                <s:textfield name="detailsList[%{count}].value" value="%{details[#key]}"
                                             cssClass="form-control s50" disabled="true"
                                             cssStyle="display:none; width:55px;"
                                             onkeypress="return formartNumber(event, this, true)"/>
                            </s:else>
                            <s:hidden name="detailsList.rowId" value="%{rowId}" disabled="true"/> <s:hidden
                                name="detailsList.columnId" value="%{columnId}" disabled="true"/></td>
                        <s:set var="count" value="%{#count + 1}"/>
                    </s:iterator>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</s:form>	