<%@ page import="java.net.URLDecoder" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
    <%@ page session="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="../page_header.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/error/error401.css">
</head>
<body class="ptcfd-body ptcfd-block-drag">
<div class="ptcfd-body__inner ptcfd-block-drag">
    <div class="error-section">
        <div class="error-section__inner">
            <div class="error-title-section">
                <div class="input-section__inner">
                    <!-- logo and wording -->
                    <div class="logo-and-wording">
                        <span class="logo">✋🏼</span>
                        <h1 class="main-logo-wording">401<br>ERROR</h1>
                        <p class="wording">
                            접근이 허용되지 않습니다.<br><br><br>
                            Error Type : ${exception.getClass().getName()}<br>
                            Error Message : ${exception.getMessage()}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
