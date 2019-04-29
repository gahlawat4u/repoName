<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="tnt_post_code_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="tnt_post_code_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTntPostCodePageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="tnt_post_code_page" name="page"/>
    <s:hidden id="tnt_post_code_order_type" name="orderType"/>
    <s:hidden id="tnt_post_code_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="tnt_post_code_table">
        <thead>
        <tr>
            <th><xms:localization text="Zone Code"/></th>
            <th><xms:localization text="Post Code"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tntPostCodes==null || tntPostCodes.totalRecords==0">
            <tr>
                <td colspan="2"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tntPostCodes.records">
                <tr>
                    <td><s:property value="zoneCode"/></td>
                    <td><s:property value="postCode"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="tntPostCodes.startRecord"/> <xms:localization
                    text="to"/> <s:property value="tntPostCodes.endRecord"/> <xms:localization text="of"/> <s:property
                    value="tntPostCodes.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tntPostCodes!=null">
                <s:if test="tntPostCodes.hasPrev()">
                    <a href="javascript:changeTntPostCodePage(<s:property value="%{tntPostCodes.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tntPostCodes.pageRange" status="count">
                    <s:if test="%{tntPostCodes.pageRange[#count.index] == tntPostCodes.currentPage}">
                        <a class="paginate_button current"><s:property value="tntPostCodes.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeTntPostCodePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tntPostCodes.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTntPostCodePage(<s:property value="%{tntPostCodes.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#tnt_post_code_table").tablesorter({
            sortFieldId: "tnt_post_code_order_field",
            sortTypeId: "tnt_post_code_order_type",
            fieldList: ["zone_code", "post_code"],
            callback: getTntPostCodes
        });
        actionPrint = "system_stats_print_tnt_postcode.ix";
        actionExport = "system_stats_export_tnt_postcode.ix";
        <s:if test="tntPostCodes==null || tntPostCodes.totalRecords==0">
        hasRecords = false;
        </s:if>
        <s:else>
        hasRecords = true;
        </s:else>
    });

    function getTntPostCodes() {
        callAction("tnt_post_code");
    }

    function changeTntPostCodePage(page) {
        $("#tnt_post_code_page").val(page);
        getTntPostCodes();
    }

    function changeTntPostCodePageSize() {
        $("#tnt_post_code_page").val(1);
        getTntPostCodes();
    }


</script>