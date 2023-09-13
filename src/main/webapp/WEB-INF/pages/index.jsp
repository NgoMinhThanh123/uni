
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<se:authorize access="hasRole('ROLE_GIAOVU')">
    <div class="center-container">
        <h1 class="mt-10" style="text-align: center;">CHÀO MỪNG ĐẾN TRANG QUẢN LÝ TRƯỜNG ĐẠI HỌC MỞ TP.HCM</h1>
    </div>
</se:authorize>