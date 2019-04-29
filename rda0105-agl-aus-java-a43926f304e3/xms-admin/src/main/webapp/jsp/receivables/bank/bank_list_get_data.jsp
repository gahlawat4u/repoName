<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0" id="bank-list-table">
    <thead>
    <tr>
        <th><xms:localization text="Id"/></th>
        <th><xms:localization text="Bank Name"/></th>
        <th><xms:localization text="Admin Level"/></th>
        <th><xms:localization text="Last Modified"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="bankList==null || bankList.totalRecords==0">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="bankList.records">
            <tr data-bankId="<s:property value="bankId" />" ondblclick="editBank($(this).attr('data-bankId'))">
                <td><s:property value="bankId"/></td>
                <td><s:property value="bankName"/></td>
                <td><s:property value="userLevel.userLevelName"/></td>
                <td><s:property value="modifiedDate"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="row">
    <div class="col-xs-4 text-left">
        <b><xms:localization text="Showing"/> <s:property value="bankList.startRecord"/> <xms:localization text="to"/>
            <s:property value="bankList.endRecord"/> <xms:localization text="of"/> <s:property
                    value="bankList.totalRecords"/> <xms:localization text="entries"/></b>
    </div>
    <div class="col-xs-8">
        <s:if test="!bankList.hasPrev()">
            <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
        </s:if>
        <s:else>
            <a href="javascript:changePage(<s:property value="%{bankList.currentPage - 1}"/>)"
               class="paginate_button previous"><xms:localization text="Previous"/></a>
        </s:else>
		<span> <s:iterator value="bankList.pageRange" status="count">
            <s:if test="%{bankList.pageRange[#count.index] == bankList.currentPage}">
                <a class="paginate_button current"><s:property value="bankList.currentPage"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
            </s:else>
        </s:iterator>
		</span>
        <s:if test="!bankList.hasNext()">
            <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button next"
               href="javascript:changePage(<s:property value="%{bankList.currentPage+1}"/>)"><xms:localization
                    text="Next"/></a>
        </s:else>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#bank-list-table tbody tr').click(function () {
            var bId = $(this).attr('data-bankId');
            if (typeof (bId) != "undefined" && bId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                bankId = bId;
                $('#btnRemove').attr('disabled', false);
            }
        });
        var fieldList = ["bankid", "bankname", "ul_user_level_name", "modified_date"];
        $("#bank-list-table").tablesorter({
            sortFieldId: "bank_list_order_field",
            sortTypeId: "bank_list_order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });


</script>