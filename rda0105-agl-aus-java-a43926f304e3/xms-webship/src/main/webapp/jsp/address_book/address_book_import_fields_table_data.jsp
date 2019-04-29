<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="address_book_import_form">
    <s:hidden name="addressFilePath"/>
    <s:hidden name="addressDataStr"/>
    <s:hidden name="mapStateCountryDataStr"/>
    <p>
        <xms:localization text="Choose a field for every column in the grid."/>
    </p>

    <p>
        <span class="s30"><b> <xms:localization text="* Required Fields:"/></b></span> <br/>
        <xms:localization text="- Contact , Address 1 , City , Country , Phone."/>
    </p>
    <table class="table  table-bordered mg0" id="address-import-table">
        <thead>
        <tr bgcolor="#F5F5F5">
            <s:iterator begin="1" end="totalColumns" status="counter">
                <th><xms:localization text="Field"/> <s:property value="#counter.count"/></th>
            </s:iterator>
        </tr>
        <tr>
            <s:iterator begin="1" end="totalColumns" status="counter">
                <td><s:select name="selectedImportFields[%{#counter.index}]" list="importFields"
                              cssClass="form-control sel-box" headerKey="0" headerValue=""/></td>
            </s:iterator>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="rowData">
            <tr>
                <s:iterator begin="1" end="totalColumns" status="counter">
                    <td data-field="1"><s:property value="cellData[#counter.index]"/></td>
                </s:iterator>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <div class="form-group">
        <div class="checkbox">
            <label>
                <s:checkbox name="hasHeader"/>
                &nbsp;
                <xms:localization text="This file has a header row"/>
            </label>
        </div>
    </div>
</form>