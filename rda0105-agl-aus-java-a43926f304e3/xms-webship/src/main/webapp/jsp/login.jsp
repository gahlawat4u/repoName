<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ taglib prefix="s" uri="/xms-struts-tags" %>
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
                                                    <img src="<%=WebUtils.getContextPathURL(request)%>images/LOGOiN.png"
                                                         width="450" style="margin-bottom: 10px;"/>
                                                </div>
                                                <div class="form-group mgb">
                                                    <%-- <table class="s36">
                                                        <tr>
                                                            <td><i class="fa fa-check s60 b17"></i><span class=" b16"><xms:localization text="We'll call you back in less than 15 mins." /></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="fa fa-check s60 b17"></i><span class=" b16"><xms:localization text="On average our customers save 25% on their annual shipping costs." /></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="fa fa-check s60 b17"></i><span class=" b16"><xms:localization text="We are DHL's biggest global partner." /></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td><i class="fa fa-check s60 b17"></i><span class=" b16"><xms:localization text="One shipment, one invoice - we take care of the detail." /></span></td>
                                                        </tr>
                                                    </table> --%>
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
                                    <xms:localization text="LOG IN"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="">
                                <div class="panel-body pan">
                                    <s:form method="post" action="login">
                                        <div class="form-body pal">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="col-lg-12">
                                                        <div class="form-group">
                                                            <div class="input-icon left">
                                                                <i class="fa fa-user" style="margin-top: 13px;"></i>
                                                                <s:textfield name="user.name"
                                                                             label='<xms:localization text="Username" />'
                                                                             size="20" placeholder="USER NAME"
                                                                             class="form-control b19"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="input-icon left">
                                                                <i class="fa fa-lock" style="margin-top: 13px;"></i>
                                                                <s:password name="user.password"
                                                                            label='<xms:localization text="Password" />'
                                                                            size="20" placeholder="PASSWORD"
                                                                            class="form-control b19"/>
                                                            </div>
                                                            <ul style="margin-left: 5px; margin-top: 10px;">
                                                                <li style="color: #F00;"><s:fielderror/></li>
                                                                <li style="color: #F00;"><s:actionerror/></li>
                                                            </ul>
                                                            <p class="text-right">
                                                                <xms:localization text="Forgot your password?"/>
                                                                <s:a action="reset_password" class="s543">
                                                                    <xms:localization text="Click here to reset it."/>
                                                                </s:a>
                                                            </p>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group">
                                                                    <%-- <ul style="font-size: 12px; line-height: 20px; padding: 0px 10px 0px 30px; font-family: 'Open Sans', sans-serif;">
                                                                        <li class="s1 s2"><xms:localization text="Getting Started" /></li>
                                                                        <li class="s2"><xms:localization text="- You are just a click away to ship, track, import and more!" /></li>
                                                                        <li class="s1 s2"><xms:localization text="Fast and Easy Online Shipping" /></li>
                                                                        <li class="s2"><xms:localization text="- It’s easy to register – then you are ready to ship online with just a few clicks." /></li>
                                                                        <li class="s1 s2"><xms:localization text="Easy Tracking" /></li>
                                                                        <li class="s2"><xms:localization text="- Quickly track your shipments." /></li>
                                                                        <li class="s1 s2"><xms:localization text="One Single Solution for Everything" /></li>
                                                                        <li class="s2"><xms:localization text="- No more multiple passwords and login credentials - quick and easy access to the full suite of Agl solutions with one single login." /></li>
                                                                    </ul> --%>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="pal col-lg-12">
                                                                <label class="fw0 text-left s544"> <input
                                                                        type="checkbox" tabindex="5"> <span
                                                                        class="s545"><xms:localization
                                                                        text="Remember Password"/></span>
                                                                </label> <input type="submit" value="SIGN IN">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </s:form>
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