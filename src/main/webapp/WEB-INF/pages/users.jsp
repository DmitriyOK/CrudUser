<%@ page import="testask.crud.dao.UserDaoImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
   <head>
     <title>Users</title>
     <style type="text/css">
             .tg {
                 border-collapse: collapse;
                 border-spacing: 0;
                 border-color: #ccc;
             }

             .tg td {
                 font-family: Arial, sans-serif;
                 font-size: 14px;
                 padding: 10px 5px;
                 border-style: solid;
                 border-width: 1px;
                 overflow: hidden;
                 word-break: normal;
                 border-color: #ccc;
                 color: #333;
                 background-color: #fff;
             }

             .tg th {
                 font-family: Arial, sans-serif;
                 font-size: 14px;
                 font-weight: normal;
                 padding: 10px 5px;
                 border-style: solid;
                 border-width: 1px;
                 overflow: hidden;
                 word-break: normal;
                 border-color: #ccc;
                 color: #333;
                 background-color: #f0f0f0;
             }

             .tg .tg-4eph {
                 background-color: #f9f9f9
             }
         </style>
   </head>
   <body>
     <h3> Click here</h3>
        <br/>
        <a href="../index.jsp">Back to index</a>
        <br/>
        <br/>

        <h1>User list</h1>


        <table class="tg">
        <tr>
        <th width="80">Id</th>
        <th width="80">Name</th>
        <th width="80">Age</th>
        <th width="80">isAdmin</th>
        <th width="80">createDate</th>
        </tr>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.admin ? "YES": "no"}</td>
                    <td>${user.createdDate}</td>
                </tr>
            </c:forEach>
        </table>
    <br><br>

     <c:forEach var="i" begin="1" end="${pageCount}">
         <th width="15" height="15"><a href="../users/${i}"><c:out value="${i}"/></a></th>
     </c:forEach>

        <%--<h1>Add a User</h1>--%>
        <%--<c:url var="addAction" value="/users/add"/>--%>
        <%--<form:form action="${addAction}" commandName="user">--%>
            <%--<table>--%>
                <%--<c:if test="${!empty user.name}">--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<form:label path="id">--%>
                                <%--<spring:message text="ID"/>--%>
                            <%--</form:label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<form:input path="id" readonly="true" size="8" disabled="true"/>--%>
                            <%--<form:hidden path="id"/>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</c:if>--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<form:label path="name">--%>
                            <%--<spring:message text="Name"/>--%>
                        <%--</form:label>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<form:input path="name"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                   <%--<td>--%>
                     <%--<form:label path="createdDate">--%>
                       <%--<spring:message text="Created Date"/>--%>
                     <%--</form:label>--%>
                   <%--</td>--%>
                    <%--<td>--%>
                      <%--<form:input path="createdDate"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td colspan="2">--%>
                        <%--<c:if test="${!empty user.name}">--%>
                            <%--<input type="submit"--%>
                                   <%--value="<spring:message text="Edit User"/>"/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${empty user.name}">--%>
                            <%--<input type="submit"--%>
                                   <%--value="<spring:message text="Add User"/>"/>--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        <%--</form:form>--%>
    </body>
</html>