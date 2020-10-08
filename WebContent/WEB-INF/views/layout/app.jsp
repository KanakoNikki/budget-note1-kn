<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>My Budget Note</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <c:if test="${sessionScope.login_users != null}">
                        <h1><a href="<c:url value='/budget/index' />">My budget Note</a></h1>&nbsp;&nbsp;&nbsp;
                    </c:if>
                </div>
                <div id="users_name">
                    <c:if test="${sessionScope.login_users != null}"><c:out value="${sessionScope.login_users.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </c:if>
                </div>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Kanako Niki 2020.
            </div>
        </div>
    </body>
</html>