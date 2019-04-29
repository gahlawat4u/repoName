<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0" id="manage_contact_table">
        <thead>
        <tr>
            <th><xms:localization text="Company Name"/></th>
            <th><xms:localization text="Contact Name"/></th>
            <th><xms:localization text="Address 1"/></th>
            <th><xms:localization text="Address 2"/></th>
            <th><xms:localization text="Phone"/></th>
            <th><xms:localization text="Email"/></th>
            <th><xms:localization text="Postal Code"/></th>
            <th><xms:localization text="Sales Stage"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="contacts!=null && contacts.totalRecords>0">
            <s:iterator value="contacts.records">
                <tr>
                    <td><s:property value="companyName"/></td>
                    <td><s:property value="contactName"/></td>
                    <td><s:property value="address1"/></td>
                    <td><s:property value="address2"/></td>
                    <td><s:property value="phone"/></td>
                    <td><s:property value="email"/></td>
                    <td><s:property value="postalCode"/></td>
                    <td><s:property value="saleStage"/></td>
                </tr>
            </s:iterator>
        </s:if>
        <s:else>
            <tr>
                <td colspan="8"><xms:localization text="No data available..."/></td>
            </tr>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="contacts.startRecord"/> <xms:localization
                    text="to"/> <s:property value="contacts.endRecord"/> <xms:localization text="of"/> <s:property
                    value="contacts.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="contacts!=null">
                <s:if test="!contacts.hasPrev()">
                    <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
                </s:if>
                <s:else>
                    <a href="javascript:changePage(<s:property value="%{contacts.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:else>
				<span> <s:iterator value="contacts.pageRange" status="count">
                    <s:if test="%{contacts.pageRange[#count.index] == contacts.currentPage}">
                        <a class="paginate_button current"><s:property value="contacts.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="!contacts.hasNext()">
                    <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{contacts.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:else>
            </s:if>
        </div>
    </div>
</div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    var fieldList = ["company_name", "contact_name", "address1", "address2", "phone", "email", "postal_code", "statusname"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#manage_contact_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
    });
    <s:if test="contacts==null || contacts.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>