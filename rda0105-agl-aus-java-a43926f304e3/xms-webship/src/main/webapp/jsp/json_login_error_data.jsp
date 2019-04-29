<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="col-lg-12 text-center tab-content responsive">
    <h1>ACCESS DENIED!</h1>

    <h2>
        <%
            String errorId = request.getParameter("errorId");
            if ("1".equalsIgnoreCase(errorId)) {
        %>
        Please login to admin
        <%
        } else {
        %>
        Session timed out, please login to Web Freight
        <%} %>
    </h2>
</div>
