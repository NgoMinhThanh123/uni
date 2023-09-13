
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ SINH VIÊN</h1>
<c:url value="/add_student" var="action" />
<form:form modelAttribute="add_student" action="${action}" method="post">
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="id" id="id" placeholder="MSSV" name="id" />
        <label for="id">MSSV</label>
        <form:errors path="id" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Tên sinh viên" name="name" />
        <label for="name">Tên sinh viên</label>
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
        <label for="phone">Số điện thoại</label>
        <form:errors path="phone" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Địa chỉ" name="address" />
        <label for="address">Địa chỉ</label>
        <form:errors path="address" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="user" name="user" path="userId">

            <c:forEach items="${user}" var="u">
                <option value="${u.id}" selected>${u.id}</option>
            </c:forEach>
        </form:select>
        <label for="user" class="form-label">Mã tài khoản</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="classes" name="classes" path="classesId">

            <c:forEach items="${classes}" var="c">
                <option value="${c.id}" selected>${c.id}</option>
            </c:forEach>
        </form:select>
        <label for="classes" class="form-label">Lớp</label>
    </div>
     <div class="form-floating">
        <form:select class="form-select" id="faculty" name="faculty" path="facultyId">          
            <c:forEach items="${faculty}" var="f">
                <c:choose>
                    <c:when test="${f.id == add_student.facultyId.id}"><option value="${f.id}" selected>${f.name}</option></c:when>
                    <c:otherwise><option value="${f.id}">${f.name}</option></c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="faculty" class="form-label">Khoa</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="major" name="major" path="majorId">

            <c:forEach items="${major}" var="m">
                <option value="${m.id}" selected>${m.name}</option>
            </c:forEach>
        </form:select>
        <label for="major" class="form-label">Chuyên ngành</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info">
            Thêm sinh viên
        </button>
    </div>
</form:form>

