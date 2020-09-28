<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        こんにちは！<c:out value="${sessionScope.users.name}" />さん
        <table id="users_name">
            <tbody>
                <tr>
                    <th>氏名</th>
                </tr>
                <tr>
                   <td><c:out value="${users.name}" /></td>
                  </tr>
            </tbody>
        </table>
    </c:param>
</c:import>