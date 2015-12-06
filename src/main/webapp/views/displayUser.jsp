<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>display</title>
</head>
<body>

<c:choose>
    <c:when test="${noOfRecords == 0}">
        <h2>The list of users is empty</h2>
    </c:when>
    <c:otherwise>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Login</th>
                <th>Email</th>
                <th>Phone number</th>
            </tr>

            <c:forEach var="user" items="${currentUsersList}">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.surName}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.phoneNumber}</td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${currentPage != 1}">
            <td><a href="${pageContext.servletContext.contextPath}/user?page=${currentPage - 1}">Previous</a></td>
        </c:if>

        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="${pageContext.servletContext.contextPath}/user?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>


        <c:if test="${currentPage < noOfPages}">
            <td><a href="${pageContext.servletContext.contextPath}/user?page=${currentPage + 1}">Next</a></td>
        </c:if>


        <form action="${pageContext.servletContext.contextPath}/user?page=1" method='post'>
            <select name="recordsPerPage">
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="30">30</option>
                <option value="40">40</option>
                <option value="50">50</option>
            </select>
            <input type='submit' value='submit'>
        </form>
        <h3>Records on page:${recordsPerPage}</h3>

        <div class="sorting">
            <h3>Sort </h3>

            <form action="${pageContext.servletContext.contextPath}/user?page=${currentPage}" method='post'>
                <select name="sortedMethod">
                    <option value="name">by name</option>
                    <option value="surname">by surname</option>
                    <option value="login">by login</option>
                    <option value="email">by email</option>
                    <option value="phoneNumber">by phone number</option>
                </select>
                <input type='submit' value='sort'>
            </form>
            <h3>sorted by: ${sortedMethod}</h3>
        </div>
    </c:otherwise>
</c:choose>
<br/>
<a href="${pageContext.servletContext.contextPath}/index.jsp">back to menu</a>

</body>
</html>
