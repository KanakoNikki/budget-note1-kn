<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
           <c:choose>
            <c:when test="${budget != null}">
                <h2>詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${budget.budget_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>項目</th>
                            <td><c:out value="${budget.getItem().getItem_name()}" /></td>
                        </tr>
                         <tr>
                            <th>金額</th>
                            <td>
                                <c:out value="${budget.amount}" />
                            </td>
                        </tr>
                        <tr>
                            <th>詳細</th>
                            <td>
                                <pre><c:out value="${budget.detail}" /></pre>
                            </td>
                        </tr>
                        <tr>
                       </tbody>
                </table>
                <c:if test="${sessionScope.login_users.getId() == budget.getUsers().getId()}">
                    <p><a href="<c:url value="/budget/edit?id=${budget.id}" />">この詳細を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/budget/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>