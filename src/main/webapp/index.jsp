<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
   <head>
     <title>UserCRUD</title>
   </head>
   <body>
   <br>
   <h3>Click "User list" or select results on pages. Default 5</h3>

   <a href="<c:url value="/users/1"/>" target="_blank">User list</a>
   <br>
   <br>
   <form method="get" name="drop_down_box">
         <select name="results" size="1">
             <option selected="selected" value="5">Default</option>
             <option value="10">10</option>
             <option value="15">15</option>
             <option value="25">25</option>
         </select>
         <button type="submit" formaction="users/pagingby">Set it.</button>
     </form>
   </body>
</html>