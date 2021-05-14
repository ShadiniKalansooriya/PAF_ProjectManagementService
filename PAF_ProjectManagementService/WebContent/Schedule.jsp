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
<script src="Components/Schedule.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Schedule Management V10.1</h1>
<form id="formItem" name="formItem">
 Project ID: 
 <input id="projectSID" name="projectSID" type="text" 
 class="form-control form-control-sm">
 <br> Start Date: 
 <input id="startDate" name="startDate" type="text" 
 class="form-control form-control-sm">
 <br> End Date: 
 <input id="endDate" name="endDate" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidScheduleIDSave" 
 name="hidScheduleIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Project projObject = new Project(); 
 out.print(projObject.readSchedules()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>