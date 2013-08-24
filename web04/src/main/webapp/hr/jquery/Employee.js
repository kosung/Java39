"use strict";

$(document).ready( function() {
	console.log('loading Employee.js');
	
	$('#detailDiv').css('display', 'none');
	$('#listDiv').load('Employee_list.html');
	
	$('#main').on('employeeList', function(){
		$('#detailDiv').css('display', 'none');
		$('#listDiv').css('display', '');
	});
	
	$('#main').on('employeeNewForm', function(){
		window.employeeNo = 0;
		$('#listDiv').css('display', 'none');
		if ( $('#detailDiv').html() == '') {
			$('#detailDiv').load('Employee_form.html');
		} else {
			EmployeeFormView.showAddForm();
		}
		$('#detailDiv').css('display', '');
	});
	
	$('#main').on('refreshEmployeeList', function() {
		//$('#listDiv').load('Employee_list.html');
		EmployeeListView.listEmployee();
		$('#detailDiv').css('display', 'none');
		$('#listDiv').css('display', '');
	});
	
	$('#main').on('employeeDetail', function(event, employeeNo){
		if ( $('#detailDiv').html() == '') {
			$('#detailDiv').load('Employee_form.html', function() {
				EmployeeFormView.showDetailForm(employeeNo);
			});
		} else {
			EmployeeFormView.showDetailForm(employeeNo);
		}
		//$('#detailDiv').load('Employee_form.html');
		$('#listDiv').css('display', 'none');
		$('#detailDiv').css('display', '');
	});
});









