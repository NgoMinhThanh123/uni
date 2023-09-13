

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ KHOA</h1>
<c:url value="/update_faculty" var="action" />
<form:form modelAttribute="update_faculty" action="${action}" method="post">
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input disabled="true" type="text" class="form-control" path="id" id="id" placeholder="ID" name="id" />
        <label for="id">ID</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên khoa" name="name" />
        <label for="name">Tên khoa</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">Cập nhật khoa</button>
    </div>
</form:form>

