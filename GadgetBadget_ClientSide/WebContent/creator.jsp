<%@page import="model.Creator_IT19234526"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/creator.js"></script>

<meta charset="ISO-8859-1">
<title>Creator Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Creator Management</h1>

	<form id="formCreator" name="formCreator" method="post" action="creator.jsp">
		Researcher Name:
		<input id="fullname" name="fullname" type="text" class="form-control form-control-sm"><br>
		 Address:
		<input id="city" name="city" type="text" class="form-control form-control-sm"><br> 
		Contact Number:
		<input id="contactnum" name="contactnum" type="text" class="form-control form-control-sm"><br>
		 Email:
		<input id="email" name="email" type="text" class="form-control form-control-sm"><br>
		Field Of Interest:
		<input id="fieldofinterest" name="fieldofinterest" type="text" class="form-control form-control-sm"><br>
		Current Budget:
		<input id="currentbudget" name="currentbudget" type="text" class="form-control form-control-sm">
		
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="creatoridsave" name="creatoridsave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divCreatorGrid">
	<%
	Creator_IT19234526 creatorObj = new Creator_IT19234526(); 
	 out.print(creatorObj.displayCreator()); 
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>