

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ ĐIỂM</h1>

<c:url value="/add_score" var="action" />
<form:form modelAttribute="add_score" action="${action}" method="post" >
    <form:hidden path="id" />
    <div class="form-floating">
        <form:select class="form-select" id="semester" name="semester" path="semesterId">          
            <c:forEach items="${semester}" var="f">
                <c:choose>
                    <c:when test="${f.id == add_score.semesterId.id}"><option value="${f.id}" selected>${f.id}</option></c:when>
                    <c:otherwise><option value="${f.id}">${f.id}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="semester" class="form-label">Học kì</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="student_subject" name="student_subject" path="studentSubjectId">
            <c:forEach items="${student_subject}" var="f">
                <c:choose>
                    <c:when test="${f.id == add_score.studentSubjectId.id}"><option value="${f.id}" selected>${f.id}</option></c:when>
                    <c:otherwise><option value="${f.id}">${f.id}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="student_subject" class="form-label">Mã sinh viên - Môn học</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${add_score.id != null}">Cập nhật điểm</c:when>
                <c:otherwise>Thêm điểm</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
