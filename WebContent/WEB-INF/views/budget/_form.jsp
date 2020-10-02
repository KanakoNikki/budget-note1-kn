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

<select name="item">
<option value="1">食費</option>
<option value="2">生活費</option>
<option value="3">固定費</option>
<option value="4">衣類、美容</option>
<option value="5">娯楽</option>
<option value="6">その他</option>
</select>
<br /><br />

<label for="detail">内容</label><br />
<textarea name="content" rows="10" cols="50">${budget.detail}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">追加</button>