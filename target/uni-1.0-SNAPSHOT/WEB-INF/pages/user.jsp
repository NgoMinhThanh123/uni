
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/user" var="action" />       
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH TÀI KHOẢN</h1>

    <div class="d-flex justify-content-between">
        <div>
            <a href="<c:url value="/add_user" />" class="btn btn-info mt-1">Thêm tài khoản</a>
        </div>
        <div>
            <form class="d-flex" action="${action}">
                <input class="form-control me-2" name="kw" type="text" placeholder="Nhập từ khóa...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
    </div>
    <c:if test="${counter > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="<c:url value="/user" />">Tất cả</a></li>
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/user" var="pageUrl">
                        <c:param name="page" value="${i}"></c:param>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>               

    <table class="table table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th></th>
                <th>Email</th>
                <th>Chức vụ</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${user}" var="l">
                <tr>
                    <th>${l.id}</th>
                    <td>
                        <img src="${l.avatar}" width="100" />
                    </td>
                    <td>${l.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${l.role eq 'ROLE_GIAOVU'}">
                                Giáo vụ
                            </c:when>
                            <c:when test="${l.role eq 'ROLE_GIANGVIEN'}">
                                Giảng viên
                            </c:when>
                            <c:otherwise>
                                Sinh viên
                            </c:otherwise>
                        </c:choose>
                    </td>                 
                    <td>
                        <c:url value="/add_user/${l.id}" var="api" />
                        <a href="${api}" class="btn btn-info">Cập nhật</a>
                        <c:url value="/api/add_user/${l.id}" var="apiDel" />
                        <button class="btn btn-danger" onclick="deleteScoreColumn('${apiDel}', ${l.id})">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<script src="<c:url value="/js/main.js" />"></script>