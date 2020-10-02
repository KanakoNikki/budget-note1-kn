<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
         <h1><c:out value="${sessionScope.login_users.name}" />さんの履歴</h1>
        <table id="login_users.name">
            <tbody>
                <tr>
<!--                    <th class="user_name">氏名</th> -->
                    <th class="budget_date">日付</th>
                    <th class="budget_item">項目</th>
                    <th class="budget_amount">金額</th>
                    <th class="budget_action">詳細</th>
                </tr>
                <tr>
 <!--              <td><c:out value="${sessionScope.login_users.name}" /></td> -->
                   <td><c:out value= "notyet"/></td>
                   <td><c:out value= "notyet"/></td>
                   <td><c:out value= "notyet"/></td>
                   <td><c:out value= "notyet"/></td>
                </tr>
            </tbody>
        </table>
    </c:param>
</c:import>