"use strict";

$(document).ready( function() {
	console.log('loading Employee_form.js');
	
	setButtonState('add');
	
	$('#addBtn').click( function() {
		$.post('../addEmp.do', {
			no: $('#no').val(),
			name: $('#name').val(),
			job: $('#job').val(),
			managerNo: $('#managerNo').val(),
			hireDate: $('#hireDate').val(),
			salary: $('#salary').val(),
			commission: $('#commission').val(),
			departmentNo: $('#departmentNo').val()
		}, function(result) {
			if (result.status == 'success') {
				clearForm();
				$('#addBtn').trigger('refreshEmployeeList');
			} else {
				alert(result.message);
			}
		}, 'json');
	});
	
	$('#updateBtn').click(function(){
		$.post('../updateEmp.do', {
			no: $('#no').val(),
			name: $('#name').val(),
			job: $('#job').val(),
			managerNo: $('#managerNo').val(),
			hireDate: $('#hireDate').val(),
			salary: $('#salary').val(),
			commission: $('#commission').val(),
			departmentNo: $('#departmentNo').val()
		}, function(result) {
			if (result.status == 'success') {
				clearForm();
				$('#updateBtn').trigger('refreshEmployeeList');
			} else {
				console.log(result.message);
			}
		}, 'json');
	});
	
	$('#deleteBtn').click(function(){
		$.getJSON('../deleteEmp.do?empno=' + $('#no').val(), function(result) {
			if (result.status == 'success') {
				clearForm();
				$('#deleteBtn').trigger('refreshEmployeeList');
			} else {
				window.alert(result.message);
			}
		});
	});
	
	$('#cancelBtn').click(function() {
		$(this).trigger('employeeList');
	});
});

function clearForm() {
	$('#cancelBtn').trigger('click');
}

function setButtonState(state) {
	if (state == 'add') {
		$('#addBtn').css('display', '');
		$('#updateBtn').css('display', 'none');
		$('#deleteBtn').css('display', 'none');
	} else { // == 'view'
		$('#addBtn').css('display', 'none');
		$('#updateBtn').css('display', '');
		$('#deleteBtn').css('display', '');
	}
}

function loadEmployee(employeeNo) {
	setButtonState('view');
	$.getJSON('../retrieveEmp.do?empno=' + employeeNo, function(result) {
		if (result.status == 'success') {
			var emp = result.data;
			$('#no').val(emp.no);
			$('#name').val(emp.name);
			$('#job').val(emp.job);
			$('#salary').val(emp.salary);
			$('#managerNo').val(emp.managerNo);
			$('#hireDate').val(new Date(emp.hireDate).format('yyyy-MM-dd'));
			$('#commission').val(emp.commission);
			$('#departmentNo').val(emp.departmentNo);
			
		} else {
			window.alert(result.message);
		}
	});
}

window.EmployeeFormView = {
	showAddForm: function() {
		setButtonState('add');
	},
	showDetailForm: function(employeeNo) {
		loadEmployee(employeeNo);
	}
};
