<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="div2">
<p id="company">third(i)</p>
<p id="slogan">Information. Intelligence. Insight. </p>

<button id="logout" style="float:right" onclick="${username}">Logout</button> 
</div><br><br>
<div>
<p id ="pms">Performance Management System</p>
</div>

<div id="test"></div>

<p>You are successfully logged in! </p>



<br>
<br>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<style>
ul {
    list-style: none;
    padding: 0;
    margin: 0;
}
li {
    float: left;
    border: 1px solid #000;
    border-bottom-width: 0;
    margin: 3px 3px 0px 3px;
    padding: 5px 5px 0px 5px;
    background-color: #CCC;
    color: #696969;
}
#mainView {
    border: 1px solid black;
	clear: both;
	padding: 0 1em;
}
.active {
    background-color: #FFF;
    color: #000;
}
</style>
<form:form method="post" action="saveappraisal" modelAttribute="selfappraisal">
 <%
out.print("Welcome to self appraisal form ");
 %>    
<div ng-app="TabsApp">
    <div id="tabs" ng-controller="TabsCtrl">
        <ul>
            <li ng-repeat="tab in tabs" 
                ng-class="{active:isActiveTab(tab.url)}" 
                ng-click="onClickTab(tab)">{{tab.title}}</li>
        </ul>
        <div id="mainView">
            <div ng-include="currentTab"></div>
        </div>
    </div>

<c:set var="sectionname" value="ABC"/>
<c:set var="sectionname1" value="ABC"/>
<c:set var="sectionname2" value="ABC"/>
<c:set var="firstrun" value="true"/>
<% int i=0; %>
<c:forEach var="a" items="${selfAppraisals.selfappraisal}" varStatus="status">

<c:set var="orderq" value="${a.sectioncolorder}"/>
	<c:set var="sectionname1" value="${a.section}"/>
	<c:if test= "${sectionname1 ne sectionname2}" >
		<% i++; %>
		<c:if test= "${firstrun eq 'false'}">
						</script> 
		</c:if>
		<script type="text/ng-template" id="${orderq}.tpl.html">
	</c:if>
	<c:if test= "${firstrun eq 'true'}">
		<c:set var="firstrun" value="false"/>
	</c:if>
	<div id="view${orderq}">
		<h3>${a.question}</h3>
			<textarea name="selfappraisal[${status.index}].remarks" value="${a.remarks}"></textarea>
			<td><input name="selfappraisal[${status.index}].rating" value="${a.rating}" maxlength="1" size="1"/></td>
 		<c:set var="sectionname2" value="${a.section}"/>
	</div>  




</c:forEach> 	
</script>
</div>
<input type="submit" value="Save" />
</form:form>	
<script>
angular.module('TabsApp', [])
.controller('TabsCtrl', ['$scope', function ($scope) {
	

	<% int start=1; String first="$scope.tabs = [{";  String next="}, {"; %>
	<c:forEach var="a" items="${listsection}">
	<% if (start==1){ start=0; out.print(first);} else out.print(next); %>
            title: '${a.section}',
            url: '${a.sectioncolorder}.tpl.html' 
    </c:forEach>
	}];

    $scope.currentTab = '1.tpl.html';

    $scope.onClickTab = function (tab) {
        $scope.currentTab = tab.url;
    }
    
    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    }
}]);
</script>                
<script type="text/javascript">
function sessionOut(user)
{
	
	//var username=user;
	document.getElementById("test").innerHTML=user+"  manual";
	window.location = "index.jsp";
	
	
}
</script>               
                
                
                
                
                
                
                
                
                
                