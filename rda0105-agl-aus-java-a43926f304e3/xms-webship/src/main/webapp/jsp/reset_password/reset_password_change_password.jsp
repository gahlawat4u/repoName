<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="page-content b15">
    <div id="tab-general">
        <div class="row mbl" style="margin-top: 16.250em;">
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="portlet box b14">
                            <div class="portlet-body b14">
                                <div class="panel-body pan">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="form-group mgb">
                                                    <img src="images/LOGOiN.png" width="450"
                                                         style="margin-bottom: 10px;"/>
                                                </div>
                                                <div class="form-group mgb">
                                                    <ul style="font-size: 12px; line-height: 20px; padding: 0px 10px 0px 30px; font-family: 'Open Sans', sans-serif;">
                                                        <li class="s1 s2"><i class="fa fa-check s60 b17"></i><span
                                                                class=" b16"><xms:localization
                                                                text="Getting Started"/></span></li>
                                                        <li class="s2" style="margin-left: 42px; color: white;">
                                                            <xms:localization
                                                                    text=" You are just a click away to ship, track, import and more!"/></li>
                                                        <li class="s1 s2"><i class="fa fa-check s60 b17"></i><span
                                                                class=" b16"><xms:localization
                                                                text="Easy Tracking"/></span></li>
                                                        <li class="s2" style="margin-left: 42px; color: white;">
                                                            <xms:localization
                                                                    text=" Quickly track your shipments."/></li>
                                                        <li class="s1 s2"><i class="fa fa-check s60 b17"></i><span
                                                                class=" b16"><xms:localization
                                                                text="One Single Solution for Everything"/></span></li>
                                                        <li class="s2" style="margin-left: 42px; color: white;">
                                                            <xms:localization
                                                                    text=" No more multiple passwords and login credentials - quick and easy access to the full suite of AGL solutions with one single login."/></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6" style="top: 15px;">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption b20">
                                    <xms:localization text="RESET"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="">
                                <div class="panel-body pan">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="col-lg-12">
                                                    <s:if test="changePasswordStatus != null">
                                                        <i style="color: green;"><s:property
                                                                value="changePasswordStatus"/> </i>

                                                        <div class="row">
                                                            <div class="pal col-lg-12">
                                                                <s:a action="login.ix" cssClass="btn">
                                                                    <xms:localization text="Back to Login"/>
                                                                </s:a>
                                                            </div>
                                                        </div>
                                                    </s:if>
                                                    <s:else>
                                                        <s:if test="validStatus">
                                                            <s:form method="post" id="change_password"
                                                                    action="change_password">
                                                                <s:hidden name="validStatus"/>
                                                                <s:hidden name="webshipModel.webshipId"/>
                                                                <p><xms:localization
                                                                        text="Password should contains minimum 8 characters with at least one alphabet and one number."/></p>

                                                                <div class="form-group">
                                                                    <div class="input-icon left">
                                                                        <i class="fa fa-code "
                                                                           style="margin-top: 13px;"></i>
                                                                        <s:password name="webshipModel.password"
                                                                                    placeholder="New Password"
                                                                                    cssClass="form-control b19"/>
                                                                    </div>
                                                                    <s:hidden name="user.email"></s:hidden>
                                                                </div>
                                                                <div class="form-group">
                                                                    <div class="input-icon left">
                                                                        <i class="fa fa-code "
                                                                           style="margin-top: 13px;"></i>
                                                                        <s:password name="passwordConfim"
                                                                                    placeholder="Confim Password"
                                                                                    cssClass="form-control b19"/>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <ul style="margin-left: 5px; margin-top: 10px;">
                                                                        <li style="color: #F00;"><s:fielderror/></li>
                                                                        <li style="color: #F00;"><s:actionerror/></li>
                                                                    </ul>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="pal col-lg-12">
                                                                        <s:a action="login.ix" cssClass="btn">
                                                                            <xms:localization text="Login"/>
                                                                        </s:a>
                                                                        <input type="submit" name="commit"
                                                                               value='<xms:localization text="Change Password"/>'>
                                                                    </div>
                                                                </div>
                                                            </s:form>
                                                        </s:if>
                                                        <s:else>
                                                            <s:form method="post">
                                                                <div class="form-group">
                                                                    <div class="input-icon left">
                                                                        <i class="fa fa-code "
                                                                           style="margin-top: 13px;"></i>
                                                                        <s:textfield name="codeResetPassword"
                                                                                     placeholder="Input the code we sent you in the email"
                                                                                     cssClass="form-control b19"/>
                                                                        <span style="color: red"><s:property
                                                                                value="fieldErrors.get('codeResetPassword').get(0)"/></span>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <ul style="margin-left: 5px; margin-top: 10px;">
                                                                        <li style="color: #F00;"><s:actionerror/></li>
                                                                    </ul>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="pal col-lg-12">
                                                                        <s:a action="login.ix" cssClass="btn">
                                                                            <xms:localization text="Back to Login"/>
                                                                        </s:a>
                                                                        <input type="submit" name="commit"
                                                                               value='<xms:localization text="Confirm Code"/>'>
                                                                    </div>
                                                                </div>
                                                            </s:form>
                                                        </s:else>
                                                    </s:else>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-12 text-center">
                <br/> <br/> <br/> <br/> <br/>
            </div>
        </div>
    </div>
</div>