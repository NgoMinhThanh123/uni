<%-- 
    Document   : header
    Created on : Jul 22, 2023, 1:08:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<c:url value="/" var="action" />
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">TRƯỜNG ĐẠI HỌC MỞ TP.HCM</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <se:authorize access="hasRole('ROLE_GIAOVU')">
                <li class="nav-item">
                    <a class="nav-link" href="${action}">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/faculty" />">Khoa</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/major" />"> Chuyên ngành</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/lecturer" />"> Giảng viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/student" />">Sinh viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/user" />">Tài Khoản</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/classes" />">Lớp học</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/subject" />">Môn học</a>
                </li>    
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/semester" />">Học kỳ</a>
                </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Quản lý điểm
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="<c:url value="/score" />">Điểm</a>
                            <a class="dropdown-item" href="<c:url value="/score_value" /> ">Chi tiết điểm</a>
                            <a class="dropdown-item" href="<c:url value="/score_column" /> ">Cột điểm</a>
                        </div>
                    </li>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="<c:url value="/score" />">Điểm</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="<c:url value="/score_value" />">Chi tiết điểm</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="<c:url value="/score_column" />">Cột điểm</a>--%>
<%--                </li>--%>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/student_subject" />">SV-MH</a>
                    </li>
                </se:authorize>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <se:authorize access="hasRole('ROLE_GIAOVU')">
                            <li class="nav-item">
                                <a style="color: blue;" class="nav-link" href="<c:url value="/" />">Xin chào ${pageContext.request.userPrincipal.name}!</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/logout" />">Đăng xuất</a>
                            </li>
                        </se:authorize>
                    </c:when>

                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>               
        </div>
    </div>
</nav>
