
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ CHUYÊN NGÀNH</h1>
<c:url value="/add_major" var="action" />
<form:form modelAttribute="add_major" action="${action}" method="post">
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="id" id="id" placeholder="ID" name="id" />
        <label for="id">ID</label>
        <form:errors path="id" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên khoa" name="name" />
        <label for="name">Tên chuyên ngành</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="faculty" name="faculty" path="facultyId">          
            <c:forEach items="${faculty}" var="f">
                <c:choose>
                    <c:when test="${f.id == add_major.facultyId.id}"><option value="${f.id}" selected>${f.name}</option></c:when>
                    <c:otherwise><option value="${f.id}">${f.name}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="faculty" class="form-label">Khoa</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${add_major.id != null}">Cập nhật chuyên ngành</c:when>
                <c:otherwise>Thêm chuyên ngành</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

