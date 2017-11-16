<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${cssPath}/css.css">
</head>
<body>
	<!-- commandName为回显的Attribute对象名称,下面的id,available等默认值为child这个对象的对应属性值,属性值为null则为空字符串 -->
	<!-- 若不写action,则默认为上一个请求,此处上一个请求为:/{parentId}/appendChild,但是请求方式改为POST -->
    <form:form id="form" method="post" commandName="child">
        <form:hidden path="id"/>
        <form:hidden path="available"/>
        <form:hidden path="parentId"/>
        <form:hidden path="parentIds"/>
        
        <div class="form-group">
            <label>父节点名称：</label>
            ${parent.name}
        </div>

        <div class="form-group">
            <form:label path="name">子节点名称：</form:label>
            <form:input path="name"/>
        </div>

        <form:button>新增子节点</form:button>
    </form:form>
</body>
</html>