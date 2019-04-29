<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="s70">
    <table class="table table-bordered mg0" id="airbill_list_table">
        <thead>
        <tr>
            <th>&nbsp;</th>
            <th><xms:localization text="Airbill #"/></th>
            <th><xms:localization text="Invoice Date"/></th>
            <th><xms:localization text="Customer #"/></th>
            <th group="sender"><xms:localization text="Sender"/></th>
            <th group="sender"><xms:localization text="Sender Attn"/></th>
            <th group="sender"><xms:localization text="Sender Address"/></th>
            <th group="sender"><xms:localization text="Sender City"/></th>
            <th group="sender"><xms:localization text="Sender State"/></th>
            <th group="sender"><xms:localization text="Sender Zip"/></th>
            <th><xms:localization text="Reference"/></th>
            <th><xms:localization text="Service"/></th>
            <th group="receiver"><xms:localization text="Receiver"/></th>
            <th group="receiver"><xms:localization text="Receiver Attn"/></th>
            <th group="receiver"><xms:localization text="Receiver Address"/></th>
            <th group="receiver"><xms:localization text="Receiver City"/></th>
            <th group="receiver"><xms:localization text="Receiver State"/></th>
            <th group="receiver"><xms:localization text="Receiver Zip"/></th>
            <th group="account"><xms:localization text="Carrier Cost"/></th>
            <th group="account"><xms:localization text="Sender Account"/></th>
            <th group="account"><xms:localization text="Receiver Account"/></th>
            <th group="account"><xms:localization text="BILL TO Account"/></th>
            <th group="account"><xms:localization text="3P Account"/></th>
            <th><xms:localization text="Ship Date"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="airbills==null || airbills.totalRecords==0">
            <tr>
                <td colspan="26"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="airbills.records" status="awb">
                <tr>
                    <td align="center"><input name='chkAirbill_<s:property value="%{#awb.index+1}" />'
                                              group="airbill-list" type="checkbox"
                                              airbill-number='<s:property value="airbillNumber" />'/></td>
                    <td><i class="fa fa-times-circle-o"
                           onclick="deleteAirbillError('<s:property value="airbillNumber"/>')"></i> <s:property
                            value="airbillNumber"/></td>
                    <td><s:property value="invoiceDate"/></td>
                    <td><s:property value="customerCode"/></td>
                    <td group="sender"><s:property value="senderCompanyName"/></td>
                    <td group="sender"><s:property value="senderContactName"/></td>
                    <td group="sender"><s:property value="senderAddress1"/></td>
                    <td group="sender"><s:property value="senderCity"/></td>
                    <td group="sender"><s:property value="senderState"/></td>
                    <td group="sender"><s:property value="senderPostalCode"/></td>
                    <td><s:property value="reference"/></td>
                    <td><s:property value="service"/></td>
                    <td group="receiver"><s:property value="receiverCompanyName"/></td>
                    <td group="receiver"><s:property value="receiverContactName"/></td>
                    <td group="receiver"><s:property value="receiverAddress1"/></td>
                    <td group="receiver"><s:property value="receiverCity"/></td>
                    <td group="receiver"><s:property value="receiverState"/></td>
                    <td group="receiver"><s:property value="receiverPostalCode"/></td>
                    <td group="account" class="text-right"><s:property value="carrierCost"/></td>
                    <td group="account"><s:property value="senderAccount"/></td>
                    <td group="account"><s:property value="receiverAccount"/></td>
                    <td group="account"><s:property value="billingToAccount"/></td>
                    <td group="account"><s:property value="p3Account"/></td>
                    <td><s:property value="shipDate"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records" style="margin-bottom: 15px;">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="airbills.startRecord"/> <xms:localization
                    text="to"/> <s:property value="airbills.endRecord"/> <xms:localization text="of"/> <s:property
                    value="airbills.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="airbills!=null">
                <s:if test="!airbills.hasPrev()">
                    <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
                </s:if>
                <s:else>
                    <a href="javascript:changePage(<s:property value="%{airbills.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:else>
				<span> <s:iterator value="airbills.pageRange" status="count">
                    <s:if test="%{airbills.pageRange[#count.index] == airbills.currentPage}">
                        <a class="paginate_button current"><s:property value="airbills.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="!airbills.hasNext()">
                    <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{airbills.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:else>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    var fieldList = ["", "airbill_number", "invoice_date", "customer_code", "sender_company_name", "sender_contact_name", "sender_address1", "sender_city", "sender_state", "sender_postal_code", "reference", "service", "receiver_company_name", "receiver_contact_name", "receiver_address1",
        "receiver_city", "receiver_state", "receiver_postal_code", "carrier_cost", "sender_account", "receiver_account", "billing_to_account", "_3p_account", "ship_date"];
    $(document).ready(function () {
        // Show/hide all appropriate columns
        showHideGroup();
        // Add sorting function to the result table.
        $("#airbill_list_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
        // Add onclick handler for all checkbox of appropriate airbill.
        $("input[group='airbill-list'][type='checkbox']").each(function () {
            $(this).click(function () {
                airbillListChange();
            });
        });
    });
    <s:if test="airbills==null || airbills.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>