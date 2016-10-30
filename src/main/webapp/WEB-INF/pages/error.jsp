<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserCRUD</title>
</head>
<body>
<h3>${statusCode}</h3>
<h4>${description}</h4>
<br/>
<a href="<c:url value="/users/1"/>" target="_blank">For user list click here</a>
<br/>
</body>
</html>