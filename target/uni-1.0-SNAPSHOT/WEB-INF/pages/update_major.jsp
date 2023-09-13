
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ CHUYÊN NGÀNH</h1>
<c:url value="/update_major" var="action" />
<form:form modelAttribute="update_major" action="${action}" method="post">
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input disabled="true" type="text" class="form-control" path="id" id="id" placeholder="ID" name="id" />
        <label for="id">ID</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên khoa" name="name" />
        <label for="name">Tên chuyên ngành</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="faculty" name="faculty" path="facultyId">            
            <c:forEach items="${faculty}" var="f">
                <c:choose>
                    <c:when test="${f.id == update_major.facultyId.id}"><option value="${f.id}" selected>${f.name}</option></c:when>
                    <c:otherwise><option value="${f.id}">${f.name}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="faculty" class="form-label">Khoa</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">Cập nhật chuyên ngành</button>
    </div>
</form:form>

