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
                    <td><s:if test="isChar == 0">
                        <s:property value="rowName"/>
                    </s:if> <s:else>
                        <s:property value="charRowName"/>
                    </s:else></td>
                    <s:iterator value="columns" status="columnCount">
                        <s:set name="key" value="rowId+'-'+columnId"></s:set>
                        <td data-row-id='<s:property value="rowId"/>' data-column-id='<s:property value="columnId" />'
                            data-old-value='<s:property value="%{details[#key]}" />'
                            onclick="showRateSheetInput(<s:property value="rowId"/>,<s:property
                                    value="columnId"/>,false)"><label><s:property value="%{details[#key]}"/></label>
                            <s:textfield name="detailsList[%{count}].value" value="%{details[#key]}"
                                         cssClass="form-control s50" disabled="true"
                                         cssStyle="display:none; width:55px;"
                                         onkeypress="return formartNumber(event, this, true)"
                                         onfocusout="changeValue(<s:property value="
                                         rowId"/>,<s:property value="columnId"/>)"/></td>
                        <s:set var="count" value="%{#count + 1}"/>
                    </s:iterator>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</s:form>