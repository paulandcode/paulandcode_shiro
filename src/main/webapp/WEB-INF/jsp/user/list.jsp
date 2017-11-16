<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${cssPath}/css.css">
</head>
<body>
<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<shiro:hasPermission name="user:create">
    <a href="${basePath}/user/create">用户新增</a><br/>
</shiro:hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>用户名</th>
            <th>所属组织</th>
            <th>角色列表</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${fnc:organizationName(user.organizationId)}</td>
                <td>${fnc:roleNames(user.roleIds)}</td>
                <td>
                    <shiro:hasPermission name="user:update">
                        <a href="${basePath}/user/${user.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="user:delete">
                        <a href="${basePath}/user/${user.id}/delete">删除</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="user:update">
                        <a href="${basePath}/user/${user.id}/changePassword">改密</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>