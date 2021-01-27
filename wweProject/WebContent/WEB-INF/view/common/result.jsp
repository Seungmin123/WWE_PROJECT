<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<body>
<script>

    let context = "${context}";
    <c:if test="${alertMsg!=null}">
        alert("${alertMsg}");
    </c:if>
    <%--뒤로가기--%>
    <c:if test="${back!=null}">
        history.back();
    </c:if>

    <c:if test="${url!=null}">
        location.href = context + '${url}.do'
    </c:if>
    //이쪽이 찍히는데
</script>
</body>
</html>
