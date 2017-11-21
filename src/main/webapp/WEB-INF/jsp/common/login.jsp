<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>

<html>
<head>
<title>登录</title>
<style>
	.error {
		color: red;
	}
</style>
</head>
<body>
	<div class="error">${error}</div>
	<form action="" method="post">
		用户名:	<input type="text" name="username"/><br/>
		密码:	<input type="password" name="password"/><br/>
		验证码:	<input type="text" id="captcha_text" name="captcha"/><br/>
        <img id="captcha_image" src="${basePath}/getCaptcha" onclick="javascript:this.src='${basePath}/getCaptcha'"/><br/>
		自动登录:	<input type="checkbox" name="rememberMe" value="true"/><br/>
		<input type="submit" value="登录"/>
	</form>
</body>
</html>