<%@page import="com.gms.xms.common.constants.ErrorCode" %>
<script>
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/xms-webship/json_country_list.ix?reqType=json',
        complete: function () {
        },
        success: function (data) {
            alert(data.errorMsg);
            alert(data.errorType);
            if (data.errorCode == '<%=ErrorCode.SUCCESS%>') {
            } else {
                alert(data.errorMsg);
            }
            $("#jsoncontent").html(data.content);
        }
    });
</script>
<div id="jsoncontent">

</div>