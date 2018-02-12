<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style type="text/css">
  <%@include file="mystyle1.css" %>
</style>


<div id="div2">
<p id="company">third(i)</p>
<p id="slogan">Information. Intelligence. Insight. </p>

<button id="logout" style="float:right" onclick="location.href='/PMSSPRING/logout'">Logout</button> 
</div><br><br>
<div>
<p id ="pms">Performance Management System</p>
</div>
<br><br>
<select>
		<option value="Select Designation">Select Designation</option>
	<c:forEach var="a" items="${designations}">   
		<option value="${a.designation}">${a.designation}</option>
	</c:forEach>
</select>
<br><br>
<div class="container">
<table class="table table-hover">
<thead>
<tr>
		<th>S No.</th>
		<th>ApprEmpId</th>
		<th>Job Title</th>
		<th>Current Phase</th>
		<th>Emp Name</th>
		<th>Phase1 Rating</th>
		<th>Appraiser Name</th>
		<th>Phase2 Rating</th>
		<th>Reviewer Name</th>
		<th>Phase3 Rating</th>
		<th>Link</th>
</tr>
<c:set var="srno" value="0"/>
<c:forEach var="a" items="${ratinglist}">
<c:set var="srno" value="${srno + 1}"/>
<tr>
<td>${srno}</td>
<td>${a.apprempid}</td>
<td>${a.job_title}</td>
<td>${a.current_phase}</td>
<td>${a.self_empname}</td>
<td>${a.phase1_rating}</td>
<td>${a.appraiser_name}</td>
<td>${a.phase2_rating}</td>
<td>${a.reviewer_name}</td>
<td>${a.phase3_rating}</td>
<td><a href=AppraiseServlet/4/${a.apprempid}?message=>Review</a></td>
</tr>
</c:forEach>

</table>
</div>


