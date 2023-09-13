
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ KHOA</h1>
<c:url value="/add_faculty" var="action" />
<form:form modelAttribute="add_faculty" action="${action}" method="post">
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="id" id="id" placeholder="ID" name="id" />
        <label for="id">ID</label>
        <form:errors path="id" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên khoa" name="name" />
        <label for="name">Tên khoa</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            Thêm khoa
        </button>
    </div>
</form:form>

