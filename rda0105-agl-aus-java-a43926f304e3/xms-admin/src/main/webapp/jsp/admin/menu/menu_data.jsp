<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="col-lg-6">
    <table class="table table-bordered">
        <tr>
            <td>
                <div class="easy-tree">
                    <ul>
                        <s:iterator value="menuModel.menuChilds" var="menu">
                            <li><s:property value="menuName"/></li>
                            <s:if test="%{menuChilds != null}">
                                <ul>
                                    <s:iterator value="menuChilds">
                                        <li onclick="viewMenuDetail(<s:property value='menuId'/>)"><s:property
                                                value="menuName"/></li>
                                    </s:iterator>
                                </ul>
                            </s:if>
                        </s:iterator>

                    </ul>
                </div>
            </td>
        </tr>
    </table>
</div>
<div class="col-lg-6">
    <s:form id="form_menu_edit">
        <div id="div_menu_detail">
            <div class="remember_me">
                <ul style="margin-left: 5px; margin-top: 10px;list-style: none;">
                    <li style="color: #F00;"><s:fielderror/></li>
                    <li style="color: #F00;"><s:actionerror/></li>
                </ul>
            </div>
            <table class="table" style="font-size: 11px;">
                <tr>
                    <td class="td1">ID :</td>
                    <td class="td2" colspan="2"><s:textfield name="menuModel.menuId" class="form-control alloptions"
                                                             readonly="true"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Label"/> :</td>
                    <td class="td2" colspan="2"><s:textfield name="menuModel.menuName"
                                                             class="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Hidden"/>? :</td>
                    <td class="td2" colspan="2"><s:checkbox name="menuModel.hidden"></s:checkbox></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Admin Level"/> :</td>
                    <td class="td2" colspan="2"><s:textfield name="menuModel.userLevelId"
                                                             class="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Other Admin Levels(comma-separated)"/>? :</td>
                    <td class="td2" colspan="2"><s:textfield name="menuModel.otherLevel"
                                                             class="form-control alloptions"></s:textfield></td>
                </tr>
            </table>
        </div>

        <div class="form-actions pal pdt10">
            <div class="row">
                <div class="col-lg-12 text-right">
                    <button id="localize-link" class="btn s37" type="button" onclick="showLocalize()">Localize Menu
                    </button>
                    <button id="" class="btn s37" type="button" onclick="saveMenu()">Save</button>
                    <button id="show-user-level-link" class="btn s37" type="button" onclick="showUserLevel()">Show User
                        Level
                    </button>
                    <button id="" class="btn s37" type="submit">Apply Menu</button>
                </div>
            </div>
        </div>
    </s:form>
</div>