<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${cssPath}/css.css">
</head>
<body>

    <form method="post">
        <div class="form-group">
            <label for="newPassword">新密码：</label>
            <input type="text" id="newPassword" name="newPassword"/>
        </div>
        <input type="submit" value="${op}">
    </form>

</body>
</html>