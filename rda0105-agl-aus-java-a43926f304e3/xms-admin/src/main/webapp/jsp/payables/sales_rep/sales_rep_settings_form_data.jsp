<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:form id="sales_rep_setting_form">
    <div class="row">
        <div class="col-lg-12" style="height: 400px !important; overflow: auto;">
            <s:hidden name="salesRep.salesRepId"/>
            <s:hidden name="salesRep.userId"/>
            <s:hidden name="salesRep.user.userCode"/>
            <s:hidden name="salesRep.user.displayName"/>
            <table class="s36 b24  form-group">
                <tbody>
                <tr>
                    <td class="td1"><xms:localization text="Sales Rep"/></td>
                    <td class="td2"></td>
                    <td class="td2"><xms:localization text="Name: "/><i><s:property
                            value="salesRep.user.displayName"/></i></td>
                    <td class="td2" colspan="3"><xms:localization text="Franchise: "/><i><s:property
                            value="salesRep.user.userCode"/></i></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Sales Manager"/></td>
                    <td class="td2"></td>
                    <td class="td2"><s:textfield name="salesRep.salesManager" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.salesManager"/></span></td>
                    <td class="td2"><xms:localization text="Percent Payout"/></td>
                    <td class="td2"><s:textfield name="salesRep.percentPayout" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.percentPayout"/></span></td>
                    <td class="td2">%</td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Hire/Termination Date"/></td>
                    <td class="td2"><xms:localization text="Hire Date"/> <span class="s30">*</span></td>
                    <td class="td2">
                        <div class="form-group input-group mg0">
                            <span class="input-group-addon s31"><i class="fa fa-calendar"></i></span>
                            <s:textfield name="salesRep.hireDate" cssClass="form-control form_datetime"
                                         data-date-format="dd MM yyyy"/>
                        </div>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.hireDate"/></span>
                    </td>
                    <td class="td2"><xms:localization text="Termination Date"/></td>
                    <td class="td2">
                        <div class="form-group input-group mg0">
                            <span class="input-group-addon s31"><i class="fa fa-calendar"></i></span>
                            <s:textfield name="salesRep.terminateDate" cssClass="form-control form_datetime"
                                         data-date-format="dd MM yyyy"/>
                        </div>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.terminateDate"/></span>
                    </td>
                    <td class="b30"><s:if test="salesRep.active==1">
                        <input type="checkbox" checked="checked" disabled="disabled"/>
                    </s:if> <s:else>
                        <input type="checkbox" disabled="disabled"/>
                    </s:else> <span class="b29"><xms:localization text="Active"/></span></td>
                </tr>
                <tr>
                    <td class="td1 s40"><xms:localization text="Address"/></td>
                    <td class="td2 b31"><xms:localization text="Addr1"/> <span class="s30">*</span></td>
                    <td class="td2 b31"><s:textfield name="salesRep.address1" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.address1"/></span></td>
                    <td class="td2 b31"><xms:localization text="Addr2"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.address2" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.address2"/></span></td>
                </tr>
                <tr>
                    <td class="td1 s40"></td>
                    <td class="td2 b31"><xms:localization text="City"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.city" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.city"/></span></td>
                    <td class="td2 b31"><xms:localization text="State"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.state" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.state"/></span></td>
                    <td class="td2 b31"><xms:localization text="Zip"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.zip" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.zip"/></span></td>
                </tr>
                <tr>
                    <td class="td1 s40"></td>
                    <td class="td2 b31"><xms:localization text="Phone"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.phone" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.phone"/></span></td>
                    <td class="td2 b31"><xms:localization text="Fax"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.fax" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.fax"/></span></td>
                    <td class="td2 b31"><xms:localization text="Email"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.email" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.email"/></span></td>
                </tr>
                <tr>
                    <td class="td1 s41"></td>
                    <td class="td2"></td>
                    <td colspan="5" class="b30" style="padding-left: 10px"><s:checkbox
                            name="salesRep.useWebshipContactInformation"/> <xms:localization
                            text="Use as Contact Information in Web Freight"/></td>
                </tr>
                <tr>
                    <td class="td1 s40"><xms:localization text="Salary/Allowance"/></td>
                    <td class="td2 b31"><xms:localization text="Salary"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.salary" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.salary"/></span></td>
                    <td class="td2 b31"><xms:localization text="Vehicle"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.vehicleAllowance" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.vehicleAllowance"/></span></td>
                    <td class="td2 b31"><xms:localization text="Phone"/></td>
                    <td class="td2 b31"><s:textfield name="salesRep.phoneAllowance" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.phoneAllowance"/></span></td>
                </tr>
                <tr>
                    <td class="td1 s41"></td>
                    <td class="td2"><xms:localization text="Health"/></td>
                    <td class="td2"><s:textfield name="salesRep.healthAllowance" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.healthAllowance"/></span></td>
                    <td class="td2" colspan="4"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Other 1 Allowance"/></td>
                    <td class="td2"><xms:localization text="Description"/></td>
                    <td class="td2"><s:textfield name="salesRep.allowance1Description" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.allowance1Description"/></span></td>
                    <td class="td2"><xms:localization text="Amount"/></td>
                    <td class="td2"><s:textfield name="salesRep.allowance1Amount" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.allowance1Amount"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Other 2 Allowance"/></td>
                    <td class="td2"><xms:localization text="Description"/></td>
                    <td class="td2"><s:textfield name="salesRep.allowance2Description" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.allowance2Description"/></span></td>
                    <td class="td2"><xms:localization text="Amount"/></td>
                    <td class="td2"><s:textfield name="salesRep.allowance2Amount" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.allowance2Amount"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Other 3 Allowance"/></td>
                    <td class="td2"><xms:localization text="Description"/></td>
                    <td class="td2"><s:textfield name="salesRep.allowance3Description" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.allowance3Description"/></span></td>
                    <td class="td2"><xms:localization text="Amount"/></td>
                    <td class="td2"><s:textfield name="salesRep.allowance3Amount" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.allowance3Amount"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Deduction 1"/></td>
                    <td class="td2"><xms:localization text="Description"/></td>
                    <td class="td2"><s:textfield name="salesRep.deduction1Description" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.deduction1Description"/></span></td>
                    <td class="td2"><xms:localization text="Amount"/></td>
                    <td class="td2"><s:textfield name="salesRep.deduction1Amount" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.deduction1Amount"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Deduction 2"/></td>
                    <td class="td2"><xms:localization text="Description"/></td>
                    <td class="td2"><s:textfield name="salesRep.deduction2Description" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.deduction2Description"/></span></td>
                    <td class="td2"><xms:localization text="Amount"/></td>
                    <td class="td2"><s:textfield name="salesRep.deduction2Amount" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.deduction2Amount"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Deduction 3"/></td>
                    <td class="td2"><xms:localization text="Description"/></td>
                    <td class="td2"><s:textfield name="salesRep.deduction3Description" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.deduction3Description"/></span></td>
                    <td class="td2"><xms:localization text="Amount"/></td>
                    <td class="td2"><s:textfield name="salesRep.deduction3Amount" class="form-control alloptions"/>
                        <span class="text-danger"><s:fielderror fieldName="salesRep.deduction3Amount"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Goals"/></td>
                    <td class="td2"><xms:localization text="Setups"/></td>
                    <td class="td2"><s:textfield name="salesRep.setups" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.setups"/></span></td>
                    <td class="td2"><xms:localization text="Activations"/></td>
                    <td class="td2"><s:textfield name="salesRep.activation" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.activation"/></span></td>
                    <td class="td2"><xms:localization text="Days to Activate"/></td>
                    <td class="td2"><s:textfield name="salesRep.dayOfActivate" class="form-control alloptions"/> <span
                            class="text-danger"><s:fielderror fieldName="salesRep.dayOfActivate"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Exclude from Ranking?"/></td>
                    <td colspan="6" class="b30" style="padding-left: 10px"><s:checkbox
                            name="salesRep.excludeRanking"/></td>
                </tr>
                </tbody>
            </table>
            <table class="table table-bordered mg0">
                <thead>
                <tr bgcolor="#F0F2F5">
                    <th><xms:localization text="Service"/></th>
                    <th><xms:localization text="Shipment Goal"/></th>
                    <th><xms:localization text="Payout (%Margin)"/></th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="salesRep.salesRepServices" status="saleRep">
                    <tr>
                        <td><s:property value="service.serviceName"/> <input type="hidden"
                                                                             name='salesRep.salesRepServices[<s:property value="#saleRep.index" />].serviceId'
                                                                             value='<s:property value="serviceId" />'/>
                            <input type="hidden"
                                   name='salesRep.salesRepServices[<s:property value="#saleRep.index" />].salesRepId'
                                   value='<s:property value="salesRepId" />'/> <input type="hidden"
                                                                                      name='salesRep.salesRepServices[<s:property value="#saleRep.index" />].service.serviceName'
                                                                                      value='<s:property value="service.serviceName" />'/>
                        </td>
                        <td><input type="text"
                                   name='salesRep.salesRepServices[<s:property value="#saleRep.index" />].goal'
                                   value='<s:property value="goal" />' class="form-control alloptions"/> <span
                                class="text-danger"> <s:fielderror>
                            <s:param value="%{'salesRep.salesRepServices[' + #saleRep.index +'].goal'}"/>
                        </s:fielderror></span></td>
                        <td><input type="text"
                                   name='salesRep.salesRepServices[<s:property value="#saleRep.index" />].payout'
                                   value='<s:property value="payout" />' class="form-control alloptions"/> <span
                                class="text-danger"><s:fielderror>
                            <s:param value="%{'salesRep.salesRepServices[' + #saleRep.index +'].payout'}"/>
                        </s:fielderror></span></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
</s:form>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'dd-mm-yyyy'
    });


</script>