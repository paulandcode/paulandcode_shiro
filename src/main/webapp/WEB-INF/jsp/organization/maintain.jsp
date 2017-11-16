<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${cssPath}/css.css">
</head>
<body>

    <form:form id="form" method="post" commandName="organization">
        <form:hidden path="id"/>
        <form:hidden path="available"/>
        <form:hidden path="parentId"/>
        <form:hidden path="parentIds"/>

        <div class="form-group">
            <form:label path="name">名称：</form:label>
            <form:input path="name"/>
        </div>
        <shiro:hasPermission name="organization:update">
            <form:button id="updateBtn">修改</form:button>
        </shiro:hasPermission>

        <shiro:hasPermission name="organization:delete">
            <c:if test="${not organization.rootNode}">
            	<form:button id="deleteBtn">删除</form:button>
            </c:if>
        </shiro:hasPermission>

        <shiro:hasPermission name="organization:create">
            <form:button id="appendChildBtn">添加子节点</form:button>
        </shiro:hasPermission>

        <shiro:hasPermission name="organization:update">
            <c:if test="${not organization.rootNode}">
            	<form:button id="moveBtn">移动节点</form:button>
            </c:if>
        </shiro:hasPermission>
    </form:form>

    <script src="${jsPath}/jquery-1.11.0.min.js"></script>
    <script>
        $(function() {
            $("#updateBtn").click(function() {
                $("#form")
                        .attr("action", "${basePath}/organization/${organization.id}/update")
                        .submit();
                return false;
            });
            $("#deleteBtn").click(function() {
                if(confirm("确认删除吗？")) {
                    $("#form")
                            .attr("action", "${basePath}/organization/${organization.id}/delete")
                            .submit();
                }
                return false;
            });

            $("#appendChildBtn").click(function() {
                location.href="${basePath}/organization/${organization.id}/appendChild";
                return false;
            });

            $("#moveBtn").click(function() {
                location.href="${basePath}/organization/${organization.id}/move";
                return false;
            });
        });
    </script>
</body>
</html>