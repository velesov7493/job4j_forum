<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Автонарушения" />
<%@ include file="../../modules/pageHeader.jsp" %>

<section id="page">
    <div class="container pt-3">
        <div class="row">
            <div class="col-sm-12 p-0 card">
                <div class="card-header">
                    <h3 class="text-center">${empty post ? 'Новое сообщение' : 'Редактирование сообщения'}</h3>
                </div>
                <div class="card-body">
                    <form method="post" action="<c:url value="/post/save"/>">
                        <input type="hidden" name="id" value="${empty post ? 0 : post.id}"/>
                        <div class="mb-3">
                            <label for="aName">Заголовок:</label>
                            <input id="aName" class="form-control" name="name" type="text" value="${empty post ? '' : post.name}"/>
                        </div>
                        <div class="mb-3">
                            <label for="aDescription">Текст:</label>
                            <textarea id="aDescription" class="form-control" name="description">${empty post ? '' : post.description}</textarea>
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="pull-right btn btn-primary">Сохранить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="../../modules/pageFooter.jsp" %>


