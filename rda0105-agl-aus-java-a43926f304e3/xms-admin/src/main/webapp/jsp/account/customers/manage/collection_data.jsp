<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <form id="frmSaveCustomerCollection">
        <div class="portlet-body b12 b11">
            <div class="portlet-body b22" style="padding: 0px;">
                <ul id="edit-customers-collections" class="nav nav-tabs responsive">
                    <li class="active" style="margin-left: 10px;"><a href="#collections-freight-tab"
                                                                     data-toggle="tab"><xms:localization
                            text="Freight"/></a></li>
                    <li><a href="#collections-reminder-letters-tab" data-toggle="tab" class="tb3"><xms:localization
                            text="Reminder Letters"/></a></li>
                </ul>
                <div id="edit-customers-collections-content" class="tab-content responsive">
                    <s:hidden name="saveManageCustomer.collection.userId" value="%{customerCollection.userId}"/>
                    <s:hidden name="saveManageCustomer.collection.customerCode"
                              value="%{customerCollection.customerCode}"/>
                    <div id="collections-freight-tab" class="tab-pane fade in tb2 active">
                        <div class="row">
                            <div class="portlet-body b12 b11">
                                <table class="s36">
                                    <tbody>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="caption b17" colspan="2"><xms:localization
                                                text="Credit Limits"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" height="5"></td>
                                    </tr>
                                    <tr>
                                        <td><xms:localization text="Freight Credit Limits"/>:</td>
                                        <td width="90"><s:textfield
                                                name="saveManageCustomer.collection.freightCreditLimit"
                                                value="%{customerCollection.freightCreditLimit}" cssClass="form-control"
                                                onkeypress="return formartNumber(event,this,true);"/></td>
                                        <td>$</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div id="collections-reminder-letters-tab" class="tab-pane fade in">
                        <div class="row">
                            <div class="portlet-body b12 b11">
                                <table class="s36">
                                    <tbody>
                                    <tr>
                                        <td class="caption b17" colspan="9"><xms:localization
                                                text="Reminder Letters"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="10" height="5"></td>
                                    </tr>
                                    <tr>
                                        <td><s:checkbox name="saveManageCustomer.collection.reminder"
                                                        value="%{customerCollection.reminder}"/></td>
                                        <td><xms:localization text="Send"/></td>
                                        <td><s:checkbox name="saveManageCustomer.collection.reminderEmail"
                                                        value="%{customerCollection.reminderEmail}"
                                                        onclick="changeEmailCheck()"/></td>
                                        <td><xms:localization text="Email"/></td>
                                        <td><s:checkbox name="saveManageCustomer.collection.reminderPrint"
                                                        value="%{customerCollection.reminderPrint}"/></td>
                                        <td><xms:localization text="Print"/></td>
                                        <td><s:checkbox name="saveManageCustomer.collection.reminderUseEmailInvoice"
                                                        value="%{customerCollection.reminderUseEmailInvoice}"/></td>
                                        <td><xms:localization text="Use Email Inv. AddressInv."/></td>
                                        <td><s:textfield name="saveManageCustomer.collection.reminderEmailAddress"
                                                         cssClass="form-control"
                                                         value="%{customerCollection.reminderEmailAddress}"/></td>
                                        <td><xms:localization text="Other Email Address(es)"/></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        changeEmailCheck();
    });

    function saveCustomerCollection() {
        var data = $("#frmSaveCustomerCollection").serialize();
        loadingDialog.dialog("open");
        $.post("manage_customers_collections_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            $("#collections-tab").html(res.content);
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function changeEmailCheck() {
        var isChecked = $("input[name='saveManageCustomer.collection.reminderEmail']").is(":checked");
        if (isChecked) {
            $("input[name='saveManageCustomer.collection.reminderUseEmailInvoice']").removeAttr("disabled");
            $("input[name='saveManageCustomer.collection.reminderEmailAddress']").removeAttr("readonly");
        } else {
            $("input[name='saveManageCustomer.collection.reminderUseEmailInvoice']").attr("disabled", "disabled");
            $("input[name='saveManageCustomer.collection.reminderEmailAddress']").attr("readonly", "readonly");
            $("input[name='saveManageCustomer.collection.reminderEmailAddress']").val("");
        }
    }


</script>