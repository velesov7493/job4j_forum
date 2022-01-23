<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer>
    <c:if test="${pageScript != null}">
        <script type="text/javascript" src="<c:url value="/scripts/${pageScript}"/>"></script>
    </c:if>
    <script type="text/javascript" src="<c:url value="/scripts/bootstrap.bundle.min.js"/>"></script>
</footer>
</body>
</html>
