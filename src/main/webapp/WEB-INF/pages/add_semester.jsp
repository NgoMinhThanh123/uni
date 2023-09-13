
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ HỌC KỲ</h1>
<c:url value="/add_semester" var="action" />
<form:form modelAttribute="add_semester" action="${action}" method="post">
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="id" id="id" placeholder="MSSV" name="id" />
        <label for="id">ID</label>
        <form:errors path="id" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Học kì" name="name" />
        <label for="schoolYear">Học kì</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="schoolYear" id="schoolYear" placeholder="Năm học" name="schoolYear" />
        <label for="schoolYear">Năm học</label>
        <form:errors path="schoolYear" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <input type="date" class="form-control" id="fromDate" name="fromDate" path="fromDate" />
        <label for="fromDate">Thời gian bắt đầu</label>
        <form:errors path="fromDate" element="div" cssClass="text-danger" />
    </div>
        <div class="form-floating mb-3 mt-3">
        <input type="date" class="form-control" id="toDate" name="toDate" path="toDate" />
        <label for="toDate">Thời gian kết thúc</label>
        <form:errors path="toDate" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">Thêm học kỳ</button>
    </div>
</form:form>

