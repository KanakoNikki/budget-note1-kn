<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
         <h1>こんにちは！<c:out value="${sessionScope.login_users.name}" />さん</h1>
        <table id="login_users.name">
            <tbody>
                <tr>
                    <th>氏名</th>
                </tr>
                <tr>
                   <td><c:out value="${sessionScope.login_users.name}" /></td>
                </tr>

            </tbody>
        </table>
    </c:param>
</c:import>