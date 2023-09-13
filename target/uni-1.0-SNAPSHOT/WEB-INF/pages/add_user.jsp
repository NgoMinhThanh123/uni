

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ TÀI KHOẢN</h1>

<c:url value="/add_user" var="action" />
<form:form modelAttribute="add_user" action="${action}" method="post" enctype="multipart/form-data">
    <form:hidden path="id" />
    <form:hidden path="avatar" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="username" id="username" placeholder="Tài khoản" name="username" />
        <label for="username">Tài khoản</label>
         <form:errors path="username" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="password" class="form-control" path="password" id="password" placeholder="Password" name="password" />
        <label for="name">Mật khẩu</label>    
        <form:errors path="password" element="div" cssClass="text-danger" />
    </div>
        <div class="form-floating mb-3 mt-3">
        <form:input type="email" class="form-control" path="email" id="email" placeholder="Email" name="email" />
        <label for="name">Email</label>
         <form:errors path="email" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating" id="role" name="role" path="role">
        <form:select class="form-select" path="role">
            <form:option value="ROLE_GIAOVU" label="Giáo vụ" />
            <form:option value="ROLE_GIANGVIEN" label="Giảng viên" />
             <form:option value="ROLE_SINHVIEN" label="Sinh viên" />
        </form:select>
        <label for="role" class="form-label">Quyền</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" />
        <label for="file">Ảnh của bạn</label>
         <c:if test="${add_user.avatar != null}">
             <img src="${add_user.avatar}" width="100"/>
         </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            <c:choose>
                <c:when test="${add_user.id != null}">Cập nhật tài khoản</c:when>
                <c:otherwise>Thêm tài khoản</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
