# SSM整合shiro
	01.密码加密:md5算法,散列迭代2次,16进制字符串加密(用户名+盐)
	02.生成验证码(利用google的kaptcha工具)
	03.EhCache缓存实现登录次数过多提示(5次)
	04.提供rememberMe(自动登录)功能
	05.点击退出登录后,缓存失效
	06.功能模块:
		01>.机构管理:树结构,可以移动某个节点及其下所有子节点到另一个节点下
		02>.用户管理:用户属于某个机构,用户拥有某些角色
		03>.资源管理:树结构,资源有两种类型(菜单和按钮)
			a.菜单下可以有子菜单或者按钮,可以拥有url进行页面跳转,可以有权限字符串
			b.按钮不可以有子节点,可以有权限字符串
		04>.角色管理:角色拥有某些资源(即相应的权限)