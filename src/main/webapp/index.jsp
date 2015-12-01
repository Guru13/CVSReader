<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>lounge</title>
  <style type="text/css">
    div{
      position: absolute;
      top: 300px;
      left: 600px;
      width: 400px;
    }
  </style>
</head>
<body>
<div class="menu">
  <form action="${pageContext.servletContext.contextPath}/user" method='post'>
    <input type='submit' value='Show contacts'>
  </form>
  <form action="views/importContacts.jsp" method='post'>
    <input type='submit' value='Import contacts'>
  </form>
</div>

</body>
</html>