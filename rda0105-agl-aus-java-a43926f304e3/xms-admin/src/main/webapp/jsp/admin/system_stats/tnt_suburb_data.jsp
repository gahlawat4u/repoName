<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="tnt_suburb_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="tnt_suburb_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTntSuburbPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="tnt_suburb_page" name="page"/>
    <s:hidden id="tnt_suburb_order_type" name="orderType"/>
    <s:hidden id="tnt_suburb_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="tnt_suburb_table">
        <thead>
        <tr>
            <th><xms:localization text="Suburb Name"/></th>
            <th><xms:localization text="State Code"/></th>
            <th><xms:localization text="Post Code"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tntSuburbs==null || tntSuburbs.totalRecords==0">
            <tr>
                <td colspan="3"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tntSuburbs.records">
                <tr>
                    <td><s:property value="suburbName"/></td>
                    <td><s:property value="stateCode"/></td>
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
            <b><xms:localization text="Showing"/> <s:property value="tntSuburbs.startRecord"/> <xms:localization
                    text="to"/> <s:property value="tntSuburbs.endRecord"/> <xms:localization text="of"/> <s:property
                    value="tntSuburbs.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tntSuburbs!=null">
                <s:if test="tntSuburbs.hasPrev()">
                    <a href="javascript:changeTntSuburbPage(<s:property value="%{tntSuburbs.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tntSuburbs.pageRange" status="count">
                    <s:if test="%{tntSuburbs.pageRange[#count.index] == tntSuburbs.currentPage}">
                        <a class="paginate_button current"><s:property value="tntSuburbs.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeTntSuburbPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tntSuburbs.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTntSuburbPage(<s:property value="%{tntSuburbs.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#tnt_suburb_table").tablesorter({
            sortFieldId: "tnt_suburb_order_field",
            sortTypeId: "tnt_suburb_order_type",
            fieldList: ["suburb_name", "state_code", "post_code"],
            callback: getTntSuburbs
        });
        actionPrint = "system_stats_print_tnt_suburb.ix";
        actionExport = "system_stats_export_tnt_suburb.ix";
        <s:if test="tntSuburbs==null || tntSuburbs.totalRecords==0">
        hasRecords = false;
        </s:if>
        <s:else>
        hasRecords = true;
        </s:else>
    });

    function getTntSuburbs() {
        callAction("tnt_suburb");
    }

    function changeTntSuburbPage(page) {
        $("#tnt_suburb_page").val(page);
        getTntSuburbs();
    }

    function changeTntSuburbPageSize() {
        $("#tnt_suburb_page").val(1);
        getTntSuburbs();
    }


</script>