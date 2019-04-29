<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-hover table-bordered mg0" id="address-book-table">
    <thead>
    <tr>
        <th>Contact</th>
        <th>Company</th>
        <th>Address#</th>
        <th>Address 2</th>
        <th>City</th>
        <th>State/Province</th>
        <th>Postal Code</th>
        <th>Country</th>
        <th>Phone</th>
    </tr>
    </thead>
    <tbody>
    <s:if test="addressBookList.records.isEmpty()">
        <td colspan="9"><xms:localization text="No data available..."/></td>
    </s:if>
    <s:else>
        <s:iterator value="addressBookList.records">
            <tr data-address-id="<s:property value="addressId"/>">
                <td><s:property value="contactName"/></td>
                <td><s:property value="companyName"/></td>
                <td><s:property value="address1"/></td>
                <td><s:property value="address2"/></td>
                <td><s:property value="city"/></td>
                <td><s:property value="state"/></td>
                <td><s:property value="postalCode"/></td>
                <td><s:property value="countryName"/></td>
                <td><s:property value="phone"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<s:hidden value="%{addressBookList.currentPage}" id="currPage"></s:hidden>
<input type="hidden" id="selectedId" value=""/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#selectedId').val("");
        $('table#address-book-table tbody tr').click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            selectedAddressId = $(this).attr("data-address-id");
            $('#selectedId').val(selectedAddressId);
            $('#customerAddressBook-addressId').val(selectedAddressId);
            $('#btnEdit').attr('disabled', false);
            $('#btnRemove').attr('disabled', false);
        });
    });
</script>
<div class="text-right">
    <ul class="pagination mts mbs" data-hover="">
        <s:if test="!addressBookList.hasPrev()">
            <li class="disabled"><a><xms:localization text="Previous"/></a></li>
        </s:if>
        <s:else>
            <li>
                <a href="javascript:changePage(<s:property value="%{addressBookList.currentPage - 1}"/>)"><xms:localization
                        text="Previous"/></a></li>
        </s:else>
        <s:iterator value="addressBookList.pageRange" status="count">
            <s:if test="%{addressBookList.pageRange[#count.index] == addressBookList.currentPage}">
                <li class="active"><a><s:property value="addressBookList.currentPage"/></a></li>
            </s:if>
            <s:else>
                <li><a href="javascript:changePage(<s:property/>);"><s:property/></a></li>
            </s:else>
        </s:iterator>
        <s:if test="!addressBookList.hasNext()">
            <li class="disabled"><a href="#"><xms:localization text="Next"/></a></li>
        </s:if>
        <s:else>
            <li>
                <a href="javascript:changePage(<s:property value="%{addressBookList.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a></li>
        </s:else>
    </ul>
</div>