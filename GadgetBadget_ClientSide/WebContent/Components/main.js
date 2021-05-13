$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

///SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateResearchForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidresearchIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "researchProjectApi",
		type : t,
		data : $("#formResearch").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onResearchSaveComplete(response.responseText, status);
		}
	});
}); 

function onResearchSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidresearchIDSave").val("");
	$("#formResearch")[0].reset();
}


//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidresearchIDSave").val($(this).closest("tr").find('#hidresearchIDUpdate').val());     
	$("#researchName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#researchDescription").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#researchPrice").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#researchDate").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});

//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "researchProjectApi",
		type : "DELETE",
		data : "researchID=" + $(this).data("researchid"),
		dataType : "text",
		complete : function(response, status)
		{
			onResearchDeletedComplete(response.responseText, status);
		}
	});
});

function onResearchDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateResearchForm() {  
	// NAME  
	if ($("#researchName").val().trim() == "")  {   
		return "Insert  Research Name.";  
		
	} 
	
	 // Description 
	if ($("#researchDescription").val().trim() == "")  {   
		return "Insert Research Description.";  
		
	} 
	 
	
	// Research Price  
	if ($("#researchPrice").val().trim() == "")  {   
		return "Insert Research Price.";  
		
	}  
	
	
	// Date  
	if ($("#researchDate").val().trim() == "")  {   
		return "Insert Research Date.";  
		
	} 
	
		 
	 return true; 
	 
}
