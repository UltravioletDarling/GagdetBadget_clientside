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
	var status = validateCreatorform();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#creatoridsave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "CreatorServlet",
		type : t,
		data : $("#formCreator").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onCreatorSaveComplete(response.responseText, status);
		}
	});
}); 

function onCreatorSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divCreatorGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#creatoridsave").val("");
	$("#formCreator")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#creatoridsave").val($(this).closest("tr").find('#hidcreatoridUpdate').val());     
	$("#fullname").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#city").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#contactnum").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#fieldofinterest").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#currentbudget").val($(this).closest("tr").find('td:eq(5)').text()); 

});

//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "CreatorServlet",
		type : "DELETE",
		data : "creatorid=" + $(this).data("creatorid"),
		dataType : "text",
		complete : function(response, status)
		{
			onCreatorDeletedComplete(response.responseText, status);
		}
	});
});

function onCreatorDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divCreatorGrid").html(resultSet.data);
	
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

// CLIENT-MODEL================================================================
function validateCreatorform()
{
	// CODE
	if ($("#name").val().trim() == "")
	{
	return "Insert The Researcher Name.";
	}
	// NAME
	if ($("#address").val().trim() == "")
	{
	return "Insert Address.";
}

// PRICE-------------------------------
if ($("#number").val().trim() == ""){
	return "Insert Contact Number.";
}
		// is numerical value
		var tpnum = $("#number").val().trim();
		if (!$.isNumeric(tpnum))
	{
	return "Only Numerical Values Are Allowed";
	}
		
// convert to decimal price
$("#number").val(parseFloat(tpnum).toFixed(2));

// DESCRIPTION------------------------
if ($("#email").val().trim() == ""){
	
	return "Insert Email";
}

if ($("#fieldofinterest").val().trim() == ""){
	
	return "Insert Field Of Interest";
}
if ($("#budget").val().trim() == ""){
	
	return "Insert Budget";
}
	return true;
}
