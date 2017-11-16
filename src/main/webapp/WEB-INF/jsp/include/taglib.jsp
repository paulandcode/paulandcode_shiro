<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ taglib prefix="fnc" uri="/WEB-INF/tld/fnc.tld" %>

<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<c:set var="staticPath" value="${basePath}/static"/>
<c:set var="jsPath" value="${staticPath}/js"/>
<c:set var="cssPath" value="${staticPath}/css"/>
<c:set var="imagePath" value="${staticPath}/image"/>