<%@page import="model.researchProject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Research Management</title>
<script src="components/jquery-3.6.0.js"></script>
<script src="components/main.js"></script>
</head>
<body>
<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Research Management</h1>        
				
				<form id="formResearch" name="formResearch" method="post" action="researchProject.jsp">  
					Research Name:  
					<input id="researchName" name="researchName" type="text" class="form-control form-control-sm">  
					
					<br> 
					Research Description:  
					<input id="researchDescription" name="researchDescription" type="text" class="form-control form-control-sm">  
					
					<br>
					 Research Project Price:  
					 <input id="researchPrice" name="researchPrice" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Research Publish Date:  
					 <input id="researchDate" name="researchDate" type="text" class="form-control form-control-sm">  
					 
				
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidresearchIDSave" name="hidresearchIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="#divItemsGrid">   
					<%    
						researchProject rObj = new researchProject();
						out.print(rObj.readResearch());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 
</body>
</html>