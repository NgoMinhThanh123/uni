
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ HỌC KÌ</h1>
<c:url value="/update_semester" var="action" />
<form:form modelAttribute="update_semester" action="${action}" method="post">
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input disabled="true" type="text" class="form-control" path="id" id="id" placeholder="id" name="id" />
        <label for="id">ID</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Học kì" name="name" />
        <label for="schoolYear">Học kì</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="schoolYear" id="schoolYear" placeholder="Năm học" name="schoolYear" />
        <label for="schoolYear">Năm học</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="fromDate" id="fromDate" placeholder="Thời gian bắt đầu" name="fromDate" />
        <label for="fromDate">Thời gian bắt đầu</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="toDate" id="toDate" placeholder="Thời gian kết thúc" name="toDate" />
        <label for="toDate">Thời gian kết thúc</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">Cập nhật học kỳ</button>
    </div>
</form:form>

