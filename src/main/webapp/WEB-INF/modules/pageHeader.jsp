<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
        Object user = request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${pageTitle}</title>
    <link href="<c:url value="/styles/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/styles/font-awesome.min.css"/>" rel="stylesheet" />
    <link href="<c:url value="/styles/main.css"/>" rel="stylesheet" />
</head>
<body>
<header>
    <div class="container pt-1">
        <div class="row header-top">
            <div class="col-4"><p>job4j_forum-0.1</p></div>
            <div class="col-4"></div>
            <div class="col-4"></div>
        </div>
        <div class="row header-bottom">
            <div class="col-12">
                <ul class="menu mb-0">
                    <% if (user == null) { %>
                    <li class="item-right"><a class="btn btn-outline-dark" href="<c:url value="/login"/>"><i class="fa fa-lock"></i> Вход</a></li>
                    <li class="item-right"><a class="btn btn-primary" href="<c:url value="/register"/>"><i class="fa fa-users"></i> Регистрация</a></li>
                    <% } else { %>
                    <li class="item-right"><a class="btn btn-outline-dark" href="<c:url value="/logout"/>"><i class="fa fa-unlock"></i> Выход</a></li>
                    <% } %>
                    <li class="item-left"><a class="btn btn-outline-dark" href="<c:url value="/home"/>"><i class="fa fa-home"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</header>