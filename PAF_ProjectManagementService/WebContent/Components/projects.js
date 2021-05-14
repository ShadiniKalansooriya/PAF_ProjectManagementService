$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidprojectIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "ProjectAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidprojectIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
{ 
$("#hidprojectIDSave").val($(this).data("projectid")); 
 $("#projectCode").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#projectName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#researcherCode").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#researcherName").val($(this).closest("tr").find('td:eq(3)').text());
 $("#projectPrice").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#projectDescription").val($(this).closest("tr").find('td:eq(5)').text()); 

 
});

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "ProjectAPI", 
 type : "DELETE", 
 data : "projectID=" + $(this).data("projectid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
function validateItemForm() 
{ 
// CODE
if ($("#projectCode").val().trim() == "") 
 { 
 return "Insert Project Code."; 
 } 
// NAME
if ($("#projectName").val().trim() == "") 
 { 
 return "Insert Project Name."; 
 } 9
// PRICE-------------------------------
if ($("#projectPrice").val().trim() == "") 
 { 
 return "Insert project Price."; 
 } 
// is numerical value
var tmpPrice = $("#projectPrice").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for Item Price."; 
 } 
// convert to decimal price
 $("#itemPrice").val(parseFloat(tmpPrice).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#projectDescription").val().trim() == "") 
 { 
 return "Insert project Description."; 
 } 
return true; 
}
