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
</div>
<div>
<p id ="pms">Performance Management System</p>
</div>
<br><br>

<select name="designation" onchange="location.href='/PMSSPRING/ViewRatingByTitleServlet/'+this.options[this.selectedIndex].value" >
		<option value="Select Designation">Select Designation</option>
	<c:forEach var="a" items="${designations}">   
		<option  value="${a.designation}">${a.designation}</option>
	</c:forEach>
</select>
<br><br>

<form:form method="post" action="/PMSSPRING/SendMailServlet" modelAttribute="emplist">
<div class="container">
<table class="table table-hover">
<thead>
<tr>
		<th>S No.</th>
		<th>Select</th>
		<th>Job Title</th>
		<th>Emp Name</th>
		<th>Phase1 Rating</th>
		<th>Appraiser Name</th>
		<th>Phase2 Rating</th>
		<th>Reviewer Name</th>
		<th>Phase3 Rating</th>
		<th>Current Phase</th>
		<th>Link</th>
</tr>
<c:set var="srno" value="0"/>
<c:forEach var="a" items="${ratinglist}">
<c:set var="srno" value="${srno + 1}"/>
<tr>
<td>${srno}</td>
<td><input type="checkbox" name="emplist[${srno}].ischecked" value="true"></td>

<td>${a.job_title}</td>
<td>${a.self_empname}</td>
<td>${a.phase1_rating}</td>
<td>${a.appraiser_name}</td>
<td>${a.phase2_rating}</td>
<td>${a.reviewer_name}</td>
<td>${a.phase3_rating}</td>
<td>${a.current_phase}</td>
<td><a href=/PMSSPRING/AppraiseServlet/4/${a.apprempid}?message=>Review</a></td>
<td><input type="hidden" value="${a.curr_phaseid }"><input type="hidden" value="${a.phase1mail}"><input type="hidden" value="${a.phase2mail}"><input type="hidden" value="${a.phase3mail}"></td>

<td><input type="hidden" name="emplist[${srno}].apprempid" value = "${a.apprempid}">
<input type="hidden" name="emplist[${srno}].curr_phaseid" value = "${a.curr_phaseid}">
<input type="hidden" name="emplist[${srno}].self_empname" value = "${a.self_empname}">
<input type="hidden" name="emplist[${srno}].appraiser_name" value = "${a.appraiser_name}">
<input type="hidden" name="emplist[${srno}].reviewer_name" value = "${a.reviewer_name}">
<input type="hidden" name="emplist[${srno}].phase1mail" value = "${a.phase1mail}">
<input type="hidden" name="emplist[${srno}].phase2mail" value = "${a.phase2mail}">
<input type="hidden" name="emplist[${srno}].phase3mail" value = "${a.phase3mail}"></td>
</tr>
</c:forEach>

</table>
<button type="submit" name="action" value="Remind">Remind</button>
<button type="submit" name="action" value="SendBack">Send Back</button>
</div>
</form:form>
