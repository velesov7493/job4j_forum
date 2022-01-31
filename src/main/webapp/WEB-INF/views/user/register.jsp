<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Регистрация"/>
<%@ include file="../../modules/pageHeader.jsp" %>

<section id="page">
    <div class="container pt-3">
        <div class="row">
            <div class="col-sm-12 p-0 card">
                <div class="card-header">
                    <h3 class="text-center">Регистрация</h3>
                </div>
                <div class="card-body">
                    <c:if test="${not empty error}">
                        <div class="status alert alert-danger"><p>${error}</p></div>
                    </c:if>
                    <form method="post" action="<c:url value="/register"/>">
                        <div class="mb-3">
                            <label for="aName">Ф.И.О:</label>
                            <input id="aName" class="form-control" name="name" type="text" required/>
                        </div>
                        <div class="mb-3">
                            <label for="aLogin">Логин:</label>
                            <input id="aLogin" class="form-control" name="login" type="text" required/>
                        </div>
                        <div class="mb-3">
                            <label for="aEmail">Электронная почта:</label>
                            <input id="aEmail" class="form-control" name="email" type="email" required/>
                        </div>
                        <div class="mb-3">
                            <label for="aPass">Пароль:</label>
                            <input id="aPass" class="form-control" name="password" type="password" required/>
                        </div>
                        <div class="mb-3">
                            <label for="aCheckPass">Подтверждение пароля:</label>
                            <input id="aCheckPass" class="form-control" name="checkPassword" type="password" required/>
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="pull-right btn btn-primary">Зарегистрироваться</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="../../modules/pageFooter.jsp" %>