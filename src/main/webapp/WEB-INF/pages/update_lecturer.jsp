
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">CẬP NHẬT GIẢNG VIÊN</h1>
<c:url value="/update_lecturer" var="action" />
<form:form modelAttribute="update_lecturer" action="${action}" method="post">
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input disabled="true" type="text" class="form-control" path="id" id="id" placeholder="ID" name="id" />
        <label for="id">ID</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên khoa" name="name" />
        <label for="name">Tên giảng viên</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="birthday" id="birthday" placeholder="Ngày sinh" name="birthday" />
        <label for="birthday">Ngày sinh</label>
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
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="email" class="form-control" path="email" id="email" placeholder="Email" name="email" />
        <label for="name">Email</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Địa chỉ" name="address" />
        <label for="name">Địa chỉ</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="faculty" name="faculty" path="facultyId">

            <c:forEach items="${faculty}" var="f">
                <c:choose>
                    <c:when test="${f.id == update_lecturer.facultyId.id}"><option value="${f.id}" selected>${f.name}</option></c:when>
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
            Cập nhật giảng viên
        </button>
    </div>
</form:form>


