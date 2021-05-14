<%@page import="com.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/projects.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Projects Management V10.1</h1>
<form id="formItem" name="formItem">
 Project code: 
 <input id="projectCode" name="projectCode" type="text" 
 class="form-control form-control-sm">
 <br> Project name: 
 <input id="projectName" name="projectName" type="text" 
 class="form-control form-control-sm">
 <br> Project price: 
 <input id="projectPrice" name="projectPrice" type="text" 
 class="form-control form-control-sm">
 <br> Project description: 
 <input id="projectDescription" name="projectDescription" type="text" 
 class="form-control form-control-sm">
 <br> Researcher Code: 
 <input id="researcherCode" name="researcherCode" type="text" 
 class="form-control form-control-sm">
 <br> Researcher Name: 
 <input id="researcherName" name="researcherName" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidprojectIDSave" 
 name="hidprojectIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Project projObject = new Project(); 
 out.print(projObject.readProjects()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>