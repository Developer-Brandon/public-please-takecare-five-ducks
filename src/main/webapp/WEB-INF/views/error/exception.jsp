<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <%@ page isErrorPage="true" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/error/exception.css">
</head>
<body class="ptcfd-body ptcfd-block-drag">
<div class="ptcfd-body__inner ptcfd-block-drag">
    <div class="error-section">
        <div class="error-section__inner">
            <div class="error-title-section">
                <div class="input-section__inner">
                    <!-- logo and wording -->
                    <div class="logo-and-wording">
                        <span class="logo">✹</span>
                        <h1 class="main-logo-wording">EXCEPTION</h1>
                        <p class="wording"><br><br>
                            <span class="pdcfd-required-symbol">*</span>Error Type <br> <%= exception.getClass().getName() %><br><br>
                            <span class="pdcfd-required-symbol">*</span>Error Message <br> <%= exception.getMessage() %></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
