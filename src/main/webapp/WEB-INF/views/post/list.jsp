<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Форум" />
<%@ include file="../../modules/pageHeader.jsp" %>

<section id="page">
    <div class="container pt-3">
        <div class="row">
            <div class="col-sm-12 p-0 card">
                <div class="card-header"><h2 class="card-title text-center">Форум</h2></div>
                <div class="card-body">
                    <ul class="menu">
                        <li class="item-left"><a class="btn btn-primary" href="<c:url value="/post/add"/>"><i class="fa fa-plus"></i> Добавить тему</a></li>
                    </ul>
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="big-col">Тема</th>
                            <th>Cоздана</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="post" items="${posts}">
                            <tr>
                                <td><a href="<c:url value="/post/${post.id}"/>">${post.name}</a></td>
                                <td>${post.created}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer"></div>
            </div>
        </div>
    </div>
</section>

<%@ include file="../../modules/pageFooter.jsp" %>