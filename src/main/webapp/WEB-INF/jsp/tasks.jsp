<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://rac.ray_llc.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/rac.common.js" defer></script>
<script type="text/javascript" src="resources/js/rac.tasks.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron pt-4">
    <div class="container">
        <h4 class="text-center"><spring:message code="tasks.title"/></h4>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>
        <br/>
        <table class="table table-hover " id="datatable">
            <thead>
            <tr>
                <th><spring:message code="tasks.address"/></th>
                <th><spring:message code="tasks.number_auto"/></th>
                <th><spring:message code="tasks.phone"/></th>
                <th><spring:message code="tasks.registered"/></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>

</html>
