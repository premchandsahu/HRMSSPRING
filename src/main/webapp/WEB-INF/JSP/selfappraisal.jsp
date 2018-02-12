<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<h1>Employees List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>section</th><th>Question</th><th>Remarks</th><th>Rating</th></tr>  
    <c:forEach var="a" items="${list}">   
   <tr>  
   <td>${a.section}</td>  
   <td>${a.question}</td>
   <td>${a.remarks}</td>  
   <td>${a.rating}</td>  
   </tr>  
   </c:forEach> 
   </table>  
   <br/>  
