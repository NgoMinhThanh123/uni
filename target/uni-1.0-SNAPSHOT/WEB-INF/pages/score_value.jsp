
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:url value="/score_value" var="action" />       
<section class="container">
    <h1 class="text-center text-success mt-1">DANH SÁCH CHI TIẾT ĐIỂM</h1>

    <div class="d-flex justify-content-between">
        <div>
            <a href="<c:url value="/add_score_value"/>" class="btn btn-info mt-1">Thêm Điểm</a>
        </div>
        <div>
            <form class="d-flex" action="${action}">
                <input class="form-control me-2" name="kw" type="text" placeholder="Nhập mã chi tiết điểm...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
    </div>
    <c:if test="${counter > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="<c:url value="/score_value" />">Tất cả</a></li>
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/score_value" var="pageUrl">
                        <c:param name="page" value="${i}"></c:param>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>                  

    <table class="table table-hover">
        <thead>
            <tr>
                <th>Cột điểm</th>
                <th>Điểm số</th>
                <th>Môn học</th>
                <th>Mã điểm</th>
                <th>Sinh viên</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${score_value}" var="l">
                <tr>   
                    <td>${l.scoreColumnId.name}</td>
                    <td>${l.value}</td>
                    <td>${l.scoreId.studentSubjectId.subjectId.name}</td>
                    <td>${l.scoreId.id}</td>
                    <td>${l.scoreId.studentSubjectId.studentId.id}</td>
                    <td>
                        <c:url value="/add_score_value/${l.id}" var="api" />
                        <a href="${api}" class="btn btn-info">Cập nhật</a>
                        <c:url value="/api/add_score_value/${l.id}" var="apiDel" />
                        <button class="btn btn-danger" onclick="deleteScoreColumn('${apiDel}', ${l.id})">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
<script src="<c:url value="/js/main.js" />"></script>