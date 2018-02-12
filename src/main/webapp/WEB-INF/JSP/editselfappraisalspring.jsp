<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
  <%@include file="mystyle1.css" %>
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">

<div id="div2">

<p id="company">third(i)</p>
<p id="slogan">Information. Intelligence. Insight. </p>

<button id="logout" style="float:right" onclick="${username}">Logout</button> 
</div><br>
<div>
<p id ="pms">Performance Management System</p>
</div>

<div id="test"></div>

<p>${message}</p>

<br>

<style>
body {font-family: Arial;}

/* Style the tab */
.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent ,#lastForm{
    display: none;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}
.selfdiv,#subbtn {
	display:none;
}
</style>
</head>


<body>


<div class="tab">
<c:set var="firstrun" value="true"/>
<c:forEach var="a" items="${listsection}">
<c:set var="firstval" value=""/>
	<c:if test= "${firstrun eq 'true'}">
			<c:set var="firstval" value="defaultOpen"/>
			<c:set var="firstrun" value="false"/>
	</c:if>
  <button class="tablinks" onclick="openSection(event, 's${a.sectioncolorder}')" id="${firstval}">${a.section}</button>
</c:forEach>
<button class="tablinks" onclick="openSection(event, 's${a.sectioncolorder}')" id='s'>Submit</button>
</div>

<form:form method="post" action="/PMSNEW/saveappraisal" modelAttribute="selfappraisal">

<c:set var="sectionname" value="ABC"/>
<c:set var="sectionname1" value="ABC"/>
<c:set var="sectionname2" value="ABC"/>
<c:set var="firstrun" value="true"/>
	
<c:forEach var="a" items="${selfAppraisalAlls.selfappraisalall}" varStatus="status">
	<c:set var="sectionname1" value="${a.section}"/>
	<c:if test= "${sectionname1 ne sectionname2}" >
		<c:if test= "${firstrun eq 'false'}">
			</div>
		</c:if>
		<div id="s${a.sectioncolorder}" class="tabcontent">		
	</c:if>
	<c:if test= "${firstrun eq 'true'}">
		<c:set var="firstrun" value="false"/>
	</c:if>	
	<h3>${a.question}</h3>
	<c:set var="vrating" value="0"/>
	<c:set var="vremarks" value=""/>
	<c:set var="ratingid" value="0"/>
	<c:choose>
	<c:when test="${apprphaseid eq 1}">
	<c:set var="vrating" value="${a.rating1}"/>
	<c:set var="vremarks" value="${a.remarks1}"/>
	<c:set var="vratingid" value="${a.ratingid1}"/>
	</c:when>
	<c:when test="${apprphaseid eq 2}">
	<c:set var="vrating" value="${a.rating2}"/>
	<c:set var="vremarks" value="${a.remarks2}"/>
	<c:set var="vratingid" value="${a.ratingid2}"/>
	</c:when>
	<c:otherwise>
	<c:set var="vrating" value="${a.rating3}"/>
	<c:set var="vremarks" value="${a.remarks3}"/>
	<c:set var="vratingid" value="${a.ratingid3}"/>
	</c:otherwise>
	</c:choose>
	
	<c:set var="vreadonly" value=""/>
	<c:set var="disabled" value=""/>
	<c:if test="${a.curr_phase_id gt apprphaseid}">
		<c:set var="vreadonly" value="readonly"/>
		<c:set var="disabled" value="disabled"/>
	</c:if>
	
	
	<c:if test="${a.ratingyn eq 'N'}">
	<textarea name="selfappraisal[${status.index}].remarks" cols="100" rows="5" ${vreadonly}>${vremarks}</textarea>
	<input type="hidden" name="selfappraisal[${status.index}].rating" value="${vrating}" maxlength="1" size="1"/>
	
	</c:if>
	
	<c:if test="${a.ratingyn eq 'Y'}">
	<textarea name="selfappraisal[${status.index}].remarks" cols="90" rows="5" ${vreadonly}>${vremarks}</textarea>
	<input name="selfappraisal[${status.index}].rating" value="${vrating}" maxlength="1" ${vreadonly} size="1"/>
	<button type="button" class="accordion${a.sectioncolorder}" onclick="loadSelfForm('PI${a.questioncolorder}')">PI</button>
	<div id="PI${a.questioncolorder}" class="selfdiv">
	<table>
	<tr>Performance Indicator</tr>
	<tr><td>Prev. Role</td><td>Curr Role</td><td>Next Role</td></tr>
  	<tr><td>${a.performanceind1}</td><td>${a.performanceind2}</td><td>${a.performanceind3}</td></tr>
  	</table>
  	</div>
	</c:if>

	<c:if test="${apprphaseid ne 1}">
	<button type="button" class="accordion${a.sectioncolorder}" onclick="loadSelfForm('DET${a.questioncolorder}')">More</button>
	</c:if>
	<td><input type="hidden" name="selfappraisal[${status.index}].apprempratingid" value="${vratingid}" /></td>
	<td><input type="hidden" name="selfappraisal[${status.index}].apprempid" value="${apprempid}" /></td>
	<td><input type="hidden" name="selfappraisal[${status.index}].apprphaseid" value="${apprphaseid}" /></td>
	<div id="DET${a.questioncolorder}" class="selfdiv">
	<table>
	<tr><td>Phase</td><td>Remarks</td><td>Rating</td></tr>
	<c:if test="${apprphaseid ge 3}">
	<tr><td>Phase</td><td>Remarks</td><td>Rating</td></tr>
	<tr><td>Appraiser</td><td>${a.remarks2}</td><td>${a.rating2}</td></tr>
	<tr><td>Appraisee</td><td>${a.remarks1}</td><td>${a.rating1}</td></tr>
	</c:if>
	<c:if test="${apprphaseid eq 2}">
	
	<tr><td>Appraisee</td><td>${a.remarks1}</td><td>${a.rating1}</td></tr>
	</c:if>
	</table>
	</div>
	<c:set var="sectionname2" value="${a.section}"/>
</c:forEach>

</div>
<div id="lastForm">
	<c:set var="rating1" value="0"/>
	<c:set var="rating2" value="0"/>
	<c:set var="rating3" value="0"/>
	<c:forEach var="r" items="${listscore}">
		<c:if test= "${apprphaseid eq 1}">
			<c:set var="rating1" value="${r.rating1}"/>
		</c:if>
		<c:if test= "${apprphaseid eq 2}">
			<c:set var="rating1" value="${r.rating1}"/>
			<c:set var="rating2" value="${r.rating2}"/>
		</c:if>
		<c:if test= "${apprphaseid eq 3}">
			<c:set var="rating1" value="${r.rating1}"/>
			<c:set var="rating2" value="${r.rating2}"/>
			<c:set var="rating3" value="${r.rating3}"/>
		</c:if>
	
	
		<c:if test= "${r.isFinalize eq 'Y'}">
		<c:set var="rating1" value="${r.rating1}"/>
		<c:set var="rating2" value="${r.rating2}"/>
		<c:set var="rating3" value="${r.rating3}"/>
	</c:if>	
<table>
<tr><td>Self Score</td><td>Appraiser Score</td><td>Reviewer Score</td></tr>
<tr><td><input value="${rating1}" readonly></td><td><input value="${rating2}" readonly></td><td><input value="${rating3}" readonly></td></tr></table>
</c:forEach>
</div>
<script>

function loadSelfForm(row_id)
{
var ans=document.getElementById(row_id).style.display==='block';
   	if(ans)
    document.getElementById(row_id).style.display='none';	
   	else
    document.getElementById(row_id).style.display='block';
}

</script>

<script>

function openSection(evt, sectionName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(sectionName).style.display = "block";
    evt.currentTarget.className += " active";
    if(sectionName=='s')
    {
    	document.getElementById("lastForm").style.display='table-row';
    	document.getElementById("subbtn").style.display='block';
    }
    else
    {
    	document.getElementById("lastForm").style.display='none';
    	document.getElementById("subbtn").style.display='none';
    }
}
document.getElementById("defaultOpen").click();
</script>
     


<input type="submit" name="action" value="Save" ${disabled}/> 
<input type="submit" name="action" id="subbtn" value="Submit" ${disabled}/>
</form:form>
</body>




<script type="text/javascript">
function sessionOut(user)
{
	
	//var username=user;
	document.getElementById("test").innerHTML=user+"  manual";
	window.location = "index.jsp";
	
	
}
</script>               
                
                
                
                
                
                
                
                
                
                