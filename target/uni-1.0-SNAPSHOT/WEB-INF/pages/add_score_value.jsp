

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ CỘT ĐIỂM</h1>

<c:url value="/add_score_value" var="action" />
<form:form modelAttribute="add_score_value" action="${action}" method="post" >
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="value" id="value" placeholder="Điểm số" name="value" />
        <label for="value">Điểm số</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="score" name="score" path="scoreId">          
            <c:forEach items="${score}" var="s">
                <c:choose>
                    <c:when test="${s.id == add_score_value.scoreId.id}"><option value="${s.id}" selected>${s.id}</option></c:when>
                    <c:otherwise><option value="${s.id}">${s.id}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="score" class="form-label">Mã điểm</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="score_column" name="score_column" path="scoreColumnId">          
            <c:forEach items="${score_column}" var="f">
                <c:choose>
                    <c:when test="${f.id == add_score_value.scoreColumnId.id}"><option value="${f.id}" selected>${f.name}</option></c:when>
                    <c:otherwise><option value="${f.id}">${f.name}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="score_column" class="form-label">Tên cột điểm</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${add_score_value.id != null}">Cập nhật cột điểm</c:when>
                <c:otherwise>Thêm cột điểm</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
