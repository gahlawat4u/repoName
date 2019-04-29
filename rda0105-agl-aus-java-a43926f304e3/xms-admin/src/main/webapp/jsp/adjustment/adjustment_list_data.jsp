<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="col-lg-12">
    <table class="table mg0">
        <tr>
            <th class="s42" class="text-center" valign="middle"><span style="margin-left: 10px;"><xms:localization
                    text="Show"/></span> <s:i18n_select name="filter.pageSize" list="pageSizeList" cssClass="s52"
                                                        onchange="changePageSize();" i18nitem="no"/>
                <span><xms:localization text="entries"/></span></th>
        </tr>
    </table>
    <table class="table table-bordered mg0" id="datatable1">
        <thead>
        <tr>
            <th width="4%"></th>
            <th width="8%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Type"/></th>
            <th width="6%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Airbill #"/></th>
            <th width="6%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Customer #"/></th>
            <th width="6%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Request Date"/></th>
            <th width="6%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Response Date"/></th>
            <s:if test="userLevel<3">
                <th width="7%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                        text="Carrier Amt.Requested Credited"/></th>
            </s:if>
            <th width="7%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Franchise Amt.Requested Credited"/></th>
            <th width="7%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Customer Amt.Requested Credited"/></th>
            <th width="5%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Status"/></th>
            <th width="8%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Reason for deleting Credit Note request"/></th>
            <th width="8%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Intial request information"/></th>
            <th width="8%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                    text="Franchise comments to FSC"/></th>
            <s:if test="userLevel<3">
                <th width="8%" class="text-center" style="vertical-align: middle !important;"><xms:localization
                        text="FSC Credit notes"/></th>
            </s:if>
        </tr>
        </thead>
        <tbody>
        <s:if test="%{adjustmentList==null || adjustmentList.totalRecords==0}">
            <tr>
                <td colspan="<s:if test="userLevel<3">14</s:if><s:else>12</s:else>"><xms:localization
                        text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="adjustmentList.records" status="row">
                <tr>
                    <td class="text-center"><s:if test="creditNoteStatus==0">
                        <s:if test="userLevel>3 && status==7">
                        </s:if>
                        <s:else>
                            <s:if test="status!=6">
                                <a href="javascript:editAdj(<s:property value='adjustmentId'/>);"
                                   title='<xms:localization text="Edit"/>'><i class="fa fa-edit"></i></a>
                                <a href="javascript:showNoteAdjDlg(<s:property value='adjustmentId'/>,1);"
                                   title='<xms:localization text="Delete"/>'><i class="fa fa-trash-o"></i></a>
                            </s:if>
                        </s:else>
                    </s:if> <s:if test="status==3">
                        <a href="javascript:resubmit(<s:property value='adjustmentId'/>);"
                           title='<xms:localization text="Resubmit"/>'><i class="fa fa-undo"></i></a>
                    </s:if></td>
                    <td><s:i18n_property value="adjustmentType"/></td>
                    <td><s:property value="airbillNumber"/></td>
                    <td><s:property value="customerCode"/></td>
                    <td><s:property value="requestDate"/></td>
                    <td><s:property value="responseDate"/></td>
                    <s:if test="userLevel<3">
                        <td align="right"><s:property value="carrierAmount"/></td>
                    </s:if>
                    <td align="right"><s:property value="franchiseAmount"/></td>
                    <td align="right"><s:property value="customerAmount"/></td>
                    <td align="center" <s:if test="status==1 && creditNoteStatus==0">
                        ondblclick="updateSubStatus(<s:property value="adjustmentId"/>)"
                        style="cursor: pointer;"
                        title='<xms:localization text="Double click to change status"/>'
                    </s:if>><s:i18n_property value="statusName"/></td>
                    <td><s:property value="reasonForDeleting"/></td>
                    <td><s:property value="note"/></td>
                    <td <s:if test="creditNoteStatus==0 && status!=6">
                        onclick="showNoteAdjDlg(<s:property value='adjustmentId'/>,2)"
                        style="cursor: pointer;" title='<xms:localization
                            text="Click to put Franchise comments to FSC"/>'
                    </s:if>><s:property escape="false" value="franchiseCommentsToFsc"/></td>
                    <s:if test="userLevel<3">
                        <td <s:if test="creditNoteStatus==0 && status!=6">
                            onclick="showNoteAdjDlg(<s:property value='adjustmentId'/>,3)"
                            style="cursor: pointer;" title='<xms:localization text="Click to put FSC Credit notes"/>'
                        </s:if>><s:property escape="false" value="fscCreditNote"/></td>
                    </s:if>
                </tr>
            </s:iterator>
        </s:else>
        <tr>
            <td colspan="<s:if test="userLevel<3">14</s:if><s:else>12</s:else>"><span class="b4"><b><xms:localization
                    text="Total Adjustments :"/> </b> <s:property value="total.recordCount"/></span> <span
                    class="b4"><b>| <xms:localization text="Franchise Request Total :"/>
            </b> <s:property value="total.carrierAmount"/></span> <span class="b4"><b>| <xms:localization
                    text="Customer Request Total :"/>
            </b> <s:property value="total.customerAmount"/></span> <span class="b4"><b>| <xms:localization
                    text="Franchise Credit Total :"/>
            </b> <s:property value="total.approvedCarrierAmount"/></span> <span class="b4"><b>| <xms:localization
                    text="Customer Credit Total :"/>
            </b> <s:property value="total.approvedCustomerAmount"/></span></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="col-lg-12">
    <div class="dataTables_paginate">
        <s:if test="%{adjustmentList!=null && adjustmentList.totalRecords>0}">
            <span><a class="paginate_button" href="javascript:changePage(1);"><xms:localization
                    text="First"/></a></span>
            <s:iterator value="adjustmentList.pageRange" status="row">
                <s:if test="%{adjustmentList.pageRange[#row.index] == adjustmentList.currentPage}">
                    <span><a class="paginate_button current"
                             href="javascript:changePage(<s:property value='adjustmentList.currentPage'/>);"><s:property
                            value="adjustmentList.currentPage"/></a></span>
                </s:if>
                <s:else>
                    <span><a class="paginate_button"
                             href="javascript:changePage(<s:property />);"><s:property/></a></span>
                </s:else>
            </s:iterator>
            <span><a class="paginate_button"
                     href="javascript:changePage(<s:property value='%{adjustmentList.totalPage}'/>);"><xms:localization
                    text="Last"/></a></span>
            <input id="current_page" type="hidden" value="<s:property value='adjustmentList.currentPage'/>"/>
        </s:if>
    </div>
</div>