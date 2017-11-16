<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${cssPath}/css.css">
</head>
<body>
<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<shiro:hasPermission name="role:create">
    <a href="${basePath}/role/create">角色新增</a><br/>
</shiro:hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>拥有的资源</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${roleList}" var="role">
            <tr>
                <td>${role.role}</td>
                <td>${role.description}</td>
                <td>${fnc:resourceNames(role.resourceIds)}</td>
                <td>
                    <shiro:hasPermission name="role:update">
                        <a href="${basePath}/role/${role.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="role:delete">
                        <a href="${basePath}/role/${role.id}/delete">删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>