//Create Template functions 

function validateCreateTemplate(){
	var form = document.forms["create-template"];
	var validForm = true;
	$(".error").hide();
	
	//Validate template name
	if(isEmpty(form["templateName"].value)){
		$("#errorTemplateName").show();
		validForm = false;
	}
	
	//Validate department
	if(isEmpty(form["department"].value)){
		$("#errorDepartment").show();
		validForm = false;
	}
	
	//Validate section titles
	if(isEmpty(form["section1"].value)){
		$("#errorSection1").show();
		validForm = false;
	}
	if(isEmpty(form["section2"].value)){
		$("#errorSection2").show();
		validForm = false;
	}
	if(isEmpty(form["section3"].value)){
		$("#errorSection3").show();
		validForm = false;
	}
	
	//Validate at least one criteria was entered for each section
	if(isEmpty(form["s1c1"].value)){
		$("#errorS1c1").show();
		validForm = false;
	}
	if(isEmpty(form["s2c1"].value)){
		$("#errorS2c1").show();
		validForm = false;
	}
	if(isEmpty(form["s3c1"].value)){
		$("#errorS3c1").show();
		validForm = false;
	}
	
	//Validate a maximum was entered for each criteria
	for (var i = 1; i <= 3; i++){
		for(var j = 1; j <= 5; j++){
			//Sections 2 and 3 only have 3 criteria
			if(i == 2 || i == 3){
				if(j > 3){
					break;
				}
			}
			
			var criteria = "s" + i + "c" + j;
			//If the criteria field is not empty, check the maximum dropdown next to it
			if(isEmpty(form[criteria].value) == false){
				if(isEmpty(form[criteria + "m"].value)){
					$("#error" + criteria + "m").show()
					validForm = false;
				}
			}
		}
	}
	
	return validForm;
	
}

function isCriteriaEntered(criteriaName){
	var s1c1 = form[criteriaName].value();
	if(isEmpty(s1c1)){
		var error = document.createElement("div");
		error.className = "invalid-feedback";
		var msg = document.createTextNode("Please enter a criteria for this section.");
		error.appendChild(msg);
		insertAfter(document.getElementsByName(criteriaName), error);
	}
}

function isEmpty(data){
	return (data == null || data == "")
}

function validateDepartment(){
	var form = document.forms["attendance-dept"];
	var validForm = true;
	$(".error").hide();
	
	if(isEmpty(form["department"].value)){
		$("#errorDepartment").show();
		validForm = false;
	}
	
	return validForm;
}
	
function validateDate(){
	var form = document.forms["attendance-date"];
	var validForm = true;
	$(".error").hide();
	
	if(isEmpty(form["attendanceDate"].value)){
		$("#errorAttendanceDate").show();
		validForm = false;
	}
	
	return validForm;
}

//View Report
function enableEdit(){
	$(".editable").prop("disabled", false);
	$("#updateReport").show();
	$("#editReport").hide();
}

function disableEdit(){
	document.getElementById("editReportForm").reset();
	$(".editable").prop("disabled", true);
	$("#editReport").show();
	$("#updateReport").hide();
}

function clickView(){
	//Validate all 3 dropdowns are populated
	if(!isEmpty(document.getElementById("reportTemplate").value) &&
			!isEmpty(document.getElementById("report").value)){
		//Submit form
		$("#reportView").show();
		document.getElementById("viewReportForm").submit();
	}
	else{
		//Show error message
		$("#reportView").hide();
		$("#viewReportError").show();
	}
}

function clickCancelView(){
	document.getElementById("reportTemplate").value = "";
	document.getElementById("department").value = "";
	document.getElementById("report").value = "";
	$("#report").prop("disabled", true);
	$("#viewReportError").hide();
	$("#reportView").hide();
}

function updateTotal(){
	var totalReport = 0;
	
	if(!isNaN(parseInt(document.getElementById("s1c1").value)))
		totalReport += parseInt(document.getElementById("s1c1").value);
	if(!isNaN(parseInt(document.getElementById("s1c2").value)))
		totalReport += parseInt(document.getElementById("s1c2").value);
	if(!isNaN(parseInt(document.getElementById("s1c3").value)))
		totalReport += parseInt(document.getElementById("s1c3").value);
	if(!isNaN(parseInt(document.getElementById("s1c4").value)))
		totalReport += parseInt(document.getElementById("s1c4").value);
	if(!isNaN(parseInt(document.getElementById("s1c5").value)))
		totalReport += parseInt(document.getElementById("s1c5").value);
	if(!isNaN(parseInt(document.getElementById("s2c1").value)))
		totalReport += parseInt(document.getElementById("s2c1").value);
	if(!isNaN(parseInt(document.getElementById("s2c2").value)))
		totalReport += parseInt(document.getElementById("s2c2").value);
	if(!isNaN(parseInt(document.getElementById("s2c3").value)))
		totalReport += parseInt(document.getElementById("s2c3").value);
	if(!isNaN(parseInt(document.getElementById("s3c1").value)))
		totalReport += parseInt(document.getElementById("s3c1").value);
	if(!isNaN(parseInt(document.getElementById("s3c2").value)))
		totalReport += parseInt(document.getElementById("s3c2").value);
	if(!isNaN(parseInt(document.getElementById("s3c3").value)))
		totalReport += parseInt(document.getElementById("s3c3").value);

	document.getElementById("totalReport").textContent = String(totalReport);
}

/****************************
 *  enter-report
 *****************************/
function validateEnterReport(){
	var form = document.forms["enter-report"];
	var validForm = true;
	$(".error").hide();
	
	//Validate report title
	if(isEmpty(form["reportTitle"].value)){
		$("#errorReportName").show();
		validForm = false;
	}
	
	//Validate date
	if(isEmpty(form["date"].value)){
		$("#errorDate").show();
		validForm = false;
	}
	
	//Validate report type
	if((isEmpty(form["group"].value)) && (isEmpty(form["employee"].value))){
		$("#errorType").show();
		validForm = false;
	}

	//validate comment1
	if(isEmpty(form["comment1"].value)){
		$("#errorComm1").show();
		validForm = false;
	}
	
	//validate comment2
	if(isEmpty(form["comment2"].value)){
		$("#errorComm2").show();
		validForm = false;
	}
	
	//validate comment3
	if(isEmpty(form["comment3"].value)){
		$("#errorComm3").show();
		validForm = false;
	}
	
	$(".evaluation").each(function(){
	    if(isEmpty(this.value)){
	    	console.log("evaluation error");
	    	$("#evaluationError").show();
	    	validForm = false;
	    }
	});
	
//	//Validate a evaluation was entered for each criteria
//	for (var i = 1; i <= 3; i++){
//		for(var j = 1; j <= 5; j++){
//			//Sections 2 and 3 only have 3 criteria
//			if(i == 2 || i == 3){
//				if(j > 3){
//					break;
//				}
//			}
//			
//			var criteria = "s" + i + "c" + j;
//			
//			if(isEmpty(form[criteria +"e"].value) == false){
//				if(isEmpty(form[criteria + "e"].value)){
//					$("#error" + criteria + "e").show()
//					validForm = false;
//				}
//			}
//		}
//	}

	return validForm;	
}
