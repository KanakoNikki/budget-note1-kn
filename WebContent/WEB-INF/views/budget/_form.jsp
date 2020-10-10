<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="budget_date">日付</label><br />
<input type="date" name="budget_date" value="<fmt:formatDate value='${budget.budget_date}' pattern='yyyy-MM-dd' />" />
<br /><br />


<label for="item_id">項目</label><br />
    <select name="item_id">
        <c:forEach var="items" items="${itemList}">
            <c:choose>
                <c:when test="${budget.getItem().getItem_Id() != null && budget.getItem().getItem_Id() == items.getItem_Id()}">
                    <option value="${budget.getItem().getItem_Id()}" selected>${items.getItem_name()}</option>
                </c:when>
                <c:otherwise>
                    <option value="${items.getItem_Id()}">${items.getItem_name()}</option>
                </c:otherwise>
            </c:choose>
    </c:forEach>
</select>
<br /><br />

<label for="amount">金額</label><br />
<input type="number" name = "amount" value="${budget.amount}"/>
<br /><br />

<label for="detail">内容</label><br />
<textarea name="detail" rows="10" cols="50">${budget.detail}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">追加</button>
