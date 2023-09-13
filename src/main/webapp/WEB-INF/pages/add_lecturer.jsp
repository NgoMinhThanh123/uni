
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ GIẢNG VIÊN</h1>
<c:url value="/add_lecturer" var="action" />
<form:form modelAttribute="add_lecturer" action="${action}" method="post">
    <div class="form-floating mb-3 mt-3">
        <form:input required = "true" type="text" class="form-control" path="id" id="id" placeholder="ID" name="id" />
        <label for="id">ID</label>
        <form:errors path="id" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên khoa" name="name" />
        <label for="name">Tên giảng viên</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="birthday" id="birthday" placeholder="Ngày sinh" name="birthday" />
        <label for="birthday">Ngày sinh</label>
        <form:errors path="birthday" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="gender" name="gender" path="gender">
            <option value="1" ${gender ? 'selected' : ''}>Nam</option>
            <option value="0" ${gender ? 'selected' : ''}>Nữ</option>
        </form:select>
        <label for="gender">Giới tính</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="phone" id="phone" placeholder="Số điện thoại" name="phone" />
        <label for="name">Số điện thoại</label>
        <form:errors path="phone" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="email" class="form-control" path="email" id="email" placeholder="Email" name="email" />
        <label for="name">Email</label>
        <form:errors path="email" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Địa chỉ" name="address" />
        <label for="name">Địa chỉ</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="faculty" name="faculty" path="facultyId">

            <c:forEach items="${faculty}" var="f">
                <c:choose>
                    <c:when test="${f.id == add_lecturer.facultyId.id}"><option value="${f.id}" selected>${f.name}</option></c:when>
                    <c:otherwise><option value="${f.id}">${f.name}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="faculty" class="form-label">Khoa</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="user" name="user" path="userId">

            <c:forEach items="${user}" var="u">
                <option value="${u.id}" selected>${u.id}</option>
            </c:forEach>
        </form:select>
        <label for="user" class="form-label">Mã tài khoản</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            Thêm giảng viên
        </button>
    </div>
</form:form>

