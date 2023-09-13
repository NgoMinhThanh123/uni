

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ CỘT ĐIỂM</h1>

<c:url value="/add_student_subject" var="action" />
<form:form modelAttribute="add_student_subject" action="${action}" method="post" >
  <form:hidden path="id" />
  <div class="form-floating">
    <form:select class="form-select" id="student" name="student" path="studentId">
      <c:forEach items="${student}" var="s">
        <c:choose>
          <c:when test="${s.id == add_student_subject.studentId.id}"><option value="${s.id}" selected>${s.id}-${s.name}</option></c:when>
          <c:otherwise><option value="${s.id}">${s.id}-${s.name}</option></c:otherwise>
        </c:choose>
      </c:forEach>
    </form:select>
    <label for="student" class="form-label">Sinh viên</label>
  </div>
  <div class="form-floating">
    <form:select class="form-select" id="subject" name="subject" path="subjectId">
      <c:forEach items="${subject}" var="f">
        <c:choose>
          <c:when test="${f.id == add_student_subject.subjectId.id}"><option value="${f.id}" selected>${f.name}</option></c:when>
          <c:otherwise><option value="${f.id}">${f.name}</option></c:otherwise>
        </c:choose>
      </c:forEach>
    </form:select>
    <label for="subject" class="form-label">Môn học</label>
  </div>
  <div class="form-floating mb-3 mt-3">
    <button type="submit" class="btn btn-info">
      <c:choose>
        <c:when test="${add_student_subject.id != null}">Cập nhật cột điểm</c:when>
        <c:otherwise>Thêm cột điểm</c:otherwise>
      </c:choose>
    </button>
  </div>
</form:form>
