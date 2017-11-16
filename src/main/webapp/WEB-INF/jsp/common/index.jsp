<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>
<html>
<head>
    <title>Shiro综合案例</title>
    <link rel="stylesheet" href="${cssPath}/layout-default-latest.css">
</head>
<body>

<iframe name="content" class="ui-layout-center" src="${basePath}/welcome" frameborder="0" scrolling="auto"></iframe>
<div class="ui-layout-north">欢迎<shiro:principal property="username"/>，<a href="${basePath}/logout">退出</a></div>
<div class="ui-layout-south">
    获取源码? 不给!
</div>
<div class="ui-layout-west">
    功能菜单<br/>
    <c:forEach items="${menus}" var="m">
        <a href="${basePath}${m.url}" target="content">${m.name}</a><br/>
    </c:forEach>
</div>

<script src="${jsPath}/jquery-1.11.0.min.js"></script>
<script src="${jsPath}/jquery.layout-latest.min.js"></script>
<script>
    $(function () {
        $(document).ready(function () {
            $('body').layout({ applyDemoStyles: true });
        });
    });
</script>
</body>
</html>