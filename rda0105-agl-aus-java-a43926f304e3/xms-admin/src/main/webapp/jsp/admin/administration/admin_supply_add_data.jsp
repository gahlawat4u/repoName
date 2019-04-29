<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:form id="admin_supply_add" method="post" enctype="multipart/form-data">
    <div class="form-actions pal pdt10">
        <div class="row">
            <div class="col-lg-12 text-right">
                <div id="edit-supply" title="Add Supply">
                    <input type="hidden" name="isAddSupply" value="1"/>

                    <div class="form-group">
                        <p class="text-left">
                            <xms:localization text="Name :"/>
                            <span class="s30">*</span>
                        </p>
                        <s:textfield name="supply.supplyName" class="form-control" maxlength="25"/>
                    </div>
                    <div class="form-group">
                        <p class="text-left">
                            <xms:localization text="Carrier :"/>
                            <span class="s30">*</span>
                        </p>
                        <s:select list="serviceList" listKey="service.serviceId" listValue="service.serviceName"
                                  id="sel_service_list" cssClass="form-control" name="supply.carrierId"
                                  value="supply.carrierId"></s:select>
                    </div>
                    <div class="row">
                        <div class="col-lg-7">
                            <div class="form-group">
                                <p class="text-left">
                                    <xms:localization text="Image :"/>
                                </p>
                                <s:file name="fileUpload" id="fileUpload" cssClass="form-control w10" accept="image/*"/>
                                <div class="progress progress-striped active" style="display: none">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0"
                                         aria-valuemax="100" style="width: 0%;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5" id="img_supply">
                            <img src="<s:property value="supply.imageUrl"/>" width="100"/>
                            <s:hidden name="supply.image"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <p class="text-left">
                            <xms:localization text="Description :"/>
                        </p>
                        <s:textarea class="form-control" name="supply.description"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</s:form>

<script type="text/javascript">
    $('#fileUpload').fileupload({
        url: "admin_supply_edit.ix?reqType=json",
        done: function (e, data) {
            $('.progress-bar').css('width', '0%');
            $('.progress').hide();
            var result = data.result;
            if (result.errorCode == "SUCCESS") {
                $('#img_supply').html(result.content);
                messageDialog.html("<xms:localization text="Upload successfully." />");
                messageDialog.dialog("open");
            } else {
                alertDialog.html(result.errorMsg);
                alertDialog.dialog("open");
            }
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('.progress-bar').css('width', progress + '%');
        }
    });


</script>