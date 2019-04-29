<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div class="col-xs-12 col-lg-8 col-lg-offset-2" style="margin-top: 10%">
    <div class="row">
        <div class="col-xs-12">
            <div class="row">
                <div class="col-xs-12 col-lg-6">
                    <div class="portlet box b14">
                        <div class="portlet-body b14">
                            <div class="panel-body pan">
                                <div class="form-body pal">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group mgb">
                                                <img src="images/LOGOiN.png" width="100%" style="margin-bottom: 10px;">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-lg-6">
                    <div class="portlet box">
                        <div class="portlet-header">
                            <div class="caption">
                                <xms:localization text="Login"/>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <form action="do_admin_login.ix" method="post">
                                <div class="form-group">
                                    <s:if test="hasActionErrors()">
                                        <tr>
                                            <td><span class="error-message"><s:actionerror/></span></td>
                                        </tr>
                                    </s:if>
                                </div>
                                <div class="form-group">
                                    <label><xms:localization text="Franchise#: "/></label><span
                                        class="required-field">*</span>
                                    <s:textfield name="franchiseCode" cssClass="form-control"/>
                                    <span class="error-message"><s:fielderror fieldName="franchiseCode"/></span>
                                </div>
                                <div class="form-group">
                                    <label><xms:localization text="User Name:"/></label><span
                                        class="required-field">*</span>
                                    <s:textfield name="userName" cssClass="form-control"/>
                                    <span class="error-message"><s:fielderror fieldName="userName"/></span>
                                </div>
                                <div class="form-group">
                                    <label><xms:localization text="Password:"/></label><span
                                        class="required-field">*</span>
                                    <s:password name="password" cssClass="form-control"/>
                                    <span class="error-message"><s:fielderror fieldName="password"/></span>
                                </div>
                                <button class="btn s37" type="submit">
                                    <xms:localization text="Login"/>
                                </button>
                                <button class="btn s37" type="reset">
                                    <xms:localization text="Reset"/>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
