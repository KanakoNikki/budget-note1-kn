<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
         </c:if>
         <h1><c:out value="${sessionScope.login_users.name}" />さんの履歴</h1>
         <h3>合計金額:　<c:out value="${budgetSum}" />円</h3>
        <table id="login_users.name">
            <tbody>
                <tr>
                    <th class="budget_date">日付</th>
                    <th class="budget_item">項目</th>
                    <th class="budget_amount">金額</th>
                    <th class="budget_action">詳細</th>
                </tr>
                <c:forEach var="budget" items="${budget}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="budget_date"><fmt:formatDate value='${budget.budget_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="budget_item"><c:out value="${budget.getItem().getItem_name()}" /></td>
                        <td class="budget_amount">${budget.amount}円</td>
                        <td class="budget_action"><a href="<c:url value='/budget/show?id=${budget.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="<c:url value='/budget/new' />">新規作成</a>
    </c:param>
</c:import>