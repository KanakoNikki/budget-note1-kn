<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>${users.name}さん、ようこそ</h2>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>氏名</th>
                </tr>
                <tr>
                    <td><c:out value="${users.name}" /></td>
                  </tr>
            </tbody>
        </table>

  <!--       <p><a href="<c:url value='/employees/new' />">新規従業員の登録</a></p> -->

    </c:param>
</c:import>