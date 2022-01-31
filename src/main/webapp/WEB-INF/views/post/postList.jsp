<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Тема"/>
<%@ include file="../../modules/pageHeader.jsp" %>

<section id="page">
    <div class="container pt-3">
        <div class="row">
            <ul class="menu">
                <li class="item-left"><a class="btn btn-primary"
                                         href="<c:url value="/topic/${posts.get(0).id}/add-post"/>"><i
                        class="fa fa-plus"></i> Добавить сообщение</a></li>
            </ul>
        </div>
        <c:forEach var="post" items="${posts}">
            <div class="row p-0 mb-1 card">
                <div class="card-header pt-0 pb-0">
                    <span class="col-4 pull-left border-end p-1"><p class="mb-0">${post.created}</p></span>
                    <span class="col-4 pull-left border-end p-1"><h5
                            class="mb-0 text-center">${post.caption}</h5></span>
                    <span class="col-4 pull-left p-1">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user and sessionScope.user.id == post.author.id}">
                                <ul class="menu">
                                    <li class="item-left"><a href="<c:url value="/post/${post.id}"/>"><i
                                            class="fa fa-edit"></i></a></li>
                                    <li class="item-left"><a href="<c:url value="/post/${post.id}/delete"/>"><i
                                            class="fa fa-times"></i></a></li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <p class="mb-0">Автор: ${not empty post.author ? post.author.login : 'anonymous'}</p>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>
                <div class="card-body">
                    <p>${post.description}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</section>

<%@ include file="../../modules/pageFooter.jsp" %>