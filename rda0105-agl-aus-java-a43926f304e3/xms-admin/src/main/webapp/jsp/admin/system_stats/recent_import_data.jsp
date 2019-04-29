<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="recent_import_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="recent_import_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeRecentImportPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="recent_import_page" name="page"/>
    <s:hidden id="recent_import_order_type" name="orderType"/>
    <s:hidden id="recent_import_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="recent_import_table">
        <thead>
        <tr>
            <th><xms:localization text="Airbill Import Date"/></th>
            <th><xms:localization text="Carrier Name"/></th>
            <th class="text-right"><xms:localization text="Import Count"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="recentImports==null || recentImports.totalRecords==0">
            <tr>
                <td colspan="3"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="recentImports.records">
                <tr>
                    <td><s:property value="importDate"/></td>
                    <td><s:property value="carrierName"/></td>
                    <td class="text-right"><s:property value="importCount"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="recentImports.startRecord"/> <xms:localization
                    text="to"/> <s:property value="recentImports.endRecord"/> <xms:localization text="of"/> <s:property
                    value="recentImports.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="recentImports!=null">
                <s:if test="recentImports.hasPrev()">
                    <a href="javascript:changeRecentImportPage(<s:property value="%{recentImports.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="recentImports.pageRange" status="count">
                    <s:if test="%{recentImports.pageRange[#count.index] == recentImports.currentPage}">
                        <a class="paginate_button current"><s:property value="recentImports.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeRecentImportPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="recentImports.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeRecentImportPage(<s:property value="%{recentImports.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#recent_import_table").tablesorter({
            sortFieldId: "recent_import_order_field",
            sortTypeId: "recent_import_order_type",
            fieldList: ["import_date", "service_name", "import_count"],
            callback: getRecentImports
        });
        actionPrint = "";
        actionExport = "";
        hasRecords = true;
    });

    function getRecentImports() {
        callAction("recent_import");
    }

    function changeRecentImportPage(page) {
        $("#recent_import_page").val(page);
        getRecentImports();
    }

    function changeRecentImportPageSize() {
        $("#recent_import_page").val(1);
        getRecentImports();
    }


</script>