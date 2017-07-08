<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>抱歉,您访问的页面不存在....</h1>
    <h2>你可以访问 <a href="/acc/list">acc list</a></h2>
    <hr />
    <h3>
    	<c:out value="${requestScope['javax.servlet.error.message']}"/>
    </h3>
    <hr />
    <script>
    	/* console.log(request); */
    </script>
</body>
</html>