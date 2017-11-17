<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
		密码:	<input type="text" name="password"/><br/>
		验证码:	<input type="text" name="captcha"/><br/>
		
        <input type="text" class="form-control" v-model="" @keyup.enter="login" placeholder="">
        <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
        <img alt="如果看不清楚，请单击图片刷新！" class="pointer" :src="src" @click="refreshCode">
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" @click="refreshCode">点击刷新</a>
        
		自动登录:	<input type="checkbox" name="rememberMe" value="true"/><br/>
		<input type="submit" value="登录"/>
	</form>
</body>
</html>