

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ CỘT ĐIỂM</h1>

<c:url value="/add_score_column" var="action" />
<form:form modelAttribute="add_score_column" action="${action}" method="post" >
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Năm học" name="name" />
        <label for="name">Tên cột điểm</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${add_score_column.id != null}">Cập nhật cột điểm</c:when>
                <c:otherwise>Thêm cột điểm</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
