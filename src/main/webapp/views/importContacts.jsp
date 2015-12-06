<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>import</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/import" method="post" enctype="multipart/form-data">
   Select file to import: <input type="file" name="fileName"  accept=".csv"/>
    <br/>
    <input type="submit" value="import"/>
</form>
<c:if test="${message.equals('empty')}">
    <h3>File is empty. Choose another file or go to menu.</h3>
</c:if>
<br/>
<a href="${pageContext.servletContext.contextPath}/index.jsp">back to menu</a>

</body>
</html>
